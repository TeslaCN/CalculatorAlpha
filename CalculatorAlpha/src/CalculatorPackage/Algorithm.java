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
//用一个数字栈和一个符号栈来存储解析式(见大一数据结构)
    private final Stack<Double> number = new Stack<>();//数字栈
    private final Stack<String> symbol = new Stack<>();//符号栈
    private static final Map<String, Integer> cmp = new HashMap<>();//map用来存储符号以及对于的运算优先级,不用原来的数组存储方法,算是一点改进吧

    private String expr;//表达式
/**
 * map每个元素包含两个值,第一个是符号,第二个是符号对应的运算优先级,数字越大,优先级越高
 * @param String 表达式 
 * 
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
        Double answer=null;
        try {
            answer=solve();
        } catch (Exception e) {
        }
        return answer;
    }
    
    private Double solve() {
        Scanner expression = new Scanner(expr);
        String temp = expression.next();
        while (!("#".equals(temp)) || !("#".equals(symbol.peek()))) {
            //正则表达式判断数字和几个应该被压入数字栈的特殊符号
            if (temp.matches("[+-]?\\d.*") || temp.matches("[+-]?π")||temp.matches("[+-]?Ans")||temp.matches("[+-]?e")) {
                if (temp.matches("[+-]?π")) {
                    if (temp.charAt(0)=='-') {
                        number.push(-Math.PI);
                    }
                    else number.push(Math.PI);
                }else if (temp.matches("[+-]?e")) {
                    if (temp.charAt(0)=='-') {
                        number.push(-Math.E);
                    }
                    else number.push(Math.E);
                }else if (temp.matches("[+-]?Ans")) {
                    if (temp.charAt(0)=='-') {
                        number.push(-Config.previousAnswer);
                    }
                    else number.push(Config.previousAnswer);
                }else {
                    Double temp_num = Double.parseDouble(temp);
                    number.push(temp_num);
                }
                temp = expression.next();
                //判断基本符号,并通过map获取运算优先级判断该把符号入栈还是弹栈进行运算
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
                } else if ("(".equals(symbol.peek())) {
                    symbol.push(temp);
                    temp = expression.next();
                }
                //else包含的是遇到sin,cos,log...这些特殊符号的操作
                //通过括号匹配原则,把sin(...) cos(...)这些交给另外一个类处理
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
//进行简单的四则运算操作
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
