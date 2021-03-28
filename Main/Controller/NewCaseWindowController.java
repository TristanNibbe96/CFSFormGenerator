package Main.Controller;

import Main.Model.CaseModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewCaseWindowController extends BaseController{

    @FXML
    private TextField FirstNameField;

    @FXML
    private TextField LastNameField;

    @FXML
    void  SaveNewCaseAction(){
        CaseModel newCaseModel = new CaseModel();

        newCaseModel.setFirstName(FirstNameField.getText());
        newCaseModel.setLastName((LastNameField.getText()));
        dataAccessor.AddNewCaseToCaseList(newCaseModel);
        vFactory.RefreshWindows();
        vFactory.closeWindow(stage);
    }

    @FXML
    void QuitAction() {
        vFactory.closeWindow(stage);
    }

}

