/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author susu_xi
 */
public class RadixConvertor {
    
/**
 * ��������ʽ�е���ֵ�������ֵ���ж����ˣ�ʮ��ʮ������֮���ת��   
 */
 
    public static String RandomConvert(String source, Integer radix, Integer targetradix){
        String convertresult = "";
        switch(radix){
            case 2: if(targetradix == 8) 
                        convertresult = new BigInteger(source, 2).toString(8);
                    else if(targetradix == 10)
                        convertresult = new BigInteger(source, 2).toString(10);
                    else if(targetradix == 16)
                        convertresult = new BigInteger(source, 2).toString(16);
                    break;
            case 8: if(targetradix == 2) 
                        convertresult = new BigInteger(source, 8).toString(2);
                    else if(targetradix == 10)
                        convertresult = new BigInteger(source, 8).toString(10);
                    else if(targetradix == 16)
                        convertresult = new BigInteger(source, 8).toString(16);
                    break;
            case 10:if(targetradix == 2) 
                        convertresult = new BigInteger(source, 10).toString(2);
                    else if(targetradix == 8)
                        convertresult = new BigInteger(source, 10).toString(8);
                    else if(targetradix == 16)
                        convertresult = new BigInteger(source, 10).toString(16);
                    break;
            case 16:if(targetradix == 2) 
                        convertresult = new BigInteger(source, 16).toString(2);
                    else if(targetradix == 8)
                        convertresult = new BigInteger(source, 16).toString(8);
                    else if(targetradix == 10)
                        convertresult = new BigInteger(source, 16).toString(10);
                    break;
        }
        return convertresult;
    }

    /**
     * @param radix expression��������
     * @param targetradix expression��Ҫת����Ŀ���������
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
        String expression = "( 111 + 111010 ) * 110101000 + 10000000000000000000000";
        expression = getRandomExpression(expression, 10 ,8);
        System.out.println(expression);
    }

}