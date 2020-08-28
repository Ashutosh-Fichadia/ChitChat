package allpages;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import application.Details;
import application.Main;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

public class Controllerhome implements Initializable {
	@FXML
	private TextField txtport;

	@FXML
	private Button btnconnect;

	@FXML
	private TextArea txtchat;

	@FXML
	private TextField txtmsg;

	@FXML
	private Button btnsend;

	@FXML
	private TextField txtip;

	@FXML
	private Label typing;

	@FXML
	private Label urnm;

	@FXML
	private Label oppnm;

	@FXML
	private Label online;

	@FXML
	private Circle green;

	@FXML
	private Pane gifpane;

	@FXML
	private ImageView imv;

	@FXML
	private PieChart pie;

	@FXML
	private TextField fileselect;

	@FXML
	private Button importfile;

	@FXML
	private Button sendfile;

	@FXML
	private Button btnimport;

	@FXML
	private Button btnsave;

	@FXML
	private Button btnclr;
	
	@FXML
	private Label labrecvfile;
	
	@FXML
	private VBox v1;

	Socket soc;
	DataInputStream dis;
	DataOutputStream dos;
	String unm;
	String msg;
	String oppnm1;
	File f;
	File openf;
	Button btn;

	String ip;
	int port;
	String nm;
	int m = 0, o = 0;
	int i=1;

	private AudioClip audioClips;
	private AudioClip audioClipr;
	private AudioClip audioClipt;
	private AudioClip audioClipsf;
	private AudioClip audioCliprf;
	private AudioClip audioClipcon;

	private HostServices hostServices;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO Auto-generated method stub\
		generateChart(0, 0);

		final URL send = getClass().getResource("/for-sure.mp3");
		audioClips = new AudioClip(send.toExternalForm());

		final URL rec = getClass().getResource("/just-maybe.mp3");
		audioClipr = new AudioClip(rec.toExternalForm());

		final URL type = getClass().getResource("/type.mp3");
		audioClipt = new AudioClip(type.toExternalForm());

		final URL sendf = getClass().getResource("/sendfile.mp3");
		audioClipsf = new AudioClip(sendf.toExternalForm());
		
		final URL recf = getClass().getResource("/recfile.mp3");
		audioCliprf = new AudioClip(recf.toExternalForm());

		final URL conn = getClass().getResource("/windows_8.mp3");
		audioClipcon = new AudioClip(conn.toExternalForm());
		

		Details d = new Details();
		ip = d.ip;
		nm = d.nm;
		port = d.port;
		urnm.setText(nm);
		System.out.println("Client load");
		typing.setVisible(false);

		System.out.println(port + "," + nm + "," + ip);

		connect();

