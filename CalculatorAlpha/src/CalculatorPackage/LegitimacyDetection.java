/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.Scanner;

/**
 * ���ʽ�ĺϷ��Լ���
 * ����һ���˶Դ���������ж�������ܲ�ȫ,�����ⲿ�ִ�����һ�𲹳�(�ǵ�д��ע��)
 */
public class LegitimacyDetection {
    //���ʽ
    private String expression;
    //���ʽ�ĺϷ���
    private boolean leagel=true;
    
    public LegitimacyDetection(String expression) {
        this.expression = expression;
    }
    
    public boolean isLeagel(){
        //������Ӹ��ּ�ⷽ��
        checkParentheses();
        
        //...
        return leagel;
    }
    
    /**
     * ���1:���ʽ������ƥ����
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
