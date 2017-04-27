package ai_devoirlibre;

import java.util.PriorityQueue;

public class EightPuzzleAStar {
	
    private static long startTime = System.currentTimeMillis();

	public static void main(String[] args) {
		
		PriorityQueue<State> q = new PriorityQueue<>();
		
		q.add(new State());
		
		boolean over = false;
		
		while(!over){
			State s = q.poll();
			for(Action a : Action.values()){
				State ss = s.resultingStateFor(a);
				if(ss==null)
					continue;
				if(ss.isGoal()){
					ss.showPath();
					over= true;
				}
				else
					q.add(ss);
			}
		}
		

		long endTime = System.currentTimeMillis();
		System.out.println();
        System.out.println("It tooks " + (endTime - startTime) + " milliseconds");
		
	}

}
