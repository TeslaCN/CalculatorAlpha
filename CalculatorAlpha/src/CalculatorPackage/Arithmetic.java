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

    /**
     * 将构造方法接收到的表达式 String 进行 数学函数 与 自变量分离,
     * 并且判断 数学函数 后将表达式送入对应的数学函数处理方法
     */
    private void handleExpression() {
        Scanner input = new Scanner(expr);
        String operation = input.next();
        String e = input.nextLine();
        switch (operation) {
            case "degree":
                ans = toDegree(e);
                break;
            case "sin":
            case "cos":
            case "tan":
            case "arcsin":
            case "arccos":
            case "arctan":
                ans = handleTrigonometric(e, operation);
                break;

            case "pow3":
            case "pow2":
            case "abs":
            case "log10":
            case "ln":
            case "√":
                ans = handleFunction(e, operation);
                break;

            case "pow":
            case "log":
                ans = handleBinaryOperator(e, operation);

                break;
            default:
        }
    }

    /**
     * 该方法用于处理 三角函数
     * @param expression 三角函数所计算的表达式
     * @param operation 本参数接收三角函数类型
     * @return 返回三角函数计算结果
     */
    public static Double handleTrigonometric(String expression, String operation) {
        //将三角函数所计算的表达式送入表达式求值类中,将得到的表达式结果送入对应的三角函数得到相应的Double结果
        Double result = new ExpressionHandler(expression + Config.END).getDecimalAnswer();
        switch (operation) {
            case "sin":
                return Math.sin(result);
            case "cos":
                return Math.cos(result);
            case "tan":
                return Math.cos(result);
            case "arcsin":
                return Math.asin(result);
            case "arccos":
                return Math.acos(result);
            case "arctan":
                return Math.atan(result);
            default:
                return 0.0;
        }
    }

    /**
     * 该方法将弧度单位的表达式计算出以角度为单位的结果
    * @param expression 弧度制表达式
    * @return Double 角度制运算结果
    */
    public static Double toDegree(String expression) {
        Double result = new ExpressionHandler(expression + Config.END).getDecimalAnswer();
        return Math.PI * result / 180;
    }

    /**
     * 本方法用于计算对数或者指数,以及绝对值等数学函数
     * @param expression 接收被计算表达式
     * @param operation 接收数学函数类型
     * @return 
     */
    public static Double handleFunction(String expression, String operation) {
        Double result = new ExpressionHandler(expression + Config.END).getDecimalAnswer();
        switch (operation) {
            case "pow3":
                return Math.pow(result, 3);
            case "pow2":
                return Math.pow(result, 2);
            case "abs":
                return Math.abs(result);
            case "log10":
                return Math.log10(result);
            case "ln":
                return Math.log(result);
            case "√":
                return Math.sqrt(result);
        }
        return 0.0;
    }
    /**
     * 本方法用于处理二元函数,例如power和log
    * @param expression 二元运算函数表达式部分
     * @param type 二元运算函数类型
    * @return Double 二元运算函数计算结果
    */
    public static Double handleBinaryOperator(String expression, String type) {
        Scanner in = new Scanner(expression);
        in.next();//消除左括号
        String base = "";
        String index = "";
        while (in.hasNext()) {
            String temp = in.next();
            if(temp.equals(",")) {
                break;
            }
            base += temp + " ";
        }
        System.out.println("base:" + base);
        Double baseValue = new ExpressionHandler(base + Config.END).getDecimalAnswer();
        while (in.hasNext()) {
            String temp = in.next();
            if (!in.hasNext() && temp.equals(")")) {
                break;
            }
            index += temp + " ";
        }
        System.out.println("index:" + index);
        Double indexValue = new ExpressionHandler(index + Config.END).getDecimalAnswer();
        switch (type) {
            case "pow":
                return Math.pow(baseValue, indexValue);
            case "log":
                return Math.log(indexValue) / Math.log(baseValue);
        }
        return 0.0;
    }

    /**
     *
     * @return 以Double类型返回数学函数计算结果
     */
    public Double getAnswer() {
        return ans;
    }


    public static void main(String[] args) {
        String expression = "pow ( 5 , 4 ) + pow ( sin ( π / 6 ) , 4 )  #";
        ExpressionHandler testExp = new ExpressionHandler(expression);
        Double r = testExp.getDecimalAnswer();
        System.out.println(r);
    }
}
