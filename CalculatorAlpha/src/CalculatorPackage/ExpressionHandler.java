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

    /**
    * @param expression 获取原始表达式
    */
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

    /**
    * @return String 已预处理表达式
    */
    public String getProcessed() {
        return processed;
    }

    public static boolean isSymbol(String object) {
        return !Character.isDigit(object.charAt(0));
    }

    /**
    * @return Double 已预处理表达式 的十进制运算结果
     */
    public Double getDecimalAnswer() {
        return new Algorithm(processed).getAnswer();
    }

    /**
    * @param args
     */
    public static void main(String[] args) {
        ExpressionHandler test = new ExpressionHandler("854 + - 88 * - 751 * - 42 #");
        System.out.println(test.getProcessed());
        System.out.println(test.getDecimalAnswer());
    }

}
