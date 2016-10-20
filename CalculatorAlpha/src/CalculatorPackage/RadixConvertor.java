/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.Scanner;

/**
 *
 * @author susu_xi
 */
public class RadixConvertor {
    

 
    public static String RandomConvert(String source, Integer radix, Integer targetradix){
        String convertresult = "";
        switch(radix){
            case 2: if(targetradix == 8) 
                        convertresult = Integer.toOctalString(Integer.parseInt(source, 2));
                    else if(targetradix == 10)
                        convertresult = Integer.valueOf(source,2).toString();
                    else if(targetradix == 16)
                        convertresult = Integer.toHexString(Integer.parseInt(source,2));
                    break;
            case 8: if(targetradix == 2) 
                        convertresult = Integer.toBinaryString(Integer.parseInt(source, 8));
                    else if(targetradix == 10)
                        convertresult = Integer.valueOf(source,8).toString();
                    else if(targetradix == 16)
                        convertresult = Integer.toHexString(Integer.parseInt(source, 8));
                    break;
            case 10:if(targetradix == 2) 
                        convertresult = Integer.toBinaryString(Integer.parseInt(source, 10));
                    else if(targetradix == 8)
                        convertresult = Integer.toOctalString(Integer.parseInt(source, 10));
                    else if(targetradix == 16)
                        convertresult = Integer.toHexString(Integer.parseInt(source, 10));
                    break;
            case 16:if(targetradix == 2) 
                        convertresult = Integer.toBinaryString(Integer.parseInt(source, 16));
                    else if(targetradix == 8)
                        convertresult = Integer.toOctalString(Integer.parseInt(source, 16));
                    else if(targetradix == 10)
                        convertresult = Integer.valueOf(source,16).toString();
                    break;
        }
        return convertresult;
    }

    /**
     * @param radix expression进制类型
     * @param targetradix expression需要转换的目标进制类型
     * @return 
     */
    public static String getRandomExpression(String expression, Integer radix, Integer targetradix) {
        
        Scanner in = new Scanner(expression);
        StringBuffer stringbuffer = new StringBuffer();
        while (in.hasNext()) {
            String temp = in.next();
            if(temp.matches("[+-/*()]")){
                stringbuffer.append(temp);
                stringbuffer.append(" ");
            }else{
                temp =  RandomConvert(temp, radix, targetradix);
                stringbuffer.append(temp);
                stringbuffer.append(" ");
            }
        }
        return stringbuffer.toString();
    }
    
    
        public static void main(String args[]){
        String expression = "( 111 + 111010 ) * 110101 + 100000";
        expression = getRandomExpression(expression, 2 ,10);
       // expression = a.RandomConvert(expression,2);
        System.out.println(expression);
    }

}