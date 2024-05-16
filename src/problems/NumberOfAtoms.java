package problems;

import java.util.*;

public class NumberOfAtoms {

    public static void main(String[] args){
        String formula = "Be32";
        System.out.println(new NumberOfAtoms().countOfAtoms(formula));
    }
    //Given a string formula representing a chemical formula, return the count of each atom.
    public String countOfAtoms(String formula) {
        Map<String, Integer> result = Count(formula);
        StringBuilder answer = new StringBuilder();
        for(Map.Entry<String, Integer> entry: result.entrySet()){
            answer.append(entry.getKey());
            if(entry.getValue() != 1)answer.append(entry.getValue().toString());

        }
        return answer.toString();
    }

    public Map<String, Integer> Count(String formula) {
        System.out.println(formula);
        Map<String, Integer> result = new TreeMap<>();
        if(formula.length() == 1){
            result.put(formula, 1);
            return result;
        }else if(formula.length() == 2 && Character.isLowerCase(formula.charAt(1))){
            result.put(formula, 1);
            return result;
        }

        for (int i = formula.length() - 1, j; i >= 0; i = j - 1) {
            j = i;
            //System.out.println(i);
            while (Character.isDigit(formula.charAt(j))) {
                j--;
            }
            // j lands on the first non-digit character

            int multiplier = convertToNumber(formula.substring(j + 1, i + 1));
            if (j == i) multiplier = 1;// 1 won't be provided
            String substring = "";
            System.out.println(multiplier);
            if (j >= 0 && formula.charAt(j) == ')') {
                System.out.println("parentheses");
                int k = j;
                int stack = 1;
                while(stack > 0){
                    k--;
                    if(formula.charAt(k) == '(') stack--;
                    if(formula.charAt(k) == ')') stack++;
                }
                // k lands on the '('
                substring = formula.substring(k+1, j);
                j = k;
            }

            if (j >= 0 && Character.isLowerCase(formula.charAt(j))) {
                j--;
                substring = formula.substring(j, j + 2);
            }
            else if (j >= 0 && Character.isUpperCase(formula.charAt(j))) {
                substring = formula.substring(j, j + 1);
            }
//            System.out.println(i);
//            System.out.println(j);
//            System.out.println(substring);
//            System.out.println(formula.substring(j, j + 2));

            Map<String, Integer> object = Count(substring);
            for (Map.Entry<String, Integer> atom : object.entrySet()) {
                object.put(atom.getKey(), atom.getValue() * multiplier);
                result.merge(atom.getKey(), atom.getValue(), Integer::sum);
            }
        }
        return result;
    }

    public int convertToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res *= 10;
            res += s.charAt(i) - '0';
        }
        return res;
    }
}
