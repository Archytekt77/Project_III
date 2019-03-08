package strategyGames;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import config.StrategyGamesGetPropertyValues;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static Games game = null;

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		StrategyGamesGetPropertyValues properties = null;

		try {
			properties = new StrategyGamesGetPropertyValues();
			properties.getPropValues();
		} catch (Exception e) {
			logger.error("Erreur de propriété");
		}

		game = selectGame(properties);

		restart(game.selectGameMode(), game);
		sc.close();

	}

	public static void restart(int gameSelect, Games game) {
		int question = 0;

		try {
			logger.info("Veuillez choisir entre : Rejouer (1), Jouer à un autre jeu (2), ou quitter (3).");
			question = sc.nextInt();

			switch (question) {
			case 1:
				logger.info("NOUVELLE PARTIE");
				restart(game.selectGameMode(gameSelect), game);
				break;
			case 2:
				main(null);
				break;
			case 3:
				logger.info("Au revoir !");
				System.exit(0);
			}
			if (question != 1 || question != 2 || question != 3) {
				sc = new Scanner(System.in);
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			logger.error("Mauvaise sélection pour la sélection de fin de partie.");
			restart(gameSelect, game);
		}
	}

	public static Games selectGame(StrategyGamesGetPropertyValues properties) {
		int select = 0;

		try {
			logger.info("A quel jeu voulez vous jouer ? Recherche +/- (1) ou Mastermind (2).");
			select = sc.nextInt();

			if (select == 1) {
				logger.info("Vous avez choisi le jeu \"Recherche +/-\".");
				return new VariantMastermind(properties);
			}

			else if (select == 2) {
				logger.info("Vous avez choisi le jeu \"Mastermind\".");
				return new Mastermind(properties);
			} else {
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			logger.error("Mauvaise sélection pour le choix du jeu.");
			sc = new Scanner(System.in);
			return selectGame(properties);
		}
	}
}
