package Main.Controller;

import Main.Model.DataAccessor;
import Main.View.ViewFactory;
import javafx.stage.Stage;

public class BaseController {
    protected ViewFactory vFactory;
    protected Stage stage;
    protected DataAccessor dataAccessor;

    public void setViewFactory(ViewFactory viewFactory){
        vFactory = viewFactory;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setDataAccessor(DataAccessor data){
        this.dataAccessor = data;
    }

}
