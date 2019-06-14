package Controllers;

import Categories.Category;
import Events.Event;
import Users.RegularUsers.RegularUser;
import Users.RegularUsers.SecurityForceUser;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public TableView<Event> eventsTable;
    //Tab Change Password
    public Tab passwordTab;
    public TextField passwordOld;
    public TextField passwordNew;

    private List<Event> events;


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

    private void initializeEvents(){
        events=new ArrayList<Event>();
        eventsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Event, String> eventTitle = new TableColumn("Title");
        TableColumn<Event, Date> eventDate = new TableColumn("Date");
        TableColumn<Event, Event.EventStatus> eventStatus = new TableColumn("Status");
        // TODO: 14-Jun-19
        /*eventTitle.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTitle()));
        eventDate.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getPublishTime()));
        eventStatus.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getStatus()));*/
        eventsTable.getColumns().addAll(eventTitle, eventDate, eventStatus);
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
