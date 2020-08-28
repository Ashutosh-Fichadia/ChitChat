package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SampleController implements Initializable {

	

	@FXML
	private AnchorPane root;

	@FXML
	private Label lblclient;



	@FXML
	private TextField txtport;

	@FXML
	private TextField txtnm;

	@FXML
	private Button btnconnect;

	static AnchorPane main;
	
	public String nm;
	public String port;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		txtport.setText("4444");
		txtnm.setText("A");
		main = root;
		System.out.println("Load Success");
		btnconnect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				port = txtport.getText();
				nm = txtnm.getText();
				Details d = new Details();
				d.nm = nm;
				d.port = (Integer.parseInt(port));
				
				Navigation.navigate("/allpages/home.fxml");
			}
		});
		

	}

}
