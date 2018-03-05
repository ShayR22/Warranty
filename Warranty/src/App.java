import Controller.Controller;
import DataManagement.DataManager;
import GUI.Frame;
import InterFaces.IController;
import InterFaces.IDataManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage ps) throws Exception {
		IController icm = new Controller();
		IDataManager idm = new DataManager("Warrant Data.txt" , icm);
		Frame f = new Frame(icm);
		
		f.init(ps);
		icm.registerView(f.getPane());
		icm.registerModul(idm);
		idm.loadExistingData();
	}

}
