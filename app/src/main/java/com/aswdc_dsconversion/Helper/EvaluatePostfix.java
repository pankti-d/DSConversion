package com.aswdc_dsconversion.Helper;
import java.util.Stack;


public class EvaluatePostfix {
    // Method to evaluate value of a postfix expression
    public int evaluatePostfix(String exp) {
        //create a stack
        try {
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < exp.length(); i++) {
                char c = exp.charAt(i);

                if (c == ' ')
                    continue;

                    // If the scanned character is an operand
                    // Push it to the stack.
                else if (Character.isDigit(c)) {
                    int n = 0;

                    //extract the characters and store it in num
                    while (Character.isDigit(c)) {
                        n = n * 10 + (int) (c - '0');
                        i = i + 1;
                        c = exp.charAt(i);
                    }
                    i--;

                    //push the number in stack
                    stack.push(n);
                } else {
                    int val1 = stack.pop();
                    int val2 = stack.pop();

                    switch (c) {
                        case '+':
                            stack.push(val2 + val1);
                            break;

                        case '-':
                            stack.push(val2 - val1);
                            break;

                        case '/':
                            stack.push(val2 / val1);
                            break;

                        case '*':
                            stack.push(val2 * val1);
                            break;
                    }
                }
            }
            return stack.pop();
        }
        catch(Exception e){return -1;}

    }

}

// Driver program to test above functions
    //public static void main(String[] args)
    //{
    //String exp="231*+9-";
    //System.out.println("postfix evaluation: "+evaluatePostfix(exp));
    //}

