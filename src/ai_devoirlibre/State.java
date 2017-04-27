package ai_devoirlibre;

import java.util.ArrayList;
import java.util.Comparator;

public class State implements Comparable<State>{

	int[][] tile;
	State parent;
	String cameFrom = "";
	int g;
	private int h = -1;
	int blankX, blankY;
	
	static final int[][] goal = {{1,2,3},{8,-1,4},{7,6,5}};
	
	public State(State s){
		this.tile = new int[3][3] ;
		tile[0] = s.tile[0].clone() ;
		tile[1] = s.tile[1].clone() ;
		tile[2] = s.tile[2].clone() ;
		g = s.g;
		blankX = s.blankX;
		blankY = s.blankY;
		parent = s;
	}
	
	public State(){
		//TODO do not hard code that!
		this.tile = new int[][]{{8,1,3},{-1,4,5},{2,7,6}};
		blankX = 0;
		blankY = 1;
		parent = null;
		g = 0 ;
		getH();
	}
	
	
	public boolean possible(Action a){
		switch (a) {
		case DOWN:
			return blankY<2;
		case UP:
			return blankY>0;
		case LEFT:
			return blankX>0;
		case RIGHT:
			return blankX<2;
		default:
			return true;
		}
	}
	
	public State resultingStateFor(Action a){
		if(!possible(a))
			return null;
		
		State s = new State(this);
		
		s.doAction(a);
		
		return s;
	}
	
	public void doAction(Action a){
		switch (a) {
		case DOWN:
			tile[blankY][blankX] = tile[blankY+1][blankX];
			tile[blankY+1][blankX] = -1;
			blankY++;
			this.cameFrom = "D";
			break;
		case UP:
			tile[blankY][blankX] = tile[blankY-1][blankX];
			tile[blankY-1][blankX] = -1;
			blankY--;
			this.cameFrom = "U";
			break;
		case LEFT:
			tile[blankY][blankX] = tile[blankY][blankX-1];
			tile[blankY][blankX-1] = -1;
			blankX--;
			this.cameFrom = "L";
			break;
		case RIGHT:
			tile[blankY][blankX] = tile[blankY][blankX+1];
			tile[blankY][blankX+1] = -1;
			blankX++;
			this.cameFrom = "R";
			break;
		default:
			break;
		}
		g++;
	}
	
	public int getF(){
		return g + getH();
	}
	
	public int getH(){
		if(h!=-1)
			return h;
		
		int count = 0;
		for(int i=0; i<=2; i++)
			for(int j=0; j<=2; j++)
				if(tile[i][j]!=goal[i][j])
					count++;
		h = count;
		return h;
	}
	
	public boolean isGoal(){
		return getH()==0;
	}
	
	public void showPath(){
		String path = " " + this.cameFrom+ ".";
		if(parent!=null)
			parent.showPath(path);
		System.out.println("g = "+g);
	}
	
	public void showPath(String p){
		if(parent==null)
			System.out.println(p);
		else
			parent.showPath(" "+this.cameFrom+p);
	}

	@Override
	public int compareTo(State o) {
		return this.getF() - o.getF();
	}
	
	
}
