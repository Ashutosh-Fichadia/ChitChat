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
	private TextField txtip;

	@FXML
	private TextField txtport;

	@FXML
	private TextField txtnm;

	@FXML
	private Button btnconnect;

	static AnchorPane main;
	public String ip;
	public String port;
	public String name;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtip.setText("localhost");
		txtport.setText("4444");
		txtnm.setText("B");
		// TODO Auto-generated method stub
		main = root;
		System.out.println("Load Success");
		btnconnect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				btnconnect.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						//TODO Auto-generated method stub
						Details d = new Details();
						
						ip = txtip.getText();
						System.out.println(ip);
						port = txtport.getText();
						System.out.println(port);
						name = txtnm.getText();
						System.out.println(name);
						d.ip = ip;
						d.nm = name;
						d.port = (Integer.parseInt(port));
						Navigation.navigate("/allpages/home.fxml");
					}
				});
			}
		});
		

	}

}
