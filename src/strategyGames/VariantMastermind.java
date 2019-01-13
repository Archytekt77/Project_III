package strategyGames;

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
	protected int[] computerAlgo(String s) {
		for (int i = 0; i < secretHumanTable.length; i++) {
			if (s.charAt(i) == '=')
				;
			else if (s.charAt(i) == '+' && answerComputerTable[i] == 4)
				answerComputerTable[i] = 7;
			else if (s.charAt(i) == '-' && answerComputerTable[i] == 4)
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

		System.out.println("Correction :");
		ret = sc.nextLine();
		return ret;
	}
}
