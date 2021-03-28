package Main.Controller;

import Main.Model.CaseModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Optional;

public class CaseEditorController extends BaseController{

    private CaseModel currentCase;

    @FXML
    private AnchorPane CaseViewPane;
    @FXML
    private MenuItem deleteCaseButton;
    @FXML
    private ListView<CaseModel> CaseList;
    @FXML
    private TextField firstNameCaseViewer;

    @FXML
    private TextField lastNameCaseViewer;

    @FXML
    void AddNewCaseMenuAction() {
        vFactory.OpenNewCaseWindow();
    }

    @FXML
    void SaveCaseChangesAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Case Overwrite");
        alert.setHeaderText("This will permanently overwrite this case with the information you entered");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            overWriteCurrentCase();
        }
    }

    @FXML
    void deleteCaseAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Case Deletion");
        alert.setHeaderText("This will permanently delete this case.");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dataAccessor.RemoveCaseFromList(currentCase);
            deselectCurrentCase();
            vFactory.RefreshWindows();
        }
    }

    @FXML
    void discardChangesAction(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Discard Changes");
        alert.setHeaderText("This will discard all unsaved changes on this case.");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            setCaseViewValues();
        }else{
            CaseList.getSelectionModel().select(currentCase);
        }
    }


    void deselectCurrentCase(){
        currentCase = null;
        disableButtonsAndCaseView();
    }

    void setCurrentCase(){
        if(currentCase != null && currentCase != CaseList.getSelectionModel().getSelectedItem())
        {
            if(checkForCaseChanges()){
                discardChangesAction();
            }
        }
        currentCase = CaseList.getSelectionModel().getSelectedItem();
        if(currentCase != null) {
            enableButtonsAndCaseView();
            setCaseViewValues();
        }
    }

    boolean checkForCaseChanges(){
        if(! firstNameCaseViewer.getText().equals(currentCase.getFirstName())){
            return true;
        }
        if(! lastNameCaseViewer.getText().equals(currentCase.getLastName())){
            return true;
        }

        return false;
    }

    void disableButtonsAndCaseView(){
        deleteCaseButton.setDisable(true);
        CaseViewPane.setDisable(true);
        CaseViewPane.setOpacity(0);
    }

    void setCaseViewValues(){
        firstNameCaseViewer.setText(currentCase.getFirstName());
        lastNameCaseViewer.setText(currentCase.getLastName());
    }

    void enableButtonsAndCaseView(){
        deleteCaseButton.setDisable(false);
        CaseViewPane.setDisable(false);
        CaseViewPane.setOpacity(1);
    }

    void overWriteCurrentCase(){
        currentCase.setLastName(lastNameCaseViewer.getText());
        currentCase.setFirstName(firstNameCaseViewer.getText());
        dataAccessor.SaveCaseChanges();
        deselectCurrentCase();
        vFactory.RefreshWindows();
    }

    public void setUpWindow(){
        deselectCurrentCase();
        CaseList.getSelectionModel().selectedItemProperty().addListener(
                (observable) ->
                setCurrentCase()
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
