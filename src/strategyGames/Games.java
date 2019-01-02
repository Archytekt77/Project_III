package strategyGames;

import java.util.InputMismatchException;
import java.util.Scanner;
import config.StrategyGamesGetPropertyValues;


public abstract class Games {

	public Scanner sc = new Scanner(System.in);
	protected StrategyGamesGetPropertyValues prop;
	protected int size;
	protected int finalTurn;
	protected int[] secretTable;
	protected int[] answerComputerTable;
	protected int[] answerTable;
	
	public Games() {
		
	}
	
	public Games(StrategyGamesGetPropertyValues prop) {
		this.prop = prop;
		size = Integer.parseInt(prop.getSize());
		finalTurn = Integer.parseInt(prop.getFinalTurn());
		sc = new Scanner(System.in);
	}
	
	abstract protected int challengerMode();

	abstract protected int defenderMode();

	abstract protected int duelMode();
	
	abstract protected int[] computerAlgo(String s);

	abstract protected String secretTableCheck(int tab[]);
	
	public int selectGameMode() {
		int ret = 0;
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
		return ret;
	}
	
	protected int[] createComputerTable() {
		secretTable = new int[size];

		for (int i = 0; i < secretTable.length; i++) {
			secretTable[i] = (int) (Math.random() * (10));
		}
		return secretTable;
	}
	
	protected int[] createHumanTable() {
		String answer = "";

		try {
			secretTable = new int[size];
			System.out.println("Veuillez taper votre combinaison secrète :");
			answer = sc.nextLine();
			secretTable = verifyStringIntegrity(answer);
			if (secretTable == null) {
				createHumanTable();
			}
		} catch (Exception e) {

		}
		return secretTable;
	}
	
	protected int[] firstDefaultComputerResponse() {
		answerComputerTable = new int[size];

		for (int i = 0; i < size; i++) {
			answerComputerTable[i] = 5;
		}
		return answerComputerTable;
	}
	
	protected int[] answerHumanTable() {
		String answer = sc.nextLine();

		answerTable = verifyStringIntegrity(answer);
		if (answerTable == null)
			return answerHumanTable();

		return answerTable;
	}
	
	protected int[] verifyStringIntegrity(String s) {
		int tab[] = new int[size];

		if (s.length() != size)
			return null;
		try {
			for (int i = 0; i < s.length(); i++) {
				String tmp = "";
				tmp += s.charAt(i);
				tab[i] = Integer.parseInt(tmp);
			}
		} catch (Exception e) {
			return null;
		}
		return tab;
	}
	
	protected void printTab(int tab[]) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i]);
		}
		System.out.println("");
	}

	/**
	 * @return the prop
	 */
	public StrategyGamesGetPropertyValues getProp() {
		return prop;
	}

	/**
	 * @param prop the prop to set
	 */
	public void setProp(StrategyGamesGetPropertyValues prop) {
		this.prop = prop;
	}

	/* public void gameParameter() */

}
