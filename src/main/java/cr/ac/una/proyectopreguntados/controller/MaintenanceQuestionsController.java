package cr.ac.una.proyectopreguntados.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class MaintenanceQuestionsController extends Controller implements Initializable {

    @FXML
    private MFXTextField txfId;
    @FXML
    private MFXComboBox<String> cbxTypeQuestion;
    @FXML
    private MFXTextField txfQuestion;
    @FXML
    private MFXTextField txfCorrectAnswer;
    @FXML
    private Label lblQuantityCorrectAnswer;
    @FXML
    private MFXTextField txfIncorrectAnswer1;
    @FXML
    private Label lblQuantityIncorrectAnswer1;
    @FXML
    private MFXTextField txfIncorrectAnswer2;
    @FXML
    private Label lblQuantityIncorrectAnswer2;
    @FXML
    private MFXTextField txfIncorrectAnswer3;
    @FXML
    private Label lblQuantityIncorrectAnswer3;
    @FXML
    private Label lblNumberOfTimeAsked;
    @FXML
    private MFXButton btnNew;
    @FXML
    private MFXButton btnSearch;
    @FXML
    private MFXButton btnDelete;
    @FXML
    private MFXButton btnSave;
    @FXML
    private MFXButton btnExit;
    @FXML
    private MFXCheckbox chkActive;

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
    private void onKeyPressedTxfId(KeyEvent event) {
    }

    @FXML
    private void onActionBtnNew(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
    }

    @FXML
    private void onActionBtnDelete(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
    }

    @FXML
    private void onActionBtnExit(ActionEvent event) {
       getStage().close();
    }

    @FXML
    private void onActionChkTypeQuestion(ActionEvent event) {
    }
    
}
