package lee;

import java.util.ArrayList;

public class CommandLine {
	
	private String heading;
	
	ArrayList<String> computeLine(ArrayList<Vakje> path, String heading){
		initialize(heading);
		
		return calculator(path);
	}

	private ArrayList<String> calculator(ArrayList<Vakje> path) {
		// TODO Auto-generated method stub
		return null;
	}

	private void initialize(String heading) {
		this.heading = heading;
	}
}
