
package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.model.CompetidorDto;
import cr.ac.una.proyectopreguntados.model.PreguntaDto;
import cr.ac.una.proyectopreguntados.util.AppContext;
import cr.ac.una.proyectopreguntados.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private CompetidorDto challenged;
    private CompetidorDto challenging;
    private String challengingAvatar;
    private String challengedAvatar;
    private ObservableList<PreguntaDto> preguntasList = FXCollections.observableArrayList();
    private ObservableList<PreguntaDto> preguntasEchas = FXCollections.observableArrayList();
    private PreguntaDto preguntaDto;
    private InputStream inputStream;
    private String question;
    private Boolean showingFrontCardOne = true;
    private Boolean showingFrontCardTwo = true;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        challenged = (CompetidorDto) AppContext.getInstance().get("challenged");
        challenging = (CompetidorDto) AppContext.getInstance().get("challenging");
        challengingAvatar = (String) AppContext.getInstance().get("typeAvatarChallenging");
        challengedAvatar = (String) AppContext.getInstance().get("typeAvatarChallenged");
        lblNamePlayer1.setText(challenging.getJugador().getNombre());
        lblNamePlayer2.setText(challenged.getJugador().getNombre());
    }    

    @Override
    public void initialize() {
}

    @FXML
    private void onActionBtnOption(ActionEvent event) {
        MFXButton button = (MFXButton) event.getSource();
        String buttonText = button.getText();
        SixPlayerBoardController sixPlayerBoardController = (SixPlayerBoardController) FlowController.getInstance().getController("SixPlayerBoardView");

        preguntaDto.getRespuestasList().stream()
                .filter(respuesta -> buttonText.equals(respuesta.getContenido()))
                .findFirst()
                .ifPresent(respuesta -> {
                    respuesta.setCantidadSelecciones(respuesta.getCantidadSelecciones() + 1);
                    if ("V".equals(respuesta.getTipo())) {
                        challenging.getJugador().setCantidadAciertos(challenging.getJugador().getCantidadAciertos() + 1);
                        typeQuestionJugador(preguntaDto.getCategoria(), challenging);
                      //  answer = true;
                        Platform.runLater(() -> button.setStyle("-fx-background-color: #00FF00"));
                        System.out.println("Respuesta correcta");
                    } else {
                      //  answer = false;
                        System.err.println("Respuesta incorrecta");
                        Platform.runLater(() -> button.setStyle("-fx-background-color: #FF0000"));
                    }

                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e -> {
                        //restoreCard();
                      //  preguntaDto.setModificado(true);
                       // preguntasEchas.add(preguntaDto);
                      //  sixPlayerBoardController.setCurrentCompetitor(challenging);
                        //unblockButtons();
                    });
                    pause.play();
                });

    }

    @FXML
    private void onActionBtnBombPlayer1(ActionEvent event) {
    }

    @FXML
    private void onActionBtnOptionPlayer2(ActionEvent event) {
        MFXButton button = (MFXButton) event.getSource();
        String buttonText = button.getText();
             preguntaDto.getRespuestasList().stream()
                .filter(respuesta -> buttonText.equals(respuesta.getContenido()))
                .findFirst()
                .ifPresent(respuesta -> {
                    respuesta.setCantidadSelecciones(respuesta.getCantidadSelecciones() + 1);
                    if ("V".equals(respuesta.getTipo())) {
                        challenged.getJugador().setCantidadAciertos(challenged.getJugador().getCantidadAciertos() + 1);
                        typeQuestionJugador(preguntaDto.getCategoria(), challenged);
                        //  answer = true;
                        Platform.runLater(() -> button.setStyle("-fx-background-color: #00FF00"));
                        System.out.println("Respuesta correcta");
                    } else {
                        //  answer = false;
                        Platform.runLater(() -> button.setStyle("-fx-background-color: #FF0000"));
                    }

                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e -> {
                        //restoreCard();
                       // preguntaDto.setModificado(true);
                     //   preguntasEchas.add(preguntaDto);
                        //  sixPlayerBoardController.setCurrentCompetitor(challenging);
                        //unblockButtons();
                    });
                    pause.play();
                });
        flipCardPlayerTwo();
    }

    @FXML
    private void onActionBtnBombPlayer2(ActionEvent event) {
        flipCardPlayerOne();
    }
    public void setTypeOfCard(String typeOfCard) {
        newQuestion(typeOfCard);
        //question = typeOfCard;
        if (typeOfCard.equals("Geografía")) {
            typeOfCard = "Geografia";
        }
        inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/" + typeOfCard + "Card.png");
        imgCardBack.setImage(new Image(inputStream));
        //showingFront = true;

        shuffleOption();
        //flipCard();
    }
