package arellano.castrohw4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.*;

/**
 * CIS484 Summer 22 HW 3 Daniella Castro-De La O Mariela Arellano
 */
public class CalculatorApplication extends Application implements EventHandler<ActionEvent> {

    TextField txtFormula = new TextField();
    TextArea txtTicker = new TextArea();

    Button btnZero = new Button("0");
    Button btnOne = new Button("1");
    Button btnTwo = new Button("2");
    Button btnThree = new Button("3");
    Button btnFour = new Button("4");
    Button btnFive = new Button("5");
    Button btnSix = new Button("6");
    Button btnSeven = new Button("7");
    Button btnEight = new Button("8");
    Button btnNine = new Button("9");
    Button btnPlus = new Button("+");
    Button btnMinus = new Button("-");
    Button btnDivide = new Button("/");
    Button btnClear = new Button("C");
    Button btnMultiply = new Button("*");
    Button btnEqual = new Button("=");
    Button btnSave = new Button("Save");
    Button btnLoad = new Button("Load");

    String formula = " ";

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane primaryPane = new GridPane();

        //HBox and VBox
        VBox grpCalcVB = new VBox();
        HBox calcHB = new HBox();
        HBox rowOneHB = new HBox();
        HBox rowTwoHB = new HBox();
        HBox rowThreeHB = new HBox();
        HBox rowFourHB = new HBox();
        HBox tickerButtHB = new HBox();
        VBox tickerVB = new VBox();

        //Nodes
        txtFormula.setEditable(false);//To make text field uneditable
        txtFormula.setAlignment(Pos.CENTER_RIGHT);
        txtTicker.setEditable(false);

        //Event Handler
        btnZero.setOnAction(this);
        btnOne.setOnAction(this);
        btnTwo.setOnAction(this);
        btnThree.setOnAction(this);
        btnFour.setOnAction(this);
        btnFive.setOnAction(this);
        btnSix.setOnAction(this);
        btnSeven.setOnAction(this);
        btnEight.setOnAction(this);
        btnNine.setOnAction(this);
        btnPlus.setOnAction(this);
        btnMinus.setOnAction(this);
        btnDivide.setOnAction(this);
        btnMultiply.setOnAction(this);
        btnClear.setOnAction(this);
        btnEqual.setOnAction(this);
     

        primaryPane.add(grpCalcVB, 0, 0);
        primaryPane.add(tickerVB, 6, 0);

        grpCalcVB.getChildren().addAll(calcHB, rowOneHB, rowTwoHB, rowThreeHB, rowFourHB);
        grpCalcVB.setPadding(new Insets(0, 0, 20, 0));

        //Row Zero
        txtFormula.setPrefWidth(200);
        txtFormula.setPrefHeight(100);
        calcHB.getChildren().addAll(txtFormula);

        //Row One
        rowOneHB.getChildren().addAll(btnOne, btnTwo, btnThree,
                btnPlus);
        rowOneHB.setPrefWidth(200);
        rowOneHB.setPrefHeight(100);
        rowOneHB.setPadding(new Insets(0));
        btnOne.setMaxWidth(Double.MAX_VALUE);
        btnTwo.setMaxWidth(Double.MAX_VALUE);
        btnThree.setMaxWidth(Double.MAX_VALUE);
        btnPlus.setMaxWidth(Double.MAX_VALUE);
        btnOne.setMaxHeight(Double.MAX_VALUE);
        btnTwo.setMaxHeight(Double.MAX_VALUE);
        btnThree.setMaxHeight(Double.MAX_VALUE);
        btnPlus.setMaxHeight(Double.MAX_VALUE);
        rowOneHB.setHgrow(btnOne, Priority.ALWAYS);
        rowOneHB.setHgrow(btnTwo, Priority.ALWAYS);
        rowOneHB.setHgrow(btnThree, Priority.ALWAYS);
        rowOneHB.setHgrow(btnPlus, Priority.ALWAYS);

