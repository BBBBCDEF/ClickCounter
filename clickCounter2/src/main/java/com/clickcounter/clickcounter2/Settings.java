package com.clickcounter.clickcounter2;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Settings {
    private static int timeLimit = 3;
    private static int language = 0;
    private static boolean noLimit = false;
    private static boolean realTime = false;
    public Settings(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Settings.class.getResource("settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 250);
        stage.setTitle("Settings");
        stage.setScene(scene);
        SettingsController settingsController = fxmlLoader.getController();
        settingsController.setThisStage(stage);
        settingsController.receiveValue(timeLimit, language, noLimit, realTime);
        stage.show();
        stage.setOnCloseRequest(e -> {
            Platform.exit();
        });
        stage.setResizable(false);
    }
    public static void receiveValue(int timeLimitIn, int languageIn, boolean noLimitIn, boolean realTimeIn){
        timeLimit = timeLimitIn;
        language = languageIn;
        noLimit = noLimitIn;
        realTime = realTimeIn;
    }

}