package com.benpiotti.cs252lab6.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {


    private static final Logger log = LoggerFactory.getLogger(Config.class);

    private static Config instance = new Config();

    Properties props = null;
    String environment = null;

    public static Config getInstance() {
        return instance;
    }

    private Config() {
        loadApplicationProperties();
    }

    public static Properties getProps() {
        return getInstance().props;
    }

    public String getValue(String key) {

        return props.getProperty(key);
    }

    public int getIntValue(String key) {

        return Integer.parseInt(props.getProperty(key).trim());
    }

    public boolean getBooleanValue(String key) {
        return  Boolean.parseBoolean(key);
    }

    public String getValue(String key, String defaultValue) {

        return props.getProperty(key,defaultValue);
    }

    public static String getEnvironment() {
        return getInstance().environment;
    }

    private void loadApplicationProperties() {

        Properties prop = new Properties();
        InputStream input = null;

        this.environment = System.getProperty("APP_ENV");
        if (this.environment == null) {
            this.environment = "dev.properties";
        }
        this.environment = this.environment.toLowerCase();
        log.info("loading properties for environment {}", this.environment);

        try {

            String filename = "default.properties";
            input = Config.class.getClassLoader().getResourceAsStream(filename);
            if(input==null) {
                log.error("Unable to load application properties ");
            }

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            //log.info(prop.getProperty("property1"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        props = prop;
        //return prop;

    }

}
