package nl.sogyo.rblom.lee;

import java.util.ArrayList;

public class CommandLine {
	
	private Direction direction;
	
	ArrayList<String> computeLine(ArrayList<Vakje> path, Direction heading){
		initialize(heading);
		
		return calculator(path);
	}

	private ArrayList<String> calculator(ArrayList<Vakje> path) {
		// TODO Auto-generated method stub
		return null;
	}

	private void initialize(Direction heading) {
		this.direction = heading;
	}
}
