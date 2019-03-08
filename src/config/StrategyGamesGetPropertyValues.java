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
	private String maxNumber;
	private String developerMode;

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
			maxNumber = prop.getProperty("maxNumber");
			developerMode = prop.getProperty("developerMode");

			result = "Propriété du jeu = Réponse à " + size + " chiffres, limite en " + finalTurn + " tours, avec pour chiffre minimum 0 " + 
					" et chiffre maximum " + maxNumber + ". Activation du mode développeur : " + developerMode + ".";
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
	
	public String getMaxNumber() {
		return maxNumber;
	}

	public String getDeveloperMode() {
		return developerMode;
	}
}
