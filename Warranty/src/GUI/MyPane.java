package GUI;

import InterFaces.IController;
import InterFaces.IProduct;
import InterFaces.IMyPanel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MyPane extends VBox implements IMyPanel {

	private final int WIDTH = 1500;
	private final int HEIGHT = 900;
	
	private final double WIDTH_BUTTON_COEFFIENT = 1.0/3.0; 
	private final double HEIGHT_UPPER_BUTTON_COEFFIENCT = 3.0/5.0;
	
	private final int TITLE_PANE_HEIGHT = 50;
	
	private TitlePane titlePane;
	
	private HBox dispalyAndUpperLowerPane;
	
	private VBox upperAndLowerButtonPane;
	private ProductLowerButtonPane lowerButtonPane;
	private ProductUpperButtonPane upperButtonPane;
	
	private ProductDisplayPane displayPane; 

	private IController controller;
	
	public MyPane(IController controller) {
		this.controller = controller;
		
		this.setPrefSize(WIDTH, HEIGHT);
		this.setStyle("-fx-background-color: azure");
		
		initProperties();
		setInsidePanes();
		

		
		this.getChildren().addAll(titlePane ,dispalyAndUpperLowerPane);
		
	}
	

	private void initProperties() {
	
		this.titlePane = new TitlePane(WIDTH , TITLE_PANE_HEIGHT);
		this.titlePane.setStyle("-fx-border-color: BLACK");	
		
		this.displayPane = new ProductDisplayPane((int) (WIDTH * (1.0 - WIDTH_BUTTON_COEFFIENT)), HEIGHT - TITLE_PANE_HEIGHT);
		this.displayPane.setStyle("-fx-border-color: BLACK");	
		
		this.upperButtonPane = new ProductUpperButtonPane(controller, (int) (WIDTH * WIDTH_BUTTON_COEFFIENT), (int) ((HEIGHT - TITLE_PANE_HEIGHT) * (HEIGHT_UPPER_BUTTON_COEFFIENCT)));
		this.upperButtonPane.setStyle("-fx-border-color: BLACK");	
		
		this.lowerButtonPane = new ProductLowerButtonPane(this.controller , (int) (WIDTH * WIDTH_BUTTON_COEFFIENT), (int) ((HEIGHT - TITLE_PANE_HEIGHT) * (1.0 - HEIGHT_UPPER_BUTTON_COEFFIENCT)));
		this.lowerButtonPane.setStyle("-fx-border-color: BLACK");	
		
		this.upperAndLowerButtonPane = new VBox();
		this.upperAndLowerButtonPane.setPrefSize(WIDTH * WIDTH_BUTTON_COEFFIENT , HEIGHT - TITLE_PANE_HEIGHT);
		
		this.dispalyAndUpperLowerPane = new HBox();
		this.dispalyAndUpperLowerPane.setPrefSize(WIDTH,  HEIGHT - TITLE_PANE_HEIGHT);
		

	}
	

	private void setInsidePanes() {
		
		this.upperAndLowerButtonPane.getChildren().addAll(this.upperButtonPane , this.lowerButtonPane);
		this.dispalyAndUpperLowerPane.getChildren().addAll(this.displayPane , this.upperAndLowerButtonPane);	
	}



	@Override
	public void notify(IProduct product) {
		displayPane.addProduct(product);
	}

	@Override
	public void notify(boolean clean) {
		if(clean) {
			displayPane.cleanProducts();
			
		}
		
	}
	


	
	
	
}
