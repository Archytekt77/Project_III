package strategyGames;

import java.util.InputMismatchException;
import java.util.Scanner;
import config.StrategyGamesGetPropertyValues;

public abstract class Games {

	public Scanner sc = new Scanner(System.in);
	protected StrategyGamesGetPropertyValues prop;
	protected int size;
	protected int finalTurn;
	protected int minNumber;
	protected int maxNumber;
	protected int[] secretComputerTable;
	protected int[] answerComputerTable;
	protected int[] secretHumanTable;
	protected int[] answerHumanTable;

	public Games() {

	}

	public Games(StrategyGamesGetPropertyValues prop) {
		this.prop = prop;
		size = Integer.parseInt(prop.getSize());
		finalTurn = Integer.parseInt(prop.getFinalTurn());
		minNumber = Integer.parseInt(prop.getMinNumber());
		maxNumber = Integer.parseInt(prop.getMaxNumber());
		sc = new Scanner(System.in);
	}

	abstract protected int challengerMode();

	abstract protected int defenderMode();

	abstract protected int duelMode();

	abstract protected int[] computerAlgo(String s);

	abstract protected String secretComputerTableCheck(int tab[]);

	abstract protected String secretHumanTableCheck(int tab[]);

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

	public int selectGameMode(int game) {
		if (game == 1)
			return challengerMode();
		else if (game == 2)
			return defenderMode();
		else
			return duelMode();
	}

	protected int[] createComputerTable() {
		secretComputerTable = new int[size];

		for (int i = 0; i < secretComputerTable.length; i++) {
			secretComputerTable[i] = minNumber + (int) (Math.random() * ((maxNumber - minNumber)+ 1));
		}
		return secretComputerTable;
	}

	protected int[] createHumanTable() {
		String answer = "";
		sc = new Scanner(System.in);

		try {
			secretHumanTable = new int[size];
			System.out.println("Veuillez taper votre combinaison secrète :");
			answer = sc.nextLine();
			secretHumanTable = verifyStringIntegrity(answer);
			if (secretHumanTable == null) {
				createHumanTable();
			}
		} catch (Exception e) {

		}
		return secretHumanTable;
	}

	protected int[] firstDefaultComputerResponse() {
		answerComputerTable = new int[size];

		for (int i = 0; i < size; i++) {
			answerComputerTable[i] = 5;
		}
		return answerComputerTable;
	}

	protected int[] createAnswerHumanTable() {
		String answer = sc.nextLine();

		answerHumanTable = verifyStringIntegrity(answer);
		if (answerHumanTable == null)
			return createAnswerHumanTable();

		return answerHumanTable;
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
