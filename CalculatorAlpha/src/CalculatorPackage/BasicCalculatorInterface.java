package CalculatorPackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Stack;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author TESLA_CN
 */
public class BasicCalculatorInterface extends Application {

    //由于大多数引用不改变,因此设为final
    private static final Config CONFIG = new Config();

    //此处创建各种主要面板
    private final BorderPane root = new BorderPane();
    private final GridPane basicKeyBoard = new GridPane();
    private final VBox display = new VBox();
    private final VBox boxForRadioButton = new VBox(5);

    //此处创建表达式输入与答案输出文本框
    private final TextArea expression = new TextArea();
    private final TextField answer = new TextField();

    //此处创建各种基本功能的实现按钮
    private final Button[] btNumber = new Button[10];
    private final Button btDot = new Button(".");
    private final Button btAC = new Button("AC");
    private final Button btPreviousAns = new Button("ANS");
    private final Button btAdd = new Button("+");
    private final Button btSubtract = new Button("-");
    private final Button btMultiply = new Button("×");
    private final Button btDivide = new Button("÷");
    private final Button btEquals = new Button("=");

    private final Button btPrevious = new Button("<-");
    private final Button btNext = new Button("->");
    private final HBox previousNext = new HBox(Config.BASIC_KEYBOARD_GAP);

    private final RadioButton rbBasic = new RadioButton("Basic");
    private final RadioButton rbSenior = new RadioButton("Senior");
    private final ToggleGroup tg = new ToggleGroup();

    private final Button btRadixConverter = new Button("创建进制转换新窗口");
    private final Button btSolveEquation = new Button("线性方程组求解窗口");
    private final HBox extraButtonBox = new HBox(Config.BASIC_KEYBOARD_GAP / 2);

    @Override
    public void start(Stage primaryStage) {

        basicKeyBoard.setAlignment(Pos.CENTER);
        basicKeyBoard.setHgap(Config.BASIC_KEYBOARD_GAP);
        basicKeyBoard.setVgap(Config.BASIC_KEYBOARD_GAP);
        basicKeyBoard.setPadding(new Insets(Config.BASIC_KEYBOARD_INSETS));

        btRadixConverter.setPrefSize((5 * Config.BUTTON_WIDTH + 4 * Config.BASIC_KEYBOARD_GAP) / 2,
                Config.BUTTON_HEIGHT);
        btSolveEquation.setPrefSize((5 * Config.BUTTON_WIDTH + 4 * Config.BASIC_KEYBOARD_GAP) / 2,
                Config.BUTTON_HEIGHT);

        extraButtonBox.getChildren().addAll(btRadixConverter, btSolveEquation);

        display.getChildren().addAll(expression, answer, extraButtonBox);

        root.setTop(display);
        root.setCenter(basicKeyBoard);

        initializeBasicFunction();
        initializeSeniorFunction();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        setBasicAction();
        setSeniorAction();

        rbBasic.setOnAction(e -> {
            if (rbBasic.isSelected()) {
                primaryStage.close();
                root.setBottom(null);
                primaryStage.show();
                returnFocus();
            }
        });
        rbSenior.setOnAction(e -> {
            if (rbSenior.isSelected()) {
                primaryStage.close();
                root.setBottom(seniorKeyBoard);
                primaryStage.show();
                returnFocus();
            }
        });

        btRadixConverter.setOnAction(e -> {
            new RadixConvertorInterface().start(new Stage());
            returnFocus();
        });
        btSolveEquation.setOnAction(e -> {
            new LinearEquationInterface().start(new Stage());
            returnFocus();
        });

        answer.focusedProperty().addListener(lv -> {
            if (answer.isFocused()) {
                returnFocus();
            }
        });
        expression.requestFocus();
    }

