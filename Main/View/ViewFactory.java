package Main.View;

import Main.Controller.BaseController;
import Main.Controller.CaseEditorController;
import Main.Controller.MainWindowController;
import Main.Model.DataAccessor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private final DataAccessor dataAccessor;
    private MainWindowController mainWindow;
    private CaseEditorController caseEditor;

    public ViewFactory(DataAccessor dataAccessor){
        this.dataAccessor = dataAccessor;
    }

    public void closeWindow(Stage stageToClose){
        stageToClose.close();
    }

    public void RefreshWindows(){
        if(mainWindow != null) {
            mainWindow.refreshCases();
        }
        if(caseEditor != null){
            caseEditor.refreshCases();
        }
    }

    public void OpenNewCaseWindow(){
        OpenNewWindow("NewCaseWindow.fxml", "Add New Case",false, 1000,800);
    }

    public void openCaseEditorWindow(){
        FXMLLoader loader = OpenNewWindow("CaseEditorWindow.fxml","Case Editor", true,1000,800);
        caseEditor = loader.getController();
        caseEditor.setUpWindow();
    }

    public void openMainWindow(){
        FXMLLoader loader = OpenNewWindow("MainWindow.fxml","CFS Form Filler V.1b",true, 1000,800);
        mainWindow = loader.getController();
        mainWindow.setUpWindow();
    }

    private FXMLLoader OpenNewWindow(String windowName, String Title, Boolean maximized, double width, double height ){
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(windowName));

        Stage stage = new Stage();

        try {
            root = fxmlLoader.load();
        }catch (IOException io){
            io.printStackTrace();
            return null;
        }

        BaseController controller = fxmlLoader.getController();
        controller.setViewFactory(this);
        controller.setStage(stage);
        controller.setDataAccessor(dataAccessor);
        stage.setMaximized(maximized);
        stage.setTitle(Title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
        return fxmlLoader;
    }


}
