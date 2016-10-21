package CalculatorPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 * Double getAnswer() ���ر��ʽ��������
 * Algorithm(String ���ʽ) ���췽��
 * 
 */
public class Algorithm {
//��һ������ջ��һ������ջ���洢����ʽ(����һ���ݽṹ)
    private final Stack<Double> number = new Stack<>();//����ջ
    private final Stack<String> symbol = new Stack<>();//����ջ
    private static final Map<String, Integer> cmp = new HashMap<>();//map�����洢�����Լ����ڵ��������ȼ�,����ԭ��������洢����,����һ��Ľ���

    private String expr;//���ʽ
/**
 * mapÿ��Ԫ�ذ�������ֵ,��һ���Ƿ���,�ڶ����Ƿ��Ŷ�Ӧ���������ȼ�,����Խ��,���ȼ�Խ��
 * @param String ���ʽ 
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
            //������ʽ�ж����ֺͼ���Ӧ�ñ�ѹ������ջ���������
            if (temp.matches("[+-]?\\d.*") || temp.matches("[+-]?��")||temp.matches("[+-]?Ans")||temp.matches("[+-]?e")) {
                if (temp.matches("[+-]?��")) {
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
                //�жϻ�������,��ͨ��map��ȡ�������ȼ��жϸðѷ�����ջ���ǵ�ջ��������
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
                //else������������sin,cos,log...��Щ������ŵĲ���
                //ͨ������ƥ��ԭ��,��sin(...) cos(...)��Щ��������һ���ദ��
            } else {
                StringBuilder subExpression = new StringBuilder();
                subExpression.append(temp).append(" ");
                temp = expression.next();
                int parenthesesStack = 0;//����ƥ��
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
//���м򵥵������������
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
