module com.clickcounter.clickcounter2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.clickcounter.clickcounter2 to javafx.fxml;
    exports com.clickcounter.clickcounter2;
}