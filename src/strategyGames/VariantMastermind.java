package strategyGames;

import java.util.Arrays;
import java.util.Scanner;
import config.StrategyGamesGetPropertyValues;

public class VariantMastermind extends Games {

	public VariantMastermind() {

	}

	public VariantMastermind(StrategyGamesGetPropertyValues prop) {
		super(prop);
		sc = new Scanner(System.in);
	}

	@Override
	protected int challengerMode() {
		String response = null;

		createComputerTable();

		for (int turn = 0; turn < finalTurn; turn++) {
			System.out.println("Votre combinaison à " + size + " chiffres :");
			response = secretComputerTableCheck(createAnswerHumanTable());
			System.out.println("Correction : " + response);
			if (Arrays.equals(answerHumanTable, secretComputerTable)) {
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
			if (turn == 0) {
				firstDefaultComputerResponse();
			} else
				computerAlgo(response);

			System.out.println("Combinaison de l'ordinateur :");
			printTab(answerComputerTable);
			response = secretHumanTableCheck(answerComputerTable);

			if (Arrays.equals(answerComputerTable, secretHumanTable)) {
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
		createHumanTable();

		for (int turn = 0; turn < finalTurn; turn++) {
			if (turn % 2 == 0) {
				System.out.println("Votre combinaison : ");
				response = secretComputerTableCheck(createAnswerHumanTable());
				System.out.println(response);
				if (Arrays.equals(answerHumanTable, secretComputerTable)) {
					System.out.println("Bravo ! Vous avez battu l'ordinateur en " + (turn / 2 + 1) + " tours !");
					break;
				}
			} else {
				System.out.println("Combinaison de l'ordinateur : ");
				if (turn == 1)
					firstDefaultComputerResponse();
				else
					computerAlgo(computerResponse);
				
				System.out.println("Combinaison de l'ordinateur :");
				printTab(answerComputerTable);
				computerResponse = secretHumanTableCheck(answerComputerTable);
				if (Arrays.equals(answerComputerTable, secretHumanTable)) {
					System.out.println("Perdu ! L'ordinateur vous a battu en " + (turn / 2 + 1) + " tours !");
					break;
				}
			}
		}
		return 3;
	}
	
	@Override
	protected int[] computerAlgo(String s) {
		for (int i = 0; i < secretHumanTable.length; i++) {
			if (s.charAt(i) == '=')
				;
			else if (s.charAt(i) == '+' && answerComputerTable[i] == 5)
				answerComputerTable[i] = 7;
			else if (s.charAt(i) == '-' && answerComputerTable[i] == 5)
				answerComputerTable[i] = 2;
			else if (s.charAt(i) == '+')
				answerComputerTable[i] += 1;
			else
				answerComputerTable[i] -= 1;
		}
		return answerComputerTable;
	}

	@Override
	protected String secretComputerTableCheck(int tab[]) {
		String ret = "";

		for (int i = 0; i < secretComputerTable.length; i++) {
			if (secretComputerTable[i] == tab[i]) {
				ret += "=";
			} else if (secretComputerTable[i] > tab[i]) {
				ret += "+";
			} else if (secretComputerTable[i] < tab[i]) {
				ret += "-";
			}
		}
		return ret;
	}

	@Override
	protected String secretHumanTableCheck(int[] tab) {
		String ret = "";
		sc = new Scanner(System.in);

		System.out.println("Correction :");
		ret = sc.nextLine();

		return ret;
	}
}
