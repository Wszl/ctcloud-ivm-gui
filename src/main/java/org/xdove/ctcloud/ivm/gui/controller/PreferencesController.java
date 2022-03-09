package org.xdove.ctcloud.ivm.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.xdove.ctcloud.ivm.gui.ConfigUtils;

import java.io.IOException;

/**
 * Preferences
 * @author Wszl
 * @date 2021年8月13日
 */
public class PreferencesController {

    public static final String KEY_CONFIG_PREFERENCES_SERVER_URL = "server_url";
    public static final String KEY_CONFIG_PREFERENCES_APPKEY = "appkey";
    public static final String KEY_CONFIG_PREFERENCES_SECRET = "secret";

    @FXML
    private TextArea preferencesServerUrl;
    @FXML
    private TextField preferencesAppkey;
    @FXML
    private TextField preferencesSecret;

    public void saveConfig(ActionEvent event) {
        ConfigUtils.setConfig(KEY_CONFIG_PREFERENCES_SERVER_URL, preferencesServerUrl.getText());
        ConfigUtils.setConfig(KEY_CONFIG_PREFERENCES_APPKEY, preferencesAppkey.getText());
        ConfigUtils.setConfig(KEY_CONFIG_PREFERENCES_SECRET, preferencesSecret.getText());
    }



}
