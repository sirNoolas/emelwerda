package startHere;


import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;


@SuppressWarnings("serial")
public class StartHere extends Applet implements Runnable, KeyListener{
        Image i, city;
        Graphics grap;
        URL url;
        
        Font font = new Font("Calibri", Font.BOLD, 30);
        Font font2 = new Font("Calibri", Font.BOLD, 72);
        
        int width = 800;
        int height = 600;
        int score = 0;
        int levelcount = 100;
        int level = 1;
        
        Ball b1 = new Ball();
        Platform p[] = new Platform[8];
        Item item[] = new Item[3];
        
        @Override
        public void init() {
                setSize(width, height);
                addKeyListener(this);

                
                try{
                        url = getDocumentBase();
                }catch(Exception e) {
                }
                
                city = getImage(url, "images/city3.png");
        }
        
        @Override
        public void start() {
                for(int i = 0; i < p.length; i++) {
                        p[i] = new Platform((200*i) +200, Platform.yRandomHeight(this));
                }
                for(int i = 0; i < item.length; i++) {
                    item[i] = new ScoreBoost(getWidth() + 2000 * i);
                }
                
                Thread thread = new Thread(this);
                thread.start();
        }
        


		public void run() {
                while(true) {
                		score++;
                		b1.update(this);
                		
                        for(int i = 0; i < item.length; i++) {
                                item[i].update(this, b1);
                        }
                        for(int i = 0; i < p.length; i++) {
                            p[i].update(this, b1);
                        }
                        for(int i = 0; i < item.length; i++) {
                        	if(item[i].getY() == 10000) {
                        		item[i] = null;
                        		item[i] = new ScoreBoost(getWidth() + 6000);
                        		System.out.println(item[i].getY());
                        	}
                        }
                        
                        repaint();
                        try {
                                Thread.sleep(17);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        
                }
                
        }
        
        // DOUBLE BUFFER
                public void update(Graphics g) {
                        if(i == null) {
                                i = createImage(this.getSize().width, this.getSize().height);
                                grap = i.getGraphics();
                        }
                        
                        grap.setColor(getBackground());
                        grap.fillRect(0, 0, this.getSize().width, this.getSize().height);
                        
                        grap.setColor(getForeground());
                        paint(grap);
                                        
                        g.drawImage(i, 0, 0, this);
                }
        
        @Override
        public void stop() {
                // TODO Auto-generated method stub
        }
        
        @Override
        public void destroy() {
                // TODO Auto-generated method stub
        }
        
        @Override
        public void paint(Graphics g) {
                g.fillRect(0, 0, getWidth(), getHeight());
                g.drawImage(city, 0, 0, this);
                b1.paint(g);
                
                for(int i = 0; i < p.length; i++) {
                        p[i].paint(g);
                }
                
                for(int i = 0; i < item.length; i++) {
                    item[i].paint(g);
                }
                
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), 32);
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), 30);
                
                g.setFont(font);
                String s = "Score: " + Integer.toString(score);
                g.setColor(Color.WHITE);
                g.drawString(s, getWidth()-200, 25);
                
               
				if(Ball.bouncecount < levelcount ) {
                	g.drawString("Level " + level, 50, 25);
                	if(Ball.bouncecount <= levelcount - 95) {
                		g.setFont(font2);
                		g.setColor(Color.BLACK);
                    	g.drawString("LEVEL "+ level, 22, 152);
                    	g.setColor(Color.WHITE);
                    	g.drawString("LEVEL " + level, 20, 150);
                	}
                }else{
                	levelcount += 100;
                	level++;
                }
               
                
                
                
                
        }

        public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                                b1.moveLeft();
                                System.out.println("Left is pressed");
                                break;
                        case KeyEvent.VK_RIGHT:
                                b1.moveRight();
                                System.out.println("Right is pressed");
                                break;
                        case KeyEvent.VK_DOWN:
                                b1.slowDown();
                                System.out.println("Down is pressed");
                                break;
                }
                
        }

        public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                
        }
        public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                
        }
        
        public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}
}
