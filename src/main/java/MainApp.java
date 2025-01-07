import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainapp.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
