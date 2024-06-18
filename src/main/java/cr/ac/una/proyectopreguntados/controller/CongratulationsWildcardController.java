package cr.ac.una.proyectopreguntados.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CongratulationsWildcardController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAccept;
    @FXML
    private Label lblWildInfo;
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
    private void onActionBtnAccept(ActionEvent event) {
        getStage().close();
    }

    public void setTypeMessage(String type) {
        if(type.equals("OneWild")){
        lblWildInfo.setText("¡Felicidades! Has obtenido una ayuda");}
    else if(type.equals("TwoWild")){
        lblWildInfo.setText("¡Felicidades! Has obtenido dos ayudas");}
    }
}
