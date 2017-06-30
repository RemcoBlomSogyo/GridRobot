package nl.sogyo.rblom.lee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Grid{
	ArrayList<Vakje> content = new ArrayList<Vakje>();
	int xDimension;
	int yDimension;
	
	
	public Grid(int xDimension, int yDimension, ArrayList<int[]> obstacles){
		this.xDimension = xDimension;
		this.yDimension = yDimension;
		for (int y = 0 ; y <yDimension ; y++){
			for (int x = 0 ; x < xDimension; x++){
				if (isInList(obstacles,new int[]{x,y})){
					content.add(new Vakje(x,y,-2));
				}else{
					content.add(new Vakje(x,y,-1));
				}
			}
		}
		
	}

	private static boolean isInList(
	final List<int[]> list, final int[] candidate) {

		for (int[] item : list){
			if (Arrays.equals(item, candidate)){
				return true;
			}

		}
		return false;

	}
	
	public int getxDimension(){
		return xDimension;
	}
	
	public int getyDimension(){
		return yDimension;
	}
	
	public Vakje getVakje(int xcor,int ycor){
		for (Vakje vakje : content){
			if (vakje.getx() == xcor && vakje.gety() == ycor){
				return vakje;
			}
		}
		return null;
	}
	
	public void print(){
		int number = 0;
		
		for (Vakje vakje : content){
			System.out.print(vakje.getValue());
			number += 1;
			if (number % xDimension==0){
				System.out.println();
			}
		}
	}
}