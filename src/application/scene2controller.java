package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class scene2controller {

    @FXML
    private Label monthChosen;
    
    @FXML
    public Button closeButton;
    
    @FXML
    private TextField incomeEntry;
    
    @FXML 
    private TextField expenseEntry;
    
    private double income;
    private double expense;
    
    /**
     * This method will set the label in scene2 with the month that was chosen
     * in the scene before
     * @param month, input received from scene1
     */
    public void display(String month) {
    	monthChosen.setText(month);
    }
    
    /**
     * This method will save all the input values submitted by the user and pass them to the
     * main driver where the calculation will be done
     * Once done, it will close the window
     * @param e, even handler on the scene when the submit button is clicked
     * @throws IOException
     */
    public void submitAction(ActionEvent e) throws IOException{
    	income = Double.parseDouble(incomeEntry.getText());
    	expense = Double.parseDouble(expenseEntry.getText());
    	Driver setInput = new Driver();
    	setInput.setter(income, expense);
    	
    	// get the current stage after the button is pressed
    	Stage stage = (Stage) closeButton.getScene().getWindow();

    	// this will close the current stage
    	stage.close();
    }
  
}
