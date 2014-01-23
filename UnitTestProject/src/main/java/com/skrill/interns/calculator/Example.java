package com.skrill.interns.calculator;

import java.util.Scanner;

public class Example {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String newExpression = "";

        System.out.println("Insert your expression:");
        Scanner scan = new Scanner(System.in);
        Expression ex;

        do {
            newExpression = scan.nextLine();
            try {
                ex = Expression.parse(newExpression);
                break;
            } catch (IllegalArgumentException er) {
                System.err.println(er.getMessage());
            }

        } while (true);

        System.out.println(ex.calculate());

    }
}
