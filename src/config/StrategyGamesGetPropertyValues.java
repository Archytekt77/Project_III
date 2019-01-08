package config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;

public class StrategyGamesGetPropertyValues {

	private String result = "";
	private InputStream inputStream;
	private String size;
	private String finalTurn;
	private String minNumber;
	private String maxNumber;

	public String getPropValues() throws IOException {

		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			Date time = new Date(System.currentTimeMillis());

			// get the property value and print it out
			size = prop.getProperty("size");
			finalTurn = prop.getProperty("finalTurn");
			minNumber = prop.getProperty("minNumber");
			maxNumber = prop.getProperty("maxNumber");

			result = "Propriété du jeu = Réponse à " + size + " chiffres, limite en " + finalTurn + " tours, avec pour chiffre minimum " + minNumber + " et chiffre maximum " + maxNumber + ".";
			System.out.println(result + "\nProgram Ran on " + time);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

	public String getSize() {
		return size;
	}

	public String getFinalTurn() {
		return finalTurn;
	}

	public String getMinNumber() {
		return minNumber;
	}
	
	public String getMaxNumber() {
		return maxNumber;
	}

}
