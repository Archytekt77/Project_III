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
			if (s.charAt(i) == '=' || answerComputerTable[i] == 0 || answerComputerTable[i] == maxNumber)
				;
			else if (s.charAt(i) == '+' && answerComputerTable[i] == average)
				answerComputerTable[i] = (average + maxNumber) / 2;
			else if (s.charAt(i) == '-' && answerComputerTable[i] == average)
				answerComputerTable[i] = average / 2;
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
		try {
			logger.info("Correction :");
			ret = sc.nextLine();

			for (int i = 0; i < ret.length(); i++) {
				if (ret.charAt(i) != '=' && ret.charAt(i) != '+' && ret.charAt(i) != '-')
					throw new Exception();
			}

			if (ret.length() != size)
				throw new Exception();
		} catch (Exception e) {
			sc = new Scanner(System.in);
			secretHumanTableCheck(tab);
		}

		return ret;
	}

	@Override
	protected int[] firstDefaultComputerResponse() {
		answerComputerTable = new int[size];

		for (int i = 0; i < size; i++) {
			answerComputerTable[i] = average;
		}
		return answerComputerTable;
	}
}
