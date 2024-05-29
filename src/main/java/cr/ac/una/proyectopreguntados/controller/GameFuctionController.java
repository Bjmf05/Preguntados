package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Marconi Calvo Campos
 */
public class GameFuctionController extends Controller implements Initializable {

    @FXML
    private AnchorPane rootGameFuction;
    @FXML
    private ImageView imgStack;
    @FXML
    private AnchorPane rootCardQuestion;
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

    private boolean showingFront = true;
    private InputStream inputStream;
    private double initialX;
    private double initialY; 
    private double centerX;
    private double centerY;
    private PrincipalController principalController = (PrincipalController) FlowController.getInstance().getController("PrincipalView");
    
    /**
     * Initializes the controller class.
     */
     public GameFuctionController() {
    }   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/CardBack.png");
        imgCardBack.setImage(new Image(inputStream));
        centerX = principalController.getWidth() / 2 - rootCardQuestion.getBoundsInLocal().getWidth() / 2;
        centerY = principalController.getHeight()/ 2 - rootCardQuestion.getBoundsInLocal().getHeight()/ 2;
        initialX = rootCardQuestion.getTranslateX();
        initialY = rootCardQuestion.getTranslateY(); 
        imgStack.setImage(new Image(inputStream));
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnOptionOne(ActionEvent event) {
        restoreCard();
    }

    @FXML
    private void onActionBtnOptionTwo(ActionEvent event) {
        restoreCard();
    }

    @FXML
    private void onActionBtnOptionThree(ActionEvent event) {
        restoreCard();
    }

    @FXML
    private void onActionBtnOptionFour(ActionEvent event) {
        restoreCard();
    }

    public void moveCard(String typeOfCard) {
        inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/" + typeOfCard + "Card.png");
        imgCardBack.setImage(new Image(inputStream));
        shuffleOption();
        showingFront = true;
        TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(1.5), rootCardQuestion);
        moveTransition.setToX(principalController.getWidth() / 2 - rootCardQuestion.getBoundsInLocal().getWidth() / 2 + 650);
        moveTransition.setToY(principalController.getHeight() / 2 - rootCardQuestion.getBoundsInLocal().getHeight()/ 2);
        moveTransition.play();
        moveTransition.setOnFinished(flip -> {
            flipCard();
        });
    }

    private void flipCard() {
        RotateTransition rotateBack = new RotateTransition(Duration.seconds(0.5), imgCardBack);
        rotateBack.setAxis(Rotate.Y_AXIS);
        rotateBack.setFromAngle(0);
        rotateBack.setToAngle(90);
        //Rotación hacia adelante 
        RotateTransition rotateFront = new RotateTransition(Duration.seconds(0.5), imgCardBack);
        rotateFront.setAxis(Rotate.Y_AXIS);
        rotateFront.setFromAngle(90);
        rotateFront.setToAngle(180);
        rotateBack.setOnFinished(event -> {
            rootCardQuestion.setVisible(!showingFront);
            imgCardBack.setVisible(showingFront);
            btnOptionOne.setPrefSize(170, 20);
            btnOptionTwo.setPrefSize(170, 20);
            btnOptionThree.setPrefSize(170, 20);
            btnOptionFour.setPrefSize(170, 20);
        });
        SequentialTransition flipAnimation = new SequentialTransition(rotateBack, rotateFront);
        flipAnimation.play();
        showingFront = !showingFront;
    }

    private void shuffleOption() {
        ArrayList<String> textosOriginales = new ArrayList<>();
        textosOriginales.add(btnOptionOne.getText());
        textosOriginales.add(btnOptionTwo.getText());
        textosOriginales.add(btnOptionThree.getText());
        textosOriginales.add(btnOptionFour.getText());
        Collections.shuffle(textosOriginales);
        btnOptionOne.setText(textosOriginales.get(0));
        btnOptionTwo.setText(textosOriginales.get(1));
        btnOptionThree.setText(textosOriginales.get(2));
        btnOptionFour.setText(textosOriginales.get(3));
        textosOriginales.clear();
    }

    private void restoreCard() {
        inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/CardBack.png");
        imgCardBack.setImage(new Image(inputStream));
        TranslateTransition moveBack = new TranslateTransition(Duration.seconds(1), rootCardQuestion);
        moveBack.setToX(initialX);
        moveBack.setToY(initialY);
        moveBack.play();
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), imgCardBack);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setToAngle(0);
        rotateTransition.play();
        //ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), rootCardQuestion);
        //scaleTransition.setToX(initialScaleX);
        //scaleTransition.setToY(initialScaleY);
        //rootCardQuestion.setPrefSize(68, 250);
        //scaleTransition.play();
        // Restablecer la visibilidad de los componentes
        btnOptionOne.setVisible(true);
        btnOptionTwo.setVisible(true);
        btnOptionThree.setVisible(true);
        btnOptionFour.setVisible(true);       
        imgCardBack.setVisible(true);
        imgStack.setImage(new Image (inputStream));
        rootCardQuestion.setVisible(true);
//        sliderTime.setValue(0);
    }

}
