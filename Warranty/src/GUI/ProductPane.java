package GUI;

import java.time.LocalDate;

import InterFaces.IProduct;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ProductPane extends HBox {
	
	
	private final double NAME_WIDTH_COEFFIENCT = 0.35;
	private final double PURCHASE_DATE_WIDTH_COEFFIENCT = 0.2;
	private final double EXPIRE_DATE_WIDTH_COEFFIENCT = 0.18;
	private final double WARRANTIY_WIDTH_COEFFIENCT = 0.25;
	
	
	private final Font LABEL_FONT = Font.font("Serif", 20);

	
	private Label productNameValue;
	private Label productPurchaseDateValue;
	private Label productExpireDateValue;
	private Label productWarrantyValue;


	public ProductPane(IProduct product , int width , int height) {
		this.setPrefSize(width , height);
		
		productNameValue = new Label(" - " + product.getName());
		productNameValue.setPrefSize(NAME_WIDTH_COEFFIENCT * width , height);
		productNameValue.setAlignment(Pos.BASELINE_RIGHT);
		productNameValue.setFont(LABEL_FONT);
		productNameValue.setStyle("-fx-border-color: BLACK");
		
		
		productPurchaseDateValue = new Label(product.toStringPurchaseDate());
		productPurchaseDateValue.setPrefSize(PURCHASE_DATE_WIDTH_COEFFIENCT * width , height);
		productPurchaseDateValue.setAlignment(Pos.CENTER);
		productPurchaseDateValue.setFont(LABEL_FONT);
		productPurchaseDateValue.setStyle("-fx-border-color: BLACK");
		
		

		productExpireDateValue = new Label(product.toStringExpireDate());
		productExpireDateValue.setPrefSize(EXPIRE_DATE_WIDTH_COEFFIENCT * width , height);
		productExpireDateValue.setAlignment(Pos.CENTER);
		productExpireDateValue.setFont(LABEL_FONT);
		productExpireDateValue.setStyle("-fx-border-color: BLACK");
		
		
		if(product.getExpireDate().compareTo(LocalDate.now()) <= 0 ) {
			productExpireDateValue.setStyle("-fx-border-color: BLACK;\n -fx-background-color : red");
		}
		else {
			int month = product.getExpireDate().getMonthValue();
			int year = product.getExpireDate().getYear();
			
			int nowMonth = LocalDate.now().getMonthValue();
			int nowYear = LocalDate.now().getYear();
			
			if(month == nowMonth && year == nowYear) {
				productExpireDateValue.setStyle("-fx-border-color: BLACK;\n -fx-background-color : yellow");
			}
		}

		
		productWarrantyValue = new Label(String.valueOf(product.getWarrantyDurationMonths()));
		productWarrantyValue.setPrefSize(WARRANTIY_WIDTH_COEFFIENCT * width , height);
		productWarrantyValue.setAlignment(Pos.CENTER);
		productWarrantyValue.setFont(LABEL_FONT);
		productWarrantyValue.setStyle("-fx-border-color: BLACK");
		
		
		this.getChildren().addAll( productExpireDateValue , productWarrantyValue ,productPurchaseDateValue , productNameValue);
			
	}
	

	
}
