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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ProductLowerButtonPane extends HBox{
	
//	private final String YEAR_LABEL = "Year Search";
//	private final String MONTH_LABEL = "Month Search";
	
	private final String MONTH_BUTTON = "Search Month";
	private final String YEAR_BUTTON = "Search Year";
	private final String ALL_BUTTON = "Search All";
	
//	private final double WIDTH_BUTTON_COEFFIENCT = 1.0/3.0;
	private final int LABEL_HEIGHT = 50;
	
	
	
	private final Font PANE_FONT = Font.font("Serif", 20);
	
//	
//	private Label yearL;
//	private Label monthL;
//	
//	private TextField yearT;
//	private TextField monthT;
	
	private Button monthB;
	private Button yearB;
	private Button allB;
	
//	private VBox searchInputPane;
	private VBox searchButtonsPane;
	
	private int paneWidth;
	private int paneHeight;
	
	private IController controller;
	
	public ProductLowerButtonPane(IController controller ,int width , int height) {
		this.paneWidth = width;
		this.paneHeight = height;
		
		this.controller = controller;
		this.setPrefSize(this.paneWidth, this.paneHeight);
		
		initProperties();
		setInsidePanes();
		setButtons();
		
		this.getChildren().addAll(this.searchButtonsPane);
	}

	private void initProperties() {
		
//		yearL = new Label(YEAR_LABEL);
//		yearL.setPrefSize((this.paneWidth * (1.0 - WIDTH_BUTTON_COEFFIENCT) ) / 2.0 , LABEL_HEIGHT);
//		yearL.setAlignment(Pos.CENTER);
//		yearL.setFont(PANE_FONT);
//		
//		monthL = new Label(MONTH_LABEL);
//		monthL.setPrefSize((this.paneWidth * (1.0 - WIDTH_BUTTON_COEFFIENCT) ) / 2.0 , LABEL_HEIGHT);
//		monthL.setAlignment(Pos.CENTER);
//		monthL.setFont(PANE_FONT);
//		
//		yearT = new TextField();
////		yearT.setPrefSize((this.paneWidth * (1.0 - WIDTH_BUTTON_COEFFIENCT) ) / 2.0 , LABEL_HEIGHT);
////		yearT.setAlignment(Pos.CENTER);
//		yearT.setFont(PANE_FONT);
//		
//		monthT = new TextField();
////		monthT.setPrefSize((this.paneWidth * (1.0 - WIDTH_BUTTON_COEFFIENCT) ) / 2.0 , LABEL_HEIGHT);
////		monthT.setAlignment(Pos.CENTER);
//		monthT.setFont(PANE_FONT);
//		
//		monthL = new Label(MONTH_LABEL);
//		monthL.setPrefSize((this.paneWidth * (1.0 - WIDTH_BUTTON_COEFFIENCT) ) / 2.0 , LABEL_HEIGHT);
//		monthL.setAlignment(Pos.CENTER);
//		monthL.setFont(PANE_FONT);
		
		
		monthB = new Button(MONTH_BUTTON);
		monthB.setPrefSize(this.paneWidth, LABEL_HEIGHT);
		monthB.setAlignment(Pos.CENTER);
		monthB.setFont(PANE_FONT);
		monthB.setStyle("-fx-background-color : WHITE;\n -fx-border-color: BLACK ");
		
		yearB = new Button(YEAR_BUTTON);
		yearB.setPrefSize(this.paneWidth, LABEL_HEIGHT);
		yearB.setAlignment(Pos.CENTER);
		yearB.setFont(PANE_FONT);
		yearB.setStyle("-fx-background-color : WHITE;\n -fx-border-color: BLACK ");
		
		allB = new Button(ALL_BUTTON);
		allB.setPrefSize(this.paneWidth, LABEL_HEIGHT);
		allB.setAlignment(Pos.CENTER);
		allB.setFont(PANE_FONT);
		allB.setStyle("-fx-background-color : WHITE;\n -fx-border-color: BLACK ");
		
	//	searchInputPane = new VBox();
	//	searchInputPane.setPrefSize((this.paneWidth * (1.0 - WIDTH_BUTTON_COEFFIENCT) ) , this.paneHeight);
		
		searchButtonsPane = new VBox();
		searchButtonsPane.setPrefSize(this.paneWidth , this.paneHeight);
		
	}
	

	private void setInsidePanes() {
		
		// set left side input pane
		
	//	HBox yearSearchBox = new HBox();
	//	yearSearchBox.setPrefSize((this.paneWidth * (1.0 - WIDTH_BUTTON_COEFFIENCT) ) , this.paneHeight/2);
	//	yearSearchBox.getChildren().addAll(yearL , yearT);
		
	//	HBox monthSearchBox = new HBox();
	//	monthSearchBox.setPrefSize((this.paneWidth * (1.0 - WIDTH_BUTTON_COEFFIENCT) ) , this.paneHeight/2);
	//	monthSearchBox.getChildren().addAll(monthL , monthT);
		
	//	searchInputPane.getChildren().addAll(yearSearchBox , monthSearchBox);
	//	searchInputPane.setPadding(new Insets(100, 20, 0, 0));
				
		// set right side button pane
		
		searchButtonsPane.getChildren().addAll(allB , yearB , monthB);
		searchButtonsPane.setSpacing(100);
		searchButtonsPane.setPadding(new Insets(50, 0 , 50, 0));
		searchButtonsPane.setStyle("-fx-border-color: BLACK");
		
	}
	
	
	private void setButtons() {
		//TODO implement button
		monthB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//				int month = checkMonthValidity();
//				int year = checkYearValidity();
//				
//				if(month != -1 && year != -1) {
					LocalDate date = LocalDate.now();
					IProduct p = new Product(null, date, 1);
					ProductData pd = new ProductData(p, false, false, true, false , false);
					controller.notify(pd);
		//		}
					
			}
		});
		
		yearB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//				int month = checkMonthValidity();
//				int year = checkYearValidity();
//				
//				if(month != -1 && year != -1) {
					LocalDate date = LocalDate.now();
					IProduct p = new Product(null, date, 1);
					ProductData pd = new ProductData(p, false, false, true, false , true);
					controller.notify(pd);
			//	}
				
			}
		});
		
		allB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//				int month = checkMonthValidity();
//				int year = checkYearValidity();
//				
//				if(month != -1 && year != -1) {
					LocalDate date = LocalDate.now();
					IProduct p = new Product(null, date, 1);
					ProductData pd = new ProductData(p, false, false, true, true , false);
					controller.notify(pd);
	//			}
		
			}
		});
		
		
	}
	
//	private int checkYearValidity() {
//		if(yearT.getText().isEmpty()) {
//			return -1;
//		}
//		try {
//			int year = Integer.valueOf(yearT.getText().trim());
//			return year;
//			
//		}catch(NumberFormatException e) {
//			return -1;
//		}
//	}
//	
//	private int checkMonthValidity() {
//		if(monthT.getText().isEmpty()) {
//			return -1;
//		}
//		try {
//			int month = Integer.valueOf(monthT.getText().trim());
//			if(month < 1 || month > 12) {
//				return -1;
//			}
//			return month;
//		}catch(NumberFormatException e) {
//			return -1;
//		}
//	}
	

}
