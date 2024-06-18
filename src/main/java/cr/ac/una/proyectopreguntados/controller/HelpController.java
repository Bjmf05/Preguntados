package cr.ac.una.proyectopreguntados.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Marconi
 */
public class HelpController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String route = "/cr/ac/una/proyectopreguntados/resources/Ayuda/Ayuda";
    private String[] nameLabel = {"Estadistica", "Mantenimiento", "Buscar Partida", "Nueva Partida", "Elegir Avatar", "Juego Pricipal", "Pregunta", "Seleccionar Avatar Duelo", "Duelo"};
    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane scrollPaneHelp;
    @FXML
    private VBox vbHelp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vbHelp.prefWidthProperty().bind(scrollPaneHelp.widthProperty());
        fillHelp();
    }

    @Override
    public void initialize() {

    }

    public void fillHelp() {
        for (int i = 0; i < nameLabel.length; i++) {
            int n = i + 1;
            String routeVar;
            String number = String.valueOf(n) + ".png";
            routeVar = route + number;
            Label label = new Label(nameLabel[i]);
            label.setStyle("-fx-font-size: 15px; -fx-background-color: #ffffff; -fx-font-family: Roboto;-fx-fill-width: bold;");
            vbHelp.getChildren().add(label);
            InputStream is = getClass().getResourceAsStream(routeVar);
            vbHelp.getChildren().add(new ImageView(new Image(is)));
        }
    }

}
