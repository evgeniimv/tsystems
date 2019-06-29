package com.tsystems.javaschool.tasks.calculator;

import static com.tsystems.javaschool.tasks.calculator.CheckCalculateInput.checkCalculateInput;

public class Calculator {
    Lexer lexer;

    private double primary() {
        double result = 0;
        LexemType curType = lexer.currentLexem.getType();

        switch (curType) {
            case OPEN:
                lexer.nextLexem();
                result = expression();
                lexer.nextLexem();
                break;
            case NUMBER:
                result = lexer.currentLexem.getValue();
                lexer.nextLexem();
                break;
            default:
                result = expression();
        }
        return result;
    }

    private double unary() {
        double result = 0;
        LexemType curType = lexer.currentLexem.getType();

        switch (curType) {
            case MINUS:
                lexer.nextLexem();
                result = -primary();
                break;
            case PLUS:
                lexer.nextLexem();
                result = primary();
                break;
            default:
                result = primary();
        }
        return result;
    }

    private double term() {
        double result = unary();
        LexemType curType = lexer.currentLexem.getType();

        while (curType == LexemType.MULT || curType == LexemType.DIV) {
            lexer.nextLexem();
            if (curType == LexemType.MULT)
                result *= unary();
            else
                result /= unary();
            curType = lexer.currentLexem.getType();
        }
        return result;
    }

    private double expression() {
        double result = term();
        LexemType curType = lexer.currentLexem.getType();

        while (curType == LexemType.PLUS || curType == LexemType.MINUS) {
            lexer.nextLexem();
            if (curType == LexemType.PLUS) {
                result += term();
            } else {
                result -= term();
            }
            curType = lexer.currentLexem.getType();
        }
        return result;
    }

    /**
     * Evaluate expression represented as string.
     *
     * @param expression mathematical statement containing digits, '.' (dot) as decimal mark,
     *                   parentheses, operations signs '+', '-', '*', '/'<br>
     *                   Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String expression) {
        if (!checkCalculateInput(expression)) {
            return null;
        }

        lexer = new Lexer(expression.toLowerCase());
        lexer.nextLexem();
        double result = expression();
        if (lexer.getIt() == expression.length() && result != Double.POSITIVE_INFINITY) {
            return simpleRounding(result);
        } else {
            return null;
        }
    }

    private static String simpleRounding(double result) {
        if (result / Math.round(result) == 1) {
            return "" + Math.round(result);
        } else {
            return "" + result;
        }
    }
}
