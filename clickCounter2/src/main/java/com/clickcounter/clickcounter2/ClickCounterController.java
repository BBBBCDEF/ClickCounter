package com.clickcounter.clickcounter2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ClickCounterController implements Initializable{

    private Stage thisStage;

    //変数
    private int clickCount = 0;
    private float clickSpeed = 0;
    private int time = 30;
    private int selectedTime = 30;
    private Timeline timer = new Timeline();
    private boolean isCount = false;
    private int language = 1;// 0 = English, 1 = Japanese
    private int timeLimit = 3;
    private boolean noLimit = false;
    private boolean realTime = false;
    //ラベル
    @FXML
    private Label timeLabel = new Label();
    @FXML
    private Label speedLabel = new Label();
    @FXML
    private Label countLabel = new Label();
    @FXML
    private Button clickButton = new Button();
    @FXML
    private Button settingsButton = new Button();
    @FXML
    private Button resetButton = new Button();

    //ボタンの処理
    @FXML
    private void onResetButtonClicked() {
        reset();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }
    protected void setThisStage(Stage stage){
        thisStage = stage;
    }

    @FXML
    private void onSettingButtonClicked() throws Exception {
        Settings.receiveValue(timeLimit, language, noLimit, realTime);
        Stage settingsStage = new Stage();
        new Settings(settingsStage);
        thisStage.close();
    }

    private void reset(){
        time = selectedTime;
        clickSpeed = 0;
        clickCount = 0;
        isCount = false;
        timer.stop();
        if(language == 0){
            timeLabel.setText("Time:" + (float)time / 10F + "seconds");
            speedLabel.setText("Speed:" + clickSpeed + "click/second");
            countLabel.setText("Count:" + clickCount + "click");
            clickButton.setText("Click");
            settingsButton.setText("Settings");
            resetButton.setText("Reset");
        }else if(language == 1){
            timeLabel.setText("時間:" + (float)time / 10F + "秒");
            speedLabel.setText("速度:" + clickSpeed + "クリック/秒");
            countLabel.setText("クリック:" + clickCount + "回");
            clickButton.setText("クリック");
            settingsButton.setText("設定");
            resetButton.setText("リセット");
        }
    }
    @FXML
    private void onClickButtonClicked() {
        if (clickCount == 0 && !isCount) {
            timer();
            isCount = true;
            clickCount++;
            if(language == 0){
                countLabel.setText("Count:" + clickCount + "click");
            }else if(language == 1){
                countLabel.setText("クリック数:" + clickCount + "回");
            }else{
                countLabel.setText("Count:" + clickCount + "click");
            }
        }else if(isCount){
            clickCount++;
            if(language == 0){
                countLabel.setText("Count:" + clickCount + "click");
            }else if(language == 1){
                countLabel.setText("クリック数:" + clickCount + "回");
            }else{
                countLabel.setText("Count:" + clickCount + "click");
            }
        }
    }

    private void timer() {
        timer = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
            time--;
            if(language == 0){
                timeLabel.setText("Time:" + (float)time / 10F + "seconds");
            }else if(language == 1){
                timeLabel.setText("時間:" + (float)time / 10F + "秒");
            }else{
                timeLabel.setText("Time:" + (float)time / 10F + "seconds");
            }
            if(time <= 0){
                timer.stop();
                isCount = false;
                getClickSpeed();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
    private void getClickSpeed(){
        clickSpeed = (float)clickCount / ((float)selectedTime / 10F);
        if(language == 0){
            speedLabel.setText("Speed:" + Math.round(clickSpeed * 100F) / 100F + "click/second");
        }else if(language == 1){
            speedLabel.setText("速度:" + Math.round(clickSpeed * 100F) / 100F + "クリック/秒");
        }else{
            speedLabel.setText("Speed:" + Math.round(clickSpeed * 100F) / 100F + "click/second");
        }
    }
    protected void receiveValue(int timeLimitIn, int languageIn, boolean noLimitIn, boolean realTimeIn){
        timeLimit = timeLimitIn;
        language = languageIn;
        noLimit = noLimitIn;
        realTime = realTimeIn;
        selectedTime = time = timeLimit * 10;
        reset();
    }
}