
import Controllers.*;
import DatabaseManager.RegisteredUserTableManager;
import Categories.Category;
import Controllers.ViewController;
import DatabaseManager.*;
import Events.Event;
import Organizations.EmergencyCenter;
import Organizations.Organization;
import Organizations.SecurityForces.Police;
import Updates.Update;
import Updates.UpdateData;
import Users.RegisteredUser;
import Users.RegularUsers.EmergencyCenterUser;
import Users.RegularUsers.SecurityForceUser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class main extends Application {

    private static int testNum = 1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        //primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("vacationPic2.jpg")));
        Parent root = fxmlLoader.load(getClass().getResource("View.fxml").openStream());
        ViewController viewController = fxmlLoader.getController();
        viewController.setControllers(new ChangePasswordController(),new CreateCategoryController(),new CreateEventController(),new EditUpdateController());
        /*Model model = new Model();
        viewController.setModel(model);*/
        primaryStage.setTitle("Emer Agency");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //runDBTests1();
        //runDBTests2();
        launch(args);
    }

    private static void runDBTests2() {
        Organization center = new EmergencyCenter("EmergencyCenter");
        EmergencyCenterUser creator = new EmergencyCenterUser(null,null,"Emergencyman", "E@E.com", "Emergency", center, "7");
        Organization police = new Police("PoliceOrganization");
        SecurityForceUser invitee = new SecurityForceUser(null, "Policeman", "P@P.com", "Police", police, "6");
        UpdateData data = new UpdateData("A break in has occurred at Beer Sheva, Gun shots have been heard");
        data.setDate(new Date());
        Update update = new Update(null, data);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Gun Battle"));
        categories.add(new Category("Robbery"));
        Event event = new Event(creator,"Break in in Beer Sheva", update, invitee, categories);

        /*
        for(Category category : categories){
            CategoriesTableManager.getInstance().CreateANewCategory(category);
        }
        */
        //EventsTableManager.getInstance().CreateANewEvent(event);

        //UserAtEventTableManager.getInstance().CreateUserAtEvent(event.getUsers().get(0));

        List<Event> eventList = EventsTableManager.getInstance().getAllEventsForUsername("Emergencyman");
        Update update1 = eventList.get(0).getInitialUpdate();
        UpdateData data1 = new UpdateData("A break in has occurred at Beer Sheva");
        UpdatesTableManager.getInstance().EditAnUpdate(update1.getID(), data1);

        List<Event> eventList2 = EventsTableManager.getInstance().getAllEventsForUsername("Emergencyman");
        int bp = 0;

    }

    private static void runDBTests1(){
        //check if username is taken
        printMessage(RegisteredUserTableManager.getInstance().CheckIfUsernameIsTaken("a"));
        //check if can retrieve password correctly
        boolean success = RegisteredUserTableManager.getInstance().GetPasswordByUsername("a").equals("a");
        printMessage(success);
        //check if can change password correctly
        RegisteredUserTableManager.getInstance().ChangePassword("a", "a");
        success = RegisteredUserTableManager.getInstance().GetPasswordByUsername("a").equals("a");
        printMessage(success);

        UpdateData data = new UpdateData("data");
        data.setDate(new Date());
        UpdateDataTableManager.getInstance().CreateANewUpdateData(data);


    }

    private static void printMessage(boolean success){
        if(success){
            System.out.println("Success in test num " + testNum);
        }
        else {
            System.out.println("Failure in test num " + testNum);
        }
        testNum++;
    }

}
