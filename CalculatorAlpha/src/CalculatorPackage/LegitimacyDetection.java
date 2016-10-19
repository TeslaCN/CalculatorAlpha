/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 表达式的合法性检验
 * 由于一个人对错误情况的判断情况可能不全,所以这部分代码大家一起补充(记得写好注释)
 */
public class LegitimacyDetection {
    private static final Map<String, Integer> CMP = new HashMap<>();
    
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
        checkNumber();
        checkOperator();
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
        } while (!temp.equals("#")&&leagel);
        if(parenthesesStack!=0) leagel=false;
    }
    
     /**
     * 情况2:数字合法性检测
     * 防止出现:
     *      数字包含一个以上的小数点
     *      数字包含除 "数字" 和 "." 之外的其它字符
     *      数字以 "." 结尾
     */   
    private void checkNumber(){
        Scanner exp=new Scanner(expression);
        String temp = exp.next();
        do {
            if (Character.isDigit(temp.charAt(0))) {
                boolean radixPoint = true;
                for (int i = 0; i < temp.length(); i++) {
                    if (Character.isDigit(temp.charAt(i)));
                    else if (temp.charAt(i)=='.'&&radixPoint)radixPoint=false;
                    else {
                        radixPoint=false;
                        leagel=false;
                    }
                }
//                if (temp.charAt(temp.length()-1)=='.') leagel=false;
            }
        } while (!temp.equals("#")&&leagel);
    }
    
     /**
     * 情况3:运算符搭配合法性检测
     * 防止出现:
     *      +-,++,**,(+,-),...等非法搭配出现
     *      由于运算字符是通过界面输入,所以字符的合法性可不必检测,只需关注搭配问题
     */    
    private void checkOperator(){
        //待完善
        //...
    }
}
    
    
  
