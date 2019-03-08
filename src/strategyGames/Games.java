package strategyGames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import config.StrategyGamesGetPropertyValues;

public abstract class Games {

	protected Scanner sc = new Scanner(System.in);
	protected boolean developerMode;
	protected StrategyGamesGetPropertyValues prop;
	protected int size;
	protected int finalTurn;
	protected int maxNumber;
	protected int average;
	protected int[] secretComputerTable;
	protected int[] answerComputerTable;
	protected int[] secretHumanTable;
	protected int[] answerHumanTable;
	protected List<int[]> computerList;
	protected int[] sol;
	protected int rightPlace = 0;
	protected int wrongPlace = 0;
	protected int multiplier = 1;
	protected String bufferAnswerComputerTable;

	protected Logger logger = Logger.getLogger(Games.class);

	public Games() {

	}

	public Games(StrategyGamesGetPropertyValues prop) {
		this.prop = prop;
		size = Integer.parseInt(prop.getSize());
		finalTurn = Integer.parseInt(prop.getFinalTurn());
		maxNumber = Integer.parseInt(prop.getMaxNumber());
		developerMode = Boolean.parseBoolean(prop.getDeveloperMode());
		average = maxNumber / 2;
		sc = new Scanner(System.in);
	}

	abstract protected int[] computerAlgo(String s);

	abstract protected int[] firstDefaultComputerResponse();

	abstract protected String secretComputerTableCheck(int tab[]);

	abstract protected String secretHumanTableCheck(int tab[]);

