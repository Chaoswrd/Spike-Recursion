import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application{

	@FXML Slider iterationsSlider;
	@FXML Slider lengthSlider;
	@FXML ChoiceBox<String> choiceBox;
	String algo = "spikes";
	
	public static void main(String[] args) {
		launch(args);
	}
	double degreeChange = 1;
	double lengthChange = 0.6;

	@Override
	@FXML
	public void start(Stage primaryStage) throws Exception {
		
		double length = 300;
		double x = 100;
		double y = 200;
		
	    Parent vbx = FXMLLoader.load(getClass().getResource("Client.fxml"));
	    
	    
	    iterationsSlider = (Slider) vbx.lookup("#iterationsSlider");
	    iterationsSlider.setPrefWidth(1300*0.2);
	    
	    lengthSlider = (Slider) vbx.lookup("#lengthSlider");
	    lengthSlider.setPrefWidth(1300*0.2);
	    
	    choiceBox = ((ChoiceBox<String>) vbx.lookup("#choiceBox"));
	    //Sets the list of algorithms
	    choiceBox.setItems(FXCollections.observableArrayList("spikes", "snowflake"));
		
		Pane root = new Pane();
		ObservableList list = root.getChildren();
		list.addAll(getFunc(algo, x, y, lengthChange*length, 0, (int) degreeChange));
		
		BorderPane bp = new BorderPane();
		bp.setCenter(root);
		bp.setLeft(vbx);
	
		//Changes the amount of iterations the recursive function makes.
		iterationsSlider.valueProperty().addListener(e -> {
			degreeChange = iterationsSlider.getValue()+1;
			list.clear();
			list.addAll(getFunc(algo, x, y, lengthChange*length, 0, (int) degreeChange));
			bp.setCenter(root);
		});
		
		//Changes the length of the recursive function
		lengthSlider.valueProperty().addListener(e -> {
			lengthChange = lengthSlider.getValue()/50;//Divides by fifty to keep it on screen.
			list.clear();
			list.addAll(getFunc(algo, x, y, lengthChange*length, 0, (int) degreeChange));
			bp.setCenter(root);
		});
		
		//Sets the algorithm
		choiceBox.valueProperty().addListener(e -> {
			algo = choiceBox.getValue().toString(); //Checks the value of the choicebox
			list.clear();
			list.addAll(getFunc(algo, x, y, lengthChange*length, 0, (int) degreeChange));
			bp.setCenter(root);
		});
		
		Scene scene = new Scene(bp, 1280, 800);
		primaryStage.setTitle("SpikeRecursion");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//Checks which algorithm is set ands calls that specific algorithm
	private static ArrayList<Line> getFunc(String id, double x, double y, double length, double degrees, int iterations){
		switch(id){
		case "spikes":
			return Spike.spikes(x, y, length, degrees, iterations);
		case "snowflake":
			return SnowFlake.snowflake(x, y, length, degrees, iterations);
		}
	return null;
	}
}
