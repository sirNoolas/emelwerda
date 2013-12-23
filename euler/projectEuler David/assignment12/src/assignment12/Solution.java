package assignment12;

public class Solution {

	public static void main(String[] args) {
		int neededDevisors = 500;
		long potNum = 0;
		int addNum = 1;
		long amountOfDevisors = 1L;

		while(amountOfDevisors < neededDevisors){
			potNum += addNum;
			addNum ++;

			amountOfDevisors = 1L;

			if(potNum % 4 == 0){
				for(long i = 1L; i <= (potNum / 2); i++){
					if(potNum % i == 0){
						amountOfDevisors ++;
					}
				} // end of for
			}
		} // end of while

		System.out.println(potNum);
		System.out.println("DONE");
		
	}

}
