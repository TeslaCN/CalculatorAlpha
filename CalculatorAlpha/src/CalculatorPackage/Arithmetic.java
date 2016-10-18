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
                ans = handleSin(e);
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

    public static Double handleSin(String expression) {
        Double result = new Algorithm(expression + Config.END).getAnswer();
        return Math.sin(Math.toDegrees(result));
    }

    /**
     *
     * @return 算数值
     */
    public Double getAnswer() {
        return ans;
    }

    public static void main(String[] args) {
        String expression = " sin ( 30 + 60 ) ";
        Arithmetic test = new Arithmetic(expression);
        System.out.println(test.getAnswer());
        Algorithm testAl = new Algorithm(expression + Config.END);
        Double r = testAl.getAnswer();
        System.out.println(r);

    }
}
