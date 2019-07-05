package com.demo.framework;

import java.io.*;
import java.util.Properties;


public class PropertiesReader {

    private static final String CONFIG_PATH = System.getProperty("user.dir") + File.separator + "application.properties";

    /**
     * @param propertyKey it store the variable whose value needs to be fetch
     * @return it return the value that is fetch from the key
     */
    public static String readAppProperty(String propertyKey) {
        String value = null;
        Properties config = new Properties();
        try (FileInputStream inputFile = new FileInputStream(CONFIG_PATH)) {
            config.load(inputFile);
            value = config.getProperty(propertyKey);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return value;
    }

    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("<br>");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
