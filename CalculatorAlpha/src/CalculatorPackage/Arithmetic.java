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
     * @param expression sin ( ���ʽ )
     */
    public Arithmetic(String expression) {
        expr = expression;
        handleExpression();
    }

    /**
     * �����췽�����յ��ı��ʽ String ���� ��ѧ���� �� �Ա�������,
     * �����ж� ��ѧ���� �󽫱��ʽ�����Ӧ����ѧ����������
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
            case "��":
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
     * �÷������ڴ��� ���Ǻ���
     * @param expression ���Ǻ���������ı��ʽ
     * @param operation �������������Ǻ�������
     * @return �������Ǻ���������
     */
    public static Double handleTrigonometric(String expression, String operation) {
        //�����Ǻ���������ı��ʽ������ʽ��ֵ����,���õ��ı��ʽ��������Ӧ�����Ǻ����õ���Ӧ��Double���
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
     * �÷��������ȵ�λ�ı��ʽ������ԽǶ�Ϊ��λ�Ľ��
    * @param expression �����Ʊ��ʽ
    * @return Double �Ƕ���������
    */
    public static Double toDegree(String expression) {
        Double result = new ExpressionHandler(expression + Config.END).getDecimalAnswer();
        return Math.PI * result / 180;
    }

    /**
     * ���������ڼ����������ָ��,�Լ�����ֵ����ѧ����
     * @param expression ���ձ�������ʽ
     * @param operation ������ѧ��������
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
            case "��":
                return Math.sqrt(result);
        }
        return 0.0;
    }
    /**
     * ���������ڴ����Ԫ����,����power��log
    * @param expression ��Ԫ���㺯�����ʽ����
     * @param type ��Ԫ���㺯������
    * @return Double ��Ԫ���㺯��������
    */
    public static Double handleBinaryOperator(String expression, String type) {
        Scanner in = new Scanner(expression);
        in.next();//����������
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
     * @return ��Double���ͷ�����ѧ����������
     */
    public Double getAnswer() {
        return ans;
    }


    public static void main(String[] args) {
        String expression = "pow ( 5 , 4 ) + pow ( sin ( �� / 6 ) , 4 )  #";
        ExpressionHandler testExp = new ExpressionHandler(expression);
        Double r = testExp.getDecimalAnswer();
        System.out.println(r);
    }
}
