module CFSFormGenerator {
    requires javafx.web;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;


    opens Main;
    opens Main.Controller;
    opens Main.View;
}