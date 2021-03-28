package Main;

import Main.Model.DataAccessor;
import Main.View.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    private DataAccessor dataAccessor;

    @Override
    public void start(Stage primaryStage){
        ViewFactory viewFactory = new ViewFactory(new DataAccessor());

        viewFactory.openMainWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