        //Row Two
        rowTwoHB.getChildren().addAll(btnFour, btnFive, btnSix, btnMinus);
        rowTwoHB.setPrefWidth(200);
        rowTwoHB.setPrefHeight(100);
        rowTwoHB.setPadding(new Insets(0));
        btnFour.setMaxWidth(Double.MAX_VALUE);
        btnFive.setMaxWidth(Double.MAX_VALUE);
        btnSix.setMaxWidth(Double.MAX_VALUE);
        btnMinus.setMaxWidth(Double.MAX_VALUE);
        btnFour.setMaxHeight(Double.MAX_VALUE);
        btnFive.setMaxHeight(Double.MAX_VALUE);
        btnSix.setMaxHeight(Double.MAX_VALUE);
        btnMinus.setMaxHeight(Double.MAX_VALUE);
        rowTwoHB.setHgrow(btnFour, Priority.ALWAYS);
        rowTwoHB.setHgrow(btnFive, Priority.ALWAYS);
        rowTwoHB.setHgrow(btnSix, Priority.ALWAYS);
        rowTwoHB.setHgrow(btnMinus, Priority.ALWAYS);

        //Row Three
        rowThreeHB.getChildren().addAll(btnSeven, btnEight,
                btnNine, btnDivide);
        rowThreeHB.setPrefWidth(200);
        rowThreeHB.setPrefHeight(105);
        rowThreeHB.setPadding(new Insets(0));
        btnSeven.setMaxWidth(Double.MAX_VALUE);
        btnEight.setMaxWidth(Double.MAX_VALUE);
        btnNine.setMaxWidth(Double.MAX_VALUE);
        btnDivide.setMaxWidth(Double.MAX_VALUE);
        btnSeven.setMaxHeight(Double.MAX_VALUE);
        btnEight.setMaxHeight(Double.MAX_VALUE);
        btnNine.setMaxHeight(Double.MAX_VALUE);
        btnDivide.setMaxHeight(Double.MAX_VALUE);
        rowThreeHB.setHgrow(btnSeven, Priority.ALWAYS);
        rowThreeHB.setHgrow(btnEight, Priority.ALWAYS);
        rowThreeHB.setHgrow(btnNine, Priority.ALWAYS);
        rowThreeHB.setHgrow(btnDivide, Priority.ALWAYS);

        //Row Four
        rowFourHB.getChildren().addAll(btnZero, btnClear, btnEqual, btnMultiply);
        rowFourHB.setPrefWidth(200);
        rowFourHB.setPrefHeight(100);
        rowFourHB.setPadding(new Insets(0));

        btnZero.setMaxWidth(Double.MAX_VALUE);
        btnClear.setMaxWidth(Double.MAX_VALUE);
        btnEqual.setMaxWidth(Double.MAX_VALUE);
        btnMultiply.setMaxWidth(Double.MAX_VALUE);
        btnZero.setMaxHeight(Double.MAX_VALUE);
        btnClear.setMaxHeight(Double.MAX_VALUE);
        btnEqual.setMaxHeight(Double.MAX_VALUE);
        btnMultiply.setMaxHeight(Double.MAX_VALUE);
        rowFourHB.setHgrow(btnZero, Priority.ALWAYS);
        rowFourHB.setHgrow(btnClear, Priority.ALWAYS);
        rowFourHB.setHgrow(btnEqual, Priority.ALWAYS);
        rowFourHB.setHgrow(btnMultiply, Priority.ALWAYS);

        tickerButtHB.getChildren().addAll(btnSave, btnLoad);
        tickerVB.getChildren().addAll(txtTicker, tickerButtHB);

        tickerVB.setPadding(new Insets(0, 0, 20, 0));
        txtTicker.setPrefSize(300, 400);

        //when save button is clicked, ticker box will save onto .dat file 
        btnSave.setOnAction(e -> {
            try {
                FileOutputStream load = new FileOutputStream("ticker.dat");
                DataOutputStream outputLoad = new DataOutputStream(load);

                outputLoad.writeUTF(txtTicker.getText());
                outputLoad.writeUTF("");
                outputLoad.write('\n');

                outputLoad.close();
            } catch (Exception ex) {
                System.out.println("Error:" + ex.getMessage());
            }

            //ticker.getText();
            Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
            saveAlert.setAlertType(Alert.AlertType.INFORMATION);
            saveAlert.setContentText("Saved Successfully!");
            saveAlert.show();
        });

        //when button is clicked, ticker box will load the saved history
        btnLoad.setOnAction(e -> {
            try {
                FileInputStream fw = new FileInputStream("ticker.dat");
                DataInputStream pw = new DataInputStream(fw);
                txtTicker.setText(pw.readUTF());

            } catch (Exception ex) {
                System.out.println("Error:" + ex.getMessage());
            }
        });

