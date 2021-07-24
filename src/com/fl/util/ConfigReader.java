package com.fl.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties read(String fileName) {
        Properties ps = new Properties();
        try (InputStream in = ConfigReader.class.getResourceAsStream(fileName)) {
            ps.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }
}
