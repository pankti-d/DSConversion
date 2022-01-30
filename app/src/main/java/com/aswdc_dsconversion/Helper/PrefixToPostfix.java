package com.aswdc_dsconversion.Helper;

import java.util.Stack;

public class PrefixToPostfix {
    public boolean Prec(char c)
    {
        if(c == '+' || c == '-' || c == '*' || c =='/' || c == '^')
            return true;
        return false;

    }
    public String prefixToPostfix(String exp) {
        // initializing empty String for result
        try {
            String result = new String("");
            String op1 = new String("");
            String op2 = new String("");
            String ans = new String("");

            // initializing empty stack
            Stack<String> stack = new Stack<>();

            for (int i = exp.length() - 1; i >= 0; i--) {
                char c = exp.charAt(i);

                if (Prec(exp.charAt(i))) {
                    op1 = stack.pop();
                    op2 = stack.pop();

                    result = op1 + op2 + c;

                    stack.push(result);
                } else {
                    stack.push(Character.toString(c));
                }
            }
            //return stack.pop();//
            while (!stack.isEmpty())
                ans += stack.pop();

            return ans;
        }
        catch(Exception e){return "Please Enter a Valid Prefix";}

    }
}
