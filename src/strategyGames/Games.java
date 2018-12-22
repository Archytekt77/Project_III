package strategyGames;

import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;


public abstract class Games {

	public Scanner sc = new Scanner(System.in);
	protected Properties prop;
	
	public Games() {
		
	}
	
	public Games(Properties prop) {
		this.prop = prop;
		sc = new Scanner(System.in);
	}

	abstract protected int challengerMode();

	abstract protected int defenderMode(); // faire un algo

	abstract protected int duelMode();

	abstract protected String computerTableCheck(int tab[]);
	
	public void selectGameMode() {
		//int ret;
		int gameMode = 0;
		
		try {
			System.out.println("1: Mode Challenger; 2: Mode defenseur; 3: Mode duel");
			gameMode = sc.nextInt();
			
			if (gameMode == 1)
				challengerMode();
				//ret = challengerMode();
			else if (gameMode == 2)
				defenderMode();
				//ret = defenderMode();
			else if (gameMode == 3)
				duelMode();
				//ret = duelMode();
			else {
				sc = new Scanner(System.in);
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			selectGameMode();
		}
	}

	/**
	 * @return the prop
	 */
	public Properties getProp() {
		return prop;
	}

	/**
	 * @param prop the prop to set
	 */
	public void setProp(Properties prop) {
		this.prop = prop;
	}

	/* public void gameParameter() */

}
