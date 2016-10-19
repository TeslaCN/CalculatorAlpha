package CalculatorPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 * Double getAnswer() 返回表达式的运算结果
 * Algorithm(String 表达式) 构造方法
 * 
 */
public class Algorithm {

    private final Stack<Double> number = new Stack<>();
    private final Stack<String> symbol = new Stack<>();
    private static final Map<String, Integer> cmp = new HashMap<>();

    private String expr;
/**
 * 
 * @param String 表达式 
 */
    public Algorithm(String expression) {
        expr = expression;
        symbol.push("#");
        number.push(0.0);
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
            if (temp.matches("[+-]?\\d.*") || temp.charAt(0) == 'π'||temp.equals("Ans")) {
                if (temp.charAt(0) == 'π') {
                    number.push(Math.PI);
                }else if (temp.equals("ANS")) {
                    number.push(Config.previousAnswer);
                }else {
                    Double temp_num = Double.parseDouble(temp);
                    number.push(temp_num);
                }
                temp = expression.next();
            } else if (temp.equals("#")||temp.equals("+")||temp.equals("-")||
                    temp.equals("*")||temp.equals("/")||temp.equals("(")||temp.equals(")")) {
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
                    //System.out.println(i+"此时栈顶优先级高,弹出栈");
                } else if ("(".equals(symbol.peek())) {
                    symbol.push(temp);
                    temp = expression.next();
                }
            } else {
                StringBuilder subExpression = new StringBuilder();
                subExpression.append(temp).append(" ");
                temp = expression.next();
                int parenthesesStack = 0;//括号匹配
                do {                    
                    if (temp.equals("(")) {
                        parenthesesStack++;
                    } else if (temp.equals(")")) {
                        parenthesesStack--;
                    }
                    subExpression.append(temp).append(" ");
                    temp = expression.next();
                } while (parenthesesStack > 0);
                Arithmetic subResult = new Arithmetic(subExpression.toString());
                number.push(subResult.getAnswer());
            }
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
