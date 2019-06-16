package Controllers;

import Events.Event;
import Updates.UpdateData;
import Users.RegisteredUser;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewUpdateController implements Initializable {

    public TextField updateData;
    private EditUpdateController editUpdateController;
    private Event event;
    private RegisteredUser user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init(RegisteredUser user, EditUpdateController editUpdateController, Event event){
        this.editUpdateController = editUpdateController;
        this.event = event;
        this.user = user;
    }
    public void cancel(ActionEvent actionEvent){
        close();
    }

    public void edit(ActionEvent actionEvent){
        try {
            if(Massage.confirmMassage("Are you sure you want to edit the latest update?")){
                editUpdateController.Edit(user,event,new UpdateData(updateData.getText()));
                Massage.infoMassage("Update was edited successfully!");
                close();
            }
        }
        catch (Exception e){

        }
    }

    private void close(){
        Stage stage = (Stage) updateData.getScene().getWindow();
        stage.close();
    }
}
