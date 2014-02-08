package euler2;

public class Euler2 {

	public static void main(String[] args) {
		
		int sum = 0;
		int een = 0;
		int twee = 1;
		int drie = een + twee;
		
	    while(drie < 4000000){
	    	if(drie % 2 == 0){
	    		sum += drie;
	    		System.out.println(drie);
	    	}
	    	 een = twee;
	    	 twee = drie;
	    	 drie = een + twee;}
System.out.println("Sum is: " + sum);
	}

}
