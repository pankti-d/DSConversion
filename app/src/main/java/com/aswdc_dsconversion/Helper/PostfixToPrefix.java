package com.aswdc_dsconversion.Helper;

import android.util.Log;

import java.util.Stack;

public class PostfixToPrefix {
    public static final String TAG = "PostfixToPrefix";
    static boolean Prec(char c) {

        if(c == '+' || c == '-' || c == '*' || c =='/' || c == '^')
            return true;
        return false;
    }

    public static String postfixToPrefix(String exp) {
        // initializing empty String for result
        try {
            String result = new String("");
            String op1 = new String("");
            String op2 = new String("");
            String ans = new String("");

            // initializing empty stack
            Stack<String> stack = new Stack<>();

            for (int i = 0; i <= exp.length() - 1; i++) {
                char c = exp.charAt(i);

                if (Prec(exp.charAt(i))) {
                    op1 = stack.pop();
                    op2 = stack.pop();

                    result = String.valueOf(c + op2 + op1);

                    stack.push(result);

                } else {

                    stack.push(Character.toString(c));
                    Log.d(TAG, "postfixToPrefix:" + stack.toArray());
                }

            }
            //return stack.peek();//
            while (!stack.isEmpty())
                ans += stack.pop();

            return ans;
        }
        catch(Exception e){return "Please Enter a Valid Postfix";}

    }
}
