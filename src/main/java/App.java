import diagram.Diagram;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utilities.KeyHandler;

/**
 * App
 *
 * @author Seehait Chockthanyawat
 * @since 2017-04-29
 */
public class App extends Application {
    private static final int SCENE_WIDTH = 480;
    private static final int SCENE_HEIGHT = 480;
    private static final String TITLE = "Diagator";

    /**
     * Main flow of Diagator.
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
            Process diagram image that should generate appropriate diagrams structure.
         */

        Diagram diagram = new Diagram();
        // Inject diagram's params here.

        Scene scene = new Scene(new BorderPane(), SCENE_WIDTH, SCENE_HEIGHT);
        scene.setOnKeyPressed(new KeyHandler(diagram));
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
