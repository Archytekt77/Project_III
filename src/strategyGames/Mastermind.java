package strategyGames;

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
	protected int[] computerAlgo(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String secretComputerTableCheck(int[] tab) {
		String ret = "";
		int goodLocated = 0;
		int badlyLocated = 0;
		
		//231
		//121

		for (int i = 0; i < secretComputerTable.length; i++) {
			if (secretComputerTable[i] == tab[i]) {
				tab[i] = -1;
				goodLocated++;
			} else {
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