		btnclr.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				clear();
			}
		});
		btnsave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						savechat();

					}
				});

			}
		});
		
		btnimport.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						importchat();

					}
				});

			}
		});
		sendfile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				audioClipsf.play();
				sendFile();
			}
		});
		importfile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				importFile();
			}
		});
		btnsend.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				audioClips.play();
				sendMsg();
				if (txtmsg.getText().isEmpty()) {
					sendTypingAlert("no");
				} else {
					sendTypingAlert("yes");
				}
			}
		});
		txtmsg.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				sendMsg();
				audioClips.play();
			}
		});
		txtmsg.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				if (txtmsg.getText().isEmpty()) {
					sendTypingAlert("no");
				} else {
					audioClipt.play();
					sendTypingAlert("yes");
				}

			}
		});
	}
	
	void showfile(String file) {
		labrecvfile.setVisible(true);
		System.out.println("in show");
		HBox h = new HBox(10);
		h.setPadding(new Insets(5));

		Label lab = new Label();
		lab.setText("" + i);

		Label lab1 = new Label();
		lab1.setText(file);
		lab1.setPrefWidth(320);

		btn = new Button();
		btn.setText("Open");
		btn.setStyle("-fx-background-color:#beddfb;");
		btn.setId(file);
		btn.setPrefHeight(20);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("clicked = " + f.getName());
				
				
				
				
					 
			}
		});

		System.out.println("created");

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				v1.getChildren().add(h);
				h.getChildren().add(lab);
				h.getChildren().add(lab1);
				h.getChildren().add(btn);
				
			}
		});

	}
	void clear() {
		txtchat.setText("");
		generateChart(0, 0);
	}

	void importchat() {
		try {
			FileChooser fc = new FileChooser();

			FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TEXT Files (*.txt)", "*.txt");
			fc.getExtensionFilters().add(txtFilter);

			openf = fc.showOpenDialog(Main.mainstage);

			FileReader fr = new FileReader(openf);
			String data = "";
			int n;
			while ((n = fr.read()) != -1) {
				data = data + (char) n;
			}

			fr.close();

			txtchat.setText(data);
			// lblnm.setText(openf.getName());

		} catch (Exception e) {
			// lblnm.setText("Retry");
			e.printStackTrace();
		}
	}

	void savechat() {
		try {
			if (openf != null) {

				FileWriter fw = new FileWriter(openf);
				fw.write(txtchat.getText());
				// lblnm.setText(openf.getName());
				fw.close();
				// lblmsg.setText("File Saved!");

			} else {
				try {
					FileChooser fc = new FileChooser();

					// FileChooser fc = new FileChooser();

					FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TEXT Files (*.txt)",
							"*.txt");
					fc.getExtensionFilters().add(txtFilter);

					openf = fc.showSaveDialog(Main.mainstage);

					FileWriter fw = new FileWriter(openf);
					fw.write(txtchat.getText());

					fw.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			// lblnm.setText("Retry" + e.getMessage());
			e.printStackTrace();
		}

	}

	void importFile() {
		FileChooser f = new FileChooser();
		openf = f.showOpenDialog(application.Main.mainstage);
		fileselect.setText(openf.getName());

	}

	void sendFile() {
		try {
			byte[] data = Files.readAllBytes(openf.toPath());
			System.out.println("in Send File");
			// String str1 = new String(data);
			dos.writeUTF("104:" + openf.getName());
			System.out.println("Filenm sent");
			// dos.writeUTF("105:" + str1);
			dos.write(data);
			audioClipsf.play();
			System.out.println("File Sent");
			txtchat.appendText("\nFile Sent: " + openf.getName());
			
		} catch (Exception e) {

		}
	}

	void generateChart(int oth, int my) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ObservableList<PieChart.Data> pieData = FXCollections
						.observableArrayList(new PieChart.Data(oppnm1, oth), new PieChart.Data("My", my));
				pie.setData(pieData);
			}
		});

	}

	void sendTypingAlert(String msg) {

		try {
			dos.writeUTF("102:" + msg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Not send");
		}

	}

	void sendMsg() {
		String msg = txtmsg.getText();
		if (!msg.isEmpty()) {
			try {
				dos.writeUTF("101:" + msg);

				txtchat.appendText("\nMe: " + msg);
				txtmsg.setText("");
				m++;
				generateChart(o, m);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Not send");
			}
		}

	}

	void getMsg() {

		// System.out.println("connected succesfully1");
		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					try {
						msg = dis.readUTF();
						System.out.println("Msg from Server :" + msg);

						if (msg.startsWith("101:")) {
							audioClipr.play();
							txtchat.appendText("\n" + oppnm1 + ": " + msg.replace("101:", ""));
							o++;
							generateChart(o, m);
						} else if (msg.startsWith("102:")) {
							msg = msg.replace("102:", "");
							if (msg.equals("yes")) {
								typing.setVisible(true);
							} else {
								typing.setVisible(false);
							}

						} else if (msg.startsWith("103:")) {
							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									msg = msg.replace("103:", "");
									oppnm.setText(msg);
									oppnm1 = msg;

								}
							});
						} else if (msg.startsWith("104:")) {
							msg = msg.replace("104:", "");
							f = new File(msg);
							System.out.println("File Created");
							byte[] bytes = new byte[16 * 1024];
							dis.read(bytes);
							FileOutputStream fos = new FileOutputStream(f);

							fos.write(bytes);
							System.out.println("File Write Success");
							txtchat.appendText("\nFile Recieved: " + f.getName());
							showfile(msg);
							i++;
							audioCliprf.play();
						} 

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}

				}

			}
		}).start();

	}

	void sendnm() {
		try {
			dos.writeUTF("103:" + nm);
			System.out.println("Send nm true:" + nm);
		} catch (Exception e) {
			System.out.println("sendmnm failed");
			e.printStackTrace();
		}
	}

	void connect() {
		try {
			System.out.println("in connect0");
			soc = new Socket(ip, port);
			dis = new DataInputStream(soc.getInputStream());
			dos = new DataOutputStream(soc.getOutputStream());

			getMsg();
			sendnm();

		} catch (Exception e) {

		}

	}

}
