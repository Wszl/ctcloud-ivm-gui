package org.xdove.ctcloud.ivm.gui.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xdove.ctcloud.ivm.IVMConfig;
import org.xdove.ctcloud.ivm.ServiceRequests;
import org.xdove.ctcloud.ivm.entity.CreateTaskAnalysisRule;
import org.xdove.ctcloud.ivm.gui.ConfigUtils;
import org.xdove.ctcloud.ivm.gui.ErrorUtils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
        final TextArea preferencesServerUrl = (TextArea) scene.lookup("#preferencesServerUrl");
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


    /********************************************
     *  Task
     *
     *********************************/

    @FXML
    private TextField createTaskDeviceNum;
    @FXML
    private TextField createTaskUrl;
    @FXML
    private TextField createTaskType;
    @FXML
    private TextField createTaskAnalysisRule;
    @FXML
    private TextField createTaskSceneBase64;
    @FXML
    private TextField createTaskName;

    @FXML
    public void createTask(ActionEvent actionEvent) {
        Object rule = getNullableText(createTaskAnalysisRule);
        if (Objects.nonNull(rule)) {
            CreateTaskAnalysisRule r = new CreateTaskAnalysisRule();
            r = JSONObject.parseObject(rule.toString(), CreateTaskAnalysisRule.class);
            rule = r;
        }
        try {
            Map<String, Object> r = requests.createTask(
                    getNullableText(createTaskDeviceNum),
                    getNullableText(createTaskUrl),
                    getNullableInt(createTaskType),
                    Objects.nonNull(rule) ? ((CreateTaskAnalysisRule)rule) : null,
                    getNullableText(createTaskSceneBase64),
                    getNullableText(createTaskName)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    @FXML
    private TextField updateTaskDeviceNum;
    @FXML
    private TextField updateTaskUrl;
    @FXML
    private TextField updateTaskAnalysisRule;
    @FXML
    private TextField updateTaskSceneBase64;
    @FXML
    private TextField updateTaskName;
    @FXML
    private TextField updateTaskId;

    @FXML
    public void updateTask(ActionEvent actionEvent) {
        Object rule = getNullableText(updateTaskAnalysisRule);
        if (Objects.nonNull(rule)) {
            CreateTaskAnalysisRule r = new CreateTaskAnalysisRule();
            r = JSONObject.parseObject(rule.toString(), CreateTaskAnalysisRule.class);
            rule = r;
        }
        try {
            Map<String, Object> r = requests.updateTask(
                    getNullableText(updateTaskDeviceNum),
                    getNullableText(updateTaskUrl),
                    getNullableText(updateTaskName),
                    Objects.nonNull(rule) ? ((CreateTaskAnalysisRule)rule) : null,
                    getNullableText(updateTaskSceneBase64),
                    getNullableText(updateTaskId)
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
    public void deleteTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.deleteTask(
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
    public void queryTask(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.queryTask(
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
    private TextField updateSubscribeId;
    @FXML
    private TextField updateSubscribeReturnUrl;
    @FXML
    private TextField updateSubscribeCodes;

    @FXML
    public void updateSubscribe(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.updateSubscribe(
                    getNullableText(updateSubscribeId),
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

    @FXML
    private TextField querySceneEnterpriseUser;
    @FXML
    private TextField querySceneTaskId;
    @FXML
    private TextField querySceneDeviceNum;

    @FXML
    public void queryScene(ActionEvent actionEvent) {
        try {
            Map<String, Object> r = requests.queryScene(
                    getNullableText(querySceneEnterpriseUser),
                    getNullableText(querySceneTaskId),
                    getNullableText(querySceneDeviceNum)
            );
            showResponse(r);
        } catch (Exception e) {
            log.warn(e);
            ErrorUtils.alert(e.getLocalizedMessage());
        }
    }

    private void showResponse(Object r) {
       textArea.setText(JSONObject.toJSONString(r,
               SerializerFeature.PrettyFormat,
               SerializerFeature.WriteMapNullValue,
               SerializerFeature.WriteDateUseDateFormat
               ));
    }


}
