package strategyGames;

import java.util.Arrays;
import java.util.Scanner;
import config.StrategyGamesGetPropertyValues;

public class Mastermind extends Games {

	public Mastermind() {

	}

	public Mastermind(StrategyGamesGetPropertyValues prop) {
		super(prop);
		sc = new Scanner(System.in);

	}

	@Override
	protected int challengerMode() {
		String response = null;

		createComputerTable();

		printTab(secretComputerTable);

		for (int turn = 0; turn < finalTurn; turn++) {
			System.out.println("Votre combinaison : ");
			response = secretComputerTableCheck(createAnswerHumanTable());
			System.out.println(response);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String secretComputerTableCheck(int[] tab) {
		String ret = "";
		int goodLocated = 0;
		int badlyLocated = 0;

		for (int i = 0; i < secretComputerTable.length; i++) {
			if (secretComputerTable[i] == tab[i])
				goodLocated++;
			else {
				int j = 0;
				for (int k = 0; k < secretComputerTable.length; k++) {
					if (secretComputerTable[j] == tab[k])
						badlyLocated++;
				}
			}
		}

		/*
		 * for (int j = 0; j < secretComputerTable.length; j++) { if
		 * (secretComputerTable[j] != tab[j]) { for (int k = 0; k <
		 * secretComputerTable.length; k++) { if (secretComputerTable[j] == tab[k])
		 * badlyLocated++; } } }
		 */

		ret = "Chiffre(s) bien placé(s) " + goodLocated + ", chiffre(s) mal placé(s) " + badlyLocated;
		return ret;

	}

	@Override
	protected String secretHumanTableCheck(int[] tab) {
		String ret = "";
		int goodLocated = 0;
		int badlyLocated = 0;
		sc = new Scanner(System.in);

		System.out.println("Chiffre(s) bien placé(s) :");
		goodLocated = sc.nextInt();

		System.out.println("Chiffre(s) bon(s) mais mal placé(s)");
		badlyLocated = sc.nextInt();

		ret = "Chiffre(s) bien placé(s) " + goodLocated + ", chiffre(s) mal placé(s) " + badlyLocated;
		return ret;
	}

}
