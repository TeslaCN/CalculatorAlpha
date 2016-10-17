/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

/**
 *
 * @author TESLA_CN
 */
public class ExpressionHandler {
    
    private String expression;
    
    public ExpressionHandler(String expression) {
        this.expression = expression;
    }
    
    public Double getDecimalAnswer() {
        return new Algorithm(expression).getAnswer();
    }
    
    
}
