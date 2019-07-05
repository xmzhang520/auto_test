package com.demo.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is for loading test parameters from Environment Variables or from application.properties
 */

public class TestConfig {

    private static Logger logger = LoggerFactory.getLogger(TestConfig.class);
    private static String api_url;
    private static String environment;
    private static String environment_url;
    private static String browser;
    private static String grid_url;

    // initiate test parameters
    static {
        setEnvironment();
        // below settings depends on value of "environment"
        setEnvironmentURL();
        setApiURL();

        //setup browser
        setBrowser();
        setGridURL();
    }

    /**
     * load default.env from application.properties when environment parameter ${environment} isn't given
     *
     * @return
     */
    private static void setEnvironment() {
        if (environment == null) {
            environment = readProperties("environment", "default.env");
            logger.info("environment = {}", environment);
        }
    }

    public static String getEnvironment() {
        return environment;
    }

    public static String getEnvironmentURL() {
        return environment_url;
    }

    private static void setEnvironmentURL() {
        environment_url = PropertiesReader.readAppProperty(getEnvironment());
        logger.info("Web URL: {}", environment_url);
    }


    public static String getApiURL() {
        return api_url;
    }

    /**
     * load default.api from application.properties when environment parameter ${api} isn't given
     *
     * @return
     */
    private static void setApiURL() {
        // to check if system parameter defines which parameter to used for api URL
        String apiPara = System.getProperty("api");

        // to check from which parameter to load api URL
        String apiEnv = (apiPara == null || apiPara.isEmpty())
                ? PropertiesReader.readAppProperty("default.api") : apiPara;
        api_url = PropertiesReader.readAppProperty(apiEnv);
        logger.info("api url: {}", api_url);
    }

    public static String getBrowser() {
        return browser;
    }

    /**
     * load default.browser when ${browser} isn't given
     *
     * @return
     */
    private static void setBrowser() {
        browser = readProperties("browser", "default.browser").toLowerCase();
        logger.info("browser: ", browser);
    }

    public static String getGridURL() {
        return grid_url;
    }

    private static void setGridURL() {
        grid_url = readProperties("grid", "local").toLowerCase();
        logger.info("grid_url: ", grid_url);
    }

    //To updated execution environment on local machine to remote change default value to remote
    public static String readExecutionType() {
        return readProperties("execution", "local");
    }


    public static String readWebURL() {
        return PropertiesReader.readAppProperty(readBCAAWebSiteConfig());
    }


    private static String readReskinnedWebConfigType() {
        return readProperties("webconfig", "default.reskinned");
    }

    private static String readBCAAWebSiteConfig() {
        return readProperties("bcaa.com", "default.bcaa.com");
    }


    //Method listens command line args and uses matching properties to start desired configuration
    // when no match it reads and returns "default" properties in application.properties
    private static String readProperties(String getProperty, String getDefault) {
        String systemEnv = System.getProperty(getProperty);
        logger.debug("system env:  '{}' ='{}'", getProperty, systemEnv);
        return (systemEnv == null || systemEnv.isEmpty())
                ? PropertiesReader.readAppProperty(getDefault) : systemEnv;
    }
}
