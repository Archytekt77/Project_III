package strategyGames;

import java.util.Properties;
import java.util.Scanner;

public class VariantMastermind extends Games {

	private int[] computerTable;
	private int[] humanTable;
	private int[] answerTable;
	private int size;
	private int turn;

	public VariantMastermind() {

	}

	public VariantMastermind(Properties prop) {
		super(prop);
		size = Integer.parseInt(prop.getProperty("size"));
		turn = Integer.parseInt(prop.getProperty("turn"));
		sc = new Scanner(System.in);
	}

	/**
	 * Creation of table for human player
	 *
	 * @return humanTable
	 * @throws IOException
	 */
	public int[] createHumanTable() {
		try {
			humanTable = new int[size];
			System.out.print("Veuillez taper votre combinaison secrète :");
			String answer = sc.nextLine();
			humanTable = verifyStringIntegrity(answer);
			if (humanTable == null) {
				createHumanTable();
			}
		} catch (Exception e) {

		}
		return humanTable;
	}

	/**
	 * Creation of table for the computer player
	 * 
	 * @return computerTable
	 */

	public int[] createComputerTable() {
		computerTable = new int[size];

		for (int i = 0; i < computerTable.length; i++) {
			computerTable[i] = (int) (Math.random() * (10));
		}
		return computerTable;
	}

	/**
	 * Response from the human player
	 * 
	 * @return answerTable
	 */

	public int[] answerTable() {
		String answer = sc.nextLine();

		answerTable = verifyStringIntegrity(answer);

		if (answerTable == null)
			return answerTable();

		return answerTable;
	}
	
	
	private void computerAlgo() {
		
	}

	private int[] verifyStringIntegrity(String s) {
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

	@Override
	protected int challengerMode() {
		int i;

		createComputerTable();

		for (i = 0; i < turn; i++) {
			String response = computerTableCheck(answerTable);
			System.out.println(response);
			if (answerTable.toString().equals(computerTable.toString())) {
				System.out.println("bravo");
				break;
			}
		}

		return 0;
	}

	@Override
	protected int defenderMode() {
		createHumanTable();

		for (int i = 0; i < turn; i++) {
			System.out.print(computerTableCheck(answerTable()));
		}
		return 0;
	}

	@Override
	protected int duelMode() {
		createComputerTable();
		return 0;
	}

	@Override
	protected String computerTableCheck(int tab[]) {
		String ret = "";

		for (int i = 0; i < computerTable.length; i++) {
			if (computerTable[i] == tab[i]) {
				ret += "=";
			} else if (computerTable[i] > tab[i]) {
				ret += "+";
			} else if (computerTable[i] < tab[i]) {
				ret += "-";
			}
		}
		return ret;
	}
}
