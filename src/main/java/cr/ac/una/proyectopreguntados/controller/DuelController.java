
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

import javafx.animation.*;
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
    private InputStream inputStreamCardPlayerOne;
    private InputStream inputStreamCardPlayerTwo;
    private InputStream inputStreamAvatar;
    private String question;
    private Boolean showingFrontCardOne = true;
    private Boolean showingFrontCardTwo = true;
    private boolean answerPlayer1;
    private boolean answerPlayer2;
    private boolean tied = false;
    private boolean winner = false;
    MFXButton buttonPlayer1;
    MFXButton buttonPlayer2;
    @FXML
    private Label lblPlayerTurn;
    @FXML
    private Label lblWinner;
    private final String routeArte = "/cr/ac/una/proyectopreguntados/resources/Art.png";
    private final String routeGeografia = "/cr/ac/una/proyectopreguntados/resources/Geography.png";
    private final String routeHistoria = "/cr/ac/una/proyectopreguntados/resources/History.png";
    private final String routeCiencia = "/cr/ac/una/proyectopreguntados/resources/Science.png";
    private final String routeDeporte = "/cr/ac/una/proyectopreguntados/resources/Sports.png";
    private final String routeEntretenimiento = "/cr/ac/una/proyectopreguntados/resources/Entertainment.png";
    @FXML
    private MFXButton btnSecondTryPlayerOne;
    @FXML
    private MFXButton btnPassQuestionPlayerOne;
    @FXML
    private MFXButton btnSecondTryPlayerTwo;
    @FXML
    private MFXButton btnPassQuestionPlayerTwo;
    @FXML
    private AnchorPane root;
    private Boolean secondTryPlayerOne = false;
    private Boolean secondTryPlayerTwo = false;
    private Timeline timeline;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clearButtons();
        challenged = (CompetidorDto) AppContext.getInstance().get("challenged");
        challenging = (CompetidorDto) AppContext.getInstance().get("challenging");
        challengingAvatar = (String) AppContext.getInstance().get("typeAvatarChallenging");
        challengedAvatar = (String) AppContext.getInstance().get("typeAvatarChallenged");
        inputStreamAvatar = App.class.getResourceAsStream(getRouteAvatar(challengedAvatar));
        imgTypeQuestion.setImage(new Image(inputStreamAvatar));
        preguntasList = (ObservableList<PreguntaDto>) AppContext.getInstance().get("PreguntasList");
        preguntasEchas = (ObservableList<PreguntaDto>) AppContext.getInstance().get("PreguntasEchas");
        lblNamePlayer1.setText(challenging.getJugador().getNombre());
        lblPlayerTurn.setText(challenging.getJugador().getNombre());
        lblNamePlayer2.setText(challenged.getJugador().getNombre());
        setTypeOfCard(challengedAvatar);
        flipCardPlayerOne();
        blockButtons(btnOptionOnePlayer2, btnOptionTwoPlayer2, btnOptionThreePlayer2, btnOptionFourPlayer2);
        vbCard1.setVisible(false);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnOption(ActionEvent event) {
        blockButtons(btnOptionOne, btnOptionTwo, btnOptionThree, btnOptionFour);
        buttonPlayer1 = (MFXButton) event.getSource();
        String buttonText = buttonPlayer1.getText();
        preguntaDto.getRespuestasList().stream()
                .filter(respuesta -> buttonText.equals(respuesta.getContenido()))
                .findFirst()
                .ifPresent(respuesta -> {
                    respuesta.setCantidadSelecciones(respuesta.getCantidadSelecciones() + 1);
                    if ("V".equals(respuesta.getTipo())) {
                        challenging.getJugador().setCantidadAciertos(challenging.getJugador().getCantidadAciertos() + 1);
                        typeQuestionJugador(preguntaDto.getCategoria(), challenging);
                        answerPlayer1 = true;
                    } else {
                        answerPlayer1 = false;
                        if (secondTryPlayerOne) {
                            secondTryPlayerOne = false;
                            unblockButtons(btnOptionOne, btnOptionTwo, btnOptionThree, btnOptionFour);
                            return;
                        }
                    }
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.40));
                    pause.setOnFinished(e -> {
                        lblPlayerTurn.setText(challenged.getJugador().getNombre());
                        unblockButtons(btnOptionOnePlayer2, btnOptionTwoPlayer2, btnOptionThreePlayer2, btnOptionFourPlayer2);
                        flipCardPlayerTwo();
                    });
                    pause.play();
                });
    }

    @FXML
    private void onActionBtnBombPlayer1(ActionEvent event) {
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
        challenging.setComodinBomba(0L);
        buttonList.clear();
        blockWildCardsPlayerOne();
    }

    @FXML
    private void onActionBtnOptionPlayer2(ActionEvent event) {
        buttonPlayer2 = (MFXButton) event.getSource();
        String buttonText = buttonPlayer2.getText();
        preguntaDto.getRespuestasList().stream()
                .filter(respuesta -> buttonText.equals(respuesta.getContenido()))
                .findFirst()
                .ifPresent(respuesta -> {
                    respuesta.setCantidadSelecciones(respuesta.getCantidadSelecciones() + 1);
                    if ("V".equals(respuesta.getTipo())) {
                        challenged.getJugador().setCantidadAciertos(challenged.getJugador().getCantidadAciertos() + 1);
                        typeQuestionJugador(preguntaDto.getCategoria(), challenged);
                        answerPlayer2 = true;
                        Platform.runLater(() -> {
                            buttonPlayer2.setStyle("-fx-background-color: #00FF00");
                            if (buttonPlayer1 != null) {
                                buttonPlayer1.setStyle(answerPlayer1 ? "-fx-background-color: #00FF00" : "-fx-background-color: #FF0000");
                            }
                        });
                    } else {
                        answerPlayer2 = false;
                        Platform.runLater(() -> {
                            buttonPlayer2.setStyle("-fx-background-color: #FF0000");
                            if (buttonPlayer1 != null) {
                                buttonPlayer1.setStyle(answerPlayer1 ? "-fx-background-color: #00FF00" : "-fx-background-color: #FF0000");
                            }
                        });
                        if (secondTryPlayerTwo) {
                            secondTryPlayerTwo = false;
                            unblockButtons(btnOptionOnePlayer2, btnOptionTwoPlayer2, btnOptionThreePlayer2, btnOptionFourPlayer2);
                            return;
                        }
                    }
                    timeline.stop();
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        restoreOptionPlayerTwo();
                        clearButtons();
                        finishDuel();
                    });
                    pause.play();
                });
    }

    private void finishDuel() {
        restoreCardPlayerOne();
        if (buttonPlayer2 == null && buttonPlayer1 != null) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                buttonPlayer1.setStyle(answerPlayer1 ? "-fx-background-color: #00FF00" : "-fx-background-color: #FF0000");
            });
            pause.play();
        }
        if (tied && !answerPlayer2 || !tied && answerPlayer1 && !answerPlayer2) {
            actionAvatarPlayer(challengedAvatar, challenged, "I");
            actionAvatarPlayer(challengedAvatar, challenging, "A");
            answerPlayer1 = true;
            winner = true;
            lblWinner.setText(challenging.getJugador().getNombre());
        } else if (!tied && (answerPlayer1 == answerPlayer2)) {
            tied = true;
            restoreCardPlayerOne();
            flipCardPlayerTwo();
            setTypeOfCard(challengedAvatar);
            flipCardPlayerTwo();
            blockButtons(btnOptionOne, btnOptionTwo, btnOptionThree, btnOptionFour);
            clearButtons();
            lblWinner.setText("Empate");
            return;
        } else if (!tied && !answerPlayer1 && answerPlayer2 || tied && answerPlayer2) {
            actionAvatarPlayer(challengingAvatar, challenging, "I");
            answerPlayer1=false;
            winner = false;
            lblWinner.setText(challenged.getJugador().getNombre());
        }
        actionFinishGame();
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(p -> Platform.runLater(() -> {
            if (root != null && ((javafx.scene.Node) root).getScene() != null) {
                Stage stage = (Stage) ((javafx.scene.Node) root).getScene().getWindow();
                if (stage != null) {
                    stage.close();
                }
            }
        }));
        pause.play();
    }

    private void actionFinishGame() {
        GameBoardController gameBoardController = (GameBoardController) FlowController.getInstance().getControllerBoard();
        CompetidorDto[] competitiors = {challenging, challenged};
        int current = gameBoardController.getCurrentPlayer();
        for (CompetidorDto competidorDto : competitiors) {
            gameBoardController.setCurrentPlayer(competidorDto.getNumeroJugador().intValue());
            String[] playerCategories = {competidorDto.getDeporte(), competidorDto.getEntretenimiento(), competidorDto.getCiencias(), competidorDto.getHistoria(), competidorDto.getGeografia(), competidorDto.getArte()};
            ImageView[] playerImages = gameBoardController.getPlayerImages();
            for (int j = 0; j < playerCategories.length; j++) {
                if (playerCategories[j].equals("A")) {
                    gameBoardController.getCharacter(playerImages[j]);
                } else {
                    ImageView imageView = playerImages[j];
                    imageView.setOpacity(0.5);
                }

            }
        }
        gameBoardController.setCurrentPlayer(current);
        updatePlayer(challenging);
        updatePlayer(challenged);
        gameBoardController.setCurrentCompetitor(challenging);
        gameBoardController.setPlayers();

    }

    private void updatePlayer(CompetidorDto competidorDto) {
        ObservableList<CompetidorDto> competitorsPlayer = (ObservableList<CompetidorDto>) AppContext.getInstance().get("competitorsPlayer");
        competitorsPlayer.replaceAll(competitor -> competitor.getJugador().getNombre().equals(competidorDto.getJugador().getNombre()) ? competidorDto : competitor);
    }

    @FXML
    private void onActionBtnBombPlayer2(ActionEvent event) {
        ArrayList<MFXButton> buttonList = new ArrayList<>();
        buttonList.add(btnOptionOnePlayer2);
        buttonList.add(btnOptionTwoPlayer2);
        buttonList.add(btnOptionThreePlayer2);
        buttonList.add(btnOptionFourPlayer2);
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
        challenged.setComodinBomba(0L);
        buttonList.clear();
        blockWildCardsPlayerTwo();
    }

    public void setTypeOfCard(String typeOfCard) {
        newQuestion(typeOfCard);
        question = typeOfCard;
        if (typeOfCard.equals("Geografia")) {
            typeOfCard = "Geografia";
        }
        inputStreamCardPlayerOne = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/" + typeOfCard + "Card.png");
        inputStreamCardPlayerTwo = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/" + typeOfCard + "Card.png");
        imgCardBack.setImage(new Image(inputStreamCardPlayerOne));
        imgCardBack.setFitHeight(272);
        imgCardBack.setFitWidth(244);
        imgCardBack.setPreserveRatio(false);
        imgCardBack2.setImage(new Image(inputStreamCardPlayerTwo));
        imgCardBack2.setFitHeight(272);
        imgCardBack2.setFitWidth(244);
        imgCardBack2.setPreserveRatio(false);
        //showingFront = true;

        shuffleOption();
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
        if (questionFiltered.isEmpty()) {
            GameBoardController gameBoardController = (GameBoardController) FlowController.getInstance().getControllerBoard();
            gameBoardController.finishGame("no hay preguntas disponibles.");
            getStage().close();
        }
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

    private void blockButtons(MFXButton btn1, MFXButton btn2, MFXButton btn3, MFXButton btn4) {
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
    }

    private void unblockButtons(MFXButton btn1, MFXButton btn2, MFXButton btn3, MFXButton btn4) {
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
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

    private void typeQuestionJugador(String typeOfQuestion, CompetidorDto competidorDto) {
        switch (typeOfQuestion) {
            case "Arte":
                competidorDto.getJugador().setCantidadAArte(competidorDto.getJugador().getCantidadAArte() + 1);
                break;
            case "Historia":
                competidorDto.getJugador().setCantidadAHistoria(competidorDto.getJugador().getCantidadAHistoria() + 1);
                break;
            case "Geografia":
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

    private void actionAvatarPlayer(String typeOfQuestion, CompetidorDto competidorDto, String state) {
        switch (typeOfQuestion) {
            case "Arte":
                competidorDto.setArte(state);
                break;
            case "Historia":
                competidorDto.setHistoria(state);
                break;
            case "Geografia":
                competidorDto.setGeografia(state);
                break;
            case "Ciencia":
                competidorDto.setCiencias(state);
                break;
            case "Deporte":
                competidorDto.setDeporte(state);
                break;
            case "Entretenimiento":
                competidorDto.setEntretenimiento(state);
                break;
            default:
                break;
        }
    }

    private void flipCardPlayerOne() {
        RotateTransition rotateBack = new RotateTransition(Duration.seconds(1.5), imgCardBack);
        rotateBack.setAxis(Rotate.Y_AXIS);
        rotateBack.setFromAngle(0);
        rotateBack.setToAngle(90);
        //Rotación hacia adelante 
        RotateTransition rotateFront = new RotateTransition(Duration.seconds(1.5), vbCard);
        rotateFront.setAxis(Rotate.Y_AXIS);
        rotateFront.setFromAngle(-90);
        rotateFront.setToAngle(0);
        rotateBack.setOnFinished(event -> {
            rootCardQuestion.setVisible(!showingFrontCardOne);
            imgCardBack.setVisible(showingFrontCardOne);
        });
        SequentialTransition flipAnimation = new SequentialTransition(rotateBack, rotateFront);
        flipAnimation.play();
        showingFrontCardOne = !showingFrontCardOne;
        setTimeOfSliderTime(challenging);
    }

    private void flipCardPlayerTwo() {
        RotateTransition rotateBack = new RotateTransition(Duration.seconds(1.5), imgCardBack2);
        rotateBack.setAxis(Rotate.Y_AXIS);
        rotateBack.setFromAngle(0);
        rotateBack.setToAngle(90);
        //Rotación hacia adelante 
        RotateTransition rotateFront = new RotateTransition(Duration.seconds(1.5), vbCard1);
        rotateFront.setAxis(Rotate.Y_AXIS);
        rotateFront.setFromAngle(-90);
        rotateFront.setToAngle(0);
        vbCard1.setVisible(true);
        rotateBack.setOnFinished(event -> {
            rootCardQuestion1.setVisible(!showingFrontCardTwo);
            imgCardBack2.setVisible(showingFrontCardTwo);
        });
        SequentialTransition flipAnimation = new SequentialTransition(rotateBack, rotateFront);
        flipAnimation.play();
        showingFrontCardTwo = !showingFrontCardTwo;
        setTimeOfSliderTime(challenged);
    }

    public boolean isWinner() {
        return winner;
    }

    private void clearButtons() {
        btnOptionOne.setStyle("-fx-background-color: #FFFFFF");
        btnOptionTwo.setStyle("-fx-background-color: #FFFFFF");
        btnOptionThree.setStyle("-fx-background-color: #FFFFFF");
        btnOptionFour.setStyle("-fx-background-color: #FFFFFF");
        btnOptionOnePlayer2.setStyle("-fx-background-color: #FFFFFF");
        btnOptionTwoPlayer2.setStyle("-fx-background-color: #FFFFFF");
        btnOptionThreePlayer2.setStyle("-fx-background-color: #FFFFFF");
        btnOptionFourPlayer2.setStyle("-fx-background-color: #FFFFFF");
    }

    private String getRouteAvatar(String routeAvatar) {
        switch (routeAvatar) {
            case "Arte":
                return routeArte;
            case "Geografia":
                return routeGeografia;
            case "Historia":
                return routeHistoria;
            case "Ciencia":
                return routeCiencia;
            case "Deporte":
                return routeDeporte;
            case "Entretenimiento":
                return routeEntretenimiento;
            default:
                return "";
        }
    }

    @FXML
    private void onActionBtnSecondTryPlayerOne(ActionEvent event) {
        challenging.setComodinDoble(challenging.getComodinDoble() - 1);
        secondTryPlayerOne = true;
        challenging.setComodinDoble(0L);
        blockWildCardsPlayerOne();
    }

    @FXML
    private void onActionBtnPassQuestionPlayerOne(ActionEvent event) {
        GameBoardController gameBoardController = (GameBoardController) FlowController.getInstance().getControllerBoard();
        //partidaDto = gameBoardController.getGame();
        ObservableList<PreguntaDto> questionFiltered = preguntasList.filtered(question -> question.getCategoria().equals(this.question));
        if (questionFiltered.isEmpty()) {
            gameBoardController.finishGame("no hay preguntas disponibles.");
            ((Stage) root.getScene().getWindow()).close();
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
        challenging.setComodinPasar(0L);
//        unblockButtons();
        preguntasList.remove(preguntaDto);
        blockWildCardsPlayerOne();
    }

    @FXML
    private void onActionBtnSecondTryPlayerTwo(ActionEvent event) {
        challenged.setComodinDoble(challenged.getComodinDoble() - 1);
        secondTryPlayerTwo = true;
        challenged.setComodinDoble(0L);
        blockWildCardsPlayerTwo();
    }

    @FXML
    private void onActionBtnPassQuestionPlayerTwo(ActionEvent event) {
        GameBoardController gameBoardController = (GameBoardController) FlowController.getInstance().getControllerBoard();
        //partidaDto = gameBoardController.getGame();
        ObservableList<PreguntaDto> questionFiltered = preguntasList.filtered(question -> question.getCategoria().equals(this.question));
        if (questionFiltered.isEmpty()) {
            gameBoardController.finishGame("no hay preguntas disponibles.");
            ((Stage) root.getScene().getWindow()).close();
            return;
        }
        Random random = new Random();
        int index = random.nextInt(questionFiltered.size());
        preguntaDto = questionFiltered.get(index);
        preguntaDto.setCantidadLlamadas(preguntaDto.getCantidadLlamadas() + 1);
        textOfQuestionPlayer2.setText(preguntaDto.getContenido());
        btnOptionOnePlayer2.setText(preguntaDto.getRespuestasList().get(0).getContenido());
        btnOptionTwoPlayer2.setText(preguntaDto.getRespuestasList().get(1).getContenido());
        btnOptionThreePlayer2.setText(preguntaDto.getRespuestasList().get(2).getContenido());
        btnOptionFourPlayer2.setText(preguntaDto.getRespuestasList().get(3).getContenido());
        challenged.setComodinPasar(0L);
//        unblockButtons();
        preguntasList.remove(preguntaDto);
        blockWildCardsPlayerTwo();
    }

    private void blockWildCardsPlayerOne() {
        btnBombPlayer1.setDisable(true);
        btnPassQuestionPlayerOne.setDisable(true);
        btnSecondTryPlayerOne.setDisable(true);
    }

    private void blockWildCardsPlayerTwo() {
        btnBombPlayer2.setDisable(true);
        btnPassQuestionPlayerTwo.setDisable(true);
        btnSecondTryPlayerTwo.setDisable(true);
    }

    private void restoreCardPlayerOne() {
        rootCardQuestion.setDisable(false);
        vbCard.setVisible(false);
        imgCardBack.setFitHeight(272);
        imgCardBack.setFitWidth(244);
        imgCardBack.setPreserveRatio(false);
        imgCardBack.setVisible(true);
    }

    private void setTimeOfSliderTime(CompetidorDto competidorDto) {
        sliderTime1.setMin(0);
        sliderTime1.setMax(18);
        sliderTime1.setValue(0);
        sliderTime.setMin(0);
        sliderTime.setMax(18);
        sliderTime.setValue(0);
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    double currentValue = competidorDto == challenging ? sliderTime.getValue() : sliderTime1.getValue();
                    double newValue = currentValue + 1;
                    if (newValue > sliderTime.getMax()) {
                        newValue = sliderTime.getMin();
                    }
                    if (competidorDto == challenging) {
                        sliderTime.setValue(newValue);
                        if (newValue >= 18) {
                            answerPlayer1 = false;
                            preguntaDto.setModificado(true);
                            preguntasEchas.add(preguntaDto);
                            flipCardPlayerTwo();
                            unblockButtons(btnOptionOnePlayer2, btnOptionTwoPlayer2, btnOptionThreePlayer2, btnOptionFourPlayer2);
                            lblPlayerTurn.setText(challenged.getJugador().getNombre());
                        }
                    } else if (competidorDto == challenged) {
                        sliderTime1.setValue(newValue);
                        if (newValue >= 18) {
                            answerPlayer2 = false;
                            preguntaDto.setModificado(true);
                            preguntasEchas.add(preguntaDto);
                            finishDuel();
                        }
                    }

                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        sliderTime.setDisable(true);
        sliderTime1.setDisable(true);
    }

    private void restoreOptionPlayerTwo() {
        btnOptionOnePlayer2.setVisible(true);
        btnOptionTwoPlayer2.setVisible(true);
        btnOptionThreePlayer2.setVisible(true);
        btnOptionFourPlayer2.setVisible(true);
    }
}
