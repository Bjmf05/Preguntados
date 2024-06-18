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
import javafx.animation.KeyFrame;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
    private boolean answer = false;
    @FXML
    private VBox vbCard;
    @FXML
    private AnchorPane principalRoot;
    private ObservableList<PreguntaDto> preguntasList = FXCollections.observableArrayList();
    private ObservableList<PreguntaDto> preguntasEchas = FXCollections.observableArrayList();
    private String question;
    @FXML
    private MFXButton btnBomb;
    @FXML
    private MFXButton btnSecondTry;
    @FXML
    private MFXButton btnPassQuestion;
    GameBoardController gameBoardController;
    private Boolean isDuel = false;
    private Boolean secondTry = false;
    private Timeline timeline;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GameBoardController gameBoardController = (GameBoardController) FlowController.getInstance().getControllerBoard();
        // TODO
        this.gameBoardController = gameBoardController;
        competidorDtoCurrent = gameBoardController.getCurrentCompetitor();
        fillQuestions();
        clearButtons();
        disableWildCards();
        unblockButtons();
    }

    @Override
    public void initialize() {
        fillQuestions();
        GameBoardController gameBoardController = (GameBoardController) FlowController.getInstance().getControllerBoard();
        this.gameBoardController = gameBoardController;
        clearButtons();
        unblockWildCards();
        disableWildCards();
        unblockButtons();
    }

    @FXML
    private void onActionBtnOption(ActionEvent event) {
        clearButtons();
        blockButtons();
        blockWildCards();
        MFXButton button = (MFXButton) event.getSource();
        String buttonText = button.getText();
        GameBoardController gameBoardController = (GameBoardController) FlowController.getInstance().getControllerBoard();

        preguntaDto.getRespuestasList().stream()
                .filter(respuesta -> buttonText.equals(respuesta.getContenido()))
                .findFirst()
                .ifPresent(respuesta -> {
                    respuesta.setCantidadSelecciones(respuesta.getCantidadSelecciones() + 1);
                    if ("V".equals(respuesta.getTipo())) {
                        competidorDtoCurrent.getJugador().setCantidadAciertos(competidorDtoCurrent.getJugador().getCantidadAciertos() + 1);
                        typeQuestionJugador(preguntaDto.getCategoria());
                        answer = true;
                        Platform.runLater(() -> button.setStyle("-fx-background-color: #00FF00"));
                    } else {
                        answer = false;
                        Platform.runLater(() -> button.setStyle("-fx-background-color: #FF0000"));
                        if (secondTry) {
                            secondTry = false;
                            unblockButtons();
                            return;
                        }
                    }
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e -> {
                        restoreCard();
                        preguntaDto.setModificado(true);
                        preguntasEchas.add(preguntaDto);
                        gameBoardController.setCurrentCompetitor(competidorDtoCurrent);
                          ((Stage) principalRoot.getScene().getWindow()).close();
                    });
                    pause.play();
                });
        timeline.stop();
    }

    public void setTypeOfCard(String typeOfCard) {
        newQuestion(typeOfCard);
        question = typeOfCard;
        if (typeOfCard.equals("Geografía")) {
            typeOfCard = "Geografia";
        }
        inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/" + typeOfCard + "Card.png");
        imgCardBack.setImage(new Image(inputStream));
        imgCardBack.setFitHeight(243);
        imgCardBack.setFitWidth(200);
        imgCardBack.setPreserveRatio(false);
        showingFront = true;
        shuffleOption();
        flipCard();
    }

    private void flipCard() {
        RotateTransition rotateBack = new RotateTransition(Duration.seconds(3.5), imgCardBack);
        rotateBack.setAxis(Rotate.Y_AXIS);
        rotateBack.setFromAngle(0);
        rotateBack.setToAngle(90);
        //Rotación hacia adelante 
        RotateTransition rotateFront = new RotateTransition(Duration.seconds(2.5), vbCard);
        rotateFront.setAxis(Rotate.Y_AXIS);
        rotateFront.setFromAngle(-90);
        rotateFront.setToAngle(0);
        rotateBack.setOnFinished(event -> {
            rootCardQuestion.setVisible(!showingFront);
            imgCardBack.setVisible(showingFront);
        });
        SequentialTransition flipAnimation = new SequentialTransition(rotateBack, rotateFront);
        flipAnimation.play();
        showingFront = !showingFront;
        setTimeOfSliderTime();
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
        btnSecondTry.setDisable(true);
}
    
    private void newQuestion(String typeOfQuestion) {

        //partidaDto = gameBoardController.getGame();
        ObservableList<PreguntaDto> questionFiltered = preguntasList.filtered(question -> question.getCategoria().equals(typeOfQuestion));
        if (questionFiltered.isEmpty()) {
            gameBoardController.finishGame("no hay preguntas disponibles.");
            ((Stage) principalRoot.getScene().getWindow()).close();
            return;
        }else {
            Random random = new Random();
            int index = random.nextInt(questionFiltered.size());
            preguntaDto = questionFiltered.get(index);
            preguntaDto.setCantidadLlamadas(preguntaDto.getCantidadLlamadas() + 1);
            textOfQuestion.setText(preguntaDto.getContenido());
            btnOptionOne.setText(preguntaDto.getRespuestasList().get(0).getContenido());
            btnOptionTwo.setText(preguntaDto.getRespuestasList().get(1).getContenido());
            btnOptionThree.setText(preguntaDto.getRespuestasList().get(2).getContenido());
            btnOptionFour.setText(preguntaDto.getRespuestasList().get(3).getContenido());
            preguntasList.remove(preguntaDto);
            competidorDtoCurrent.getJugador().setCantidadPreguntas(competidorDtoCurrent.getJugador().getCantidadPreguntas() + 1);
        }
    }
    private void typeQuestionJugador(String typeOfQuestion) {
        switch (typeOfQuestion) {
            case "Arte":
                competidorDtoCurrent.getJugador().setCantidadAArte(competidorDtoCurrent.getJugador().getCantidadAArte() + 1);
                break;
            case "Historia":
                competidorDtoCurrent.getJugador().setCantidadAHistoria(competidorDtoCurrent.getJugador().getCantidadAHistoria() + 1);
                break;
            case "Geografía":
                competidorDtoCurrent.getJugador().setCantidadAGeografia(competidorDtoCurrent.getJugador().getCantidadAGeografia() + 1);
                break;
            case "Ciencia":
                competidorDtoCurrent.getJugador().setCantidadACiencia(competidorDtoCurrent.getJugador().getCantidadACiencia() + 1);
                break;
            case "Deporte":
                competidorDtoCurrent.getJugador().setCantidadADeporte(competidorDtoCurrent.getJugador().getCantidadADeporte() + 1);
                break;
            case "Entretenimiento":
                competidorDtoCurrent.getJugador().setCantidadAEntretenimiento(competidorDtoCurrent.getJugador().getCantidadAEntretenimiento() + 1);
                break;
            default:
                break;
        }
    }
    private void clearButtons() {
        btnOptionOne.setStyle("-fx-background-color: #FFFFFF");
        btnOptionTwo.setStyle("-fx-background-color: #FFFFFF");
        btnOptionThree.setStyle("-fx-background-color: #FFFFFF");
        btnOptionFour.setStyle("-fx-background-color: #FFFFFF");
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    private void fillQuestions(){
        preguntasList = (ObservableList<PreguntaDto>) AppContext.getInstance().get("PreguntasList");
        preguntasEchas = (ObservableList<PreguntaDto>) AppContext.getInstance().get("PreguntasEchas");
    }
    private void blockButtons(){
        btnOptionOne.setDisable(true);
        btnOptionTwo.setDisable(true);
        btnOptionThree.setDisable(true);
        btnOptionFour.setDisable(true);
    }
    private void unblockButtons(){
        btnOptionOne.setDisable(false);
        btnOptionTwo.setDisable(false);
        btnOptionThree.setDisable(false);
        btnOptionFour.setDisable(false);
    }

    @FXML
    private void onActionBtnBomb(ActionEvent event) {
        ArrayList<MFXButton> buttonList = new ArrayList<>();
        buttonList.add(btnOptionOne);
        buttonList.add(btnOptionTwo);
        buttonList.add(btnOptionThree);
        buttonList.add(btnOptionFour);
        // Eliminar dos botones al azar, excepto la respuesta correcta
        MFXButton correctButton = null;
        for (MFXButton button : buttonList) {
            String buttonText = button.getText();
            boolean correctOpcion = preguntaDto.getRespuestasList().stream().anyMatch(respuesta -> 
                    buttonText.equals(respuesta.getContenido()) && "V".equals(respuesta.getTipo()));
            if (correctOpcion) {
                correctButton = button;
                break;
            }
        }
        if (correctButton != null) {
            buttonList.remove(correctButton);
            buttonList.get(0).setVisible(false);
            buttonList.get(1).setVisible(false);
        }
        competidorDtoCurrent.setComodinBomba(0L);
        buttonList.clear();
        blockWildCards();
    }

    @FXML
    private void onActionBtnSecondTry(ActionEvent event) {
        competidorDtoCurrent.setComodinDoble(competidorDtoCurrent.getComodinDoble() - 1); 
        secondTry = true;
        competidorDtoCurrent.setComodinDoble(0L);
        blockWildCards();
    }

    @FXML
    private void onActionBtnPassQuestion(ActionEvent event) {
        GameBoardController gameBoardController = (GameBoardController) FlowController.getInstance().getControllerBoard();
        ObservableList<PreguntaDto> questionFiltered = preguntasList.filtered(question -> question.getCategoria().equals(this.question));
        if (questionFiltered.isEmpty()) {
            gameBoardController.finishGame("no hay preguntas disponibles.");
            ((Stage) principalRoot.getScene().getWindow()).close();
            return;
        }
        Random random = new Random();
        int index = random.nextInt(questionFiltered.size());
        preguntaDto = questionFiltered.get(index);
        preguntaDto.setCantidadLlamadas(preguntaDto.getCantidadLlamadas() + 1);
        textOfQuestion.setText(preguntaDto.getContenido());
        btnOptionOne.setText(preguntaDto.getRespuestasList().get(0).getContenido());
        btnOptionTwo.setText(preguntaDto.getRespuestasList().get(1).getContenido());
        btnOptionThree.setText(preguntaDto.getRespuestasList().get(2).getContenido());
        btnOptionFour.setText(preguntaDto.getRespuestasList().get(3).getContenido());
        competidorDtoCurrent.setComodinPasar(0L);
        unblockButtons();
        blockWildCards();
        preguntasList.remove(preguntaDto);
    }
    private void disableWildCards(){
        if(competidorDtoCurrent.getComodinBomba() == 0){
            btnBomb.setDisable(true);
        }
        if (competidorDtoCurrent.getComodinPasar()==0){
            btnPassQuestion.setDisable(true);
        }
        if (competidorDtoCurrent.getComodinDoble()==0){
            btnSecondTry.setDisable(true);
        }

    }
    private void blockWildCards(){
        btnBomb.setDisable(true);
        btnPassQuestion.setDisable(true);
        btnSecondTry.setDisable(true);
    }
    private void unblockWildCards(){
        btnBomb.setDisable(false);
        btnPassQuestion.setDisable(false);
        btnSecondTry.setDisable(false);
    }

    private void setTimeOfSliderTime(){
        sliderTime.setMin(0);
        sliderTime.setMax(18);
        sliderTime.setValue(0);
        // Detener y limpiar cualquier Timeline existente
        if (timeline != null) {
            timeline.stop();
        }
        // Configurar el timeline para actualizar el slider cada segundo
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    double currentValue = sliderTime.getValue();
                    double newValue = currentValue + 1;
                    if (newValue > sliderTime.getMax()) {
                        newValue = sliderTime.getMin();
                    }
                    sliderTime.setValue(newValue);
                    if (newValue >= 18) {
                        answer = false;
                        preguntaDto.setModificado(true);
                        preguntasEchas.add(preguntaDto);
                        gameBoardController.setCurrentCompetitor(competidorDtoCurrent);
                        if (principalRoot != null && ((javafx.scene.Node) principalRoot).getScene() != null) {
                            Stage stage = (Stage) ((javafx.scene.Node) principalRoot).getScene().getWindow();
                            if (stage != null) {
                                stage.close();
                            }
                        }
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        sliderTime.setDisable(true);
    }
}
