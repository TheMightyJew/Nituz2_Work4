
import Controllers.ViewController;
import DatabaseManager.RegisteredUserTableManager;
import Users.RegisteredUser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {

    private static int testNum = 1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        //primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("vacationPic2.jpg")));
        Parent root = fxmlLoader.load(getClass().getResource("View.fxml").openStream());
        ViewController viewController = fxmlLoader.getController();
        /*Model model = new Model();
        viewController.setModel(model);*/
        primaryStage.setTitle("Emer Agency");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //runDBTests();
        launch(args);
    }

    private static void runDBTests(){
        //check if username is taken
        printMessage(RegisteredUserTableManager.getInstance().CheckIfUsernameIsTaken("a"));
        //check if can retrieve password correctly
        boolean success = RegisteredUserTableManager.getInstance().GetPasswordByUsername("a").equals("a");
        printMessage(success);
        //check if can change password correctly
        RegisteredUserTableManager.getInstance().ChangePassword("a", "newPassword");
        success = RegisteredUserTableManager.getInstance().GetPasswordByUsername("a").equals("newPassword");
        printMessage(success);


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
