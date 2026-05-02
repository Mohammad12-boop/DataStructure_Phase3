package project_3;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import avl_tree.Avl_tree;
import avl_tree.TNode;
import hashing.QuadraticOHash;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import linkedQueue.LinkedQueue;
import linkedQueue.StackQueue;
import recordClasses.DateRecord;
import recordClasses.DistrictRecord;
import recordClasses.MartyrRecord;



public class DateFx {

	private BorderPane pane;
	private QuadraticOHash<DateRecord> dateHash;
	TextArea Result;
	MartyrFx martyrS = new MartyrFx();
	ComboBox<DistrictRecord>districts=new ComboBox<>();

	
	public DateFx() {
		
		Text text;
		Label lblInsert, lblUpdate, lblDelete, lblPrint, lblLoadMartyrs;
		Button btInsert, btUpdate, btDelete, btPrint, btLoadMartyrs;
		DatePicker tfInsert, tfUpdate, tfDelete;
		Alert alertC, alertW;
		
		dateHash=new QuadraticOHash<>(11);
		
		StackQueue<DateRecord> list1 = new StackQueue<>();
		StackQueue<DateRecord> list2 = new StackQueue<>();
		
		alertC = new Alert(AlertType.CONFIRMATION);
		alertC.setTitle("Confirming");

		alertW = new Alert(AlertType.WARNING);
		alertW.setTitle("Warning");
		
		Scene scene2 = new Scene(martyrS.getPane(), 1100, 600);

//-------------------------------------------------------------------
		// This pane in the top of borderPane.

		StackPane pane1 = new StackPane();
		pane1.setPadding(new Insets(11, 12, 13, 14));
		text = new Text("Dates Screen");
		text.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 40));
		pane1.getChildren().add(text);

//----------------------------------------------------------------
		// This pane in the left of borderPane.

		GridPane gp1 = new GridPane();
		gp1.setPadding(new Insets(11, 12, 13, 14));
		gp1.setHgap(10);
		gp1.setVgap(10);

		tfInsert = new DatePicker();
		tfInsert.setEditable(false);
		btInsert = new Button("Insert");
		btInsert.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
		lblInsert = new Label("Insert a new district:");
		lblInsert.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		gp1.add(lblInsert, 0, 0);
		gp1.add(tfInsert, 0, 1);
		gp1.add(btInsert, 0, 2);
		
		tfDelete = new DatePicker();
		tfDelete.setEditable(false);
		btDelete = new Button("Delete");
		btDelete.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
		lblDelete = new Label("Delete a district:");
		lblDelete.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		gp1.add(lblDelete, 0, 3);
		gp1.add(tfDelete, 0, 4);
		gp1.add(btDelete, 0, 5);
		
		GridPane gp1_2 = new GridPane();
		gp1_2.setPadding(new Insets(11, 12, 13, 14));
		gp1_2.setAlignment(Pos.CENTER);
		gp1_2.setHgap(10);
		gp1_2.setVgap(10);
		gp1_2.setVisible(false);
		Label lblnewDate = new Label();
		lblnewDate.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		DatePicker tfnewDate = new DatePicker();
		tfnewDate.setEditable(false);
		Button btnewDate = new Button("Update");
		gp1_2.add(lblnewDate, 0, 0);
		gp1_2.add(tfnewDate, 0, 2);
		gp1_2.add(btnewDate, 0, 3);
		
		tfUpdate = new DatePicker();
		tfUpdate.setEditable(false);
		btUpdate = new Button("Update");
		btUpdate.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
		lblUpdate = new Label("Update a district:");
		lblUpdate.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		gp1.add(lblUpdate, 0, 6);
		gp1.add(tfUpdate, 0, 7);
		gp1.add(btUpdate, 0, 8);
		
		VBox pane2 = new VBox(10);
		pane2.setPadding(new Insets(11, 12, 13, 14));
		pane2.getChildren().addAll(gp1,gp1_2);
	
