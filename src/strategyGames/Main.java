package strategyGames;

import java.util.InputMismatchException;
import java.util.Scanner;

import config.StrategyGamesGetPropertyValues;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		StrategyGamesGetPropertyValues properties = new StrategyGamesGetPropertyValues();
		try {
		properties.getPropValues();
		}catch (Exception e) {
			
		}
		Games game;

		game = selectGame();
		game.selectGameMode();

		restart();
		sc.close();

	}

	public static void restart() {
		int question = 0;

		try {
			System.out.println("1 : Rejouer; 2: autre jeu; 3: quitter");
			question = sc.nextInt();

			switch (question) {
			case 1:
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("NOUVELLE PARTIE");
			case 2:
				main(null);
			case 3:
				System.out.println("Au revoir !");
				System.exit(0);
			}
			if (question != 1 || question != 2 || question != 3) {
				sc = new Scanner(System.in);
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			restart();
		}
	}

	public static Games selectGame() {
		int select = 0;

		try {
			System.out.println("1: Recherche +/-; 2: Mastermind");
			select = sc.nextInt();

			if (select == 1)
				return new VariantMastermind();
			else if (select == 2)
				return new Mastermind();
			else {
				sc = new Scanner(System.in);
				throw new InputMismatchException();
			}
		} catch (InputMismatchException e) {
			return selectGame();
		}
	}
}
