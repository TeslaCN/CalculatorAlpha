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

        boxForObjectAmount.getChildren().addAll(tfObject, btContinue);

        root.setTop(boxForObjectAmount);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        btContinue.setOnAction(e -> {
            primaryStage.close();
            root.setCenter(generateTable(Integer.parseInt(tfObject.getText().trim())));
            primaryStage.show();
        });
        btSolve.setOnAction(e -> {
            solveEquation();
            primaryStage.close();
            
            primaryStage.show();
        });
    }

    private Double[][] constants;
    private Double[] solution;
    private TextField[][] input;

    private GridPane generateTable(Integer object) {
        GridPane table = new GridPane();
        table.setAlignment(Pos.CENTER);
        input = new TextField[object][object + 1];
        for (int i = 1; i <= object + 1; i++) {
            table.add(new Text("     " + Character.toString((char) (96 + i))
                    + (i <= object ? "(x" + i + ")" + (i < object ? " +" : " ==") : "")), i, 0);
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

    private VBox solutionTable() {
        VBox table = new VBox(5);

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
