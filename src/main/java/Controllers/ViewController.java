package Controllers;

import Categories.Category;
import Users.RegularUsers.RegularUser;
import Users.RegularUsers.SecurityForceUser;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    public TabPane tabPane;
    public GridPane userDetails;
    //Tab login
    public Tab loginTab;
    public TextField loginUsername;
    public TextField loginPassword;
    //Tab Add Event
    public Tab addEventTab;
    public TextField publishTitle;
    public TextField publishUpdate;
    public ChoiceBox<Category> publishCategories;
    public ComboBox<SecurityForceUser> publishUser;
    //Tab My Events
    public Tab myEventsTab;
    //Tab Change Password
    public Tab passwordTab;
    public TextField passwordOld;
    public TextField passwordNew;


    public void initialize(URL location, ResourceBundle resources) {
        // TODO: 14-Jun-19
        /*loggedOut();*/
    }

    private void loggedOut() {
        userDetails.setVisible(false);
        tabPane.getTabs().remove(0, tabPane.getTabs().size());
        tabPane.getTabs().add(loginTab);
    }

    private void logIn(){
        userDetails.setVisible(true);
        tabPane.getTabs().remove(0, tabPane.getTabs().size());
        tabPane.getTabs().add(myEventsTab);
        tabPane.getTabs().add(addEventTab);
        tabPane.getTabs().add(passwordTab);

    }

    public void publish(ActionEvent actionEvent){
        // TODO: 14-Jun-19
    }

    public void loginSignIn(ActionEvent actionEvent){
        // TODO: 14-Jun-19
    }

    public void passwordConfirm(ActionEvent actionEvent){
        // TODO: 14-Jun-19
    }


}