//--------------------------------------------------------------
		// This pane in the right of borderPane.

		GridPane pane3 = new GridPane();
		pane3.setPadding(new Insets(11, 12, 13, 14));
		pane3.setHgap(10);
		pane3.setVgap(10);

		lblPrint = new Label("Print the table of dates\n(including/excluding empty):");
		lblPrint.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		btPrint = new Button("Print");
		btPrint.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
		RadioButton inc=new RadioButton("including");
		inc.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
		RadioButton exc=new RadioButton("excluding");
		exc.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
		ToggleGroup group=new ToggleGroup();
		inc.setToggleGroup(group);
		exc.setToggleGroup(group);
		HBox ch=new HBox(10);
		ch.setPadding(new Insets(11, 12, 13, 14));
		ch.setAlignment(Pos.CENTER);
		ch.getChildren().addAll(inc,exc);
		pane3.add(lblPrint, 0, 1);
		pane3.add(ch, 0, 2);
		pane3.add(btPrint, 0, 3);
		
		lblLoadMartyrs = new Label("Load martyrs in date:");
		lblLoadMartyrs.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		btLoadMartyrs = new Button("Load");
		lblLoadMartyrs.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
		pane3.add(lblLoadMartyrs, 0, 4);
		pane3.add(btLoadMartyrs, 0, 5);

		Label lblSave=new Label("Save:");
		lblSave.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		Button btSave=new Button("Save");
		btSave.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
		pane3.add(lblSave, 0, 6);
		pane3.add(btSave, 0, 7);
	
//--------------------------------------------------------------
		// This pane in the bottom of borderPane.

		VBox nav = new VBox(10);
		nav.setPadding(new Insets(11, 12, 13, 14));
		nav.setAlignment(Pos.CENTER);
		Button up = new Button("  ٨  ");
		Button down = new Button("  ٧  ");
		nav.getChildren().addAll(up, down);
			
		HBox pane4 = new HBox(10);
		pane4.setPadding(new Insets(11, 12, 13, 14));
		pane4.setAlignment(Pos.CENTER);
		TextField dates = new TextField("Dates");
		dates.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		dates.setEditable(false);
		pane4.getChildren().addAll(dates, nav);

//----------------------------------------------------------------
		// This pane in the center of borderPane.

		StackPane pane5 = new StackPane();
		pane5.setPadding(new Insets(11, 12, 13, 14));
		Result = new TextArea();
		Result.setEditable(false);
		Result.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		pane5.getChildren().add(Result);
		
//----------------------------------------------------------------
		// Add the all previous panes to borderPane(base pane).

		pane = new BorderPane();
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setTop(pane1);
		pane.setLeft(pane2);
		pane.setRight(pane3);
		pane.setBottom(pane4);
		pane.setCenter(pane5);
		
//-------------------------------------------------------------------------------------
		// This to insert a new Date (it must a not exist date to add it).

		btInsert.setOnAction(e -> {

			System.out.println("Insert Date Button clicked !\n");
			
			if (tfInsert.getValue()!=null) {
			
				LocalDate date = tfInsert.getValue();
				DateRecord d = new DateRecord(date);
				
				if (dateHash.find(d)==null) { 
					
					dateHash.add(d);
					Result.setText(date+" Added date successfully!!\n\n");
					
					for (int i = 0; i < dateHash.getM(); i++) 
						if (dateHash.getTable()[i].getData() != null)
							Result.setText(Result.getText() + i + " " + dateHash.getTable()[i] + " - \n");
					
					dates.setText("Dates");
					list1.clear();
					list2.clear();
					
				}else {
					
					Result.setText(date+" this date is already exist!!\n");
				}
				

			}else {
				
				Result.setText("Enter the date to insert!!\n");
			}
			
			tfInsert.setValue(null);
		});
		
