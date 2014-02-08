package euler3;

public class Euler3 {

	public static void main(String[] args) {
		long ans = 600851475143L;
		boolean prime = true;

		for (long i = 1L; i < ans / 2; i++) {
			if(ans % i == 0){
				prime = true;

				for (long j = 2L; j < i && prime ; j++) {
					if( i % j != 0){						
						prime = true;						
					}
					else {
						prime = false;
					}//end if 2


				}


			}//end if 1
			else{prime = false;}

			if(prime){
				System.out.println(i);
			}
		}


	}

}


