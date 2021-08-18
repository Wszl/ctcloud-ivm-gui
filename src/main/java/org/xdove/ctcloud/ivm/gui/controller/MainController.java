package org.xdove.ctcloud.ivm.gui.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.stage.StageHelper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xdove.ctcloud.ivm.IVMConfig;
import org.xdove.ctcloud.ivm.ServiceRequests;
import org.xdove.ctcloud.ivm.entity.KitchenCreateTaskAnalysisRule;
import org.xdove.ctcloud.ivm.entity.KitchenUpdateTaskAnalysisRule;
import org.xdove.ctcloud.ivm.gui.ConfigUtils;
import org.xdove.ctcloud.ivm.gui.ErrorUtils;

import javax.xml.soap.Text;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Main
 * @author Wszl
 * @date 2021年8月13日
 */
public class MainController {

    private final static Logger log = LogManager.getFormatterLogger(MainController.class);


    private ServiceRequests requests;

    @FXML
    private TextArea textArea;

    public MainController() {
        IVMConfig config = new IVMConfig(
                ConfigUtils.getConfig(PreferencesController.KEY_CONFIG_PREFERENCES_SERVER_URL),
                ConfigUtils.getConfig(PreferencesController.KEY_CONFIG_PREFERENCES_SECRET),
                ConfigUtils.getConfig(PreferencesController.KEY_CONFIG_PREFERENCES_APPKEY)
        );
        try {
            this.requests = new ServiceRequests(config);
        } catch (NoSuchAlgorithmException e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    public void start(ActionEvent actionEvent) throws IOException {
        Parent preferences = FXMLLoader.load(getClass().getResource("/preferences.fxml"));
        Scene preferencesScene = new Scene(preferences);
        initConfig(preferencesScene);
        Stage secondStage = new Stage();
        secondStage.setScene(preferencesScene);
        secondStage.show();
    }

    private void initConfig(Scene scene) {
        final TextField preferencesServerUrl = (TextField) scene.lookup("#preferencesServerUrl");
        preferencesServerUrl.setText(ConfigUtils.getConfig(PreferencesController.KEY_CONFIG_PREFERENCES_SERVER_URL));
        final TextField preferencesAppkey = (TextField) scene.lookup("#preferencesAppkey");
        preferencesAppkey.setText(ConfigUtils.getConfig(PreferencesController.KEY_CONFIG_PREFERENCES_APPKEY));
        final TextField preferencesSecret = (TextField) scene.lookup("#preferencesSecret");
        preferencesSecret.setText(ConfigUtils.getConfig(PreferencesController.KEY_CONFIG_PREFERENCES_SECRET));
    }

    private String getNullableText(TextField textField) {
        if ("".equals(textField.getText())) {
            return null;
        }
        return textField.getText();
    }

    private int parseInt(String s) {
        if (Objects.isNull(s)) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }

    private int getNullableInt(TextField textField) {
        return parseInt(getNullableText(textField));
    }

    /*************************
     *  VideoTask
     */

    @FXML
    private TextField createTaskExtractionFrequency;
    @FXML
    private TextField createTaskName;
    @FXML
    private TextField createTaskUrl;
    @FXML
    private TextField createTaskDeviceNum;
    @FXML
    private TextField createTaskType;

    @FXML
    public void createVideoTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.createVideoTask(
                    getNullableInt(createTaskExtractionFrequency),
                    getNullableText(createTaskName),
                    getNullableText(createTaskUrl),
                    getNullableText(createTaskDeviceNum),
                    getNullableInt(createTaskType)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField updateTaskExtractionFrequency;
    @FXML
    private TextField updateTaskName;
    @FXML
    private TextField updateTaskUrl;
    @FXML
    private TextField updateTaskDeviceNum;
    @FXML
    private TextField updateTaskTaskId;

    @FXML
    public void updateVideoTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.updateVideoTask(
                    getNullableInt(updateTaskExtractionFrequency),
                    getNullableText(updateTaskName),
                    getNullableText(updateTaskUrl),
                    getNullableText(updateTaskDeviceNum),
                    getNullableText(updateTaskTaskId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField deleteTaskTaskId;

    @FXML
    public void deleteVideoTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.deleteVideoTask(
                    getNullableText(deleteTaskTaskId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField queryTaskTaskId;
    @FXML
    private TextField queryTaskPageSize;
    @FXML
    private TextField queryTaskPageNum;


    @FXML
    public void queryVideoTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.queryVideoTask(
                    getNullableText(queryTaskTaskId),
                    getNullableInt(queryTaskPageSize),
                    getNullableInt(queryTaskPageNum)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField startTaskTaskId;

    @FXML
    public void startVideoTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.startVideoTask(
                    getNullableText(startTaskTaskId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField stopTaskTaskId;

    @FXML
    public void stopVideoTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.stopVideoTask(
                    getNullableText(stopTaskTaskId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField queryTaskSceneTaskId;

    @FXML
    public void queryVideoScene(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.queryVideoScene(
                    getNullableText(queryTaskSceneTaskId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField createKitchenTaskRule;
    @FXML
    private TextField createKitchenTaskSceneBase64;
    @FXML
    private TextField createKitchenTaskName;
    @FXML
    private TextField createKitchenTaskVideoTaskId;

    @FXML
    public void createKitchenTask(ActionEvent actionEvent) {
        Object rule = getNullableText(createKitchenTaskRule);
        if (Objects.nonNull(rule)) {
            KitchenCreateTaskAnalysisRule r = new KitchenCreateTaskAnalysisRule();
            r = JSONObject.parseObject(rule.toString(), KitchenCreateTaskAnalysisRule.class);
            rule = r;
        }
        try {
            Map<String, Object> r = requests.createKitchenScene(
                    Objects.nonNull(rule) ? ((KitchenCreateTaskAnalysisRule)rule) : null,
                    getNullableText(createKitchenTaskSceneBase64),
                    getNullableText(createKitchenTaskName),
                    getNullableText(createKitchenTaskVideoTaskId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField updateKitchenTaskRule;
    @FXML
    private TextField updateKitchenTaskSceneBase64;
    @FXML
    private TextField updateKitchenTaskName;
    @FXML
    private TextField updateKitchenTaskTaskId;

    @FXML
    public void updateKitchenTask(ActionEvent actionEvent) {
        Object rule = getNullableText(updateKitchenTaskRule);
        if (Objects.nonNull(rule)) {
            KitchenUpdateTaskAnalysisRule r = new KitchenUpdateTaskAnalysisRule();
            r = JSONObject.parseObject(rule.toString(), KitchenUpdateTaskAnalysisRule.class);
            rule = r;
        }
        try {
            Map<String, Object> r = requests.updateKitchenScene(
                    Objects.nonNull(rule) ? ((KitchenUpdateTaskAnalysisRule)rule) : null,
                    getNullableText(updateKitchenTaskSceneBase64),
                    getNullableText(updateKitchenTaskName),
                    getNullableText(updateKitchenTaskTaskId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField deleteKitchenTaskTaskId;

    @FXML
    public void deleteKitchenTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.deleteKitchenScene(
                    getNullableText(deleteKitchenTaskTaskId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField queryKitchenTaskTaskId;
    @FXML
    private TextField queryKitchenTaskPageSize;
    @FXML
    private TextField queryKitchenTaskPageNum;

    @FXML
    public void queryKitchenTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.queryKitchenScene(
                    getNullableText(queryKitchenTaskTaskId),
                    getNullableInt(queryKitchenTaskPageSize),
                    getNullableInt(queryKitchenTaskPageNum)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField createSubscribeReturnUrl;
    @FXML
    private TextField createSubscribeCodes;

    @FXML
    public void createSubscribe(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.createSubscribe(
                    getNullableText(createSubscribeReturnUrl),
                    getNullableText(createSubscribeCodes)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField updateSubscribeReturnUrl;
    @FXML
    private TextField updateSubscribeCodes;

    @FXML
    public void updateSubscribe(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.updateSubscribe(
                    getNullableText(updateSubscribeReturnUrl),
                    getNullableText(updateSubscribeCodes)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField deleteSubscribeSubId;

    @FXML
    public void deleteSubscribe(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.deleteSubscribe(
                    getNullableText(deleteSubscribeSubId)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    public void querySubscribe(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.querySubscribe();
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    private void showResponse(Object r) {
       textArea.setText(JSONObject.toJSONString(r));
    }


}
