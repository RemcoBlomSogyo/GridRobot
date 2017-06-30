package lee;

import java.util.ArrayList;

public class CommandLine {
	
	private String direction;
	private int[] goal;
	private String commandLine = "";
	
	public String computeLine(ArrayList<int[]> path, String heading){
		initialize(heading,path);
		
		return calculator(path);
	}

	private String calculator(ArrayList<int[]> path) {
		int i = 0;
		while (!path.get(i).equals(goal)){
			String nextDirection = nextPosition(path,i);
			if (nextDirection.equals(direction)){
				commandLine = commandLine+"f";
			}else{
				turn(nextDirection);
			}
			i++;
		}
		return commandLine;
	}

	private void turn(String nextDirection) {
		switch (nextDirection){
			case "north":
				if (direction == "west"){
					commandLine = commandLine+"r";
				}else if(direction == "east"){
					commandLine = commandLine+"l";
				}else{
					commandLine = commandLine+"l";
					commandLine = commandLine+"l";
				}
				this.direction = "north";
				break;
			case "west":
				if (direction == "south"){
					commandLine = commandLine+"r";
				}else if(direction == "north"){
					commandLine = commandLine+"l";
				}else{
					commandLine = commandLine+"l";
					commandLine = commandLine+"l";
				}
				this.direction = "west";
				break;
			case "east":
				if (direction == "north"){
					commandLine = commandLine+"r";
				}else if(direction == "south"){
					commandLine = commandLine+"l";
				}else{
					commandLine = commandLine+"l";
					commandLine = commandLine+"l";
				}
				this.direction = "east";
				break;
			case "south":
				if (direction == "east"){
					commandLine = commandLine+"r";
				}else if(direction == "west"){
					commandLine = commandLine+"l";
				}else{
					commandLine = commandLine+"l";
					commandLine = commandLine+"l";
				}
				this.direction = "south";
				break;
		
				
		}
		commandLine = commandLine+"f";
		
	}

	private String nextPosition(ArrayList<int[]> path, int i) {
		int xcurrent = path.get(i)[0];
		int ycurrent = path.get(i)[1];
		int xnext = path.get(i+1)[0];
		int ynext = path.get(i+1)[1];
		if (xcurrent>xnext){
			return "east";
		}
		if (xcurrent<xnext){
			return "west";
		}
		if (ycurrent>ynext){
			return "south";
			
		}else{
			return "north";
		}
	}
		
		
	private boolean turn(){
		switch (direction){
			case "north":
			case "south":
			case "west":
			case "east":
		}
		return false;
		
	}
	
	

	private void initialize(String heading,ArrayList<int[]> path) {
		this.direction = heading.toLowerCase();
		this.goal = path.get(path.size()-1);
	}
}
