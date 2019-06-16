package Controllers;

import DatabaseManager.UpdatesTableManager;
import Events.Event;
import Updates.UpdateData;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditFXMLUpdateController implements Initializable {

    public TextField updateData;
    private EditUpdateController editUpdateController;
    private Event event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init(EditUpdateController editUpdateController, Event event){
        this.editUpdateController = editUpdateController;
        this.event = event;
    }
    public void cancel(ActionEvent actionEvent){
        close();
    }

    public void edit(ActionEvent actionEvent){
        if(editUpdateController.Edit(event,new UpdateData(updateData.getText()))){
            close();
        }
    }

    private void close(){
        Stage stage = (Stage) updateData.getScene().getWindow();
        stage.close();
    }
}
