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
 * ���ʽ�ĺϷ��Լ���
 * ����һ���˶Դ���������ж�������ܲ�ȫ,�����ⲿ�ִ�����һ�𲹳�(�ǵ�д��ע��)
 */
public class LegitimacyDetection {
    private static final Map<String, Integer> CMP = new HashMap<>();
    
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
        checkNumber();
        checkOperator();
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
        } while (!temp.equals("#")&&leagel);
        if(parenthesesStack!=0) leagel=false;
    }
    
     /**
     * ���2:���ֺϷ��Լ��
     * ��ֹ����:
     *      ���ְ���һ�����ϵ�С����
     *      ���ְ����� "����" �� "." ֮��������ַ�
     *      ������ "." ��β
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
     * ���3:���������Ϸ��Լ��
     * ��ֹ����:
     *      +-,++,**,(+,-),...�ȷǷ��������
     *      ���������ַ���ͨ����������,�����ַ��ĺϷ��Կɲ��ؼ��,ֻ���ע��������
     */    
    private void checkOperator(){
        //������
        //...
    }
}
    
    
  
