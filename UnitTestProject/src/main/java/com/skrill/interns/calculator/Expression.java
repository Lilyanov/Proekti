package com.skrill.interns.calculator;

import java.math.BigDecimal;

public class Expression {

    private String expression; // contains the String expression that I want to calculate
    private int position; // contains the current position in the expression
    private char operation; // contains the current operation
    private boolean flagMinus; // true until we have subtraction, false when I have add

    public static Expression parse(String expression) throws IllegalArgumentException {
        if (expression == null) {
            throw new IllegalArgumentException("Invalid input!");
        }
        else if (expression.matches("((0|[1-9]\\d{0,9})(\\.\\d{0,20})?[-+*/])+(0|[1-9]\\d{0,9})(\\.\\d{0,20})?=$")) {
            return new Expression(expression);
        }
        else {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    public Expression() {
        expression = "";
        operation = '\0';
        position = 0;
        flagMinus = false;
    }

    public Expression(String expression) throws IllegalArgumentException {
        this.expression = expression;
        operation = '\0';
        position = 0;
        flagMinus = false;
    }

    protected BigDecimal getOperand() { // return the current operand from expression as BigDecimal and also set the current operation
        int start = position;

        while (expression.charAt(position) != '+' && expression.charAt(position) != '-' && expression.charAt(position) != '*'
                && expression.charAt(position) != '/' && expression.charAt(position) != '=') {
            position++;
        }

        BigDecimal operand = new BigDecimal(expression.substring(start, position));
        operation = expression.charAt(position);
        position++;

        return operand;
    }

    protected BigDecimal selectOperation(BigDecimal operand) {
        switch (operation) {
            case '=':
                return operand; // if operation is '=' => expression is over and return the current operand
            case '+':
                return add(operand);
            case '-':
                return subtract(operand);
            case '*':
                return mult(operand);
            case '/':
                return divide(operand);
            default:
                return BigDecimal.valueOf(1);
        }
    }

    protected BigDecimal add(BigDecimal operand) { // if operation is '+' => get current operand and add the rest of the expression
        if (flagMinus) {
            flagMinus = false; /*
                                * if flagMinus is true, so the last operation was subtraction and rest of the expression
                                * has sign - so I subtract it again from the current operand to change its sign to +
                                */
            return operand.subtract(calculate());
        }
        else { // if flagMinus is false => the last operation was add and rest of the expression has sign +, so I add to the current operand
            return operand.add(calculate());
        }
    }

    protected BigDecimal subtract(BigDecimal operand) { // if operation is '-' => get current operand and subtract the rest of the expression
        if (flagMinus == false) {
            flagMinus = true; // flagMinus become true, because we have subtraction, so the rest of the expression will have sign -
            return operand.subtract(calculate());
        }
        else { // if flagMinus is true => get the current operand and add the rest of the expression, because the rest of expression is with sign -
            return operand.add(calculate());
        }
    }

    protected BigDecimal mult(BigDecimal operand) { // if operation is '*' => get the current operand, get the next operand, multiply them and continue with expression
        BigDecimal nextOperand = getOperand();
        return selectOperation(operand.multiply(nextOperand));
    }

    protected BigDecimal divide(BigDecimal operand) throws ArithmeticException { // if operation is '/' => get the current operand, get the next operand, divide them and continue with expression
        BigDecimal nextOperand = getOperand();

        if (nextOperand.compareTo(BigDecimal.ZERO) == 0) { // if the next operand is 0, the program is terminated
            throw new ArithmeticException();
        }

        return selectOperation(operand.divide(nextOperand, 20, BigDecimal.ROUND_HALF_UP));
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public char getOperation() {
        return operation;
    }

    public int getPosition() {
        return position;
    }

    public boolean getFlagMinus() {
        return flagMinus;
    }

    public BigDecimal calculate() {
        BigDecimal operand = getOperand();
        return selectOperation(operand);
    }
}
