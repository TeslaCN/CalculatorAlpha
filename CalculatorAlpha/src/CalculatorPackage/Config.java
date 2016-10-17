package CalculatorPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author TESLA_CN¡£
 */
public class Config {

    public static final double BUTTON_WIDTH = 75;
    public static final double BUTTON_HEIGHT = 50;
    
    public static final double BASIC_KEYBOARD_GAP = -2;
    public static final double BASIC_KEYBOARD_INSETS = 0;
    public static final double SENIOR_KEYBOARD_GAP = -2;
    public static final double SENIOR_KEYBOARD_INSETS = 0;

    public static final String END = " #";
    
    public static final String PLUS = " + ";
    public static final String SUBSTRAC = " - ";
    public static final String MULTIPLY = " * ";
    public static final String DIVIDE = " / ";
    public static final String LEFT_BRACKET = " ( ";
    public static final String RIGHT_BRACKET = " ) ";
    public static final String ANS = " Ans ";
    
    public static final String SIN = " sin ( ";
    public static final String COS = " cos ( ";
    public static final String TAN = " tan ( ";
    public static final String ASIN = " arcsin ( ";
    public static final String ACOS = " arccos ( ";
    public static final String ATAN = " arctan ( ";
    
    public static final String ABS = " abs ( ";
    public static final String LN = " ln ( ";
    
    public static final String PI = " ¦Ð ";
    
    public static double previousAnswer;
    
    Config() {
        previousAnswer = 0;
    }
}
