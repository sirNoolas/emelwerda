package startHere;

import java.awt.Color;
import java.awt.Graphics;


//Extends Item-class (it's an item)
public class ScoreBoost extends Item{

	public ScoreBoost(int i) {
		super(i);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void performAction(Ball ball, StartHere sh) {
		sh.setScore(sh.getScore() + 1000); 
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		super.paint(g);
	}

}
