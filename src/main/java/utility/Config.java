package utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Holds the configuration data.
 * This class is a singleton.
 */
public class Config {

    final static Logger logger = LoggerFactory.getLogger(Config.class);
    public static Config instance;

    // database
    private final String connectionUrl;
    private final String user;
    private final String password;

    /**
     * Instantiates all the configuration fields.
     *
     * @throws FileNotFoundException thrown when the file can't be found
     */
    private Config() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("config.yml");

        Yaml yaml = new Yaml();
        HashMap<String, Object> data = yaml.load(inputStream);

        this.connectionUrl = (String) data.get("connectionUrl");
        this.user = (String) data.get("user");
        this.password = (String) data.get("password");
    }

    /**
     * Returns the only existing instance of Config or creates a new one if no instance exists.
     */
    public static synchronized Config getInstance() {
        try {
            if (instance == null) {
                instance = new Config();
                logger.debug("config.yml file loaded in.");
            }
            return instance;
        } catch (FileNotFoundException e) {
            logger.error("config.yml file not found.", e);
            throw new IllegalStateException("config.yml file not found.");
        }
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
