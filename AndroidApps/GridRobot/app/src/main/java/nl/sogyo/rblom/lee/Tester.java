package nl.sogyo.rblom.lee;

import java.util.ArrayList;

public class Tester {
	
	public static void main(String args[]){
		ShortestPath test = new ShortestPath();
		
		int[] robot =  new int[]{0,0};
		int[] flag =  new int[]{6,6};
		
		ArrayList<int[]> path = test.pathComputer(robot, flag);
		
		CommandLine testLine = new CommandLine();
		
		String cl = testLine.computeLine(path, "east");

		System.out.println(cl);
	}
}