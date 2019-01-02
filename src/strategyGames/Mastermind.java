package strategyGames;

import java.util.Arrays;
import java.util.Scanner;
import config.StrategyGamesGetPropertyValues;


public class Mastermind extends Games{
	
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

		for (int turn = 0; turn < finalTurn; turn++) {
			System.out.println("Votre combinaison : ");
			response = secretTableCheck(answerHumanTable());
			System.out.println(response);
			if (Arrays.equals(answerTable, secretTable)) {
				System.out.println("Bravo ! Vous avez trouvé la solution en " + (turn + 1) + " tours !");
				break;
			}
		}
		return 4;
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
		return 5;
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
		return 6;
	}

	@Override
	protected String secretTableCheck(int[] tab) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int[] computerAlgo(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
