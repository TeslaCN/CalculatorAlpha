/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author TESLA_CN
 */
public class LinearEquationInterface extends Application {

    private final BorderPane root = new BorderPane();

    private final TextField tfObject = new TextField();
    private final Button btContinue = new Button("Continue");
    private final HBox boxForObjectAmount = new HBox();

    private final Button btSolve = new Button("Solve");

    @Override
    public void start(Stage primaryStage) {

        tfObject.setPromptText("未知数个数(1~26)(缺省2)");
        boxForObjectAmount.setAlignment(Pos.CENTER);
        boxForObjectAmount.getChildren().addAll(tfObject, btContinue);

        root.setTop(boxForObjectAmount);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        btContinue.setOnAction(e -> {
            if (!tfObject.getText().trim().equals("") && (Integer.parseInt(tfObject.getText().trim()) > 26 || Integer.parseInt(tfObject.getText().trim()) < 1)) {
                error(new Stage());
            } else {
                primaryStage.close();
                root.setCenter(generateTable(Integer.parseInt(
                        tfObject.getText().trim().equals("") ? "2"
                        : tfObject.getText().trim())));
                primaryStage.show();
            }
        });
        btSolve.setOnAction(e -> {
            solveEquation();
            primaryStage.close();
            root.setBottom(solutionTable());
            primaryStage.show();
        });
        root.requestFocus();
    }

    private void error(Stage stage) {
        VBox pane = new VBox(10);
        pane.setAlignment(Pos.CENTER);
        Button btOK = new Button("确定");
        btOK.setOnAction(e -> stage.close());
        Text t = new Text("输入有误");
        pane.getChildren().addAll(t, btOK);
        Scene scene = new Scene(pane, 150, 75);
        stage.setScene(scene);
        stage.setTitle("Input Error");
        stage.show();
    }

    private Double[][] constants;
    private Double[] solution;
    private TextField[][] input;

    private GridPane generateTable(Integer object) {
        GridPane table = new GridPane();
        table.setVgap(5);
        table.setAlignment(Pos.CENTER);
        input = new TextField[object][object + 1];
        for (int i = 1; i <= object + 1; i++) {
            table.add(new Text("     " + Character.toString((char) (96 + i))
                    + (i <= object ? "(X" + i + ")" + (i < object ? " +" : " ==") : "")), i, 0);
        }
        for (int i = 0; i < object; i++) {
            table.add(new Text(Integer.toString(i + 1)), 0, i + 1);
            for (int j = 0; j < object + 1; j++) {
                input[i][j] = new TextField("0.0");
                input[i][j].setPrefColumnCount(5);
                input[i][j].setAlignment(Pos.CENTER);
                table.add(input[i][j], j + 1, i + 1);
            }
        }

        table.add(btSolve, object + 1, object + 1);

        return table;
    }

    private GridPane solutionTable() {
        GridPane table = new GridPane();
        for (int i = 1; i < solution.length; i++) {
            TextField temp = new TextField(String.format("%f", solution[i]));
            table.addRow(i, new Text("X" + i), temp);
        }
        return table;
    }

    private void solveEquation() {
        constants = new Double[input.length][input[0].length];
        for (int i = 0; i < constants.length; i++) {
            for (int j = 0; j < constants[i].length; j++) {
                try {
                    constants[i][j] = Double.parseDouble(input[i][j].getText().trim());
                } catch (NumberFormatException ex) {

                }
            }
        }
        solution = new SolutionOfLinearEquations(constants.length, constants).solves.clone();
        System.out.println(Arrays.toString(solution));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
