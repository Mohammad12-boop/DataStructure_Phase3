package project_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import recordClasses.DateRecord;
import recordClasses.DistrictRecord;
import recordClasses.MartyrRecord;
import sLinkedList.SLinkedList;
import sLinkedList.SNode;

public class LoadFx extends Application { // Class Fx for the First screen(Load file).

	private Label lblLoad, lblResult;
	private Button btLoad;
	DateFx dateS=new DateFx();
	SLinkedList<DistrictRecord> districts=new SLinkedList<>();

	public void start(Stage primaryStage) {

		FileChooser fileChooser = new FileChooser();

		VBox pane = new VBox(20);
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setAlignment(Pos.CENTER);
		lblLoad = new Label("Load the file here (.csv/.txt):");
		lblLoad.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		btLoad = new Button("Load");
		btLoad.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		lblResult = new Label();
		lblResult.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		pane.getChildren().addAll(lblLoad, btLoad, lblResult);
		
		Scene scene2 = new Scene(dateS.getPane(), 1100, 700);

		btLoad.setOnAction(e -> {

			System.out.println("Load button clicked !\n"); // This is the fileChooser for the user what will he choose
															// (.csv\.txt).
			fileChooser.setTitle("Open File");
			fileChooser.setInitialDirectory(new File("C:\\"));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel file", "*.csv"),
					new FileChooser.ExtensionFilter("Text file", "*.txt"));
			File selectedFile = fileChooser.showOpenDialog(primaryStage);

			try {

				Scanner sc = new Scanner(selectedFile);
				sc.nextLine();

				while (sc.hasNextLine()) {

					
					String row[] = sc.nextLine().split(",");
					if (row.length==6) {
						
						String name = row[0];
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
						LocalDate date = LocalDate.parse(row[1], formatter);
	
						int age;
						if (row[2] == "") {
	
							age = 0;
						} else {
	
							age = Integer.parseInt(row[2]);
						}
						String location = row[3];
						String district = row[4];
						String gender = row[5];
	
						if (age != 0) {
	
							MartyrRecord martyr = new MartyrRecord(name, age, location, district, gender);
							DateRecord d = new DateRecord(date);
							DistrictRecord dis=new DistrictRecord(district);
								
							districts.insert(dis);
							districts.find(dis).getData().getLocations().insert(location);
							
							if (dateS.getDates().find(d)==null) {
								
								d.getMartyrs().insert(martyr);
								dateS.getDates().add(d);
								
							}else {
								
								dateS.getDates().find(d).getData().getMartyrs().insert(martyr);
							}
							
						
						}
					
					}
				}
						
				SNode <DistrictRecord> curr1=districts.getHead();
				while (curr1 != null) {
					
					dateS.districts.getItems().add(curr1.getData());
					curr1=curr1.getNext();
				}
				
//------------------------------------------------------------------

//				System.out.println("\n-----------------------------------------------------\n");
//				dateS.getDates().traverse();
//
//				System.out.println("\n-----------------------------------------------------\n");
//
//
//				for (int i = 0; i < dateS.getDates().getM()-5500; i++) {
//					
//					if (dateS.getDates().getTable()[i].getData() != null) {
//						
//						System.out.print(i + " " + dateS.getDates().getTable()[i] + " - \n");
//						dateS.getDates().getTable()[i].getData().getMartyrs().traverseInOrder();
//						System.out.println("\n-----------------------------------------------------\n");
//					}
//				
//					
//
//				}
				
				System.out.println("\n-----------------------------------------------------\n");

				System.out.println(dateS.getDates().getM());  
				System.out.println(dateS.getDates().getSize());
						

//-------------------------------------------------------------------

				lblResult.setText("Load file successfully\n" + selectedFile.getPath());

				System.out.println("Date Screen !\n"); // when the user clicked the load button it will transport to
															// the District screen.
				primaryStage.close();

				primaryStage.setTitle("Date_Screen");
				primaryStage.setScene(scene2);
				primaryStage.show();

				sc.close();

			} catch (FileNotFoundException ex) {

				ex.printStackTrace();
			}

		});

		Scene scene = new Scene(pane, 400, 200);
		primaryStage.setTitle("Load martyrs");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {

		launch(args);
	}
	

}
