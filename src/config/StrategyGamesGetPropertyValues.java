package config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;

public class StrategyGamesGetPropertyValues {

	String result = "";
	InputStream inputStream;

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
			String size = prop.getProperty("size");
			String turn = prop.getProperty("turn");
			String color = prop.getProperty("couleur");

			result = "Propriété du jeu = " + size + " taille, " + turn + " tours, " + color + " couleurs";
			System.out.println(result + "\nProgram Ran on " + time);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

}
