/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalculatorPackage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
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
    
    private final Button[] btNumber = new Button[16];
            
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
        
        rbHexadecimal.setOnAction(e -> {
            if(rbHexadecimal.isSelected()) {
                
            }
        });
        
        rbDecimal.setOnAction(e -> {
            if(rbDecimal.isSelected()) {
                
            }
        });
        
        rbOctonary.setOnAction(e -> {
            if(rbOctonary.isSelected()) {
                
            }
        });
        
        rbBinary.setOnAction(e -> {
            if(rbBinary.isSelected()) {
                
            }
        });
        
        
        
    }
    
    private void setDecimalKeyboard() {
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
