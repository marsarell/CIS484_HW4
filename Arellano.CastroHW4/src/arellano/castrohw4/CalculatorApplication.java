package arellano.castrohw4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.*;

/**
 * CIS484 Summer 22
 * HW 3
 * Daniella Castro-De La O
 * Mariela Arellano
 */
public class CalculatorApplication extends Application {

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
        HBox trinketButtHB = new HBox();
        VBox trinketVB = new VBox();

        //Nodes
        TextField txtFormula = new TextField();
        TextField txtTrinket = new TextField();
        txtFormula.setEditable(false);//To make text field uneditable
        txtFormula.setAlignment(Pos.CENTER_RIGHT);
        txtTrinket.setEditable(false);

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

        //The clear button will clear any calculations in the formula box
        Button btnClear = new Button("C");
        Button btnMultiply = new Button("*");
        Button btnEqual = new Button("=");
        Button btnSave = new Button("Save");
        Button btnLoad = new Button("Load");

        primaryPane.add(grpCalcVB, 0, 0);
        primaryPane.add(trinketVB, 6, 0);

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

        trinketButtHB.getChildren().addAll(btnSave, btnLoad);
        trinketVB.getChildren().addAll(txtTrinket, trinketButtHB);

        //(top/right/bottom/left)
        trinketVB.setPadding(new Insets(0, 0, 20, 0));
        txtTrinket.setPrefSize(300, 400);

        //To show on Formula Box
        btnOne.setOnAction(e -> {

            txtFormula.setText("1");

        });
        btnClear.setOnAction(e -> {

            txtFormula.clear();

        });

        Scene primaryScene = new Scene(primaryPane, 500, 250);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("DukeCalc v 0.1");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
