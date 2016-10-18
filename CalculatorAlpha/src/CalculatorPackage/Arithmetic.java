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
public class Arithmetic {

    private String expr;

    private Double ans;

    /**
     *
     * @param expression sin ( 表达式 )
     */
    public Arithmetic(String expression) {
        expr = expression;
        handleExpression();
    }

    private void handleExpression() {
        Scanner input = new Scanner(expr);
        String operation = input.next();
        String e = input.nextLine();
        switch (operation) {
            case "sin":
                ans = handleTrigonometric(e, operation);
                break;
            case "cos":
            case "tan":
            case "arcsin":
            case "arccos":
            case "arctan":
            case "abs":
            case "ln":
            case "√":
            default:
        }
    }

    public static Double handleTrigonometric(String expression, String operation) {
        Double result = new Algorithm(expression + Config.END).getAnswer();
        switch(operation) {
            case "sin": return Math.sin(Math.PI * result / 180);
            case "cos": return Math.cos(Math.PI * result / 180);
            case "tan": return Math.cos(Math.PI * result / 180);
            case "arcsin" : return Math.asin(Math.PI * result / 180);
            case "arccos" : return Math.acos(Math.PI * result / 180);
            case "arctan" : return Math.atan(Math.PI * result / 180);
        }
        return 0.0;
    }

    
    /**
     *
     * @return 算数值
     */
    public Double getAnswer() {
        return ans;
    }

    public static void main(String[] args) {
        String expression = " sin ( 30 + 60 * sin ( 30 ) ) ";
        Arithmetic test = new Arithmetic(expression);
        System.out.println(test.getAnswer());
        Algorithm testAl = new Algorithm(expression + Config.END);
        Double r = testAl.getAnswer();
        System.out.println(r);
    }
}
