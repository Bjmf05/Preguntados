package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.model.CompetidorDto;
import cr.ac.una.proyectopreguntados.model.PartidaDto;
import cr.ac.una.proyectopreguntados.model.PreguntaDto;
import cr.ac.una.proyectopreguntados.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Marconi Calvo Campos
 */
public class CardController extends Controller implements Initializable {

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

    private Boolean showingFront = true;
    private PrincipalController principalController = (PrincipalController) FlowController.getInstance().getController("PrincipalView");
    private InputStream inputStream;
    private PreguntaDto preguntaDto;
    private CompetidorDto competidorDtoCurrent;
    private PartidaDto partidaDto;
    private boolean answer = false;
    @FXML
    private VBox vbCard;
    @FXML
    private AnchorPane principalRoot;

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
        restoreCard();
        ((Stage) principalRoot.getScene().getWindow()).close();
    }

    public void setTypeOfCard(String typeOfCard) {
        if (typeOfCard.equals("Geografía")) {
            typeOfCard = "Geografia";
        }
        inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/" + typeOfCard + "Card.png");
        imgCardBack.setImage(new Image(inputStream));
        showingFront = true;
        newQuestion(typeOfCard);
        shuffleOption();
        flipCard();
    }

    public void moveCard(String typeOfCard) {
        if (typeOfCard.equals("Geografía")) {
            typeOfCard = "Geografia";
        }
        inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/" + typeOfCard + "Card.png");
        imgCardBack.setImage(new Image(inputStream));
        //newQuestion(typeOfCard);
        shuffleOption();
        showingFront = true;
        TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(1.5), rootCardQuestion);
        moveTransition.setToX(principalController.getWidth() / 2 - rootCardQuestion.getBoundsInLocal().getWidth() / 2 + 650);
        moveTransition.setToY(principalController.getHeight() / 2 - rootCardQuestion.getBoundsInLocal().getHeight() / 2);
        moveTransition.play();
        moveTransition.setOnFinished(flip -> {
            flipCard();
        });
    }

    private void flipCard() {
        RotateTransition rotateBack = new RotateTransition(Duration.seconds(2), imgCardBack);
        rotateBack.setAxis(Rotate.Y_AXIS);
        rotateBack.setFromAngle(0);
        rotateBack.setToAngle(90);
        //Rotación hacia adelante 
        RotateTransition rotateFront = new RotateTransition(Duration.seconds(2), imgCardBack);
        rotateFront.setAxis(Rotate.Y_AXIS);
        rotateFront.setFromAngle(90);
        rotateFront.setToAngle(180);
        rotateBack.setOnFinished(event -> {
            rootCardQuestion.setVisible(!showingFront);
            imgCardBack.setVisible(showingFront);
//            btnOptionOne.setPrefSize(170, 20);
//            btnOptionTwo.setPrefSize(170, 20);
//            btnOptionThree.setPrefSize(170, 20);
//            btnOptionFour.setPrefSize(170, 20);
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

    private void restoreCard(){
        inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/CardBack.png");
        imgCardBack.setImage(new Image(inputStream));
        btnOptionOne.setVisible(true);
        btnOptionTwo.setVisible(true);
        btnOptionThree.setVisible(true);
        btnOptionFour.setVisible(true);
        imgCardBack.setVisible(true);
        rootCardQuestion.setVisible(true);
        showingFront = true;
}
    
    private void newQuestion(String typeOfQuestion) {
        SixPlayerBoardController sixPlayerBoardController = (SixPlayerBoardController) FlowController.getInstance().getController("SixPlayerBoardView");
        partidaDto = sixPlayerBoardController.getGame();
        ObservableList<PreguntaDto> questions = sixPlayerBoardController.getPreguntasList();
        ObservableList<PreguntaDto> questionFiltered = questions.filtered(question -> question.getCategoria().equals(typeOfQuestion));
        Random random = new Random();
        int index = random.nextInt(questionFiltered.size());
        preguntaDto = questionFiltered.get(index);
        preguntaDto.setCantidadLlamadas(preguntaDto.getCantidadLlamadas() + 1);
        textOfQuestion.setText(preguntaDto.getContenido());
        btnOptionOne.setText(preguntaDto.getPlamRespuestasList().get(0).getContenido());
        btnOptionTwo.setText(preguntaDto.getPlamRespuestasList().get(1).getContenido());
        btnOptionThree.setText(preguntaDto.getPlamRespuestasList().get(2).getContenido());
        btnOptionFour.setText(preguntaDto.getPlamRespuestasList().get(3).getContenido());
        questions.remove(index);
        competidorDtoCurrent = sixPlayerBoardController.getCurrentCompetitor();
        competidorDtoCurrent.getJugador().setPartidasJugadas(competidorDtoCurrent.getJugador().getPartidasJugadas() + 1);
        sixPlayerBoardController.setPreguntasList(questions);
    }
}
