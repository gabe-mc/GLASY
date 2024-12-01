package data_access;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loader for app configurations that stores api keys.
 */
public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        final String configFile = "src/main/resources/application.properties";
        try (FileInputStream input = new FileInputStream(configFile)) {
            properties.load(input);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Returns the API key from the configuration file.
     * @param key name of the api
     * @return api key
     */
    public static String getKey(String key) {
        return properties.getProperty(key);
    }
}
