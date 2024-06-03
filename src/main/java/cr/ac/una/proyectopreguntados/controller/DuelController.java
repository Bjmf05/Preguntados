
package cr.ac.una.proyectopreguntados.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DuelController extends Controller implements Initializable {

    @FXML
    private Label lblNamePlayer1;
    @FXML
    private ImageView imgStack;
    @FXML
    private AnchorPane rootCardQuestion;
    @FXML
    private VBox vbCard;
    @FXML
    private Slider sliderTime;
    @FXML
    private Text textOfQuestion;
    @FXML
    private MFXButton btnOptionOne;
    @FXML
    private MFXButton btnOptionTwo;
    @FXML
    private MFXButton btnOptionThree;
    @FXML
    private MFXButton btnOptionFour;
    @FXML
    private ImageView imgCardBack;
    @FXML
    private MFXButton btnBombPlayer1;
    @FXML
    private ImageView imgTypeQuestion;
    @FXML
    private Label lblNamePlayer2;
    @FXML
    private ImageView imgStack1;
    @FXML
    private AnchorPane rootCardQuestion1;
    @FXML
    private VBox vbCard1;
    @FXML
    private Slider sliderTime1;
    @FXML
    private Text textOfQuestionPlayer2;
    @FXML
    private MFXButton btnOptionOnePlayer2;
    @FXML
    private MFXButton btnOptionTwoPlayer2;
    @FXML
    private MFXButton btnOptionThreePlayer2;
    @FXML
    private MFXButton btnOptionFourPlayer2;
    @FXML
    private ImageView imgCardBack2;
    @FXML
    private MFXButton btnBombPlayer2;

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
    private void onActionBtnOption(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBombPlayer1(ActionEvent event) {
    }

    @FXML
    private void onActionBtnOptionPlayer2(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBombPlayer2(ActionEvent event) {
    }
    
}
