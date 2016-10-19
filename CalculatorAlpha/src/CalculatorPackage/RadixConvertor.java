/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.Scanner;

/**
 *
 * @author TESLA_CN
 */
public class RadixConvertor {
    
    private String expression = "";
    private int radix = 10;
    
    public RadixConvertor(String expression, Integer radix) {
       this.expression = expression;
       this.radix = radix;
    }
    
    public String OthersToDecimal(String expression, Integer radix) {
        String decimal = "";
        switch(radix){
            //其他进制转十进制
            case 2: decimal = Integer.valueOf(expression,2).toString(); break;
            case 8: decimal = Integer.valueOf(expression,8).toString(); break;
            case 16:decimal = Integer.valueOf(expression,16).toString();break;
        }
        return decimal;   
    }
    
    public String DecimalToOthers(int decimal, Integer radix){
        //十进制转其他进制
        String convertresult = "";
        switch(radix){
            case 2: convertresult = Integer.toBinaryString(decimal);break;
            case 8: convertresult = Integer.toOctalString(decimal); break;
            case 16:convertresult = Integer.toHexString(decimal);   break;
        }
        return convertresult;
    }
    
    public String getDecimalExpression(String expression, Integer radix) {
        Scanner in = new Scanner(expression);
        String number1 = in.next();  //第一个运算数
        String operator = in.next(); //运算符
        String number2 = in.next();  //第二个运算数
        number1 = OthersToDecimal(number1,radix);
        number2 = OthersToDecimal(number2,radix);
        expression = number1+" "+operator+" "+number2;
        return expression;
    }
    
    public String getBinaryHexExpression(String expression,Integer radix) {
        //获取二进制或八进制形式的表达式
        //radix 为目标转换数制
        Scanner in = new Scanner(expression);
        int number1 = in.nextInt();  //第一个运算数
        String operator = in.next(); //运算符
        int number2 = in.nextInt();  //第二个运算数
        String number1Binary = DecimalToOthers(number1,radix);
        String number2Binary = DecimalToOthers(number2,radix);
        expression = number1Binary+" "+operator+" "+number2Binary;
        return expression;
    }
        public static void main(String args[]){
        String expression = "122 + 65535";
        RadixConvertor a = new RadixConvertor(expression,10);
        expression = a.getBinaryHexExpression(expression,2);
        System.out.println(expression);
    }

}