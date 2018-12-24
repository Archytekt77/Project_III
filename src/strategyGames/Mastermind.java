package strategyGames;

import java.util.Properties;
import java.util.Scanner;


public class Mastermind extends Games{
	
	public Mastermind() {
		
	}
	
	public Mastermind(Properties prop) {
		super(prop);
		sc = new Scanner(System.in);
		
	}

	@Override
	protected int challengerMode() {
		return 4;
	}

	@Override
	protected int defenderMode() {
		return 5;
	}

	@Override
	protected int duelMode() {
		return 6;
	}

	@Override
	protected String secretTableCheck(int[] tab) {
		// TODO Auto-generated method stub
		return null;
	}

}
