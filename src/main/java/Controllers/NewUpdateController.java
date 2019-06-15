package Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewUpdateController implements Initializable {

    public TextField updateData;
    private PublishUpdateController publishUpdateController;
    private Event event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init(PublishUpdateController publishUpdateController, Event event){
        this.publishUpdateController = publishUpdateController;
        this.event = event;
    }
    public void cancel(ActionEvent actionEvent){
        close();
    }

    public void add(ActionEvent actionEvent){
        // TODO: 15-Jun-19
        close();
    }

    private void close(){
        Stage stage = (Stage) updateData.getScene().getWindow();
        stage.close();
    }
}
