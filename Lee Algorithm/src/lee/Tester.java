package lee;

import java.util.ArrayList;
import java.util.List;

public class Tester {
	private static final Direction NORTH = null;

	public static void main(String[] args){
		shortestPathTest();
	}
	
	static void commandLineTest(){
		CommandLine test = new CommandLine();
		ArrayList<int[]> input = new ArrayList<int[]>();
		input.add(new int[]{1,2});
	}
	
	static void shortestPathTest(){
		ShortestPath test = new ShortestPath();
		
		int[] testRobot = new int[]{0,0};
		int[] testFlag = new int[]{6,6};
		ArrayList<int[]> obstacles = new ArrayList<int[]>();
		
		obstacles.add(new int[]{1,0});	
		obstacles.add(new int[]{1,1});	
		
		List<int[]> result = test.pathComputer(testRobot,testFlag, obstacles);
		for (int[] hokje : result){
			System.out.println(hokje[0]+" "+hokje[1]);
		}
		System.out.println(result.get(result.size()-1));
	}
}