//----------------------------------------------------------------------------------------
		// This to update a Date name.

		btUpdate.setOnAction(e -> {

			System.out.println("Update Date Butoon clicked !\n");
			
			if (tfUpdate.getValue()!=null) {
			
				LocalDate date = tfUpdate.getValue();
				DateRecord d = new DateRecord(date);
				
				if (dateHash.find(d)!=null) { 
					
					lblnewDate.setText("Update name for " + date);
					gp1_2.setVisible(true); // --------------------------------------> this gridPane is not visible but when
											// we click update button and the date is found it will visible to update.
					btnewDate.setOnAction(eh -> {
	
						if (tfnewDate.getValue()!=null) {
						
							LocalDate newDate = tfnewDate.getValue();
							DateRecord dateN = new DateRecord(newDate);
		
							if (dateHash.find(dateN) == null) {
		
								alertW.setHeaderText("if you update it, it will change his information !! ");
		
								Optional<ButtonType> result1 = alertW.showAndWait();
		
								if (result1.isPresent() && result1.get() == ButtonType.OK
										&& result1.get() != ButtonType.CLOSE) {
		
									alertC.setHeaderText("Are you sure to update this date from ("
											+ d.getDate() + ") to (" + dateN.getDate() + ") ?!");
									alertC.setContentText("if Yes click (Ok) button \nif No click (Cancel) button");
		
									Optional<ButtonType> result = alertC.showAndWait();
		
									if (result.isPresent() && result.get() == ButtonType.OK
											&& result.get() != ButtonType.CLOSE) {
		
										dateHash.add(dateN);
										dateHash.find(dateN).getData().setMartyrs(dateHash.find(d).getData().getMartyrs());
										dateHash.delete(d).getData();
										
										dates.setText("Dates");
										list1.clear();
										list2.clear();
										
										Result.setText("Update " + date + " to "+newDate+ " successfully!!\n\n"+newDate+":\n\n");
										
										traverseInOrder(dateHash.find(dateN).getData().getMartyrs());
										Result.setText(Result.getText()+"\n-----------------------------------------------------------\n");
										
										for (int i = 0; i < dateHash.getM(); i++) 
											if (dateHash.getTable()[i].getData() != null)
												Result.setText(Result.getText() + i + " " + dateHash.getTable()[i] + " - \n");
		
										gp1_2.setVisible(false); // -----------------------> when we finish updated the date
																	// and click update it will not visible again.
		
									} else {
		
										Result.setText("");								
		
										gp1_2.setVisible(false); // -----------------------> when we finish updated the date
																	// and click update it will not visible again.
									}
		
								}
		
							}else {
								
								if (!date.equals(newDate)) {
								
									alertW.setHeaderText("if you update it, it will change his information !! ");
			
									Optional<ButtonType> result1 = alertW.showAndWait();
			
									if (result1.isPresent() && result1.get() == ButtonType.OK
											&& result1.get() != ButtonType.CLOSE) {
			
										alertC.setHeaderText("Are you sure to update this date from ("
												+ d.getDate() + ") to (" + dateN.getDate() + ") ?!");
										alertC.setContentText("if Yes click (Ok) button \nif No click (Cancel) button");
			
										Optional<ButtonType> result = alertC.showAndWait();
			
										if (result.isPresent() && result.get() == ButtonType.OK
												&& result.get() != ButtonType.CLOSE) {
									
									
													if (dateHash.find(d).getData().getMartyrs().getRoot() == null) return;
													
													LinkedQueue<TNode<MartyrRecord>> queue = new LinkedQueue<>();
													
													queue.enqueue(dateHash.find(d).getData().getMartyrs().getRoot());
													
													while (!queue.isEmpty()) {
														
														TNode<MartyrRecord> curr = queue.dequeue();
														
														dateHash.find(dateN).getData().getMartyrs().insert(curr.getData());
														
														if (curr.hasLeft()) queue.enqueue(curr.getLeft());
														if (curr.hasRight()) queue.enqueue(curr.getRight());
													}
													
													dateHash.delete(d).getData();
													
													dates.setText("Dates");
													list1.clear();
													list2.clear();
													
													Result.setText("Update " + date + " to "+newDate+ " (exist date) successfully!!\n\n"+newDate+":\n\n");
													traverseInOrder(dateHash.find(dateN).getData().getMartyrs());
													Result.setText(Result.getText()+"\n-----------------------------------------------------------\n");
													
													for (int i = 0; i < dateHash.getM(); i++) 
														if (dateHash.getTable()[i].getData() != null)
															Result.setText(Result.getText() + i + " " + dateHash.getTable()[i] + " - \n");
													
													gp1_2.setVisible(false);
										}else {
											
											Result.setText("");											
			
											gp1_2.setVisible(false); // -----------------------> when we finish updated the date
																		// and click update it will not visible again.
										}
									}
									
								}else {
									
									Result.setText("Can't accept, You entered the same date!!\n");
									
									gp1_2.setVisible(false); // -----------------------> when we finish updated the date
																// and click update it will not visible again.
								}
							
							}
						
						}else {
							
							Result.setText("Enter the newdate to update!!\n");
							gp1_2.setVisible(false); // -----------------------> when we finish updated the date
														// and click update it will not visible again.
						}
						
						tfnewDate.setValue(null);
					});
					
						
				}else {
					
					Result.setText(date+" This date is not found to update !!\n");
					
				}
			
			}else {
				
				Result.setText("Enter the date to update!!\n");
			}
			
			tfUpdate.setValue(null);
		});
		