        Scene primaryScene = new Scene(primaryPane, 500, 250);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("DukeCalc v 0.1");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnOne) {
            formula += btnOne.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnTwo) {
            formula += btnTwo.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnThree) {
            formula += btnThree.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnFour) {
            formula += btnFour.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnFive) {
            formula += btnFive.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnSix) {
            formula += btnSix.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnSeven) {
            formula += btnSeven.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnEight) {
            formula += btnEight.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnNine) {
            formula += btnNine.getText();
            txtFormula.setText(formula);
        } else if (event.getSource() == btnPlus) {
            if (!(txtFormula.getText().endsWith("+") || txtFormula.getText().endsWith("-")
                    || txtFormula.getText().endsWith("/") || txtFormula.getText().endsWith("*"))) {
                formula += btnPlus.getText();
                txtFormula.setText(formula);

            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setAlertType(Alert.AlertType.ERROR);
                error.setContentText("Cannot enter an operator more than once in a row!");
                error.show();
                txtFormula.setText(formula);
            }
        } else if (event.getSource() == btnDivide) {
            if (!(txtFormula.getText().endsWith("+") || txtFormula.getText().endsWith("-")
                    || txtFormula.getText().endsWith("/") || txtFormula.getText().endsWith("*"))) {
                formula += btnDivide.getText();
                txtFormula.setText(formula);

            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setAlertType(Alert.AlertType.ERROR);
                error.setContentText("Cannot enter an operator more than once in a row!");
                error.show();
                txtFormula.setText(formula);
            }
        } else if (event.getSource() == btnMinus) {
            if (!(txtFormula.getText().endsWith("+") || txtFormula.getText().endsWith("-")
                    || txtFormula.getText().endsWith("/") || txtFormula.getText().endsWith("*"))) {
                formula += btnMinus.getText();
                txtFormula.setText(formula);

            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setAlertType(Alert.AlertType.ERROR);
                error.setContentText("Cannot enter an operator more than once in a row!");
                error.show();
                txtFormula.setText(formula);
            }

        } else if (event.getSource() == btnMultiply) {

            if (!(txtFormula.getText().endsWith("+") || txtFormula.getText().endsWith("-")
                    || txtFormula.getText().endsWith("/") || txtFormula.getText().endsWith("*"))) {
                formula += btnMultiply.getText();
                txtFormula.setText(formula);

            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setAlertType(Alert.AlertType.ERROR);
                error.setContentText("Cannot enter an operator more than once in a row!");
                error.show();
                //formula = txtFormula.getText().substring(0, txtFormula.getText().length() - 1);
                txtFormula.setText(formula);
            }

        } else if (event.getSource() == btnEqual) {
            int result = calculate(txtFormula.getText());
            txtTicker.appendText(txtFormula.getText() + " = " + result);
            txtTicker.appendText("\n");
            formula = " ";
            txtFormula.clear();
        }else if(event.getSource() == btnClear){
             txtFormula.clear();
        }else if(event.getSource() == btnZero){
            formula += btnZero.getText();
            txtFormula.setText(formula);
        }
    }
    //methods to caluculate: 

    public static int formula(char operator, int y, int x) {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
            default:
                break;
        }
        return 0;
    }

    public static boolean isOperator(char operator1, char operator2) {
        if ((operator1 == '*' || operator1 == '/' || operator1 == '+' || operator1 == '-')
                && (operator2 == '*' || operator2 == '/' || operator2 == '+' || operator2 == '-')) {
            return false;
        }
        return true;
    }

    //stacking and pushing
    public static int calculate(String result) {
        char[] input = result.toCharArray();
        Stack<Integer> number = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == ' ') {
                continue;
            }
            if (input[i] >= '0' && input[i] <= '9') {
                String s = "";
                while (i < input.length && input[i] >= '0' && input[i] <= '9') {
                    s += input[i++];
                }
                number.push(Integer.parseInt(s));
                i--;
            } else if (input[i] == '+' || input[i] == '-' || input[i] == '*' || input[i] == '/') {

                while (!operators.empty() && isOperator(input[i], operators.peek())) {
                    number.push(formula(operators.pop(), number.pop(), number.pop()));
                }
                operators.push(input[i]);
            }
        }
        while (!operators.empty()) {
            int numOne = number.pop();
            int numTwo = number.pop();
            number.push(formula(operators.pop(), numOne, numTwo));
        }
        return number.pop();
    }

}
