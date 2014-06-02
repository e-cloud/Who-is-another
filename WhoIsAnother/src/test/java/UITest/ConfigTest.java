package UITest;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// DefaultConfigurationBuilder builder = new
			// DefaultConfigurationBuilder();
			// builder.setFile(new File("/config.xml"));
			//
			// Configuration config = builder.getConfiguration(true);
			// System.out.println(config.getString("defaultclassification"));
			// config.setProperty("defaultclassification", "new");
			// System.out.println(config.getString("defaultclassification"));
			// builder.save();
			//
			// DefaultConfigurationBuilder builder2 = new
			// DefaultConfigurationBuilder();
			// builder2.setFile(new File("/config.xml"));
			// Configuration config2 = builder.getConfiguration(true);
			// System.out.println(config2.getString("defaultclassification"));

			// Properties properties = new Properties();
			// properties.load(ConfigTest.class
			// .getResourceAsStream("application.properties"));
			// System.out.println(properties.getProperty("defaultclassification"));
			// properties.setProperty("defaultclassification", "good");
			// properties.store(new
			// FileOutputStream("./application.properties"),
			// null);

			PropertiesConfiguration config3 = new PropertiesConfiguration(
					"application.properties");
			System.out.println(config3.getString("welcome.file"));
			config3.setProperty("defaultclassification", "new");
			System.out.println(config3.getString("defaultclassification"));
			config3.save();

			PropertiesConfiguration config4 = new PropertiesConfiguration(
					"application.properties");
			System.out.println(config4.getString("defaultclassification"));

		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
