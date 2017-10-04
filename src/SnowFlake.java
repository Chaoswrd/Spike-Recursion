import java.util.ArrayList;

import javafx.scene.shape.Line;

public class SnowFlake {
	public static ArrayList<Line> snowflake(double x, double y, double length, double degrees, int iterations){
		ArrayList<Line> allLines = new ArrayList<Line>();
		allLines.addAll(Spike.spikes(x, y, length, (int) 0, iterations));//Calls the top spike
		//Starts rotating the called "spikes"
		for(int i=0; i<5; i++){
			
			Double[] xy = snowflakeFlake(x, y, length, 60*i);//Rotation function
			allLines.addAll(Spike.spikes(x+xy[0], y+xy[1], length, 60*(i+1), iterations)); //Adds the spike function with current coordinates function
			
			//This will start the next spike function from the "end" of the previous spike.
			x = x+xy[0];
			y = y+xy[1];
		}
		return allLines;
	}
	private static Double[] snowflakeFlake(double x, double y, double length, double degrees){
		Double[] xy = new Double[2];
		double radian = Math.toRadians(degrees);
		xy[0] = length*Math.cos(radian); //x value
		xy[1] = length*Math.sin(radian); //y value
		return xy;
	}
}
