/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.Scanner;
import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    public static Double handleTrigonometric(String expression, String operation) {
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
    * @param expression 弧度制表达式
    * @return Double 角度制运算结果
    */
    public static Double toDegree(String expression) {
        Double result = new ExpressionHandler(expression + Config.END).getDecimalAnswer();
        return Math.PI * result / 180;
    }

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
    * @param expression 二元运算函数表达式部分
     * @param type 二元运算函数类型
    * @return Double 二元运算函数计算结果
    */
    public static Double handleBinaryOperator(String expression, String type) {
        Stack<String> brackets = new Stack<>();
        Scanner in = new Scanner(expression);
        in.next();
        brackets.push(in.next());
        String base = "";
        String index = "";
        base += brackets.peek() + " ";
        while (in.hasNext()) {
            String temp = in.next();
            if (temp.equals("(")) {
                brackets.push(temp);
            }
            base += temp + " ";
            if (temp.equals(")")) {
                brackets.pop();
            }
            if (brackets.empty()) {
                break;
            }
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
     * @return 算数值
     */
    public Double getAnswer() {
        return ans;
    }

    private void error(String err) {
        Stage stage = new Stage();
        Text text = new Text(err);
        Button btContinue = new Button("Continue");
        VBox pane = new VBox();
        pane.getChildren().addAll(text, btContinue);
    }

    public static void main(String[] args) {
        String expression = "pow ( ( 5 ) ( 4 ) ) + pow ( ( 5 ) ( 4 ) )  #";
        ExpressionHandler testExp = new ExpressionHandler(expression);
        Double r = testExp.getDecimalAnswer();
        System.out.println(r);
    }
}
