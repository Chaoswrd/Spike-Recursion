import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Spike {
	public static ArrayList<Line> spikes(double x, double y, double length, double degrees, int iterations){
		double length3 = length/3; //Divides by three because the small components of the spike are of length three
		//Rotates every small component so it is rotated correctly
		Double[][] doob = {
				rotation(length3, degrees),
				rotation(length3, degrees-60),
				rotation(length3, degrees+60),
				rotation(length3, degrees),
		};
		//Keeps track of the end of the previous spike, init with init-x and init-y
		Double[] lastXY = {x,y};
		//If iterations is larger than one then we want the smaller component to be a spike
		if(iterations>1){
			ArrayList<Line> lines = new ArrayList<Line>();
			for(int i=0; i<4; i++){
				//If it is the first or last spike then it should not be rotated if it is the second then rotate up, if second then rotate down
				if(i==1)
					lines.addAll(spikes(lastXY[0],lastXY[1],length3,degrees-60,iterations-1));
				else if(i==2)
					lines.addAll(spikes(lastXY[0],lastXY[1],length3,degrees+60,iterations-1));
				else
					lines.addAll(spikes(lastXY[0],lastXY[1],length3,degrees,iterations-1));
				//Calculates the end of the current spike
				lastXY[0]=doob[i][0]+lastXY[0]; 
				lastXY[1]=doob[i][1]+lastXY[1];
			}
	
			return lines;
		}
		else{
			ArrayList<Line> lines = new ArrayList<Line>();
			
			//Makes a spike
			for(int i=0; i<doob.length; i++){
				Line line = new Line();
				line.setStartX(lastXY[0]);
				line.setStartY(lastXY[1]);
				line.setEndX(doob[i][0]+lastXY[0]);
				line.setEndY(doob[i][1]+lastXY[1]);
				lastXY[0]=doob[i][0]+lastXY[0];
				lastXY[1]=doob[i][1]+lastXY[1];
				line.setFill(Color.BLACK);
				lines.add(line);
			}
			
			return lines;
		}
	}
	
	private static Double[] rotation(double length, double degrees) {
		Double[] xy = new Double[2];
		double radian = Math.toRadians(degrees);
		xy[0] = length*Math.cos(radian); //x value
		xy[1] = length*Math.sin(radian); //y value
		return xy;
	}
}
