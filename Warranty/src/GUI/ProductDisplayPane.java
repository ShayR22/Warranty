package GUI;

import InterFaces.IProduct;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ProductDisplayPane extends VBox {

	private final String NAME_LABEL = "Name";
	private final String PURCHASE_DATE_LABEL = "Purchase Date";
	private final String WARRANTY_LABEL = "Warranty in Months";
	private final String EXPIRED_DATE_LABEL = "Expired Date";
	
	private final double NAME_LABEL_WIDTH_COEFFIECNT = 0.35;
	private final double PURCHASE_DATE_LABEL_WIDTH_COEFFIECNT = 0.2;
	private final double WARRANTY_LABEL_WIDTH_COEFFIECNT = 0.25;
	private final double EXPIRED_DATE_LABEL_WIDTH_COEFFIECNT = 0.18;
	
	
	
	private final int LABEL_HEIGHT = 50;
	private final int PRODUCT_PANE_HEIGHT = 40;
	
	private int paneWidth;
	private int paneHeight;
	
	private final Font LABEL_FONT = Font.font("Serif", 25); 
	
	private Label nameL;
	private Label purchaseDateL;
	private Label warrantyL;
	private Label expiredDateL;
	
	private ScrollPane wrapperPane;
	private VBox productShowPane;
	private int numOfProducts;

	
	public ProductDisplayPane(int width , int height) {
		this.paneWidth = width;
		this.paneHeight = height;
		
		numOfProducts = 0;
		
		this.setPrefSize(paneWidth, paneHeight);
		
		initProperties();
		initHeadLines();
		
		wrapperPane = new ScrollPane();
		wrapperPane.setPrefSize(this.paneWidth, this.paneHeight);
		wrapperPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		wrapperPane.setContent(this.productShowPane);
		wrapperPane.setStyle("-fx-background: azure;\n -fx-border-color: black;\n -fx-fit-to-height: false");
		
		this.getChildren().addAll(wrapperPane);
		
	}
	
	
	
	private void initProperties() {
		
		this.nameL = new Label(NAME_LABEL);
		this.nameL.setPrefSize(this.paneWidth * NAME_LABEL_WIDTH_COEFFIECNT, LABEL_HEIGHT);
		this.nameL.setAlignment(Pos.CENTER);
		this.nameL.setFont(LABEL_FONT);
		this.nameL.setStyle("-fx-border-color: BLACK");	
		
		
		this.purchaseDateL = new Label(PURCHASE_DATE_LABEL);
		this.purchaseDateL.setPrefSize(this.paneWidth * PURCHASE_DATE_LABEL_WIDTH_COEFFIECNT, LABEL_HEIGHT);
		this.purchaseDateL.setAlignment(Pos.CENTER);
		this.purchaseDateL.setFont(LABEL_FONT);
		this.purchaseDateL.setStyle("-fx-border-color: BLACK");	


		this.warrantyL = new Label(WARRANTY_LABEL);
		this.warrantyL.setPrefSize(this.paneWidth * WARRANTY_LABEL_WIDTH_COEFFIECNT, LABEL_HEIGHT);
		this.warrantyL.setAlignment(Pos.CENTER);
		this.warrantyL.setFont(LABEL_FONT);
		this.warrantyL.setStyle("-fx-border-color: BLACK");	


		this.expiredDateL = new Label(EXPIRED_DATE_LABEL);
		this.expiredDateL.setPrefSize(this.paneWidth * EXPIRED_DATE_LABEL_WIDTH_COEFFIECNT, LABEL_HEIGHT);
		this.expiredDateL.setAlignment(Pos.CENTER);
		this.expiredDateL.setFont(LABEL_FONT);
		this.expiredDateL.setStyle("-fx-border-color: BLACK");	

		
		this.productShowPane = new VBox();
		this.productShowPane.setPrefSize(this.paneWidth, this.paneHeight - LABEL_HEIGHT);
		this.productShowPane.setSpacing(2);
		
		
	}




	private void initHeadLines() {
		HBox headlines = new HBox();
		headlines.setPrefSize(this.paneWidth , LABEL_HEIGHT);
		headlines.getChildren().addAll( expiredDateL , warrantyL  , purchaseDateL , nameL );
		headlines.setStyle(" -fx-border-color: BLACK");
		
		this.getChildren().add(headlines);
	}
	
	
	public void addProduct(IProduct product) {
		if(product != null) {
			numOfProducts++;
			if(numOfProducts * PRODUCT_PANE_HEIGHT > productShowPane.getPrefHeight()) {
				productShowPane.setPrefHeight(numOfProducts * PRODUCT_PANE_HEIGHT);
			}
			
			productShowPane.getChildren().add( new ProductPane(product ,this.paneWidth , PRODUCT_PANE_HEIGHT));
		}

	}

	public void cleanProducts() {
		productShowPane.getChildren().clear();
		numOfProducts = 0;
	}
	
}

	