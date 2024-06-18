package cr.ac.una.proyectopreguntados.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AboutController extends Controller implements Initializable{

    @FXML
    private AnchorPane root;

    @FXML
    private ScrollPane scrollPaneAbout;

    @FXML
    private VBox vbAbout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbAbout.prefWidthProperty().bind(scrollPaneAbout.widthProperty());
    }
    
    @Override
    public void initialize() {
    }
}
