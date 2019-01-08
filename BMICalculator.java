/*
 * For CSCI 112,Unit 8 - JavaFX IPO Assignment
 * Author: Huajing Lin
 * 3/23/2017
 */
package bmi.calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.HPos.RIGHT;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BMICalculator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // create a single stage for the project
        primaryStage.setTitle("Body mass index (BMI) Calculator");
        
        // use a GridPane layout object
        GridPane grid = new GridPane();
        
        // set the characteristics fro the GridPane
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // create a title for the Scene and add it to the GridPane
        final Text scenetitle = new Text("Body mass index (BMI) Calculator");
        scenetitle.setId("title");
        grid.add(scenetitle, 0, 0, 5, 1);

        // createa a label and input textfield for weight    
        final Label lWeight = new Label("Weight:");
        grid.add(lWeight, 0, 1);

        final TextField tfWeight = new TextField();
        tfWeight.setId("tfWeight");
        grid.add(tfWeight, 1, 1, 3, 1);
        
        final Label lLb = new Label();
        lLb.setId("lLb");
        lLb.setText("lb");
        grid.add(lLb, 4, 1);

        // createa a label and input textfield for height
        final Label lHeight = new Label("Height:");
        grid.add(lHeight, 0, 2);

        final TextField tfFeet = new TextField();
        tfFeet.setId("tfFeet");
        grid.add(tfFeet, 1, 2);

        final Label lFt = new Label();
        lFt.setId("lFt");
        lFt.setText("ft");
        grid.add(lFt, 2, 2);
        
        final TextField tfInch = new TextField();
        tfInch.setId("tfInch");
        grid.add(tfInch, 3, 2);

        final Label lIn = new Label();
        lIn.setId("lIn");
        lIn.setText("in");
        grid.add(lIn, 4, 2);
        
        // create a button to activate the interface
        final Button btn = new Button("Get BMI Value");
        grid.add(btn, 3, 4, 2, 1);
        grid.setHalignment(btn, RIGHT);
        
        // add a label for the result to the grid
        final Label lResult = new Label();
        lResult.setId("lResult");
        lResult.setText("A measure of body fat in adults");
        grid.add(lResult, 0, 6, 5, 1);
        grid.setHalignment(lResult, RIGHT);
        
        // the event handler for the button is defined as an onAction parameter
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //Check whether the user's input is a number
                if(tfWeight.getText().length()==0){
                    lResult.setText("Please enter your weight!");
                    return;
                }
                else if(!tfWeight.getText().matches("-?\\d+(\\.\\d+)?")){
                    lResult.setText("Your weight is not a number!");
                    return;
                }
                if(tfFeet.getText().length()==0){
                    lResult.setText("Please enter your height!");
                    return;
                }
                else if(!tfFeet.getText().matches("-?\\d+(\\.\\d+)?")){
                    lResult.setText("Your height is not a number!");
                    return;
                }
                if(tfInch.getText().length()==0){
                    lResult.setText("Please enter inch!");
                    return;
                }
                else if(!tfInch.getText().matches("-?\\d+(\\.\\d+)?")){
                    lResult.setText("Inch is not a number!");
                    return;
                }
                                
                // declare variabes for weight and height and get data from the text fields
                Double weight = Double.valueOf( tfWeight.getText() );
                Double height = Double.valueOf( tfFeet.getText() ) * 12 
                              + Double.valueOf( tfInch.getText() );
                
                if(weight > 700.0){
                    lResult.setText("Not the weight of mankind!");
                    return;
                }
                
                if(height > 100){
                    lResult.setText("Not the height of mankind!");
                    return;
                }                
                
                Double BMI = 0.0;
                
                //Multiply the weight in pounds by 0.45 (the metric conversion factor)
                weight *= 0.45; // convert to kg
                
                //Multiply the height in inches by 0.025 (the metric conversion factor)
                height *= 0.025; // convert to meter
                                
                //Square the height
                height *= height;
                                
                //Divide the answer
                BMI = weight / height;                
                System.out.printf("BMI:%9.2fm%n", BMI);
                
                // declare a String for the resulting output
                String msg = "";
                
                // create an output String from the data using String format codes
                if(BMI < 18.5)
                    msg = String.format("BMI:%9.2f, Underweight BMI", BMI);
                else if(BMI < 25.0)
                    msg = String.format("BMI:%9.2f, Normal BMI", BMI);
                else
                    msg = String.format("BMI:%9.2f, Overweight BMI", BMI);
                
                // set the tedt field for the result using the output String
                lResult.setText(msg);
            } // end handle()
        });

        // instantiate a Scene of the desired size
        Scene scene = new Scene(grid, 500, 400);
        
        // put the Scene on the Stage
        primaryStage.setScene(scene);
        
        // associate the Scene with a STYLE SHEET
        scene.getStylesheets().add(BMICalculator.class.getResource("BMI.css").toExternalForm());
     
        // make the stage visible
        primaryStage.show();
    }// end start()

    
    public static void main(String[] args) {
        launch(args);
    }
    
}// end class
