package com.clickcounter.clickcounter2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    private Stage thisStage = new Stage();
    private int language = 0;
    private int timeLimit = 3;
    private boolean noLimit = false;
    private boolean realTime = false;
    @FXML
    private TextField timeTextField = new TextField();

    @FXML
    private CheckBox checkBoxNoLimit = new CheckBox();

    @FXML
    private CheckBox checkBoxRealTime = new CheckBox();

    @FXML
    private ChoiceBox languageChoiceBox = new ChoiceBox();

    @FXML
    private Button applyButton = new Button();

    @FXML
    private Button cancelButton = new Button();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeTextField.setText(String.valueOf(timeLimit));
        checkBoxNoLimit.setSelected(noLimit);
        checkBoxRealTime.setSelected(realTime);
        if(language == 0){
            timeTextField.setPromptText("Measure Time");
        }else if(language == 1){
            timeTextField.setPromptText("計測時間");
        }else{
            timeTextField.setPromptText("Measure Time");
        }
    }
    protected void setThisStage(Stage stage){
        thisStage = stage;
    }
    @FXML
    private void onCheckBoxNoLimit(){
        noLimit = checkBoxNoLimit.isSelected();
    }

    @FXML
    private void onCheckBoxRealTime(){
        realTime = checkBoxRealTime.isSelected();
    }

    @FXML
    public void onApplyButton() throws IOException {
        timeLimit = checkText(timeTextField.getText());
        System.out.print(timeLimit);
        Stage clickCounterStage = new Stage();
        ClickCounter clickCounter = new ClickCounter();
        clickCounter.receiveValue(timeLimit, language, noLimit, realTime);
        clickCounter.start(clickCounterStage);
        thisStage.close();
    }

    @FXML
    private void onCancelButton() throws IOException {
        Stage clickCounterStage = new Stage();
        ClickCounter clickCounter = new ClickCounter();
        clickCounter.start(clickCounterStage);
        thisStage.close();
    }
    public void receiveValue(int timeLimitIn, int languageIn, boolean noLimitIn, boolean realTimeIn){
        timeLimit = timeLimitIn;
        System.out.print(timeLimit);
        language = languageIn;
        noLimit = noLimitIn;
        realTime = realTimeIn;
        timeTextField.setText(String.valueOf(timeLimit));
        checkBoxNoLimit.setSelected(noLimit);
        checkBoxRealTime.setSelected(realTime);
    }
    protected int checkText(String inputText) {
        if (Integer.parseInt(inputText) > 0 || Integer.parseInt(inputText) <= 180) {
            return Integer.parseInt(inputText);
        } else return 3;
    }
}