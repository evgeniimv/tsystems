package com.tsystems.javaschool.tasks.calculator;

public class CheckCalculateInput {

    public static boolean checkCalculateInput(String input) {
        return checkIsInputNull(input);
    }

    public static boolean checkIsInputNull(String input) {
        if (input == null || input == "") {
            return false;
        } else {
            return checkParentheses(input);
        }
    }

    public static boolean checkParentheses(String input) {
        int stackSize = input.length();
        Stack OPENStack = new Stack(stackSize);
        Stack CLOSEStack = new Stack(stackSize);

        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            switch (ch) {
                case '(':
                    OPENStack.push(ch);
                    break;
                case ')':
                    if (!OPENStack.isEmpty()) {
                        OPENStack.pop();
                    } else {
                        CLOSEStack.push(ch);
                    }
                    break;
                default:
                    break;
            }
        }
        if (!OPENStack.isEmpty() || !CLOSEStack.isEmpty()) {
            return false;
        } else {
            return checkOperandsOrder(input);
        }
    }

    public static boolean checkOperandsOrder(String input) {
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1) && !Character.isDigit(input.charAt(i)) &&
                    (input.charAt(i) != '(' || input.charAt(i) != ')')) {
                return false;
            }
        }
        return true;
    }
}

class Stack {
    private int maxSize;
    private char[] stackArray;
    private int top;

    public Stack(int max) {
        maxSize = max;
        stackArray = new char[maxSize];
        top = -1;
    }

    public void push(char j) {
        stackArray[++top] = j;
    }

    public char pop() {
        return stackArray[top--];
    }

    public char peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}
