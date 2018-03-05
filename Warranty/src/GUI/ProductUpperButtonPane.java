package GUI;

import java.time.LocalDate;

import InterFaces.IController;
import InterFaces.IProduct;
import Product.Product;
import Product.ProductData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ProductUpperButtonPane extends HBox {
	
	private final double DATA_INPUT_WIDTH_COEFFIECENT = 2.0/3.0;
	
	private final String NAME_LABEL  = "Product Name:";
	private final String PURCHASE_DATE_LABEL  = "Product Purchase Date:";
	private final String WARRANTIY_LABEL = "Warranty in Months:";

	private final Font PANE_FONT = Font.font("Serif", 20);
	private final int LABEL_HEIGHT = 50;

	private final String CREATE_BUTTON = "Create";
	private final String DELETE_BUTTON = "Delete";
	private final String REFRESH_BUTTON = "Refresh";
	
	private Button createB;
	private Button deleteB;
	private Button updateB;
	
	private Label nameL;
	private Label dateL;
	private Label warrantyL;
	
	private TextField nameT;
	private TextField purchaseDateT;
	private TextField warrantyT;
	
	private int paneWidth;
	private int paneHeight;
	
	private VBox dataInputPane;
	private VBox cduButtonsPane;
	
	private IController controller;
	
	public ProductUpperButtonPane(IController controller , int width , int height) {
		this.controller = controller;
		this.setPrefSize(width, height);
		this.paneWidth = width;
		this.paneHeight = height;
		
		initProperties();
		setSizesToComponents();
		setDataInputPane();
		setcduButtonPane();
		addListenerToButtons();
		
		this.setAlignment(Pos.CENTER);
	//	this.setPadding(new Insets(50, 150, 50, 20));
		this.getChildren().addAll(this.dataInputPane , this.cduButtonsPane);
		
	}
	


	private void initProperties() {
		nameL = new Label(NAME_LABEL);
		nameL.setFont(PANE_FONT);
	
		dateL = new Label(PURCHASE_DATE_LABEL);
		dateL.setFont(PANE_FONT);
			
		warrantyL = new Label(WARRANTIY_LABEL);
		warrantyL.setFont(PANE_FONT);
		
		nameT = new TextField();
		nameT.setFont(PANE_FONT);
		
		purchaseDateT =  new TextField();
		purchaseDateT.setFont(PANE_FONT);
		
		warrantyT = new TextField();
		warrantyT.setFont(PANE_FONT);
		
		createB = new Button(CREATE_BUTTON);
		createB.setFont(PANE_FONT);
		
		deleteB = new Button(DELETE_BUTTON);
		deleteB.setFont(PANE_FONT);
		
		updateB = new Button(REFRESH_BUTTON);
		updateB.setFont(PANE_FONT);
		
		dataInputPane = new VBox();
		dataInputPane.setPrefSize(this.paneWidth * DATA_INPUT_WIDTH_COEFFIECENT, this.paneHeight);
		
		cduButtonsPane = new VBox();
		cduButtonsPane.setPrefSize(this.paneHeight * (1.0 - DATA_INPUT_WIDTH_COEFFIECENT), this.paneHeight);
		
		
	}
	
	private void setSizesToComponents() {
		createB.setPrefSize(this.paneWidth * (1.0 - DATA_INPUT_WIDTH_COEFFIECENT) , LABEL_HEIGHT);
		createB.setStyle("-fx-background-color: white;\n -fx-border-color: black");
	
		deleteB.setPrefSize(this.paneWidth * (1.0 - DATA_INPUT_WIDTH_COEFFIECENT), LABEL_HEIGHT);
		deleteB.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		updateB.setPrefSize(this.paneWidth * (1.0 - DATA_INPUT_WIDTH_COEFFIECENT), LABEL_HEIGHT);
		updateB.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		nameL.setPrefSize(this.paneWidth/3, LABEL_HEIGHT);
		dateL.setPrefSize(this.paneWidth*2/3, LABEL_HEIGHT);
		warrantyL.setPrefSize(this.paneWidth*2/3, LABEL_HEIGHT);
			
	
	}
	
	private void setDataInputPane() {
		HBox nameBox = new HBox();
		nameBox.setPrefSize(this.paneWidth * DATA_INPUT_WIDTH_COEFFIECENT, this.paneHeight/3);
		nameBox.getChildren().addAll(this.nameL , this.nameT);
		
		HBox dateBox = new HBox();
		dateBox.setPrefSize(this.paneWidth * DATA_INPUT_WIDTH_COEFFIECENT, this.paneHeight/3);
		dateBox.getChildren().addAll(this.dateL , this.purchaseDateT);
		
		HBox warrantyBox = new HBox();
		warrantyBox.setPrefSize(this.paneWidth * DATA_INPUT_WIDTH_COEFFIECENT, this.paneHeight/3);
		warrantyBox.getChildren().addAll(this.warrantyL , this.warrantyT);
		
		this.dataInputPane.getChildren().addAll(nameBox , dateBox ,warrantyBox);
		this.dataInputPane.setPadding(new Insets(0, 5, 0, 0));
	}
	
	
	private void setcduButtonPane() {	
		this.cduButtonsPane.getChildren().addAll(this.createB , this.deleteB , this.updateB);
		this.cduButtonsPane.setSpacing(90);
		this.cduButtonsPane.setStyle("-fx-border-color: BLACK");
	}
	

	

	private void addListenerToButtons() {
		this.createB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ProductData cd = checkData();
				if(cd != null) {
					controller.notify(cd);
				}
			}
		});
		
		this.deleteB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ProductData cd = checkData();
				if(cd != null) {
					cd.setCreating(false);
					controller.notify(cd);
				}
				
			}
		});
		
		this.updateB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				ProductData pd = new ProductData(null, false, true, false, false , false);
				controller.notify(pd);
				
			}
		});

	}
	
	private ProductData checkData() {
		int warranty = 0;
		boolean validInput = true;
		if(nameT.getText().isEmpty()) {
			nameT.setText("fill me");
			validInput = false; 
		}
		
		if(purchaseDateT.getText().isEmpty()) {
			purchaseDateT.setText("fill me");
			validInput = false;
		}
		if(warrantyT.getText().isEmpty()){
			warrantyT.setText("fill me");
			validInput = false;
		}
		if(validInput) {
			try {
				if(!isValidDate(purchaseDateT.getText().trim())) {
					validInput = false;
				}
				warranty = Integer.valueOf(warrantyT.getText().trim());
			}
			catch(NumberFormatException e) {
					validInput = false;
			}
			
			if(validInput) {
				String subDay = purchaseDateT.getText().substring(0, 2);
				String subMonth = purchaseDateT.getText().substring(3, 5);
				String subYear = purchaseDateT.getText().substring(6, 10);
				int day = Integer.valueOf(subDay);
				int month = Integer.valueOf(subMonth);
				int year = Integer.valueOf(subYear);
				
				LocalDate date = LocalDate.of(year, month, day);
				IProduct product = new Product(nameT.getText().trim(), date , warranty);
				return new ProductData(product, true, false, false, false , false);
			}
			
		}
		else {
			return null;
		}
		return null;
		
	}	
	
	private boolean isValidDate(String str) {
		if(str.length() != 10) {
			return false;
		}
		if(str.charAt(2) != '.' || str.charAt(5) != '.' ) {
			return false;
		}
		
		for(int i = 0 ; i < 2 ; i++) {
			char currentChar = str.charAt(i);
			if(currentChar < '0' || currentChar > '9') {
				return false;
			}
		}
		for(int i = 3 ; i < 5 ; i++) {
			char currentChar = str.charAt(i);
			if(currentChar < '0' || currentChar > '9') {
				return false;
			}
		}
		for(int i = 6 ; i < 10 ; i++) {
			char currentChar = str.charAt(i);
			if(currentChar < '0' || currentChar > '9') {
				return false;
			}
		}
		
		return true;
		
	}

	
	
}