//--------------------------------------------------------------------------------------
		// This to delete a Date (it must a exist Date to delete it).

		btDelete.setOnAction(e -> {

			System.out.println("Delete Date Button clicked !\n");
			
			if (tfDelete.getValue()!=null) {
				
				LocalDate date = tfDelete.getValue();
				DateRecord d = new DateRecord(date);
				
				if (dateHash.find(d)!=null) { 
				
					if (!dateHash.find(d).getFlag().equals("D")) {
					
						alertW.setHeaderText("if you delete it, you can't return to it again !! ");
	
						Optional<ButtonType> result1 = alertW.showAndWait();
	
						if (result1.isPresent() && result1.get() == ButtonType.OK && result1.get() != ButtonType.CLOSE) {
	
							alertC.setHeaderText("Are you sure to delete (" + d.getDate() + ") date ?!");
							alertC.setContentText("if Yes click (Ok) button \nif No click (Cancel) button");
	
							Optional<ButtonType> result = alertC.showAndWait();
							if (result.isPresent() && result.get() == ButtonType.OK && result.get() != ButtonType.CLOSE) {
						
								dateHash.delete(d);
								
								dates.setText("Dates");
								list1.clear();
								list2.clear();
								
								Result.setText(date+" Deleted date Successfully !!\n"); // it will show the list of Date after we deleted.
								for (int i = 0; i < dateHash.getM(); i++) 
									if (dateHash.getTable()[i].getData() != null)
										Result.setText(Result.getText() + i + " " + dateHash.getTable()[i] + " - \n");
								
								
							}else {
								
								Result.setText("");					
	
							}
						}
					
					}else {

						Result.setText(date+" This date is already deleted!!");
						
					}
						
				}else {

					Result.setText(date+" This date is not found to delete!!");
				}
				
			}else {
				
				Result.setText("Enter the date to delete!!\n");
			}
			
					
			tfDelete.setValue(null);
		});
		
//----------------------------------------------------------------------------------------
		//This to Print the Hash table from top to bottom including/excluding the empty spots.
		
		btPrint.setOnAction(e-> {
			
			System.out.println("Print Button clicked !\n");
			
			if (!inc.isSelected() && !exc.isSelected()) {
				
				Result.setText("Select one of the choices (including Or excluding)!!\n");
				
			}else if (inc.isSelected()) {
				
				Result.setText("The Table of dates including the empty spots: \n\n");
				
				for (int i = 0; i < dateHash.getM(); i++) {
					
					if (dateHash.getTable()[i].getData() != null) {
						
						Result.setText(Result.getText() + i + " " + dateHash.getTable()[i] + " - \n");
					}else {
						
						Result.setText(Result.getText() + i + " null - \n");
					}
				}
				
			}else {
					
				Result.setText("The Table of dates excluding the empty spots: \n\n");
				
				for (int i = 0; i < dateHash.getM(); i++) 
					if (dateHash.getTable()[i].getData() != null)
						Result.setText(Result.getText() + i + " " + dateHash.getTable()[i] + " - \n");
			}
			

			inc.setSelected(false);
			exc.setSelected(false);
		});
		
