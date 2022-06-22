/**
 * CIS484 Summer 22
 * HW 3
 * Daniella Castro-De La O
 * Mariela Arellano
 */

package arellano_castroHW4;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CalculatorApp extends Application{
    private static TextField txtFormula;
    private static TextArea ticker;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        EventHandler<ActionEvent> action = actionListener();

        //Nodes
        txtFormula = new TextField();
        txtFormula.setEditable(false);//To make text field uneditable
        txtFormula.setMinHeight(50);
        txtFormula.setMaxHeight(50);

        ticker = new TextArea();
        ticker.setEditable(false); 
        ticker.setMinSize(400, 300);
        ticker.setMaxSize(400, 300);

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
        buttons[9].setMinSize(70, 70);
        buttons[9].setMaxSize(70, 70);

        //Create buttons for operators
        Button[] operators = new Button[5];
        operators[0] = new Button("+");
        operators[1] = new Button("-");
        operators[2] = new Button("/");
        operators[3] = new Button("*");
        operators[4] = new Button("=");

        for (int a = 0; a < operators.length; a++) {
            operators[a].setOnAction(action);
            operators[a].setMinSize(70, 70);
            operators[a].setMaxSize(70, 70);
        }
        
        //Clear button added - this button will clear the contents on the textfield
        // will be useful for clearing input mistakes 
        Button btnClear = new Button("C");
        btnClear.setMinSize(70, 70);
        btnClear.setMaxSize(70, 70);

        

        GridPane primaryPane = new GridPane();
        primaryPane.add(txtFormula, 0, 0, 4, 1);
        primaryPane.add(buttons[0], 0, 1, 1, 1);
        primaryPane.add(buttons[1], 1, 1, 1, 1);
        primaryPane.add(buttons[2], 2, 1, 1, 1);
        primaryPane.add(operators[0], 3, 1, 1, 1);

        primaryPane.add(buttons[3], 0, 2, 1, 1);
        primaryPane.add(buttons[4], 1, 2, 1, 1);
        primaryPane.add(buttons[5], 2, 2, 1, 1);
        primaryPane.add(operators[1], 3, 2, 1, 1);

        primaryPane.add(buttons[6], 0, 3, 1, 1);
        primaryPane.add(buttons[7], 1, 3, 1, 1);
        primaryPane.add(buttons[8], 2, 3, 1, 1);
        primaryPane.add(operators[2], 3, 3, 1, 1);

        primaryPane.add(buttons[9], 0, 4, 1, 1);
        primaryPane.add(operators[4], 1, 4, 1, 1);
        primaryPane.add(operators[3], 2, 4, 1, 1);
        primaryPane.add(btnClear,3,4,1,1);

               
        
        Button btnSave = new Button("Save");
        Button btnLoad = new Button("Load");
        btnSave.setMinSize(70, 25);
        btnSave.setMaxSize(70, 25);
        btnLoad.setMinSize(70, 25);
        btnLoad.setMaxSize(70, 25);
        HBox hbox1 = new HBox(btnSave, btnLoad);
        VBox vbox = new VBox(ticker, hbox1);
        HBox hbox = new HBox(primaryPane, vbox);

        Scene scene = new Scene(hbox, 700, 340);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DukeCalc v 0.1");
        
        primaryStage.show();
         
         
        //when save button is clicked, ticker box will save onto .dat file 
        btnSave.setOnAction(e -> {
            try {
                FileOutputStream load = new FileOutputStream("ticker.dat");
                DataOutputStream outputLoad = new DataOutputStream(load);

                outputLoad.writeUTF(ticker.getText());
                outputLoad.writeUTF("");
                outputLoad.write('\n');

                outputLoad.close();
            } catch (Exception ex) {
                System.out.println("Error:" +ex.getMessage());
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
                ticker.setText(pw.readUTF());
            
            }catch (Exception ex) {
                System.out.println("Error:" +ex.getMessage());
            }
            });
      
        
        //btnClear when clicked, will clear the contects of the textfield...
        //allowing user to catch mistakes
        btnClear.setOnAction(e -> {
            txtFormula.clear();
        });
        
        
        // "=" button... will operate by solving formulas
        operators[4].setOnAction(e -> {
            
            int result = calculate(txtFormula.getText());
            ticker.appendText(txtFormula.getText()+" = "+result);
            ticker.appendText("\n");
            txtFormula.setText("");
                    
        });
        
        
        //messed around trying to not use the event handler but doesn't work for me
//        operators[0].setOnAction(e -> {
//            
//            if(tf.getText().endsWith("+ ")){
//                    Alert a = new Alert(Alert.AlertType.ERROR);
//                    a.setAlertType(Alert.AlertType.ERROR);
//                    a.setContentText("Please Enter a Number!");
//                    a.show();
//                }
//                else{
//                    tf.appendText(" "+tf.getText()+" ");
//                }
//                    
//        });
//        
//        operators[1].setOnAction(e -> {
//            
//            if(tf.getText().endsWith("- ")){
//                    Alert a = new Alert(Alert.AlertType.ERROR);
//                    a.setAlertType(Alert.AlertType.ERROR);
//                    a.setContentText("Please Enter a Number!");
//                    a.show();
//                }
//                else{
//                    tf.appendText(" "+tf.getText()+" ");
//                }
//                    
//        });
//        
//        operators[2].setOnAction(e -> {
//            
//            if(tf.getText().endsWith("/ ")){
//                    Alert a = new Alert(Alert.AlertType.ERROR);
//                    a.setAlertType(Alert.AlertType.ERROR);
//                    a.setContentText("Please Enter a Number!");
//                    a.show();
//                }
//                else{
//                    tf.appendText(" "+tf.getText()+" ");
//                }
//                    
//        });


//        operators[4].setOnAction(e -> {
//            
//            if(operators[4].equals("+"))
//            {
//                if(txtFormula.getText().endsWith("* ")){
//                        Alert a = new Alert(Alert.AlertType.ERROR);
//                        a.setAlertType(Alert.AlertType.ERROR);
//                        a.setContentText("Please Enter a Number!");
//                        a.show();
//                    }
//                    else{
//                        txtFormula.appendText(" "+txtFormula.getText()+" ");
//                    }
//                }
//                else{
//                    txtFormula.appendText(operators.getText());
//                }
//                    
//        });
        

   }
    
    private EventHandler<ActionEvent> actionListener() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof Button) {
                    Button operators = (Button) event.getSource();
                                        
                    if(operators.getText().equals("+") || operators.getText().equals("-")
                                 || operators.getText().equals("/") || operators.getText().equals("*")){
                        if(txtFormula.getText().endsWith("+ ") || txtFormula.getText().endsWith("- ") ||
                                txtFormula.getText().endsWith("/ ") || txtFormula.getText().endsWith("* ")){
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setAlertType(Alert.AlertType.ERROR);
                            error.setContentText("Please Enter a Number!");
                            error.show();
                        }
                        else{
                            txtFormula.appendText(" "+operators.getText()+" ");
                        }
                    }
                    else{
                        txtFormula.appendText(operators.getText());
                    }
                }
            }
        };
    }

    
   //methods to caluculate: 
    public static int formula(char operator, int y, int x) {
        if(operator == '+'){
            return x+y;
        }
        else if(operator == '-'){
            return x-y;
        }
        else if(operator == '*'){
            return x*y;
        }        
        else if (operator == '/'){
            return x/y;
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
                String s= "";
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
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}