	public int selectGameMode() {
		int ret = 0;
		int gameMode = 0;

		try {
			logger.info("Veuillez choisir le mode de jeu : Mode Challenger (1), Mode defenseur (2) ou Mode duel (3).");
			gameMode = sc.nextInt();

			if (gameMode == 1) {
				logger.info("Vous avez choisi le mode Challenger.");
				ret = challengerMode();
			} else if (gameMode == 2) {
				logger.info("Vous avez choisi le mode Défenseur.");
				ret = defenderMode();
			} else if (gameMode == 3) {
				logger.info("Vous avez choisi le mode Duel.");
				ret = duelMode();
			} else {
				sc = new Scanner(System.in);
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			logger.error("Mauvaise sélection pour le choix du mode.");
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

	protected int challengerMode() {
		String response = null;

		createComputerTable();

		for (int turn = 0; turn < finalTurn; turn++) {
			if (developerMode == true) {
				logger.info("Combinaison secrète : " + printTab(secretComputerTable));
			} else {
				;
			}
			logger.info("Tour " + (turn + 1) + "/" + finalTurn + ". Votre combinaison : ");
			response = secretComputerTableCheck(createAnswerHumanTable());
			logger.info(response);
			if (Arrays.equals(answerHumanTable, secretComputerTable)) {
				logger.info("Bravo ! Vous avez trouvé la solution en " + (turn + 1) + " tours !");
				break;
			}
		}
		return 1;
	}

	protected int defenderMode() {
		String computerResponse = null;

		createHumanTable();
		createComputerList();

		for (int turn = 0; turn < finalTurn; turn++) {
			if (turn == 0) {
				firstDefaultComputerResponse();
			} else
				computerAlgo(computerResponse);

			logger.info("Combinaison de l'ordinateur : " + printTab(answerComputerTable));
			computerResponse = secretHumanTableCheck(answerComputerTable);

			if (Arrays.equals(answerComputerTable, secretHumanTable)) {
				logger.info("L'ordinateur a trouvé la solution en " + (turn + 1) + " tours !");
				break;
			}
		}
		return 2;
	}

	protected int duelMode() {
		String response = null;
		String computerResponse = null;
		sc = new Scanner(System.in);
		createComputerTable();
		createHumanTable();
		createComputerList();

		for (int turn = 0; turn < finalTurn*2; turn++) {
			if (turn % 2 == 0) {
				if (developerMode == true) {
					logger.info("Combinaison secrète : " + printTab(secretComputerTable));
				} else {
					;
				}
				logger.info("Tour " + (turn + 2)/2 + "/" + finalTurn + ". Votre combinaison : ");
				response = secretComputerTableCheck(createAnswerHumanTable());
				logger.info(response);
				if (Arrays.equals(answerHumanTable, secretComputerTable)) {
					logger.info("Bravo ! Vous avez battu l'ordinateur en " + (turn / 2 + 1) + " tours !");
					break;
				}
			} else {
				logger.info("Tour " + (turn + 1)/2 + "/" + finalTurn + ". (Combinaison secrète : " + printTab(secretHumanTable));
				if (turn == 1) {
					firstDefaultComputerResponse();

				} else
					computerAlgo(computerResponse);

				logger.info("Combinaison de l'ordinateur : " + printTab(answerComputerTable));
				computerResponse = secretHumanTableCheck(answerComputerTable);
				if (Arrays.equals(answerComputerTable, secretHumanTable)) {
					logger.info(
							"Perdu ! L'ordinateur vous a battu en " + (turn / 2 + 1) + " tours ! La solution était : ");
					logger.info("La réponse était : " + printTab(secretComputerTable));
					break;
				}
			}
		}
		return 3;
	}

	protected int[] createComputerTable() {
		secretComputerTable = new int[size];

		for (int i = 0; i < secretComputerTable.length; i++) {
			secretComputerTable[i] = (int) (Math.random() * (maxNumber + 1));
		}
		return secretComputerTable;
	}

	protected List<int[]> createComputerList() {
		computerList = new ArrayList<>();
		sol = new int[size];
		computerList.add(bufferTab(sol));

		for (; end(sol);) {
			sol[sol.length - 1] += 1;
			if (sol[sol.length - 1] == maxNumber + 1)
				sol = reaffect(sol);
			computerList.add(bufferTab(sol));
		}

		if (maxNumber == 9) {
			createMultiplier();

			for (int i = 1; i < maxNumber + 1; i++) {
				Collections.swap(computerList, i, multiplier * i);
			}
		}
		return computerList;
	}

	protected int createMultiplier() {
		double tmp = 0;
		for (int i = 0; i < (size - 1); i++) {
			tmp = 10 * Math.pow(10, i);
			multiplier += tmp;
		}
		return multiplier;
	}

	protected int[] bufferTab(int[] tab) {
		int[] copy = new int[tab.length];

		for (int i = 0; i < tab.length; i++) {
			copy[i] = tab[i];
		}
		return copy;
	}

	protected int[] reaffect(int[] tab) {

		for (int i = tab.length - 1; i > 0; i--) {
			if (tab[i] == maxNumber + 1) {
				tab[i] = 0;
				tab[i - 1]++;
			}
		}
		return tab;
	}

	protected boolean end(int[] tab) {
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] != maxNumber)
				return true;
		}
		return false;
	}

	protected int[] createHumanTable() {
		String answer = "";
		sc = new Scanner(System.in);

		try {
			secretHumanTable = new int[size];
			logger.info("Veuillez taper votre combinaison secrète :");
			answer = sc.nextLine();
			secretHumanTable = verifyStringIntegrity(answer);
			if (secretHumanTable == null) {
				createHumanTable();
			}
		} catch (Exception e) {
			logger.error("La combinaison ne remplit pas les conditions des paramètres du jeu.");
		}

		logger.info("Votre combinaison secrète est : " + printTab(secretHumanTable));
		return secretHumanTable;
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
				if (Integer.parseInt(tmp) < 0 || Integer.parseInt(tmp) > maxNumber)
					throw new Exception();
			}
		} catch (Exception e) {
			logger.error("La combinaison ne remplit pas les conditions des paramètres du jeu.");
			return null;
		}
		return tab;
	}

	protected String printTab(int tab[]) {
		String ret = "";

		for (int i = 0; i < tab.length; i++) {
			ret += (tab[i]);
		}
		return ret;
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