    //设置基本按钮的尺寸以及布局
    private void initializeBasicFunction() {
        rbBasic.setSelected(true);
        rbBasic.setToggleGroup(tg);
        rbSenior.setToggleGroup(tg);
        boxForRadioButton.setPadding(new Insets(5, 0, 0, 5));
        boxForRadioButton.getChildren().addAll(rbBasic, rbSenior);

        btDot.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btAC.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btPreviousAns.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btAdd.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btSubtract.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btMultiply.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btDivide.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btEquals.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        for (int i = 0; i < btNumber.length; i++) {
            btNumber[i] = new Button(Integer.toString(i));
            btNumber[i].setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        }

        btPrevious.setPrefSize(Config.BUTTON_WIDTH / 2, Config.BUTTON_HEIGHT);
        btNext.setPrefSize(Config.BUTTON_WIDTH / 2, Config.BUTTON_HEIGHT);
        previousNext.getChildren().addAll(btPrevious, btNext);

        basicKeyBoard.addRow(0, btNumber[7], btNumber[8], btNumber[9], previousNext, btAC);
        basicKeyBoard.addRow(1, btNumber[4], btNumber[5], btNumber[6], btMultiply, btDivide);
        basicKeyBoard.addRow(2, btNumber[1], btNumber[2], btNumber[3], btAdd, btSubtract);
        basicKeyBoard.addRow(3, btNumber[0], btDot, btPreviousAns, btEquals, boxForRadioButton);

        //以下代码用于设置输入与输出文本框
        expression.setPrefWidth(basicKeyBoard.getWidth());
        expression.setPrefRowCount(3);
        expression.setWrapText(true);
        expression.setEditable(false);
        expression.setPromptText("Here is the expression");
        answer.setPrefWidth(basicKeyBoard.getWidth());
        answer.setAlignment(Pos.CENTER_RIGHT);
        answer.setEditable(false);
        answer.setPromptText("The answer will be display here");
    }

