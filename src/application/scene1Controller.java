package application;


import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class scene1Controller implements Initializable {

    @FXML
    private ListView<String> monthlyListView;
    
    @FXML
    Button submit,incomeButton,expenseButton,homeButton,savingButton;
    
    @FXML
    Pane incomePane, expensePane, savingPane, sideBarPane;
    
    @FXML
    Button submitFinish;
    
    @FXML
    private BarChart<String, Number> barchartIn;
    @FXML
    private BarChart<String, Number> barchartExp;
    
    private TableView<DataInput> table;
    
    // Array to hold all the months, this will be used by the listView
    String[] months = {"January", "February", "March", "April", "May", "June", "July"
            , "August", "September", "October", "November", "December"};
    
    // This variable will hold the month selected from the listener that is in the listView model
    String monthSelected;
  
    /**
     * The initialize method is called after all @FXML annotated members have been injected.
     */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// This will set the set the months array values to the listView model
		monthlyListView.getItems().addAll(months);
		
		// Adding a listener to provide an event for certain items picked
		monthlyListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			// Implementing the listener so when month is selected in the listView, the month name is saved
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				monthSelected = monthlyListView.getSelectionModel().getSelectedItem();							
			}	
		});
		
		// This lambda function implements an event handler when the submit button is clicked
		// Once a month has been selected and submit is clicked, it will prompt a new scene
		submit.setOnAction(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Main2.fxml")); //Creating an instance
				Parent root =  loader.load(); // Loading the file and assigned it to root node
				scene2controller controller = loader.getController(); // Getting the controller from that file
				controller.display(monthSelected); //calling the controller display method and setting the month chosen as a label
				Stage stage = new Stage(); // Creating a new Stage to hold the new scene from the Main2 fxml file
				stage.setScene(new Scene(root)); // setting that root (main fxml) to the stage
				stage.setX(450); 
				stage.setY(200);
				stage.show(); // displaying that scene
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
		
		// This lambda function implements an event handler when the finish button is clicked
		// Once income and expense are inputed by the user, this will call the savings function and return a tableView of the savings
		submitFinish.setOnAction(e -> {
			// Creating an object to call the savings methods
			Driver data = new Driver();
			data.savings();
			
			// Setting up the columns
			// The dataType for this tableColum accepted which objects "DataInput" and String is the the type of data in that column
			TableColumn<DataInput,String> monthColumn =  new TableColumn<>("Months"); // This column is named months
			monthColumn.setMinWidth(200);
			monthColumn.setCellValueFactory(new PropertyValueFactory<>("stringValue")); // this will group all the values names stringValue found in that object
			
			// The dataType for this tableColum accepted which objects "DataInput" and Double is the the type of data in that column
			TableColumn<DataInput,Double> valueColumn =  new TableColumn<>("Savings"); // This column is named savings
			valueColumn.setMinWidth(100);
			valueColumn.setCellValueFactory(new PropertyValueFactory<>("doubleValue")); // this will group all the values named doubleValue found in that object 
			
			// Creating a new table
			table = new TableView<>();
			
			// Getting all the elements from getSavings methods (Observable list object)
			table.setItems(getSavings());
			
			
			// getting the columns implemented above, and putting them in the table
			table.getColumns().addAll(monthColumn,valueColumn);
			
			//Creating a new stage 
			Stage stage = new Stage();
			
			// setting the stage with a new scene that contains the table
			stage.setScene(new Scene(table));
			
			// Displaying the scene
			stage.show();
			
		});
		
		incomeButton.setOnAction(event -> {
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("Months");
			for(int i = 0; i < Driver.months.length; i++) {
				series.getData().add(new XYChart.Data<>(Driver.months[i],Driver.income.get(i)));
			}
			barchartIn.getData().add(series);
			incomePane.toFront();
			
		});
		
		expenseButton.setOnAction(event -> {
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("Months");
			for(int i = 0; i < Driver.months.length; i++) {
				series.getData().add(new XYChart.Data<>(Driver.months[i],Driver.expense.get(i)));
			}
			barchartExp.getData().add(series);
			expensePane.toFront();
			
		});
		
		homeButton.setOnAction(event -> {
			try {
			// get the current stage after the button is pressed
		    Stage homeStage = (Stage) savingButton.getScene().getWindow();

		    // this will close the current stage
		    homeStage.close();
			
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml")); //Creating an instance
			Parent root =  loader.load(); // Loading the file and assigned it to root node
			Stage stage = new Stage(); // Creating a new Stage to hold the new scene from the Main2 fxml file
			stage.setScene(new Scene(root)); // setting that root (main fxml) to the stage
			stage.show(); // displaying that scene
		} catch(IOException e) {
			e.printStackTrace();
		}});
		
		savingButton.setOnAction(event -> {
			savingPane.toFront();
			sideBarPane.toFront();
		});
		
	}
	
	
	/**
	 * This method will load in all the data that we want to implement into the table
	 * @return months (observable list)
	 */
	public ObservableList<DataInput> getSavings(){
		ObservableList<DataInput> months = FXCollections.observableArrayList(); // List where we can store java object inside 
		
		// While Hashmap has information
		 Iterator<Entry<String, Double>> it = Driver.monthSavings.entrySet().iterator(); // iterating through map entry sets has an iterator object
		    while (it.hasNext()) {
		        Map.Entry<String,Double> input = (Map.Entry<String,Double>)it.next();  // Map Entry gives access to HashMap entries through methods as getKey and getValue
		        String value = (String)input.getKey();
		        Double value2 = (Double)input.getValue();
		        months.add(new DataInput(value,value2)); // making new objects with the inputs, and adding them to observablelist
		        //it.remove(); // avoids a ConcurrentModificationException
		    }
		
		return months; // return the observable list 
	}
	
}






