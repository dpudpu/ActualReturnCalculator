package my.examples.arc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private String dbURL=null;
    private Properties properties;

    private static PropertiesUtil instance = new PropertiesUtil();

    public static PropertiesUtil getInstance(){
        return instance;
    }


    private PropertiesUtil() {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            properties = new Properties();
            properties.load(in);
            dbURL = String.format("jdbc:mysql://%s/%s", properties.getProperty("host"), properties.getProperty("database"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDbURL() {
        return dbURL;
    }

    public Properties getProperties() {
        return properties;
    }
}
