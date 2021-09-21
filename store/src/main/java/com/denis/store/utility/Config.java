package com.denis.store.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Config {

    private static final Properties props = new Properties();

    static {
        InputStream is = null;

        try {
            is = new FileInputStream("store/src/main/resources/config.properties");
            props.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (Objects.nonNull(is)) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Properties getProperties() {
        return props;
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
