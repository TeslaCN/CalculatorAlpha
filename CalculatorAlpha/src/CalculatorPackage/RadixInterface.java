/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.Stack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author TESLA_CN
 */
public class RadixInterface extends Application {

    private final BorderPane root = new BorderPane();

    private final VBox display = new VBox();
    private final TextField expression = new TextField();
    private final TextField result = new TextField();

    private final GridPane keyboard = new GridPane();

    private final HBox checkBox = new HBox(5);
    private final ToggleGroup checkBoxGroup = new ToggleGroup();
    private final RadioButton rbHexadecimal = new RadioButton("Hexadecimal");
    private final RadioButton rbDecimal = new RadioButton("Decimal");
    private final RadioButton rbOctonary = new RadioButton("Octonary");
    private final RadioButton rbBinary = new RadioButton("Binary");

    @Override
    public void start(Stage primaryStage) {
        root.setTop(display);
        root.setCenter(checkBox);
        root.setBottom(keyboard);

        initialize();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Radix Convertor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private final Button[] btNumber = new Button[16];
    private boolean[] active = new boolean[16];

    private final Button btAdd = new Button("+");
    private final Button btSubtract = new Button("-");
    private final Button btMultiply = new Button("×");
    private final Button btDivide = new Button("÷");
    private final Button btEquals = new Button("=");
    private final Button btAC = new Button("AC");

    private final Button btPrevious = new Button("<-");
    private final Button btNext = new Button("->");
    private final HBox previousNext = new HBox(Config.BASIC_KEYBOARD_GAP);

    private Integer index;

    private void initialize() {
        display.getChildren().addAll(expression, result);
        expression.setEditable(false);
        result.setEditable(false);

        checkBox.getChildren().addAll(rbHexadecimal, rbDecimal, rbOctonary, rbBinary);
        rbHexadecimal.setToggleGroup(checkBoxGroup);
        rbDecimal.setToggleGroup(checkBoxGroup);
        rbOctonary.setToggleGroup(checkBoxGroup);
        rbBinary.setToggleGroup(checkBoxGroup);
        rbDecimal.setSelected(true);

        index = 10;
        setDecimalKeyboard();
        
        rbHexadecimal.setOnAction(e -> {
            if (rbHexadecimal.isSelected()) {
                index = 16;
                setHexaDecimalKeyboard();
                buttonPressed();
            }
        });

        rbDecimal.setOnAction(e -> {
            if (rbDecimal.isSelected()) {
                index = 10;
                setDecimalKeyboard();
                buttonPressed();
            }
        });

        rbOctonary.setOnAction(e -> {
            if (rbOctonary.isSelected()) {
                index = 8;
                setOctonaryKeyboard();
                buttonPressed();
            }
        });

        rbBinary.setOnAction(e -> {
            if (rbBinary.isSelected()) {
                index = 2;
                setBinaryKeyboard();
                buttonPressed();
            }
        });

        for (int i = 0; i < btNumber.length; i++) {
            btNumber[i] = new Button(Integer.toHexString(i).toUpperCase());
            btNumber[i].setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        }
        btAdd.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btSubtract.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btMultiply.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btDivide.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btEquals.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btAC.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);

        btPrevious.setPrefSize(Config.BUTTON_WIDTH / 2, Config.BUTTON_HEIGHT);
        btNext.setPrefSize(Config.BUTTON_WIDTH / 2, Config.BUTTON_HEIGHT);
        previousNext.getChildren().addAll(btPrevious, btNext);

        
        setAction();
        expression.requestFocus();
    }

    private void setDecimalKeyboard() {
        keyboard.getChildren().removeAll();
        for (int i = 0; i < btNumber.length; i++) {
            active[i] = i < index;
        }
    }

    private void setHexaDecimalKeyboard() {
        keyboard.getChildren().removeAll();
        for (int i = 0; i < btNumber.length; i++) {
            active[i] = i < index;
        }
    }

    private void setOctonaryKeyboard() {
        keyboard.getChildren().removeAll();
        for (int i = 0; i < btNumber.length; i++) {
            active[i] = i < index;
        }
    }

    private void setBinaryKeyboard() {
        keyboard.getChildren().removeAll();
        for (int i = 0; i < btNumber.length; i++) {
            active[i] = i < index;
        }
    }

    private void setAction() {

        expression.setOnKeyPressed(e -> {
            //以下两行代码用于测试键盘输入
//            System.out.println(e.getCode() + " :: " + e.getCharacter() + " :: " + e.getText());
//            expression.setText(e.getCode() + "\n" + e.getCharacter() + "\n" + e.getText());
            String key = e.getText();
            String code = e.getCode().getName();
            System.out.println(key + "==" + e.getCode().getName());
            switch (code) {
                case "Enter":
                    break;

                case "Right":
                    returnNext();
                    break;
                case "Esc":
                    allClear();
                    break;
                case "Backspace":
                case "Delete":
                case "Left":
                    returnPrevious();
                    break;
            }
            switch (key) { 
                case "+":
                    handleNewInput();
                    append(Config.PLUS);
                    break;
                case "-":
                    handleNewInput();
                    append(Config.SUBSTRAC);
                    break;
                case "*":
                    handleNewInput();
                    append(Config.MULTIPLY);
                    break;
                case "/":
                    handleNewInput();
                    append(Config.DIVIDE);
                    break;
                case "=":
                    break;
                
            }
            if(key.matches("[\\dA-Fa-f]") && active[Integer.parseInt(key, 16)]) {
                handleNewInput();
                append(key.toUpperCase());
            }

        });
        expression.requestFocus();
    }

    private void append(String in) {
        expression.setText(expression.getText() + in);
    }

    //清除所有内容
    private void allClear() {
        expression.setText("");
        result.setText("");
        previous.removeAllElements();
        cleanNext();
    }

    //从此处开始实现撤销与恢复功能
    //若表达式内输入新的内容,反撤销功能关闭
    private Boolean allowNext = false;

    private final Stack<String> previous = new Stack<>();//用于保存撤销的行为
    private final Stack<String> next = new Stack<>();//用于保存反撤销

    private void handleNewInput() {
        cleanNext();
        recordPreviousAction();
    }

    private void buttonPressed() {
        expression.requestFocus();
    }

    //记录并使输入行为入栈,以便于撤销
    private void recordPreviousAction() {
        previous.push(expression.getText());
    }

    //撤销上一步输入
    private void returnPrevious() {
        if (previous.isEmpty()) {
            return;
        }
        allowNext = true;
        next.push(expression.getText());
        expression.setText(previous.pop());
    }

    //反撤销
    private void returnNext() {
        if (next.isEmpty() || allowNext == false) {
            return;
        }
        recordPreviousAction();
        expression.setText(next.pop());
    }

    //表达式输入新字符,清理反撤销栈,关闭反撤销功能
    private void cleanNext() {
        allowNext = false;
        if (next.isEmpty() == false) {
            next.removeAllElements();
        }
    }
    //以上方法实现撤销与恢复功能

    public static void main(String[] args) {
        launch(args);
    }

}
