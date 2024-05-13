package problems;
import datastructures.MaxHeap;
import org.w3c.dom.ls.LSOutput;

import java.beans.PropertyEditorManager;
import java.sql.Time;
import java.util.Comparator;

public class TaskScheduler {
//    You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n.
//    Each cycle or interval allows the completion of one task.
//    Tasks can be completed in any order, but there's a constraint:
//    identical tasks must be separated by at least n intervals due to cooling time.
//
//     Return the minimum number of intervals required to complete all tasks.
    public static void main(String[] args){
        char[] tasks = {'A', 'B', 'C', 'D', 'E', 'A', 'B', 'C', 'D', 'E'};
        int n = 4;
        int answer = new TaskScheduler().leastInterval(tasks, n);
        System.out.println(answer);
        MaxHeap<TimedTask> test = new MaxHeap<>(new TimedTask.CompareByLastExecuted());
//        test.insert(new TimedTask(1, 4, 1));
//        test.insert(new TimedTask(2 ,3, 1));
//        test.insert(new TimedTask(4, 2, 1));
//        test.insert(new TimedTask(3, 1, 1));
//        test.insert(new TimedTask(5, 0, 1));
//        test.insert(new TimedTask(1, 1, 1));
//        test.insert(new TimedTask(2 ,2, 1));
//        test.insert(new TimedTask(3, 3, 1));
//        test.insert(new TimedTask(5, 5, 1));
//        //test.insert(new TimedTask(4, 4, 1));
//        while(!test.isEmpty()){
//            System.out.println(test.peek().kind);
//            System.out.println(test.peek().lastExecuted);
//            TimedTask printer = new TimedTask();
//            System.out.println("Heap Structure: ");
//            printer.printHeapStructure(test);
//            test.pop();
//            System.out.println("----------------");
//        }

    }



    private static class TimedTask{
        int lastExecuted;
        int kind;
        int remainingCount;
        TimedTask(){
            this(0, 0, 0);
        }

        TimedTask(int lastExecuted, int kind, int remainingCount){
            this.lastExecuted = lastExecuted;
            this.kind = kind;
            this.remainingCount = remainingCount;
        }


        public static class CompareByLastExecuted implements Comparator<TimedTask> {
            @Override
            public int compare(TimedTask t1, TimedTask t2) {
                // Assuming you want to sort in ascending order of lastExecuted.
                if(Integer.compare(t1.lastExecuted, t2.lastExecuted) != 0){
                    return Integer.compare(t1.lastExecuted, t2.lastExecuted);
                }
                return Integer.compare(t1.kind, t2.kind);

            }
        }

        public static class CompareByRemainingCount implements Comparator<TimedTask> {
            @Override
            public int compare(TimedTask t1, TimedTask t2) {
                // Assuming you want to sort in ascending order of remainingCount.
                if(Integer.compare(t1.remainingCount, t2.remainingCount) != 0){
                    return Integer.compare(t1.remainingCount, t2.remainingCount);
                }
                return Integer.compare(t1.kind, t2.kind);
            }

        }
        public void printHeapStructure(MaxHeap<TimedTask> H){
            int current = 0;
            for(int i = 0; H.elements.size() >> i > 0; i++){
                for(int j = 0; j < (1 << i); j++){
                    if (current < H.elements.size()) {
                        System.out.print(H.elements.get(current).kind);
                        System.out.print(" ");
                        current++;
                    }
                }
                System.out.println();
            }
        }
    }
    public int leastInterval(char[] tasks, int n){
        int[] cnt = new int[26];
        for(char task: tasks){
            cnt[task - 'A'] ++;
        }
        System.out.println(Integer.compare(1, 2));

        /* idea: out of all the available tasks, choose the one with the largest amount left
        /* on each period, each step has a "remaining cooling time" */
        // time last used and current time > n
        // put pair<lastused, kind> in a MaxHeap. Each period, pop out elements with current - lastused > n
        // another MaxHeap storing the remaining number of times the tasks needs to be executed.
        // (Only store the ones that is executable, pop it and put it into the idle MaxHeap)
        MaxHeap<TimedTask> idleTasks = new MaxHeap<>(new TimedTask.CompareByLastExecuted().reversed());
        MaxHeap<TimedTask> openTasks = new MaxHeap<>(new TimedTask.CompareByRemainingCount());
        for(int i = 0; i < 26; i++){
            if(cnt[i] > 0){
                openTasks.insert(new TimedTask(-1000, i, cnt[i]));
            }
        }
        int current = 0;
        do {
            current++;
            if (!idleTasks.isEmpty() && current - idleTasks.peek().lastExecuted > n) {
                TimedTask temp = idleTasks.pop();
                openTasks.insert(temp);
            }
            System.out.println(String.format("Time = %d", current));
            System.out.println(String.format("Opentasks is size %d", openTasks.size()));
            System.out.println(String.format("Idletasks is size %d", idleTasks.size()));
            if(!idleTasks.isEmpty()){
                System.out.println(idleTasks.peek().kind);
                System.out.println(idleTasks.peek().lastExecuted);
                System.out.println(idleTasks.peek().remainingCount);
            }

            if (!openTasks.isEmpty()) {
                TimedTask taskToExecute = openTasks.pop();
                taskToExecute.remainingCount -= 1;
                taskToExecute.lastExecuted = current;
                if (taskToExecute.remainingCount > 0) {
                    idleTasks.insert(taskToExecute);
                }
            }
        } while (!idleTasks.isEmpty() || !openTasks.isEmpty());
        return current;
    }
}
/*
Time = 7
Opentasks is size 0
Idletasks is size 4
3
3
1
Time = 8
Opentasks is size 1
Idletasks is size 3
4
2
1
issue with heap, 4, 2, 1 should be seen before 3 3 1
 */