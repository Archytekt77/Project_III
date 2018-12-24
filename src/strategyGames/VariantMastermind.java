package strategyGames;

import java.util.Properties;
import java.util.Scanner;

public class VariantMastermind extends Games {

	private int[] secretTable;
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
			secretTable = new int[size];
			System.out.print("Veuillez taper votre combinaison secrète :");
			String answer = sc.nextLine();
			secretTable = verifyStringIntegrity(answer);
			if (secretTable == null) {
				createHumanTable();
			}
		} catch (Exception e) {

		}
		return secretTable;
	}

	/**
	 * Creation of table for the computer player
	 * 
	 * @return computerTable
	 */

	public int[] createComputerTable() {
		secretTable = new int[size];

		for (int i = 0; i < secretTable.length; i++) {
			secretTable[i] = (int) (Math.random() * (10));
		}
		return secretTable;
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
		String response;

		createComputerTable();
		
		for (i = 0; i < turn; i++) {
			response = secretTableCheck(answerTable);
			System.out.println(response);
			if (answerTable.toString().equals(secretTable.toString())) {
				System.out.println("bravo");
			}
		}

		return 0;
	}

	@Override
	protected int defenderMode() {
		createHumanTable();

		for (int i = 0; i < turn; i++) {
			System.out.print(secretTableCheck(answerTable()));
		}
		return 0;
	}

	@Override
	protected int duelMode() {
		createComputerTable();
		return 0;
	}

	@Override
	protected String secretTableCheck(int tab[]) {
		String ret = "";

		for (int i = 0; i < secretTable.length; i++) {
			if (secretTable[i] == tab[i]) {
				ret += "=";
			} else if (secretTable[i] > tab[i]) {
				ret += "+";
			} else if (secretTable[i] < tab[i]) {
				ret += "-";
			}
		}
		return ret;
	}
}
