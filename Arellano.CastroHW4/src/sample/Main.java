/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static TextField tf;
    private static TextArea area;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("DukeCalc v 0.1");
        EventHandler<ActionEvent> action = actionListener();
        
        
       //
        
        tf = new TextField();
        tf.setEditable(false);
        tf.setMinHeight(50);
        tf.setMaxHeight(50);
        //Create buttons
        Button[] buttons = new Button[10];
        for (int a = 0; a < buttons.length; a++) {
            buttons[a] = new Button(""+(a+1));
            buttons[a].setOnAction(action);
            buttons[a].setMinSize(70, 70);
            buttons[a].setMaxSize(70, 70);
        }
        buttons[9] = new Button("0");
        buttons[9].setOnAction(action);
        buttons[9].setMinSize(140, 70);
        buttons[9].setMaxSize(140, 70);
        
        //Creation of symbols
        Button[] symbols = new Button[5];
        symbols[0] = new Button("+");
        symbols[1] = new Button("-");
        symbols[2] = new Button("/");
        symbols[3] = new Button("*");
        symbols[4] = new Button("=");
        
        for (int a = 0; a < symbols.length; a++) {
            symbols[a].setOnAction(action);
            symbols[a].setMinSize(70, 70);
            symbols[a].setMaxSize(70, 70);
        }
        
        GridPane gridPane = new GridPane();
        //Location of buttons
        gridPane.add(tf, 0, 0, 4, 1);
        gridPane.add(buttons[0], 0, 1, 1, 1);
        gridPane.add(buttons[1], 1, 1, 1, 1);
        gridPane.add(buttons[2], 2, 1, 1, 1);
        gridPane.add(symbols[0], 3, 1, 1, 1);
        
        gridPane.add(buttons[3], 0, 2, 1, 1);
        gridPane.add(buttons[4], 1, 2, 1, 1);
        gridPane.add(buttons[5], 2, 2, 1, 1);
        gridPane.add(symbols[1], 3, 2, 1, 1);
        
        gridPane.add(buttons[6], 0, 3, 1, 1);
        gridPane.add(buttons[7], 1, 3, 1, 1);
        gridPane.add(buttons[8], 2, 3, 1, 1);
        gridPane.add(symbols[2], 3, 3, 1, 1);
        
        gridPane.add(buttons[9], 0, 4, 3, 1);
        gridPane.add(symbols[4], 2, 4, 1, 1);
        gridPane.add(symbols[3], 3, 4, 1, 1);
        
        area = new TextArea();
        area.setEditable(false);
        area.setMinSize(400, 300);
        area.setMaxSize(400, 300);
        //Save and Load buttons
        Button save = new Button("Save");
        save.setOnAction(action);
        Button load = new Button("Load");
        load.setOnAction(action);
        save.setMinSize(70, 25);
        save.setMaxSize(70, 25);
        load.setMinSize(70, 25);
        load.setMaxSize(70, 25);
        HBox hbox1 = new HBox(save, load);
        VBox vbox = new VBox(area, hbox1);
        HBox hbox = new HBox(gridPane, vbox);
        
        Scene scene = new Scene(hbox, 700, 340);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    private EventHandler<ActionEvent> actionListener() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof Button) {
                    Button b = (Button) event.getSource();
                    if (b.getText().equals("Save")) {
                        save(area.getText());
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Saved Successfully!");
                        a.show();
                    }
                    else if (b.getText().equals("Load")) {
                        area.setText("");
                        load();
                    }
                    else if (b.getText().equals("=")) {
                        int result = evaluate(tf.getText().trim());
                        area.appendText(tf.getText()+" = "+result);
                        area.appendText("\n");
                        tf.setText("");
                    }
                    else{
                        if(b.getText().equals("+") || b.getText().equals("-")
                                 || b.getText().equals("/") || b.getText().equals("*")){
                            if(tf.getText().endsWith("+ ") || tf.getText().endsWith("- ") ||
                                    tf.getText().endsWith("/ ") || tf.getText().endsWith("* ")){
                                Alert a = new Alert(Alert.AlertType.ERROR);
                                a.setAlertType(Alert.AlertType.ERROR);
                                a.setContentText("Please Enter a Number!");
                                a.show();
                            }
                            else{
                                tf.appendText(" "+b.getText()+" ");
                            }
                        }
                        else{
                             
                        }
                    }
                }
            }
        };
    }

    public static void load() {
        try {
            Scanner sc = new Scanner(new File("ticker.dat"));
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                area.appendText(line);
                area.appendText("\n");
            }
            sc.close();
        } catch (Exception ex) {
            System.out.println("Error:" +ex.getMessage());
        }
    }
    public static void save(String s) {
        try {
            FileWriter fw = new FileWriter("ticker.dat");
            PrintWriter pw = new PrintWriter(fw);
            s = s.substring(0, s.length()-1);
            pw.println(s);
            pw.close();
        } catch (Exception ex) {
            System.out.println("Error:" +ex.getMessage());
        }
    }
    
    
    //stacking - 
    public static int evaluate(String str) {
        char[] recs = str.toCharArray();
        Stack<Integer> nums = new Stack<>();
        Stack<Character> symbols = new Stack<>();
        for (int a = 0; a < recs.length; a++) {
            if (recs[a] == ' ') {
                continue;
            }
            if (recs[a] >= '0' && recs[a] <= '9') {
                String s= "";
                while (a < recs.length && recs[a] >= '0' && recs[a] <= '9') {
                    s += recs[a++];
                }
                nums.push(Integer.parseInt(s));
                a--;
            } else if (recs[a] == '+' || recs[a] == '-' || recs[a] == '*' || recs[a] == '/') {
                
                while (!symbols.empty() && checkPrec(recs[a], symbols.peek())) {
                    nums.push(solve(symbols.pop(), nums.pop(), nums.pop()));
                }
                symbols.push(recs[a]);
            }
        }
        
        while (!symbols.empty()) {
            int num1 = nums.pop();
            int num2 = nums.pop();
            nums.push(solve(symbols.pop(), num1, num2));
        }
        return nums.pop();
    }
    
    public static boolean checkPrec(char symbol1, char symbol2) {
        if ((symbol1 == '*' || symbol1 == '/')  && (symbol2 == '+' || symbol2 == '-')) {
            return false;
        }
        return true;
    }
    
    public static int solve(char symbol, int b, int a) {
        if(symbol == '+'){
            return a+b;
        }
        else if(symbol == '-'){
            return a-b;
        }
        else if(symbol == '*'){
            return a*b;
        }
        else if(symbol == '*'){
            if(b == 0){
                return 0;
            }
            return a/b;
        }
        return 0;
    }  
}