package strategyGames;

import java.util.Arrays;
import java.util.Scanner;
import config.StrategyGamesGetPropertyValues;

public class VariantMastermind extends Games {

	//private int[] secretTable;
	//private int[] answerTable;
	//private int[] answerComputerTable;
	//private int size;
	//private int finalTurn;

	public VariantMastermind() {

	}

	public VariantMastermind(StrategyGamesGetPropertyValues prop) {
		super(prop);
		//size = Integer.parseInt(prop.getSize());
		//finalTurn = Integer.parseInt(prop.getFinalTurn());
		sc = new Scanner(System.in);
	}

	/*private int[] createHumanTable() {
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
	}*/


	/*private int[] createComputerTable() {
		secretTable = new int[size];

		for (int i = 0; i < secretTable.length; i++) {
			secretTable[i] = (int) (Math.random() * (10));
		}
		return secretTable;
	}*/

	/*private int[] answerHumanTable() {
		String answer = sc.nextLine();

		answerTable = verifyStringIntegrity(answer);
		if (answerTable == null)
			return answerHumanTable();

		return answerTable;
	}*/

	/*private void firstDefaultComputerResponse() {
		answerComputerTable = new int[size];

		for (int i = 0; i < size; i++) {
			answerComputerTable[i] = 5;
		}
	}*/
	
	@Override
	protected int[] computerAlgo(String s) {
		for (int i = 0; i < secretTable.length; i++) {
			if (s.charAt(i) == '=')
				;
			else if (s.charAt(i) == '+')
				answerComputerTable[i] += 1;
			else
				answerComputerTable[i] -= 1;
		}
		return answerComputerTable;
	}

	/*private int[] verifyStringIntegrity(String s) {
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
	}*/

	/*private void printTab(int tab[]) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i]);
		}
		System.out.println("");
	}*/

	@Override
	protected int challengerMode() {
		String response = null;

		createComputerTable();

		for (int turn = 0; turn < finalTurn; turn++) {
			System.out.println("Votre combinaison : ");
			response = secretTableCheck(answerHumanTable());
			System.out.println(response);
			if (Arrays.equals(answerTable, secretTable)) {
				System.out.println("Bravo ! Vous avez trouvé la solution en " + (turn + 1) + " tours !");
				break;
			}
		}

		return 1;
	}

	@Override
	protected int defenderMode() {
		String response = null;

		createHumanTable();

		for (int turn = 0; turn < finalTurn; turn++) {
			if (turn == 0)
				firstDefaultComputerResponse();
			else
				computerAlgo(response);
			
			//printTab(secretTable);
			response = secretTableCheck(answerComputerTable);
			printTab(answerComputerTable);
			System.out.println(response);
			if (Arrays.equals(answerComputerTable, secretTable)) {
				System.out.println("L'ordinateur a trouvé la solution en " + (turn + 1) + " tours !");
				break;
			}
		}
		return 2;
	}

	@Override
	protected int duelMode() {
		String response = null;
		String computerResponse = null;
		createComputerTable();

		for (int turn = 0; turn < finalTurn; turn++) {
			if (turn % 2 == 0) {
				System.out.println("Votre combinaison : ");
				response = secretTableCheck(answerHumanTable());
				System.out.println(response);
				if (Arrays.equals(answerTable, secretTable)) {
					System.out.println("Bravo ! Vous avez battu l'ordinateur en " + (turn/2 + 1) + " tours !");
					break;
				}
			} else {
				//System.out.println("Combinaison de l'ordinateur : ");
				if (turn == 1)
					firstDefaultComputerResponse();
				else
					computerAlgo(computerResponse);
				computerResponse = secretTableCheck(computerAlgo(computerResponse));
				//printTab(answerComputerTable);
				//System.out.println(computerResponse);
				if (Arrays.equals(answerComputerTable, secretTable)) {
					System.out.println("Perdu ! L'ordinateur vous a battu en " + (turn/2 + 1) + " tours !");
					break;
				}
			}
		}
		return 3;
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
