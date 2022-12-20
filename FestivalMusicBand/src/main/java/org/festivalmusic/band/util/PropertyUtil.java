package org.festivalmusic.band.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private Properties properties = null;

    private void loadProperties(){
        if(properties == null){
            try {
                properties = new Properties();
                properties.load(new FileReader("src/main/resources/config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String keyName){
        loadProperties();
        return properties.getProperty(keyName);
    }
}
