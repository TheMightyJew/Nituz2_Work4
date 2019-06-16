package Controllers;

import Categories.Category;
import DatabaseManager.CategoriesTableManager;
import DatabaseManager.EventsTableManager;
import DatabaseManager.RegisteredUserTableManager;
import DatabaseManager.RegularUsersTableManager;
import Events.Event;
import Updates.UpdateData;
import Users.RegisteredUser;
import Users.RegularUsers.EmergencyCenterUser;
import Users.RegularUsers.RegularUser;
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
    public PasswordField loginPassword;
    public Button signInButton;
    //Tab Add Event
    public Tab addEventTab;
    public TextField publishTitle;
    public TextField publishUpdate;
    public MenuButton menuCategories;
    public ComboBox<String> publishUser;
    //Tab My Events
    public Tab myEventsTab;
    public TableView<Event> eventsTable;
    //Tab Change Password
    public Tab passwordTab;
    public TextField passwordOld;
    public TextField passwordNew;

    private List<Event> events;
    private RegisteredUser loggedInUser;

    private ChangePasswordController changePasswordController;
    private CreateCategoryController createCategoryController;
    private CreateEventController createEventController;
    private EditUpdateController editUpdateController;

    public void setControllers(ChangePasswordController changePasswordController,
            CreateCategoryController createCategoryController,
            CreateEventController createEventController,
            EditUpdateController editUpdateController){
        this.changePasswordController = changePasswordController;
        this.createCategoryController = createCategoryController;
        this.createEventController = createEventController;
        this.editUpdateController = editUpdateController;
    }


    public void initialize(URL location, ResourceBundle resources) {
        // TODO: 14-Jun-19
        loggedOut();
        signInButton.setTooltip(new Tooltip("Press to sign in"));
        //addCategories();
        initializeEvents();
    }

    private void addCategories() {
        menuCategories.getItems().clear();
        for (Category c: CategoriesTableManager.getInstance().getAllCategories()){
            CheckMenuItem item = new CheckMenuItem(c.getName());
            menuCategories.getItems().add(item);
        }
    }

    private void loggedOut() {
        userDetails.setVisible(false);
        tabPane.getTabs().remove(0, tabPane.getTabs().size());
        tabPane.getTabs().add(loginTab);
    }

    private void logIn(){
        loggedInUser = changePasswordController.reviewPersonalInformation(loginUsername.getText());
        userDetails.setVisible(true);
        helloUsername.setText(loggedInUser.getUsername());
        organization.setText(loggedInUser.getOrganization().toString());
        degree.setText(loggedInUser.getDegree());
        tabPane.getTabs().remove(0, tabPane.getTabs().size());
        tabPane.getTabs().add(myEventsTab);
        tabPane.getTabs().add(addEventTab);
        tabPane.getTabs().add(passwordTab);
        addCategories();
        addUsers();
        updateEvents();
    }

    private void addUsers() {
        publishUser.getItems().clear();
        publishUser.getItems().addAll(RegularUsersTableManager.getInstance().getSecurityForceUsers());
    }

    public void updateEvents(){
        events = EventsTableManager.getInstance().getAllEventsForUsername(loggedInUser.getUsername());
        eventsTable.getItems().clear();
        eventsTable.getItems().addAll(events);
    }

    private void initializeEvents(){
        eventsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Event, String> eventTitle = new TableColumn("Title");
        TableColumn<Event, Date> eventDate = new TableColumn("Date");
        TableColumn<Event, Event.EventStatus> eventStatus = new TableColumn("Status");
        TableColumn<Event, String> categories = new TableColumn("Categories");
        TableColumn<Event, String> initialUpdate = new TableColumn("First update");
        TableColumn<Event, String> lastUpdate = new TableColumn("Last update");
        TableColumn<Event, String> editUpdate = new TableColumn("Edit latest update");
        eventTitle.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTitle()));
        eventDate.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getPublishTime()));
        eventStatus.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getStatus()));
        categories.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getCategoriesNames()));


        Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFactoryInitial = initialUpdateButton();
        Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFactoryLast = lastUpdateButton();
        Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFactoryEdit = NewUpdateButton();

        initialUpdate.setCellFactory(cellFactoryInitial);
        lastUpdate.setCellFactory(cellFactoryLast);
        editUpdate.setCellFactory(cellFactoryEdit);

        eventsTable.getColumns().addAll(eventTitle, eventDate, eventStatus ,categories, initialUpdate , lastUpdate , editUpdate);
        eventsTable.setPrefWidth(eventsTable.getColumns().size()*250);
    }

    private Callback<TableColumn<Event, String>, TableCell<Event, String>> NewUpdateButton() {
        return new Callback<TableColumn<Event, String>, TableCell<Event, String>>() {
            public TableCell<Event, String> call(final TableColumn<Event, String> param) {
                final TableCell<Event, String> cell = new TableCell<Event, String>() {
                    final Button btn = new Button("Edit latest update");
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
                            try{
                                btn.setOnAction(event -> {
                                    try {
                                        FXMLLoader fxmlLoader = new FXMLLoader();
                                        Parent root = fxmlLoader.load(ViewController.class.getResource("/Controllers/EditUpdate.fxml").openStream());
                                        EditFXMLUpdateController controller = fxmlLoader.getController();
                                        Event pickedEvent = getTableView().getItems().get(getIndex());
                                        controller.init(editUpdateController,pickedEvent);
                                        Stage stage = new Stage();
                                        stage.initModality(Modality.APPLICATION_MODAL);
                                        stage.setTitle("Edit latest update");
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
        if(loggedInUser instanceof EmergencyCenterUser){
            try{
                List<Category> categoriesToEvent = new ArrayList<>();
                List<MenuItem> selectedCategory = menuCategories.getItems();
                for(MenuItem category : selectedCategory){
                    if(((CheckMenuItem)category).isSelected()){
                        categoriesToEvent.add(new Category(((CheckMenuItem)category).getText()));
                    }
                }
                RegisteredUser security = RegisteredUserTableManager.getInstance().getUserByUsername(publishUser.getValue());
                if(security instanceof SecurityForceUser){
                    createEventController.createNewEvent(((EmergencyCenterUser)loggedInUser),publishTitle.getText(),new UpdateData(publishUpdate.getText()),categoriesToEvent,((SecurityForceUser)security));
                    Massage.infoMassage("Event added successfully");
                    resetPublish();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                Massage.errorMassage("Bad input");
            }
        }
        else{
            Massage.errorMassage("Only Emergency center user can create an event");
        }
    }

    private void resetPublish() {
        publishTitle.setText("");
        publishUpdate.setText("");
        publishUser.setValue(null);
        List<MenuItem> selectedCategory = menuCategories.getItems();
        for(MenuItem category : selectedCategory){
            ((CheckMenuItem)category).setSelected(false);
        }

    }

    public void loginSignIn(ActionEvent actionEvent){
        if(RegisteredUserTableManager.getInstance().CheckIfUsernameIsTaken(loginUsername.getText())){
            if(RegisteredUserTableManager.getInstance().GetPasswordByUsername(loginUsername.getText()).equals(loginPassword.getText())){
                Massage.infoMassage("Logged in successfully!");
                logIn();
            }
            else {
                Massage.errorMassage("Password is incorrect!");
            }
        }
        else {
            Massage.errorMassage("Username doesnt exists");
        }
    }

    public void passwordConfirm(ActionEvent actionEvent){
        changePasswordController.changePassword(loggedInUser.getUsername(),passwordOld.getText(),passwordNew.getText());
    }


    public void myEventsOpened(javafx.event.Event event) {
        updateEvents();
    }
}