private void newQuestion(String typeOfQuestion) {
    preguntaDto = selectRandomQuestion(typeOfQuestion);
    updateQuestionCount(preguntaDto);
    displayQuestion(preguntaDto);
    displayQuestionPlayer2(preguntaDto);
    updateQuestionLists(preguntaDto);
}

private PreguntaDto selectRandomQuestion(String typeOfQuestion) {
    ObservableList<PreguntaDto> questionFiltered = preguntasList.filtered(question -> question.getCategoria().equals(typeOfQuestion));
    int index = new Random().nextInt(questionFiltered.size());
    return questionFiltered.get(index);
}

private void updateQuestionCount(PreguntaDto question) {
    question.setCantidadLlamadas(question.getCantidadLlamadas() + 1);
}

private void displayQuestion(PreguntaDto question) {
    textOfQuestion.setText(question.getContenido());
    setOptions(btnOptionOne, btnOptionTwo, btnOptionThree, btnOptionFour, question);
}

private void displayQuestionPlayer2(PreguntaDto question) {
    textOfQuestionPlayer2.setText(question.getContenido());
    setOptions(btnOptionOnePlayer2, btnOptionTwoPlayer2, btnOptionThreePlayer2, btnOptionFourPlayer2, question);
}

private void setOptions(MFXButton btn1, MFXButton btn2, MFXButton btn3, MFXButton btn4, PreguntaDto question) {
    btn1.setText(question.getRespuestasList().get(0).getContenido());
    btn2.setText(question.getRespuestasList().get(1).getContenido());
    btn3.setText(question.getRespuestasList().get(2).getContenido());
    btn4.setText(question.getRespuestasList().get(3).getContenido());
}

private void updateQuestionLists(PreguntaDto question) {
    preguntasList.remove(question);
    preguntasEchas.add(question);
}
private void shuffleOptions(MFXButton... buttons) {
    ArrayList<String> originalTexts = new ArrayList<>();
    for (MFXButton button : buttons) {
        originalTexts.add(button.getText());
    }
    Collections.shuffle(originalTexts);
    for (int i = 0; i < buttons.length; i++) {
        buttons[i].setText(originalTexts.get(i));
    }
    originalTexts.clear();
}

private void shuffleOption() {
    shuffleOptions(btnOptionOne, btnOptionTwo, btnOptionThree, btnOptionFour);
    shuffleOptions(btnOptionOnePlayer2, btnOptionTwoPlayer2, btnOptionThreePlayer2, btnOptionFourPlayer2);
}
    private void typeQuestionJugador(String typeOfQuestion, CompetidorDto competidorDto){
        switch (typeOfQuestion) {
            case "Arte":
                competidorDto.getJugador().setCantidadAArte(competidorDto.getJugador().getCantidadAArte() + 1);
                break;
            case "Historia":
                competidorDto.getJugador().setCantidadAHistoria(competidorDto.getJugador().getCantidadAHistoria() + 1);
                break;
            case "Geografía":
                competidorDto.getJugador().setCantidadAGeografia(competidorDto.getJugador().getCantidadAGeografia() + 1);
                break;
            case "Ciencia":
                competidorDto.getJugador().setCantidadACiencia(competidorDto.getJugador().getCantidadACiencia() + 1);
                break;
            case "Deporte":
                competidorDto.getJugador().setCantidadADeporte(competidorDto.getJugador().getCantidadADeporte() + 1);
                break;
            case "Entretenimiento":
                competidorDto.getJugador().setCantidadAEntretenimiento(competidorDto.getJugador().getCantidadAEntretenimiento() + 1);
                break;
            default:
                break;
        }
    }   
    private void flipCardPlayerOne(){
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
            rootCardQuestion.setVisible(!showingFrontCardOne);
            imgCardBack.setVisible(showingFrontCardOne);
        });
        SequentialTransition flipAnimation = new SequentialTransition(rotateBack, rotateFront);
        flipAnimation.play();
        showingFrontCardOne = !showingFrontCardOne;
    }
    
    private void flipCardPlayerTwo(){
        RotateTransition rotateBack = new RotateTransition(Duration.seconds(2), imgCardBack2);
        rotateBack.setAxis(Rotate.Y_AXIS);
        rotateBack.setFromAngle(0);
        rotateBack.setToAngle(90);
        //Rotación hacia adelante 
        RotateTransition rotateFront = new RotateTransition(Duration.seconds(2), imgCardBack2);
        rotateFront.setAxis(Rotate.Y_AXIS);
        rotateFront.setFromAngle(90);
        rotateFront.setToAngle(180);
        rotateBack.setOnFinished(event -> {
            rootCardQuestion1.setVisible(!showingFrontCardTwo);
            imgCardBack2.setVisible(showingFrontCardTwo);
        });
        SequentialTransition flipAnimation = new SequentialTransition(rotateBack, rotateFront);
        flipAnimation.play();
        showingFrontCardTwo = !showingFrontCardTwo;
    }
}
