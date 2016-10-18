/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.Scanner;

/**
 * 表达式的合法性检验
 * 由于一个人对错误情况的判断情况可能不全,所以这部分代码大家一起补充(记得写好注释)
 */
public class LegitimacyDetection {
    //表达式
    private String expression;
    //表达式的合法性
    private boolean leagel=true;
    
    public LegitimacyDetection(String expression) {
        this.expression = expression;
    }
    
    public boolean isLeagel(){
        //这里添加各种检测方法
        checkParentheses();
        
        //...
        return leagel;
    }
    
    /**
     * 情况1:表达式的括号匹配检测
     */
    private void checkParentheses() {
        int parenthesesStack=0;
        Scanner exp=new Scanner(expression);
        String temp=exp.next();
        do {            
           temp=exp.next();
            if (temp.equals("(")) parenthesesStack++;
            else if(temp.equals(")")) parenthesesStack--;
        } while (!temp.equals("#"));
        if(parenthesesStack!=0) leagel=false;
    }
    
    
    
    
}
