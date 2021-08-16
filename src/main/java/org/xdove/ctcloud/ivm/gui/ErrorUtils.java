package org.xdove.ctcloud.ivm.gui;

import javafx.scene.control.Alert;

/**
 * Error
 * @author Wszl
 * @date 2021年8月13日
 */
public class ErrorUtils {

    public static void alert(String content) {
        Alert a = new Alert(Alert.AlertType.ERROR, content);
        a.show();
    }
}
