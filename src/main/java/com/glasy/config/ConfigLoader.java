package com.glasy.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try {
            String configFile = "config/application.properties";
            FileInputStream input = new FileInputStream(configFile);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getKey(String key) {
        return properties.getProperty(key);
    }
}