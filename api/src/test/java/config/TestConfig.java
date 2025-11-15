package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {

    private static final Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("src/test/resources/config/testconfig.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load config file", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
