package euler1;

public class Euler {

	public static void main(String[] args) {
	 
		
		int ans = 1;
		int som = 0;
		
		while(ans < 1000){
			if(ans % 3 == 0 || ans % 5 == 0){
				som += ans;
				System.out.println(ans);}
			ans++;
			}
		System.out.println("Sum is: " + som);
		
		
		
		
	}

}
