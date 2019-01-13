package strategyGames;

import java.util.InputMismatchException;
import java.util.Scanner;

import config.StrategyGamesGetPropertyValues;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static Games game = null;

	public static void main(String[] args) {
		StrategyGamesGetPropertyValues properties = null;
		try {
			properties = new StrategyGamesGetPropertyValues();
			properties.getPropValues();
		} catch (Exception e) {

		}
		
		game = selectGame(properties);
		
		game.selectDeveloperMode();

		restart(game.selectGameMode(), game);
		sc.close();

	}
	
	public static void restart(int gameSelect, Games game) {
		int question = 0;

		try {
			System.out.println("");
			System.out.println("1 : Rejouer; 2: autre jeu; 3: quitter");
			question = sc.nextInt();

			switch (question) {
			case 1:
				System.out.println("NOUVELLE PARTIE");
				restart(game.selectGameMode(gameSelect), game);
				break;
			case 2:
				main(null);
				break;
			case 3:
				System.out.println("Au revoir !");
				System.exit(0);
			}
			if (question != 1 || question != 2 || question != 3) {
				sc = new Scanner(System.in);
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			restart(gameSelect, game);
		}
	}

	public static Games selectGame(StrategyGamesGetPropertyValues properties) {
		int select = 0;

		try {
			System.out.println("1: Recherche +/-; 2: Mastermind");
			select = sc.nextInt();

			if (select == 1)
				return new VariantMastermind(properties);
			else if (select == 2)
				return new Mastermind(properties);
			else {
				sc = new Scanner(System.in);
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			return selectGame(properties);
		}
	}
}
