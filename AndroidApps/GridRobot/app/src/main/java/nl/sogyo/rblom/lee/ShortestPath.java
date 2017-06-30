package nl.sogyo.rblom.lee;

import java.util.ArrayList;
import java.util.List;

public class ShortestPath {
	private Grid grid;
	private ArrayList<Vakje> path = new ArrayList<Vakje>();
	private Vakje flag;
	private Vakje robot;

	public List<int[]> pathComputer(int[] robot, int[] flag){
		ArrayList<int[]> obstacles = new ArrayList<int[]>();
		return pathComputer(robot,flag,obstacles);
	}
	
	public List<int[]> pathComputer(int[] robot, int[] flag, ArrayList<int[]> obstacles){
		System.out.println("Initializing...");
		initialization(robot,flag,obstacles);
		System.out.println("Wave Expanding...");
		waveExpansion();
		System.out.println("Back Tracing...");
		backTrace();
		System.out.println("Done!");
		return pathConvert(); 
	}
	
	private List<int[]> pathConvert() {
		List<int[]> finalPath = new ArrayList<int[]>();
		for (Vakje vakje : path){
			finalPath.add(new int[]{vakje.getx(),vakje.gety()});
		}
		return finalPath;
	}

	private void initialization(int[] robot, int[] flag, ArrayList<int[]> obstacles){
		this.grid = new Grid(7,7,obstacles);
		this.flag = grid.getVakje(flag[0], flag[1]);
		this.robot = grid.getVakje(robot[0], robot[1]);
		this.robot.setValue(0);
		for (Vakje vakje:grid.content){
			vakje.setGrid(grid);
		}
	}
	
	private void waveExpansion() {
		int wave=0;
		while(!goalReached(wave) && validNeighbours(wave)){
			markNeighbours(wave);
			wave++;
		}
	}	

	private boolean validNeighbours(int waveNumber) {
		for (Vakje vakje: grid.content){
			if (vakje.getValue() == waveNumber){
				return true;
			}
		}
		return false;
	}

	private void markNeighbours(int waveNumber) {
		for (Vakje vakje: grid.content){
			if (vakje.getValue() == waveNumber){
				increaseNeighbours(vakje);
			}
		}
	}
	
	private void increaseNeighbours(Vakje vakje) {
		ArrayList<Vakje> neighbours = vakje.getNeighbours();
		for (Vakje neighbour : neighbours){
			
			if (neighbour.getValue()==-1){
				neighbour.setValue(vakje.getValue()+1);
			}
		}
	}

	private boolean goalReached(int i) {
		if (flag.getValue() == i){
			return true;
		}
		return false;
	}
	
	private void backTrace() {
		path.add(flag);
		Vakje target = flag;
		while (!target.equals(robot)){
			target = addNeighbour(target);
		}
	}
	
	private Vakje addNeighbour(Vakje target) {
		ArrayList<Vakje> neighbours = target.getNeighbours();
		for (Vakje neighbour : neighbours){
			if (neighbour.getValue()<target.getValue() && neighbour.getValue()> -1){
				path.add(neighbour);
				return neighbour;
			}
		}
		return robot;
	}


}