package Controllers;

import Categories.Category;
import Events.Event;
import Organizations.EmergencyCenter;
import Organizations.SecurityForces.Police;
import Organizations.SecurityForces.SecurityForce;
import Updates.Update;
import Updates.UpdateData;
import Users.Admins.EmergencyCenterAdmin;
import Users.RegisteredUser;
import Users.RegularUsers.SecurityForceUser;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    public TabPane tabPane;
    public GridPane userDetails;
    public Label helloUsername;
    public Label organization;
    public Label degree;

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
    private RegisteredUser loggedInUser;


    public void initialize(URL location, ResourceBundle resources) {
        // TODO: 14-Jun-19
        /*loggedOut();*/
        //example
        EmergencyCenter emergencyCenter = new EmergencyCenter("1");
        loggedInUser = new EmergencyCenterAdmin(null,"TheMightyJew","ssss","ssss",emergencyCenter,null,null);
        Update update = new Update(null,new UpdateData("Some info"));
        SecurityForce org = new Police("4");
        SecurityForceUser sfuser = new SecurityForceUser(null,"a","a","a",org,"5");
        Event event = new Event((EmergencyCenterAdmin)loggedInUser,"Cool event",update,sfuser);
        eventsTable.getItems().add(event);
        //end example

        initializeEvents();
    }

    private void loggedOut() {
        userDetails.setVisible(false);
        tabPane.getTabs().remove(0, tabPane.getTabs().size());
        tabPane.getTabs().add(loginTab);
    }

    private void logIn(){
        userDetails.setVisible(true);
        helloUsername.setText(loggedInUser.getUsername());
        organization.setText(loggedInUser.getOrganization().toString());
        degree.setText(loggedInUser.getDegree());
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
        TableColumn<Event, String> initialUpdate = new TableColumn("First update");
        TableColumn<Event, String> lastUpdate = new TableColumn("Last update");
        TableColumn<Event, String> newUpdate = new TableColumn("Add new update");
        // TODO: 14-Jun-19
        eventTitle.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTitle()));
        // TODO: 15-Jun-19 fix date
        eventDate.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getPublishTime()));
        eventStatus.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getStatus()));


        Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFactoryInitial = initialUpdateButton();
        Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFactoryLast = lastUpdateButton();
        Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFactoryNew = NewUpdateButton();

        initialUpdate.setCellFactory(cellFactoryInitial);
        lastUpdate.setCellFactory(cellFactoryLast);
        newUpdate.setCellFactory(cellFactoryNew);

        eventsTable.getColumns().addAll(eventTitle, eventDate, eventStatus , initialUpdate , lastUpdate , newUpdate);
        eventsTable.setPrefWidth(eventsTable.getColumns().size()*150);
    }

    private Callback<TableColumn<Event, String>, TableCell<Event, String>> NewUpdateButton() {
        return new Callback<TableColumn<Event, String>, TableCell<Event, String>>() {
            public TableCell<Event, String> call(final TableColumn<Event, String> param) {
                final TableCell<Event, String> cell = new TableCell<Event, String>() {
                    final Button btn = new Button("Add");
                    {
                        btn.setAlignment(Pos.CENTER);
                        btn.setMaxHeight(Double.MAX_VALUE);
                        btn.setMaxWidth(Double.MAX_VALUE);
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            // TODO: 15-Jun-19 problem with lamda fucntion
                            try{
                                btn.setOnAction(event -> {
                                    try {
                                        FXMLLoader fxmlLoader = new FXMLLoader();
                                        //primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("vacationPic2.jpg")));
                                        // TODO: 15-Jun-19 fix bug with path 
                                        Parent root = fxmlLoader.load(ViewController.class.getResource("/Controllers/NewUpdate.fxml").openStream());
                                        NewUpdateController controller = fxmlLoader.getController();
                                        /*Model model = new Model();
                                        viewController.setModel(model);*/
                                        Stage stage = new Stage();
                                        stage.initModality(Modality.APPLICATION_MODAL);
                                        stage.setTitle("New Update");
                                        stage.setScene(new Scene(root));
                                        stage.show();
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                    }
                                });

                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
    }

    private Callback<TableColumn<Event, String>, TableCell<Event, String>> initialUpdateButton() {
        return new Callback<TableColumn<Event, String>, TableCell<Event, String>>() {
            public TableCell<Event, String> call(final TableColumn<Event, String> param) {
                final TableCell<Event, String> cell = new TableCell<Event, String>() {
                    final Button btn = new Button("See");
                    {
                        btn.setAlignment(Pos.CENTER);
                        btn.setMaxHeight(Double.MAX_VALUE);
                        btn.setMaxWidth(Double.MAX_VALUE);
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            // TODO: 15-Jun-19 problem with lamda fucntion
                                    btn.setOnAction(event -> {
                                        Event pickedEvent = getTableView().getItems().get(getIndex());
                                        Massage.infoMassage(pickedEvent.getInitialUpdate().getData().getData());
                                    });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
    }

    private Callback<TableColumn<Event, String>, TableCell<Event, String>> lastUpdateButton() {
        return new Callback<TableColumn<Event, String>, TableCell<Event, String>>() {
            public TableCell<Event, String> call(final TableColumn<Event, String> param) {
                final TableCell<Event, String> cell = new TableCell<Event, String>() {
                    final Button btn = new Button("See");
                    {
                        btn.setAlignment(Pos.CENTER);
                        btn.setMaxHeight(Double.MAX_VALUE);
                        btn.setMaxWidth(Double.MAX_VALUE);
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            // TODO: 15-Jun-19 problem with lamda fucntion
                                    btn.setOnAction(event -> {
                                        Event pickedEvent = getTableView().getItems().get(getIndex());
                                        Massage.infoMassage(pickedEvent.getLastUpdate().getData().getData());
                                    });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
    }

    public void publish(ActionEvent actionEvent){
        // TODO: 14-Jun-19
    }

    public void loginSignIn(ActionEvent actionEvent){
        // TODO: 14-Jun-19
        logIn();
    }

    public void passwordConfirm(ActionEvent actionEvent){
        // TODO: 14-Jun-19
    }


}