//-----------------------------------------------------------------------
		// This to navigate throw districts to the left.

		down.setOnAction(e -> {

			if (dates.getText().equalsIgnoreCase("Dates")) {

		    	getList1(list1);
		    	
				dates.setText(list1.peek().getDate().toString());
				
				int totalM=list1.peek().getMartyrs().size();
				int avgAge=averageAge(list1.peek().getMartyrs());
				String maxDistrict=districtMaxMartyrs(list1.peek().getMartyrs());
				String maxLocation=locationMaxMartyrs(list1.peek().getMartyrs());

				Result.setText(dates.getText() + ":\n1) Total number of martyrs in this date: " + totalM +"\n"
						+ "2) Average martyrs ages: "+avgAge+"\n"
						+ "3) The youngest martyr: \n" + young(list1.peek().getMartyrs())+"\n"
						+ "4) The oldest martyr: \n" + old(list1.peek().getMartyrs())+"\n"
						+ "5) District that has the maximum martyrs: "+maxDistrict+"\n"  
						+ "6) Location that has the maximum martyrs: "+maxLocation+"\n" );
				
				list2.push(list1.pop());

			} else if (list1.peek() != null) {

				if (dates.getText().equals(list1.peek().getDate().toString())) {

					list2.push(list1.pop());
					dates.setText(list1.peek().getDate().toString());

					int totalM=list1.peek().getMartyrs().size();
					int avgAge=averageAge(list1.peek().getMartyrs());
					String maxDistrict=districtMaxMartyrs(list1.peek().getMartyrs());
					String maxLocation=locationMaxMartyrs(list1.peek().getMartyrs());
							
					Result.setText(dates.getText() + ":\n1) Total number of martyrs in this date: " + totalM +"\n"
							+ "2) Average martyrs ages: "+avgAge+"\n"
							+ "3) The youngest martyr: \n" + young(list1.peek().getMartyrs())+"\n"
							+ "4) The oldest martyr: \n" + old(list1.peek().getMartyrs())+"\n"
							+ "5) District that has the maximum martyrs: "+maxDistrict+"\n" 
							+ "6) Location that has the maximum martyrs: "+maxLocation+"\n" );
		
					list2.push(list1.pop());

				} else {

					dates.setText(list1.peek().getDate().toString());

					int totalM=list1.peek().getMartyrs().size();
					int avgAge=averageAge(list1.peek().getMartyrs());
					String maxDistrict=districtMaxMartyrs(list1.peek().getMartyrs());
					String maxLocation=locationMaxMartyrs(list1.peek().getMartyrs());
					
					Result.setText(dates.getText() + ":\n1) Total number of martyrs in this date: " + totalM +"\n"
							+ "2) Average martyrs ages: "+avgAge+"\n"
							+ "3) The youngest martyr: \n" + young(list1.peek().getMartyrs())+"\n"
							+ "4) The oldest martyr: \n" + old(list1.peek().getMartyrs())+"\n"
							+ "5) District that has the maximum martyrs: "+maxDistrict+"\n"  
							+ "6) Location that has the maximum martyrs: "+maxLocation+"\n" );
					
					list2.push(list1.pop());
				}
			}

		});
		
		// This to navigate throw districts to the right.

		up.setOnAction(e -> {

			if (list2.peek() != null) {

				if (dates.getText().equals(list2.peek().getDate().toString())) {

					list1.push(list2.pop());
					dates.setText(list2.peek().getDate().toString());

					int totalM=list2.peek().getMartyrs().size();
					int avgAge=averageAge(list2.peek().getMartyrs());
					String maxDistrict=districtMaxMartyrs(list2.peek().getMartyrs());
					String maxLocation=locationMaxMartyrs(list2.peek().getMartyrs());

					Result.setText(dates.getText() + ":\n1) Total number of martyrs in this date: " + totalM +"\n"
							+ "2) Average martyrs ages: "+avgAge+"\n"
							+ "3) The youngest martyr: \n" + young(list2.peek().getMartyrs())+"\n"
							+ "4) The oldest martyr: \n" + old(list2.peek().getMartyrs()) +"\n"
							+ "5) District that has the maximum martyrs: "+maxDistrict+"\n" 
							+ "6) Location that has the maximum martyrs: "+maxLocation+"\n" );
					
					list1.push(list2.pop());

				} else {

					dates.setText(list2.peek().getDate().toString());

					int totalM=list2.peek().getMartyrs().size();
					int avgAge=averageAge(list2.peek().getMartyrs());
					String maxDistrict=districtMaxMartyrs(list2.peek().getMartyrs());
					String maxLocation=locationMaxMartyrs(list2.peek().getMartyrs());

					Result.setText(dates.getText() + ":\n 1)Total number of martyrs in this date: " + totalM +"\n"
							+ "2) Average martyrs ages: "+avgAge+"\n"
							+ "3) The youngest martyr: \n" + young(list2.peek().getMartyrs())+"\n"
							+ "4) The oldest martyr: \n" + old(list2.peek().getMartyrs()) +"\n"
							+ "5) District that has the maximum martyrs: "+maxDistrict+"\n"
							+ "6) Location that has the maximum martyrs: "+maxLocation+"\n" );
					
					list1.push(list2.pop());
				}
			}
					
		});
		
