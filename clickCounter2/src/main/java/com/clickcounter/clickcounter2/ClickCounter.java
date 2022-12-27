package com.clickcounter.clickcounter2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClickCounter extends Application {
    private int timeLimit = 3;
    private int language = 0;
    private boolean noLimit = false;
    private boolean realTime = false;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClickCounter.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 335);
        stage.setTitle("Click Counter");
        stage.setScene(scene);
        ClickCounterController clickCounterController = fxmlLoader.getController();
        clickCounterController.setThisStage(stage);
        clickCounterController.receiveValue(timeLimit, language, noLimit, realTime);
        stage.show();
        stage.setOnCloseRequest(e ->{
            Platform.exit();
        });
        stage.setResizable(false);
    }
    protected void receiveValue(int timeLimitIn, int languageIn, boolean noLimitIn, boolean realTimeIn){
        timeLimit = timeLimitIn;
        language = languageIn;
        noLimit = noLimitIn;
        realTime = realTimeIn;
    }
    public static void main(String[] args) {
        launch();
    }
}