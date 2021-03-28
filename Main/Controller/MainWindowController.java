package Main.Controller;

import Main.Model.CaseModel;
import Main.View.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainWindowController extends BaseController{

    private enum paneType {CASE_VIEWER,FORM_VIEWER}
    private CaseModel currentCase;

    @FXML
    private ListView<CaseModel> CaseList;
    @FXML
    private ListView<?> FormList;
    @FXML
    private ScrollPane FormViewerPane;
    @FXML
    private Label CurrentCaseLabel;




    void setCurrentCase(){
        if(CaseList.getSelectionModel().getSelectedItem() != null){
            currentCase = CaseList.getSelectionModel().getSelectedItem();
            CurrentCaseLabel.setText(currentCase.toString());
        }
    }

    @FXML
    void OpenCaseEditor(){
        vFactory.openCaseEditorWindow();
    }

    @FXML
    void rightClickListAction(){
        System.out.println("Right Click");
    }

    @FXML
    void AboutMenuAction() {

    }

    @FXML
    void SaveFormAction(){

    }

    @FXML
    void OpenMenuAction() {

    }


    @FXML
    void OptionsMenuAction() {

    }

    public void setUpWindow(){
        CaseList.getSelectionModel().selectedItemProperty().addListener(
                (observable) -> setCurrentCase()
        );
        refreshCases();
    }

    public void refreshCases(){
        ArrayList<CaseModel> cases = dataAccessor.getCaseList();
        CaseList.getItems().clear();

        for (CaseModel currCase : cases) {
            CaseList.getItems().add(currCase);
        }
    }

}
