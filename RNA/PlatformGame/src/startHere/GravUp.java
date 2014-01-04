package startHere;

import java.awt.Color;
import java.awt.Graphics;


//Extends Item-class (it's an item)
public class GravUp extends Item{

	public GravUp(int i) {
		super(i);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void performAction(Ball b, StartHere sh) {
		boolean inTime = true;
		int x = sh.getScore();
		
		while(inTime) {
			int y = sh.getScore();
			
			if(x + 400 < y) {
				inTime = false;
			}
			if(b.getGravity() > 2) {
			 b.setGravity(b.getGravity() -1);
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		super.paint(g);
	}

}
