package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private MFXButton btnSave;
    @FXML
    private MFXButton btnPause;

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

    public double getWidth() {
        return root.getWidth();
    }

    public double getHeight() {
        return root.getHeight();
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        SixPlayerBoardController sixPlayerBoardController = (SixPlayerBoardController) FlowController.getInstance().getController("SixPlayerBoardView");
        sixPlayerBoardController.safeGame();
        FlowController.getInstance().deleteAll();
        FlowController.getInstance().goViewInWindow("LogInView");
        FlowController.getInstance().salir();
    }
public void exit(){
    FlowController.getInstance().deleteAll();
    FlowController.getInstance().goViewInWindow("LogInView");
    FlowController.getInstance().salir();
    }
    @FXML
    private void onActionBtnPause(ActionEvent event) {
    }

}
