package CalculatorPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author TESLA_CN
 */
public class Algorithm {

    private final Stack<Double> number = new Stack<>();
    private final Stack<String> symbol = new Stack<>();
    private static final Map<String, Integer> cmp = new HashMap<>();

    private String expr;

    public Algorithm(String expression) {
        expr = expression;
        symbol.push("#");
        cmp.put("+", 1);
        cmp.put("-", 1);
        cmp.put("*", 10);
        cmp.put("/", 10);
        cmp.put("(", 100);
        cmp.put(")", -100);
        cmp.put("#", -1000);
    }

    public Double getAnswer() {
        return solve();
    }
    
    private Double solve() {
        Scanner expression = new Scanner(expr);
        String temp = expression.next();
        while (!("#".equals(temp)) || !("#".equals(symbol.peek()))) {
            //System.out.println("temp="+temp+"  symbol.peek="+symbol.peek());
            //System.out.println("symbol's peek is "+symbol.peek());
            if (Character.isDigit(temp.charAt(0))) {
                Double temp_num = Double.parseDouble(temp);
                number.push(temp_num);
                temp = expression.next();
            } else//
            if (cmp.get(symbol.peek()) < cmp.get(temp)) {
                symbol.push(temp);
                temp = expression.next();
            } else if (cmp.get(symbol.peek()) + cmp.get(temp) == 0) {
                symbol.pop();
                temp = expression.next();
            } else if ("+".equals(symbol.peek()) || "-".equals(symbol.peek()) || "*".equals(symbol.peek()) || "/".equals(symbol.peek())) {
                Double b = number.pop();
                Double a = number.pop();
                String suanzi = symbol.pop();
                number.push(calculate(a, suanzi, b));
                //System.out.println(i+"��ʱջ�����ȼ���,����ջ");
            } else if ("(".equals(symbol.peek())) {
                symbol.push(temp);
                temp = expression.next();
            } //���������sin,cos,�򴴽�һ���·���ջ��һ���µ�����ջ,Ȼ��solve(������ջ,�·���ջ)
            //��������ֵpush��ԭ�е�numberջ����
        }
        return number.peek();
    }

    private Double calculate(Double a, String s, Double b) {
        switch (s) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                System.out.println("error in calculate!");
                return 0.0;
        }
    }

    public static void main(String[] args) {

    }

}
