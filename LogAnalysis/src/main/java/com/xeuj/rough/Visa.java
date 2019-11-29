package com.xeuj.rough;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Visa {
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1235,4321);
        List<Integer> l2 = Arrays.asList(2345,3214);
        System.out.println(minimumMoves(l1,l2));

        Stack st=new Stack();
        st.push(1);
        st.push(1.1);
        st.push('z');
        st.push("Hello");
        for (Object str:st) {
            System.out.println(str);
            System.out.println(str.getClass());
        }
    }

    public static int minimumMoves(List<Integer> a, List<Integer> m) {
        int count = 0;
        if(a.size() != m.size()) {
            return Integer.MAX_VALUE;
        }
        for(int i=0;i<a.size();i++) {
            String num1=a.get(i).toString();
            String num2=m.get(i).toString();
            count+=findDiff(num1,num2);
        }
        return count;
    }

    public static int findDiff(String num1, String num2) {
        int count=0;
        for(int i=0, j=0;i<num1.length() && j<num2.length();i++,j++) {
            int f1 = Integer.parseInt(String.valueOf(num1.charAt(i)));
            int f2 = Integer.parseInt(String.valueOf(num2.charAt(j)));
            count+=Math.abs(f1-f2);
        }
        return count;
    }
}
