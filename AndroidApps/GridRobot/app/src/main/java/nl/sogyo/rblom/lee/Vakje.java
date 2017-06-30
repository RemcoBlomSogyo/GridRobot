package nl.sogyo.rblom.lee;

import java.util.ArrayList;



class Vakje{
	int xcor;
	int ycor;
	int value;
	Grid grid;
	
	
	Vakje(int xcor, int ycor, int value){
		this.xcor = xcor;
		this.ycor = ycor;
		this.value = value;
	}
	
	public void setGrid(Grid grid){
		this.grid = grid;
	}
	
	public int getx(){
		return xcor;
	}
	
	public int gety(){
		return ycor;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	ArrayList<Vakje> getNeighbours(){
		ArrayList<Vakje> neighbours = new ArrayList<Vakje>();
		if (this.ycor != 0){
			neighbours.add(grid.getVakje(xcor, ycor-1));
		}
		if (this.ycor != grid.getyDimension()-1){
			neighbours.add(grid.getVakje(xcor, ycor+1));
		}
		if (this.xcor != 0){
			neighbours.add(grid.getVakje(xcor-1, ycor));
		}
		if (this.xcor != grid.getxDimension()-1){
			neighbours.add(grid.getVakje(xcor+1, ycor));
		}
		return neighbours;
	}
	
	public void print(){
		System.out.println("x: "+xcor+" y: "+ycor+" value: "+value);
	}
}