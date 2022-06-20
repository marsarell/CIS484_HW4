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
import javafx.stage.Stage;
import javafx.util.*;

/**
 * CIS484 Summer 22 HW 3 Daniella Castro-De La O Mariela Arellano
 */
public class CalculatorApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane primaryPane = new GridPane();

        //HBox and VBox
        HBox calcHBox = new HBox();
        HBox rowOneHB = new HBox();
        HBox rowTwoHB = new HBox();
        HBox rowThreeHB = new HBox();
        HBox rowFourHB = new HBox();

        //Nodes
        TextField txtCalc = new TextField();
        txtCalc.setEditable(false);//To make text field uneditable

        Button btnZero = new Button("0");
        btnZero.setUserData(0); //To store in object in the button
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
        Button btnMultiply = new Button("*");
        Button btnEqual = new Button("=");
        Button btnSave = new Button("Save");
        Button btnLoad = new Button("Load");

        primaryPane.add(calcHBox, 0, 0);
        primaryPane.add(rowOneHB, 0, 1);
        primaryPane.add(rowTwoHB, 0, 2);
        primaryPane.add(rowThreeHB, 0, 3);
        primaryPane.add(rowFourHB, 0, 4);

        //Row Zero
        calcHBox.getChildren().addAll(txtCalc);
        txtCalc.setPrefWidth(200);
        txtCalc.setPrefHeight(35);

        //Row One
        rowOneHB.getChildren().addAll(btnOne, btnTwo, btnThree,
                btnPlus);
        rowOneHB.setPrefWidth(200);
        rowOneHB.setPrefHeight(35);
        btnOne.setMaxWidth(Double.MAX_VALUE);
        btnTwo.setMaxWidth(Double.MAX_VALUE);
        btnThree.setMaxWidth(Double.MAX_VALUE);
        btnPlus.setMaxWidth(Double.MAX_VALUE);
        rowOneHB.setHgrow(btnOne, Priority.ALWAYS);
        rowOneHB.setHgrow(btnTwo, Priority.ALWAYS);
        rowOneHB.setHgrow(btnThree, Priority.ALWAYS);
        rowOneHB.setHgrow(btnPlus, Priority.ALWAYS);

        rowTwoHB.getChildren().addAll(btnFour, btnFive, btnSix, btnMinus);
        rowThreeHB.getChildren().addAll(btnSeven, btnEight,
                btnNine, btnDivide);
        rowFourHB.getChildren().addAll(btnZero, btnEqual, btnMultiply);

        Scene primaryScene = new Scene(primaryPane, 500, 250);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("DukeCalc v 0.1");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
