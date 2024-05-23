package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PrincipalController extends Controller implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @Override
    public void initialize() {
 }

    @FXML
    private void button(ActionEvent event) {
        FlowController.getInstance().goView("SixPlayerBoardView");
    }
    
}
