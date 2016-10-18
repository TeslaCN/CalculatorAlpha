/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.Scanner;

/**
 *
 * @author TESLA_CN
 */
public class ExpressionHandler {

    private String expression = "";

    private String processed = "";

    public ExpressionHandler(String expression) {
        this.expression = expression;
        pretreat();
    }

    private void pretreat() {
        Scanner in = new Scanner(expression);
        boolean isPreviousSymbol = false;
        while (in.hasNext()) {
            String temp = in.next();
            if (isPreviousSymbol && (temp.equals("+") || temp.equals("-"))) {
                processed += temp;
                processed += in.next() + " ";
            } else {
                isPreviousSymbol = isSymbol(temp);
                processed += temp + " ";
            }
        }
    }

    public String getProcessed() {
        return processed;
    }

    public static boolean isSymbol(String object) {
        return !Character.isDigit(object.charAt(0));
    }

    public Double getDecimalAnswer() {
        return new Algorithm(processed).getAnswer();
    }

    public static void main(String[] args) {
        ExpressionHandler test = new ExpressionHandler("854 + - 88 * - 751 * - 42 #");
        System.out.println(test.getProcessed());
        System.out.println(test.getDecimalAnswer());
    }

}
