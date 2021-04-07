import java.security.SecureRandom;

/**
 * Class for simulating tortoise and hare using random number
 *
 * @author Md Sakil Khan
 * @version 1.0
 */
public class TortoiseAndHare{
	
	/** 
	 * Holds the length of track 
	 */
	int MAX_MOVES;
	
	/** 
	 * Holds the timeout limit 
	 */
	int MAX_ITERATIONs;			
	
	/** 
	 * Holds the current position of tortoise on track 
	 */
	int TortoisePos;				
	
	/** 
	 * Holds the current position of hare on track 
	 */
	int HarePos;					
	
	/** 
	 * Holds the time/number of iterations 
	 */
	int secTime;		
	
	/** 
	 * Records why the while loop is broken (win, tie, timeout) 
	 */
	String breakMessage;
	
	/** 
	 * Default constructor with default value for variable. 
	 * The also run a while loop to simulate the race. First it prints the start message using print 
	 * format. Then inside the loop prints the current iteration number. then calls the printPosition 
	 * function and increments secTime. The if statments list all the ways the race can end and depending 
	 * on why it ends or who wins will be stored in the breakMessage string.
	 * The two simulation function are called at the end to simulate for next iteration.
	 * Finally prints the end message with who won and time taken.
	 */ 
	public TortoiseAndHare() { 		
		this.MAX_MOVES = 100;
		this.MAX_ITERATIONs = 2000;		
		this.TortoisePos = 1;
		this.HarePos = 1;
		this.secTime = 0;
		this.breakMessage = null;

		System.out.printf("%n%s%n%s%n%s%n", 
				"ON YOUR MARK, GET SET", 
				"BANG !!!!!",
				"AND THEY'RE OFF !!!!!");
			
		while(true) {
			System.out.printf("%n%s%d%n", "Iteration: ", secTime);
			printPosition();
			secTime += 1;
			
			if (TortoisePos == HarePos && HarePos >= MAX_MOVES) {	
				breakMessage = "It's a tie!";
				break;
			}
			else if (TortoisePos >= MAX_MOVES) {
				breakMessage = "Tortoise Wins!!! Yay!!!";
				break;
			}
			else if (HarePos >= MAX_MOVES) {
				breakMessage = "Hare Wins. Yuch!";
				break;
			}
			if (secTime >= MAX_ITERATIONs) {
				if (HarePos == TortoisePos) {
					breakMessage = String.format("%s%n%s", 
						"Timeout!", "It's a tie!");
				}
				if (HarePos < TortoisePos) {
					breakMessage = String.format("%s%n%s", 
						"Timeout!", "Tortoise Wins!!! Yay!!!");
				}
				if (HarePos > TortoisePos) {
					breakMessage = String.format("%s%n%s", 
						"Timeout!", "Hare Wins. Yuch!");
				}
				break;
			}
			
			simulateTortoiseMove(); 
			simulateHareMove();
		}
		
		System.out.printf("%n%s%n%s%d%s%n", 
				breakMessage, 
				"Time Elapsed = ", secTime-1, " seconds");	
	}
	
	/** 
	 * The main function creates an instance of this class using default constructor to run simulation.
	 */ 	
	public static void main(String []args){
		TortoiseAndHare run = new TortoiseAndHare();
    }
	
	/**	
	 * This function decides who is in last place, prints that - 1 amount of spaces/empty 
	 * then print who is in last T/H. Next prints spaces (first places contender - last place - 1).
	 * Followed by who is in last T/H. If both are in the same place then B is printed after spaces
	 * of any contender position.
	 * Finally prints the 100 dashes representing the track.
	 */
	private void printPosition() {
		if (TortoisePos == HarePos) {
			for (int i = 0; i < (TortoisePos - 1); i++) {
				System.out.printf("%s", " ");	
			}
			System.out.printf("%s", "B");
		}
		if (TortoisePos < HarePos) {
			for (int i = 0; i < (TortoisePos - 1); i++) {
				System.out.printf("%s", " ");
			}
			System.out.printf("%s", "T");
			for (int i = 0; i < (HarePos - TortoisePos - 1); i++) {
				System.out.printf("%s", " ");
			}	
			System.out.printf("%s", "H");
		}
		if (TortoisePos > HarePos) {
			for (int i = 0; i < (HarePos - 1); i++) {
				System.out.printf("%s", " ");
			}
			System.out.printf("%s", "H");
			for (int i = 0; i < (TortoisePos - HarePos - 1); i++) {
				System.out.printf("%s", " ");
			}	
			System.out.printf("%s", "T");			
		}
		System.out.printf("%n");
		for (int i = 0; i < MAX_MOVES; i++) {	
			System.out.printf("%s", "-");
		}
	}
	
	/**	
	 * This function takes in two int values and returns a random number between them (inclusive)
	 * using SecureRandom and math max/min functions.
	 * New instance of SecureRandom is created.
	 * Max and min values are stored in different variables to get ready for the nextInt function.
	 *
	 * @param a one limit of the range
	 * @param b another limit of the range
     * @return a random number between the parameters
	 */
	private int randomBetween(int a, int b) {
		SecureRandom randNum = new SecureRandom();
		int U = Math.max(a, b);		
		int L = Math.min(a, b);
		return randNum.nextInt(U-L+1)+L;
	}
	
	/** 
	 * This function uses switch conditons and randomBetween function to simulate the tortoise movement 
	 * behavior. Example: since four cases preform this action meaning there is a 4/10 (40%) chance for 
	 * this action to be preformed.
	 * The two if statements are for checking boundaries and preventing the values from going out-of-bound.
	 */
	private void simulateTortoiseMove() {
		int num = randomBetween(0,9); 
		switch (num) {
			case 1: case 2: case 3: case 4: 
				TortoisePos += randomBetween(1,3);
				break;
            
			case 5: case 6: case 7:
				TortoisePos -= randomBetween(1,6);
				break;
            
			case 8: case 9:
				TortoisePos += randomBetween(0,1);
				break;
		}		
		
		if (TortoisePos < 1) {
			TortoisePos = 1;
		}
		if (TortoisePos > MAX_MOVES) {
			TortoisePos = MAX_MOVES;
		}		
	}

    /**
     * This function is essentially the same as simulateTortoiseMove with changes in the switch conditions 
     * to follow hare movement behavior.
	 * The two if statements are for checking boundaries and preventing the values from going out-of-bound.
	 */
	private void simulateHareMove() {
		int num = randomBetween(0,9);
		switch (num) {
            case 1: case 2: case 3:
				HarePos += randomBetween(1,5);
				break;
            case 4: case 5: 
				HarePos -= randomBetween(1,2);
				break;
            case 6: 
				HarePos -= randomBetween(1,7);
				break;
			case 7: case 8: case 9:
				HarePos += randomBetween(0,1);
				break;
		}
		
		if (HarePos < 1) {
			HarePos = 1;
		}
		if (HarePos > MAX_MOVES) {
			HarePos = MAX_MOVES;
		}
	}
}