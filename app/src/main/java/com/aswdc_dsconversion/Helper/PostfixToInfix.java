package com.aswdc_dsconversion.Helper;

import android.widget.Toast;

import java.util.Stack;

public class PostfixToInfix {
        private boolean isOperator ( char c)
        {
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^')
                return true;
            return false;
        }
        public String convert (String exp) {
            try {
                Stack<String> s = new Stack<>();
                String ans = new String("");

                for (int i = 0; i < exp.length(); i++) {
                    char c = exp.charAt(i);
                    if (isOperator(c)) {
                        String b = s.pop();
                        String a = s.pop();
                        s.push("(" + a + c + b + ")");
                    } else
                        s.push("" + c);
                }

                //return s.pop();//
                while (!s.isEmpty())
                    ans += s.pop();

                return ans;
            }
            catch (Exception e){return "Please enter a Valid Postfix";}

        }
}