//-----------------------------------------------------------------------
		
		btLoadMartyrs.setOnAction(e-> {
			
			System.out.println("Load Martyrs Button clicked !\n");
			LocalDate d = LocalDate.parse(dates.getText());
			
			if (!d.toString().equals("Dates")) {

				DateRecord dm = new DateRecord(d);
				martyrS.setMartyrs(dateHash.find(dm).getData().getMartyrs());
				martyrS.date = dateHash.find(dm).getData().getDate().toString();
				martyrS.getCbDistricts().getItems().clear();
				
				martyrS.getCbDistricts().setItems(districts.getItems());
								
				martyrS.Result.clear();

				Stage primaryStage = new Stage();
				primaryStage.setTitle("Martyr_Screen");
				primaryStage.setScene(scene2);
				primaryStage.show();

			} else {

				Result.setText("choose the district you want to load its locations!! ");
			}
		});
		
//-----------------------------------------------------------------------
		
		btSave.setOnAction(e-> {
			
			System.out.println("Save button clicked !!\n");
			
			File file=new File("dataSave.csv");
			
			try {
				
				
				FileWriter out=new FileWriter(file);
				
				out.write("Name,event,Age,Locaion,District,Gender");
				out.write("\n");
				
				for (int i = 0; i < dateHash.getM(); i++) {
					
					if (dateHash.getTable()[i].getData() != null) {
						
						
						if (dateHash.getTable()[i].getData().getMartyrs().getRoot() == null) continue;
						
						LinkedQueue<TNode<MartyrRecord>> queue = new LinkedQueue<>();
						
						queue.enqueue(dateHash.getTable()[i].getData().getMartyrs().getRoot());
						
						while (!queue.isEmpty()) {
							
							TNode<MartyrRecord> curr = queue.dequeue();
							
							out.write(curr.getData().getName()+","+dateHash.getTable()[i].getData().getDate()+","+curr.getData().getAge()+","+curr.getData().getLocation()+","+curr.getData().getDistrict()+","+curr.getData().getGender());
							out.write("\n");

							if (curr.hasLeft()) queue.enqueue(curr.getLeft());
							if (curr.hasRight()) queue.enqueue(curr.getRight());
						}
						
					}
				}
				
				out.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});

	}
	
	public BorderPane getPane() {

		return pane;
	}

	public QuadraticOHash<DateRecord> getDates() {
		return dateHash;
	}
	
	private void getList1(StackQueue<DateRecord> list1) {

		for (int i = dateHash.getM()-1; i >= 0; i--) 
			if (dateHash.getTable()[i].getData() != null) {
				
				if (dateHash.getTable()[i].getFlag().equals("F")) {
				
					list1.push(dateHash.getTable()[i].getData());
				}
			}
	}
	
	private int averageAge(Avl_tree<MartyrRecord> martyrs) {
		
		int avg=0;
		
		if (martyrs.getRoot() == null) return 0;
		
		LinkedQueue<TNode<MartyrRecord>> queue = new LinkedQueue<>();
		
		queue.enqueue(martyrs.getRoot());
		
		while (!queue.isEmpty()) {
			
			TNode<MartyrRecord> curr = queue.dequeue();
			
			avg+=curr.getData().getAge();
			
			if (curr.hasLeft()) queue.enqueue(curr.getLeft());
			if (curr.hasRight()) queue.enqueue(curr.getRight());
		}
		
		return avg/martyrs.size();
	}
	
	private MartyrRecord young(Avl_tree<MartyrRecord> martyrs) {
		
		if (martyrs.getRoot() == null) return null;
		
		MartyrRecord m=martyrs.getRoot().getData();
		
		LinkedQueue<TNode<MartyrRecord>> queue = new LinkedQueue<>();
		
		queue.enqueue(martyrs.getRoot());
		
		while (!queue.isEmpty()) {
			
			TNode<MartyrRecord> curr = queue.dequeue();
			
			if (curr.getData().getAge()<m.getAge()) {
				
				m=curr.getData();
			}
			
			if (curr.hasLeft()) queue.enqueue(curr.getLeft());
			if (curr.hasRight()) queue.enqueue(curr.getRight());
		}
		
		return m;
	}
	
	private MartyrRecord old(Avl_tree<MartyrRecord> martyrs) {
		
		if (martyrs.getRoot() == null) return null;
		
		MartyrRecord m=martyrs.getRoot().getData();
		
		LinkedQueue<TNode<MartyrRecord>> queue = new LinkedQueue<>();
		
		queue.enqueue(martyrs.getRoot());
		
		while (!queue.isEmpty()) {
			
			TNode<MartyrRecord> curr = queue.dequeue();
			
			if (curr.getData().getAge()>m.getAge()) {
				
				m=curr.getData();
			}
			
			if (curr.hasLeft()) queue.enqueue(curr.getLeft());
			if (curr.hasRight()) queue.enqueue(curr.getRight());
		}
		
		return m;
	}
	
	public String districtMaxMartyrs(Avl_tree<MartyrRecord> martyrs) {

		String maxDis=null;
		int max=0;

		if (martyrs.getRoot() == null) return null;

		LinkedQueue<TNode<MartyrRecord>> queue = new LinkedQueue<>();

		queue.enqueue(martyrs.getRoot());

		while (!queue.isEmpty()) {

			TNode<MartyrRecord> curr = queue.dequeue();

			String dis=curr.getData().getDistrict();
			int count=0;

			LinkedQueue<TNode<MartyrRecord>> queue1 = new LinkedQueue<>();

			queue1.enqueue(martyrs.getRoot());
			
			while (!queue1.isEmpty()) {

				TNode<MartyrRecord> curr1 = queue1.dequeue();

				if (curr1.getData().getDistrict().equals(dis)) {
                    count++;
                }
				
				if (curr1.hasLeft())
					queue1.enqueue(curr1.getLeft());
				if (curr1.hasRight())
					queue1.enqueue(curr1.getRight());
				
			}
			
			if (count>max) {
				
				max=count;
				maxDis=dis;
			}
			
			if (curr.hasLeft())
				queue.enqueue(curr.getLeft());
			if (curr.hasRight())
				queue.enqueue(curr.getRight());
		}

		return maxDis;
	}
	
	public String locationMaxMartyrs(Avl_tree<MartyrRecord> martyrs) {

		String maxLoc=null;
		int max=0;

		if (martyrs.getRoot() == null) return null;

		LinkedQueue<TNode<MartyrRecord>> queue = new LinkedQueue<>();

		queue.enqueue(martyrs.getRoot());

		while (!queue.isEmpty()) {

			TNode<MartyrRecord> curr = queue.dequeue();

			String loc=curr.getData().getLocation();
			int count=0;
	
			LinkedQueue<TNode<MartyrRecord>> queue1 = new LinkedQueue<>();
	
			queue1.enqueue(martyrs.getRoot());
				
			while (!queue1.isEmpty()) {
	
				TNode<MartyrRecord> curr1 = queue1.dequeue();
	
				if (curr1.getData().getLocation().equals(loc)) {
	                   count++;
                }
					
				if (curr1.hasLeft())
					queue1.enqueue(curr1.getLeft());
				if (curr1.hasRight())
					queue1.enqueue(curr1.getRight());
					
			}
				
			if (count>max) {
					
				max=count;
				maxLoc=loc;
			}
			
			
			if (curr.hasLeft())
				queue.enqueue(curr.getLeft());
			if (curr.hasRight())
				queue.enqueue(curr.getRight());
		}

		return maxLoc;
	}
	
	public void traverseInOrder(Avl_tree<MartyrRecord> martyrs) {
		traverseInOrder(martyrs.getRoot());
	}

	
	private void traverseInOrder(TNode<MartyrRecord> node) {
		
		if (node != null) {
			
			if (node.getLeft() != null)
				traverseInOrder(node.getLeft());
			
			Result.setText(Result.getText()+node);
			
			if (node.getRight() != null)
				traverseInOrder(node.getRight());
		}
	}
}


