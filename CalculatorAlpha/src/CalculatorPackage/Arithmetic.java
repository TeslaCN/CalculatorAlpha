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
            case "abs":
            case "ln":
            case "√":
                ans = handleFunction(e, operation);
                break;
            case "power":
                
                break;
            default:
        }
    }

    public static Double handleTrigonometric(String expression, String operation) {
        Double result = new Algorithm(expression + Config.END).getAnswer();
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
    
    public static Double toDegree(String expression) {
        Double result = new Algorithm(expression + Config.END).getAnswer();
        return Math.PI * result / 180;
    }

    public static Double handleFunction(String expression, String operation) {
        Double result = new Algorithm(expression + Config.END).getAnswer();
        switch (operation) {
            case "abs":
                return Math.abs(result);
            case "ln":
                return Math.log(result);
            case "√":
                return Math.sqrt(result);
            case "log":
                
        }
        return 0.0;
    }
    
    public static Double handlePower(String expression, String index) {
        Stack<String> brackets = new Stack<>();
        Scanner in = new Scanner(expression);
        
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
        String expression = " sin ( degree ( 30 ) ) ";
        Arithmetic test = new Arithmetic(expression);
        System.out.println(test.getAnswer());
        Algorithm testAl = new Algorithm(expression + Config.END);
        Double r = testAl.getAnswer();
        System.out.println(r);
    }
}
