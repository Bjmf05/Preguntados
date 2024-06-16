package cr.ac.una.proyectopreguntados;

import cr.ac.una.proyectopreguntados.util.FlowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    InputStream inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/Corona.png");
    Image icon = new Image(inputStream);
    stage.getIcons().add(icon);
        FlowController.getInstance().InitializeFlow(stage, null);
        FlowController.getInstance().goViewInWindow("LogInView");
    }

    public static void main(String[] args) {
        launch();
    }

}