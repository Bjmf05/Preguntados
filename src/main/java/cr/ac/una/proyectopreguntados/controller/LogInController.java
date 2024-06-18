package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LogInController extends Controller implements Initializable {

    @FXML
    private MFXButton btnExit;
    @FXML
    private MFXButton btnAbout;
    @FXML
    private MFXButton btnStatistics;
    @FXML
    private MFXButton btnMaintenanceQuestions;
    private MFXTextField txfNewPlayer;

    @FXML
    private VBox vbxRoot;
    @FXML
    private MFXButton btnLoadGame;
    @FXML
    private MFXButton btnNewGame;


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
    private void onActionBtnExit(ActionEvent event) {
        exit();

    }

    @FXML
    private void onActionBtnAbout(ActionEvent event) {
                FlowController.getInstance().showViewInVBox("AboutView", vbxRoot);
    }

    @FXML
    private void onActionBtnStatistics(ActionEvent event) {
        FlowController.getInstance().showViewInVBox("StatisticalGraphView", vbxRoot);
    }

    @FXML
    private void onActionBtnMaintenanceQuestions(ActionEvent event) {
                FlowController.getInstance().showViewInVBox("MaintenanceQuestionsView", vbxRoot);
    }

    @FXML
    private void onActionBtnLoadGame(ActionEvent event) {
        FlowController.getInstance().showViewInVBox("SearchGameView", vbxRoot);
    }

    @FXML
    private void onActionBtnNewGame(ActionEvent event) {
        FlowController.getInstance().showViewInVBox("NewGameView", vbxRoot);
    }
    public void exit(){
        getStage().close();
    }

}
