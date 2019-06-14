
import Controllers.ViewController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {

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
        launch(args);
    }

}
