package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitlePane extends HBox {
	
	private final String TITLE_NAME = "Products: RED means product is expired. YELLEOW mean product expiring this month";
	
	private Label products;
	
	public TitlePane(int width , int height) {	
		this.setPrefSize(width, height);
		
		this.products = new Label(TITLE_NAME);
		this.products.setPrefSize(width, height);
		this.products.setFont(Font.font("Serif", FontWeight.BOLD, 30));
		this.products.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(this.products);
		
	}
	
	
}
