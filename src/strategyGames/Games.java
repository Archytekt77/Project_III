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

	abstract protected int defenderMode();

	abstract protected int duelMode();

	abstract protected String secretTableCheck(int tab[]);
	
	public int selectGameMode() {
		int ret;
		int gameMode = 0;
		
		try {
			System.out.println("1: Mode Challenger; 2: Mode defenseur; 3: Mode duel");
			gameMode = sc.nextInt();
			
			if (gameMode == 1)
				ret = challengerMode();
			else if (gameMode == 2)
				ret = defenderMode();
			else if (gameMode == 3)
				ret = duelMode();
			else {
				sc = new Scanner(System.in);
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			selectGameMode();
		}
		return 0;
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
