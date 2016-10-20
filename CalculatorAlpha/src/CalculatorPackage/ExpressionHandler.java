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

    /**
    * 该方法对表达式String进行预处理,消除表达式中正负号与数字之间的空格,
    * 否则包装类型Double无法直接将带符号的数字String转化为Double类型
    */
    private void pretreat() {
        Scanner in = new Scanner(expression);
        boolean isPreviousOperator = false;
        while (in.hasNext()) {
            String temp = in.next();
            if (isPreviousOperator && (temp.matches("[+-]"))) {
                processed += temp;
                processed += in.next() + " ";
            } else {
                isPreviousOperator = isOperator(temp);
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

    /**
     * 
     * @param object 被判断的字符
     * @return boolean 该符号是否加减乘除号其中之一
     */
    public static boolean isOperator(String object) {
        return object.matches("[+-/*]");
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
        ExpressionHandler test = new ExpressionHandler("pow ( 4 , 5 ) + log ( 4 , 64 ) + - 5 * - 44 / - 3 #");
        System.out.println(test.getProcessed());
        System.out.println(test.getDecimalAnswer());
        System.out.println(Integer.parseInt("D", 16));
    }

}
