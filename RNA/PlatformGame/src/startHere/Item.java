package startHere;


import java.awt.Graphics;
import java.util.Random;

public class Item {
	private int x, y, dx, radius;
	

	
    public Item(int i) {
        x = i;
        Random r = new Random();
        y = r.nextInt(300) + 135;
        dx = -2;
        radius = 10;
}

    public void update(StartHere sh, Ball b) {
    	x+= dx;
        checkCollision(b, sh);
        if(x < 0 - radius) {
                Random r = new Random();
                x = sh.getWidth() + 2000 + r.nextInt(300);
        }
}
        
        
    private void checkCollision(Ball ball, StartHere sh) {
        // Pythagoras
    	int ballX = ball.getX();
        int ballY = ball.getY();
        int ballRadius = ball.getRadius();      
        
        int a = Math.abs(x - ballX);
        int b = Math.abs(y - ballY);
        int c = radius + ballRadius;
        
        if((a*a + b*b) <= (c*c)) {
        	performAction(ball, sh);
        	y = 10000;
        }
}
        

public void performAction(Ball b, StartHere sh) {
		// TODO Auto-generated method stub
		
	}

public void paint(Graphics g) {
        g.fillOval(x - radius, y - radius , radius*2, radius*2);

}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}



}
