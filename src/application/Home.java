package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Home implements Initializable {

	@FXML
	Button savingButton,incomeButton,expenseButton,mainButtonPane;
	
	@FXML
	Pane expensePane, incomePane, mainPane, sideBar;
	
	@FXML
	private WebView webView;
	
	private WebEngine engine;
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		engine = webView.getEngine();
		webView.setZoom(.50);
		loadPage();
		
		savingButton.setOnAction(event -> {
			try {
				// get the current stage after the button is pressed
		    	Stage homeStage = (Stage) savingButton.getScene().getWindow();

		    	// this will close the current stage
		    	homeStage.close();
		   
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml")); // creating an instance
				Parent root = loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			} // Loading the file and assigned it to root node
		
		});
		
		incomeButton.setOnAction(event ->{
			incomePane.toFront();
		});
		
		expenseButton.setOnAction(event -> {
			expensePane.toFront();
		});
		
		mainButtonPane.setOnAction(event -> {
			sideBar.toFront();
			mainPane.toFront();
		});
		
}
	public void loadPage() {
		engine.load("https://finviz.com");
	}
}