    private void setBasicAction() {
        for (int i = 0; i < btNumber.length; i++) {
            final Integer temp = i;
            btNumber[i].setOnAction(e -> {
                handleNewInput();
                append(temp.toString());
                returnFocus();
            });
        }
        btAdd.setOnAction(e -> {
            inputButtonPressed(Config.PLUS);
        });
        btSubtract.setOnAction(e -> {
            inputButtonPressed(Config.SUBSTRAC);
        });
        btMultiply.setOnAction(e -> {
            inputButtonPressed(Config.MULTIPLY);
        });
        btDivide.setOnAction(e -> {
            inputButtonPressed(Config.DIVIDE);
        });
        btDot.setOnAction(e -> {
            inputButtonPressed(".");
        });
        btEquals.setOnAction(e -> {
            printAnswer();
            returnFocus();
        });

        btPrevious.setOnAction(e -> {
            returnPrevious();
            returnFocus();
        });
        btNext.setOnAction(e -> {
            returnNext();
            returnFocus();
        });
        btAC.setOnAction(e -> {
            allClear();
            returnFocus();
        });
        btPreviousAns.setOnAction(e -> {
            inputButtonPressed(Config.ANS);
        });

        expression.setOnKeyPressed(e -> {
            //以下两行代码用于测试键盘输入
//            System.out.println(e.getCode() + " :: " + e.getCharacter() + " :: " + e.getText());
//            expression.setText(e.getCode() + "\n" + e.getCharacter() + "\n" + e.getText());
            String key = e.getText();
            String code = e.getCode().getName();
            System.out.println(key + "==" + e.getCode().getName());
            switch (code) {
                case "Enter":
                    printAnswer();
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
                case "F4":

                case "F5":
            }
            switch (key.toUpperCase()) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "0":
                case ".":
                    inputKeyPressed(e.getText());
                    break;

                case "+":
                    inputKeyPressed(Config.PLUS);
                    break;
                case "-":
                    inputKeyPressed(Config.SUBSTRAC);
                    break;
                case "*":
                    inputKeyPressed(Config.MULTIPLY);
                    break;
                case "/":
                    inputKeyPressed(Config.DIVIDE);
                    break;
                case "=":
                    printAnswer();
                    break;

                case "(":
                    inputKeyPressed(Config.LEFT_BRACKET);
                    break;
                case ")":
                    inputKeyPressed(Config.RIGHT_BRACKET);
                    break;
                case ",":
                    inputKeyPressed(Config.COMMA);
                    break;
                case "E":
                    inputKeyPressed(Config.E);
                    break;
                case "S":
                    inputKeyPressed(Config.SIN);
                    break;
                case "C":
                    inputKeyPressed(Config.COS);
                    break;
                case "T":
                    inputKeyPressed(Config.TAN);
                    break;
                case "A":
                    inputKeyPressed(Config.ABS);
                    break;
                case "D":
                    inputKeyPressed(Config.DEGREE);
                    break;
                case "P":
                    inputKeyPressed(Config.PI);
                    break;
            }
        });

    }

    private void append(String in) {
        expression.setText(expression.getText() + in);
    }

    private void inputKeyPressed(String object) {
        handleNewInput();
        append(object);
    }

    private void inputButtonPressed(String object) {
        handleNewInput();
        append(object);
        returnFocus();
    }

    private void printAnswer() {
        ExpressionHandler cal = new ExpressionHandler(expression.getText() + Config.END);
        String output = String.format("%.10f", cal.getDecimalAnswer());
        answer.setText(output.equals("null") ? "表达式有误" : output);
        try {
            Config.previousAnswer = cal.getDecimalAnswer();
        } catch (NullPointerException ex) {
            Config.previousAnswer = 0;
            System.out.println("表达式有误");
            System.out.println(output);
        }
    }

    //清除所有内容
    private void allClear() {
        expression.setText("");
        answer.setText("");
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

    private void returnFocus() {
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

    private final GridPane seniorKeyBoard = new GridPane();

    private final Button btLeftBracket = new Button("(");
    private final Button btRightBracket = new Button(")");
    private final Button btPower3 = new Button("Power3");
    private final Button btPower2 = new Button("Power2");
    private final Button btPower = new Button("Pow( , )");
    private final Button btSin = new Button("sin");
    private final Button btCos = new Button("cos");
    private final Button btTan = new Button("tan");
    private final Button btAbs = new Button("Abs");
    private final Button btLog = new Button("log( , )");
    private final Button btLog10 = new Button("log10");
    private final Button btLn = new Button("ln()");
    private final Button btRadical = new Button("√()");
    private final Button btPi = new Button("π");
    private final Button btE = new Button("e");
    private final Button btArcSin = new Button("arcsin");
    private final Button btArcCos = new Button("arccos");
    private final Button btArcTan = new Button("arctan");
    private final Button btDegree = new Button("degree");
    private final Button btComma = new Button(" , ");

    private void initializeSeniorFunction() {

        seniorKeyBoard.setVgap(Config.SENIOR_KEYBOARD_GAP);
        seniorKeyBoard.setHgap(Config.SENIOR_KEYBOARD_GAP);
        seniorKeyBoard.setPadding(new Insets(Config.SENIOR_KEYBOARD_INSETS));
        seniorKeyBoard.setAlignment(Pos.CENTER);

        btLeftBracket.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btRightBracket.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btPower3.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btPower2.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btPower.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btSin.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btCos.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btTan.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btAbs.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btLog.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btLog10.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btLn.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btRadical.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btPi.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btE.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btArcSin.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btArcCos.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btArcTan.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btDegree.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);
        btComma.setPrefSize(Config.BUTTON_WIDTH, Config.BUTTON_HEIGHT);

        seniorKeyBoard.addRow(0, btAbs, btLeftBracket, btRightBracket, btLog10, btLn);
        seniorKeyBoard.addRow(1, btPower3, btPower2, btPower, btLog, btComma);
        seniorKeyBoard.addRow(2, btSin, btCos, btTan, btArcSin, btArcCos);
        seniorKeyBoard.addRow(3, btArcTan, btDegree, btPi, btE, btRadical);
    }

    private void setSeniorAction() {
        btLeftBracket.setOnAction(e -> {
            inputButtonPressed(Config.LEFT_BRACKET);
        });
        btRightBracket.setOnAction(e -> {
            inputButtonPressed(Config.RIGHT_BRACKET);
        });
        btPower3.setOnAction(e -> {
            inputButtonPressed(Config.POWER3);
        });
        btPower2.setOnAction(e -> {
            inputButtonPressed(Config.POWER2);
        });
        btPower.setOnAction(e -> {
            inputButtonPressed(Config.POWER);
        });
        btSin.setOnAction(e -> {
            inputButtonPressed(Config.SIN);
        });
        btCos.setOnAction(e -> {
            inputButtonPressed(Config.COS);
        });
        btTan.setOnAction(e -> {
            inputButtonPressed(Config.TAN);
        });
        btAbs.setOnAction(e -> {
            inputButtonPressed(Config.ABS);
        });
        btLog.setOnAction(e -> {
            inputButtonPressed(Config.LOG);
        });
        btLog10.setOnAction(e -> {
            inputButtonPressed(Config.LOG10);
        });
        btLn.setOnAction(e -> {
            inputButtonPressed(Config.LN);
        });
        btRadical.setOnAction(e -> {
            inputButtonPressed(Config.RADICAL);
        });
        btPi.setOnAction(e -> {
            inputButtonPressed(Config.PI);
        });
        btE.setOnAction(e -> {
            inputButtonPressed(Config.E);
        });
        btArcSin.setOnAction(e -> {
            inputButtonPressed(Config.ASIN);
        });
        btArcCos.setOnAction(e -> {
            inputButtonPressed(Config.ACOS);
        });
        btArcTan.setOnAction(e -> {
            inputButtonPressed(Config.ATAN);
        });
        btDegree.setOnAction(e -> {
            inputButtonPressed(Config.DEGREE);
        });
        btComma.setOnAction(e -> {
            inputButtonPressed(Config.COMMA);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
