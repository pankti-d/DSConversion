package com.aswdc_dsconversion.Helper;
import java.util.Stack;
public class PrefixToInfix {
    private boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^')
            return true;
        return false;
    }

    public String prefixToInfix(String exp) {

        // initializing empty stack
        try {
            Stack<String> s = new Stack<>();

            for (int i = exp.length() - 1; i >= 0; i--) {
                char c = exp.charAt(i);

                if (isOperator(c)) {
                    String b = s.pop();
                    String a = s.pop();
                    s.push("(" + a + c + b + ")");
                } else
                    s.push("" + c);
            }

            return s.pop();

        } catch (Exception e) {return "Enter a Valid Prefix"; }
    }
}


