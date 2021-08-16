package org.xdove.ctcloud.ivm.gui;

import com.sun.javafx.collections.ObservableMapWrapper;
import com.sun.javafx.collections.UnmodifiableObservableMap;
import javafx.collections.ObservableMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * 配置文件工具类
 * @author Wszl
 * @date 2021年8月13日
 */
public class ConfigUtils {

    private static final Logger log = LogManager.getFormatterLogger(ConfigUtils.class);

    public static final Map<String, String> param;
    public static final String CONFIG_FILE_NAME = "config.ini";

    static {
        if (!Files.exists(Paths.get(CONFIG_FILE_NAME))) {
            try {
                Files.createFile(Paths.get(CONFIG_FILE_NAME));
            } catch (IOException e) {
                log.warn(e);
                ErrorUtils.alert(e.getLocalizedMessage());
            }
        }
        param = getAllConfig();
    }

    public static Map<String, String> getAllConfig() {
        if (Objects.nonNull(param)) {return param;}
        Properties p = new Properties();
        try (FileInputStream f = new FileInputStream(CONFIG_FILE_NAME)) {
            p.load(new FileInputStream(CONFIG_FILE_NAME));
        } catch (IOException e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
        Map<String, String> r = new HashMap<String, String>(p.values().size());
        p.forEach((key, value) -> r.put(key.toString(), value.toString()));
        return r;
    }

    public static String getConfig(String key) {
        return param.get(key);
    }

    public static String setConfig(String key, String value) {
        Properties p = new Properties();
        try (FileInputStream f = new FileInputStream(CONFIG_FILE_NAME)) {
            p.load(f);
            p.setProperty(key, value);
            p.store(new FileWriter(CONFIG_FILE_NAME), "");
        } catch (IOException e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
        return param.put(key, value);
    }
}
