package GUI;

import InterFaces.IController;
import InterFaces.IMyPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Frame {

	private IController controller;
	private MyPane mp;
	
	
	public Frame(IController controller) {
		this.controller = controller;
	}
	
	public void init(Stage ps) {
		mp = new MyPane(this.controller);
		Scene scene = new Scene(mp);
		ps.setResizable(false);
		ps.setScene(scene);
		ps.setAlwaysOnTop(true);
		ps.show();
	}
	
	public IMyPanel getPane() {
		return this.mp;
	}

}
