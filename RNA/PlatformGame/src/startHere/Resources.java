package startHere;

import java.awt.Image;
import java.net.URL;

public class Resources {

		static Image platform, ball;
		URL url;
		static StartHere sh;
		
		public Resources(StartHere sh) {
			try{
				url = sh.getDocumentBase();
			}catch(Exception e) {
			}
		
			ball = sh.getImage(url, "images/ball.png");
			platform = sh.getImage(url, "images/platform.png");
			
			this.sh = sh;
		}
}
