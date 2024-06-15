package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.model.*;
import cr.ac.una.proyectopreguntados.util.AppContext;
import cr.ac.una.proyectopreguntados.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author PC
 */
public class GameBoardController extends Controller implements Initializable {

    @FXML
    private Label lblPlayer1;
    @FXML
    private Label lblPlayer2;
    @FXML
    private Label lblPlayer3;
    @FXML
    private Label lblPlayer4;
    @FXML
    private Label lblPlayer5;
    @FXML
    private Label lblPlayer6;
    @FXML
    private ImageView imgTinaPlayer1;
    @FXML
    private ImageView imgBonzoPlayer1;
    @FXML
    private ImageView imgAlbertPlayer1;
    @FXML
    private ImageView imgPopPlayer1;
    @FXML
    private ImageView imgTitoPlayer1;
    @FXML
    private ImageView imgHectorPlayer1;
    @FXML
    private ImageView imgTinaPlayer3;
    @FXML
    private ImageView imgBonzoPlayer3;
    @FXML
    private ImageView imgHectorPlayer3;
    @FXML
    private ImageView imgTitoPlayer3;
    @FXML
    private ImageView imgTinaPlayer5;
    @FXML
    private ImageView imgBonzoPlayer5;
    @FXML
    private ImageView imgHectorPlayer5;
    @FXML
    private ImageView imgTitoPlayer5;
    @FXML
    private ImageView imgTinaPlayer6;
    @FXML
    private ImageView imgBonzoPlayer6;
    @FXML
    private ImageView imgHectorPlayer6;
    @FXML
    private ImageView imgTitoPlayer6;
    @FXML
    private ImageView imgTinaPlayer4;
    @FXML
    private ImageView imgBonzoPlayer4;
    @FXML
    private ImageView imgHectorPlayer4;
    @FXML
    private ImageView imgTitoPlayer4;
    @FXML
    private ImageView imgTinaPlayer2;
    @FXML
    private ImageView imgBonzoPlayer2;
    @FXML
    private ImageView imgHectorPlayer2;
    @FXML
    private ImageView imgTitoPlayer2;
    @FXML
    private ImageView imgWheel;
    @FXML
    private ImageView imgAlbertPlayer3;
    @FXML
    private ImageView imgPopPlayer3;
    @FXML
    private ImageView imgAlbertPlayer5;
    @FXML
    private ImageView imgPopPlayer5;
    @FXML
    private ImageView imgAlbertPlayer6;
    @FXML
    private ImageView imgPopPlayer6;
    @FXML
    private ImageView imgAlbertPlayer4;
    @FXML
    private ImageView imgPopPlayer4;
    @FXML
    private ImageView imgAlbertPlayer2;
    @FXML
    private ImageView imgPopPlayer2;
    @FXML
    private ImageView imgAvatarPlayer1;
    PlayerPosition playerPosition = new PlayerPosition();
    @FXML
    private ImageView imgAvatarPlayer2;
    @FXML
    private ImageView imgAvatarPlayer3;
    @FXML
    private ImageView imgAvatarPlayer5;
    @FXML
    private ImageView imgAvatarPlayer6;
    @FXML
    private ImageView imgAvatarPlayer4;
    private int currentPlayer = 1;
    private int numberOfPlayers = 3;
    private CompetidorDto currentCompetitor;
    private PartidaDto game = new PartidaDto();
    private ObservableList<CompetidorDto> competitors = FXCollections.observableArrayList();
    private boolean isFirstGame = true;
    private CompetidorDto player1;
    private CompetidorDto player2;
    private CompetidorDto player3;
    private CompetidorDto player4;
    private CompetidorDto player5;
    private CompetidorDto player6;
    private PrincipalController principalController = (PrincipalController) FlowController.getInstance().getController("PrincipalView");
    private Timeline timeline;
    private LocalTime tiempoInicial;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private int round = 1;
    @FXML
    private Label lblCurrentRound;
    @FXML
    private Label lblCurrentPlayer;
    @FXML
    private Label lblTime;
    @FXML
    private ImageView btnSpinWheel;
    @FXML
    private MFXButton btnTurnAgain;
    private boolean turnAgain = false;
    @FXML
    private MFXButton btnYieldTurn;
    int amountAvatars = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        game = (PartidaDto) AppContext.getInstance().get("Partida");
        numberOfPlayers = game.getJugadores().intValue();
        darkenIcons();
        loadGameData(game);
        fillPlayersDto();
        fillPlayersAvatar();
        fillPlayersLabels();
        checkGame();

    }

    private void checkGame() {
        ObservableList<CompetidorDto> competitorsFiltered = competitors.filtered(competitor -> competitor.getTurno().equals("A"));
        if (competitorsFiltered.size() == 1) {
            isFirstGame = false;
            currentCompetitor = competitorsFiltered.getFirst();
            currentPlayer = currentCompetitor.getNumeroJugador().intValue();
            changeLabelPlayer();
        } else {
            currentCompetitor = competitors.getFirst();
            changeLabelPlayer();
        }
    }

    private void changePlayerTurn() {
        currentCompetitor.setTurno("O");
        savePlayerDto();
        if (currentPlayer == numberOfPlayers) {
            currentPlayer = 1;
            increaseRound();
        } else {
            currentPlayer++;

        }
        currentCompetitor = competitorsPlayer(currentPlayer - 1);
        currentCompetitor.setTurno("A");
        changeLabelPlayer();
    }

    private void savePlayerDto() {
        switch (currentPlayer) {
            case 1:
                player1 = currentCompetitor;
                break;
            case 2:
                player2 = currentCompetitor;
                break;
            case 3:
                player3 = currentCompetitor;
                break;
            case 4:
                player4 = currentCompetitor;
                break;
            case 5:
                player5 = currentCompetitor;
                break;
            case 6:
                player6 = currentCompetitor;
                break;
        }
    }

    @FXML
    private void onMouseClickedSpinWheel(MouseEvent event) {
        btnSpinWheel.setDisable(true);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), imgWheel);
        rotateTransition.setByAngle(new Random().nextInt(1081) + 1080);
        rotateTransition.setCycleCount(1);
        rotateTransition.setOnFinished(evento -> Platform.runLater(() -> rouletteNumber((int) ((imgWheel.getRotate() + 360 / 14) % 360) / (360 / 7) + 1)));
        rotateTransition.play();
    }

    @Override
    public void initialize() {
    }

    private void rouletteNumber(int number) {
        btnYieldTurn.setDisable(true);
        blockbtnTurnAgain();
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(p -> Platform.runLater(() -> {
            if (turnAgain) {
                turnAgain = false;
                onMouseClickedSpinWheel(null);
            } else {
                FlowController.getInstance().delete("CardView");
                CardController cardController = (CardController) FlowController.getInstance().getController("CardView");
                if (isFirstGame) {
                    if (number == 4) {
                        currentCompetitor = competitorsPlayer(currentPlayer - 1);
                        currentCompetitor.setTurno("A");
                        changeLabelPlayer();
                        isFirstGame = false;
                    } else {

                        if (currentPlayer == numberOfPlayers) {
                            currentPlayer = 1;
                        } else {
                            currentPlayer++;
                        }
                        currentCompetitor = competitorsPlayer(currentPlayer - 1);
                        changeLabelPlayer();
                    }
                    btnSpinWheel.setDisable(false);
                } else if (number == 4) {
                    checkAnswerCrown(crownAction(), cardController);
                } else {
                    cardController.setTypeOfCard(typeOfQuestion(number));
                    FlowController.getInstance().goViewInWindowModalOfCard("CardView", getStage(), true, principalController.getWidth(), principalController.getHeight());
                    checkAnswer(cardController.isAnswer(), cardController);
                }
            }
        }));
        pause.play();
    }

    private void blockbtnTurnAgain() {
        if (!isFirstGame && currentCompetitor.getComodinTiro() > 0) {
            btnTurnAgain.setDisable(false);
        } else {
            btnTurnAgain.setDisable(true);
        }
    }

    private void checkAnswer(boolean answer, CardController cardController) {
        int position = currentCompetitor.getPosicionFicha().intValue();
        if (answer && position != 4) {
            position++;
            currentCompetitor.setPosicionFicha((long) position);
            positionPlayer(position);
            savePlayerDto();
        } //pasa al siguiente jugador si la fallo, crear funcion para nuevo jugador
        else if (!answer) {
            changePlayerTurn();
        }
        if (position == 4) {
            //Accion para cuando haya corona por responder cuatro preguntas bien
            checkAnswerCrown(crownAction(), cardController);
        }
        btnSpinWheel.setDisable(false);
        if (game.getDificultad().equals("Facil")) {
            btnYieldTurn.setDisable(false);
        }
    }

    private void checkAnswerCrown(String type, CardController cardController) {
        boolean answer = false;
        if (!type.equals("Duelo")) {
            cardController.setTypeOfCard(type);
            FlowController.getInstance().goViewInWindowModalOfCard("CardView", getStage(), true, principalController.getWidth(), principalController.getHeight());
            answer = cardController.isAnswer();
        } else {
            DuelController duelController = (DuelController) FlowController.getInstance().getController("DuelView");
            answer = duelController.isWinner();
        }

        if (answer) {
            setPlayerCharacters(type);
            setPlayerWildCard();
        }

        currentCompetitor.setPosicionFicha(1L);
        positionPlayer(1);
        savePlayerDto();

        if (!answer || (amountAvatars == 3 && round == 1)) {
            changePlayerTurn();
        }
        btnSpinWheel.setDisable(false);
        if (game.getDificultad().equals("Facil")) {
            btnYieldTurn.setDisable(false);
        }
    }

    private void setPlayerWildCard() {
        if (game.getDificultad().equals("Intermedio") && currentCompetitor.getAyudasOptenidas() < 4) {

            List<Runnable> actions = new ArrayList<>(Arrays.asList(
                    () -> currentCompetitor.setComodinBomba(1L),
                    () -> currentCompetitor.setComodinDoble(1L),
                    () -> currentCompetitor.setComodinPasar(1L),
                    () -> currentCompetitor.setComodinTiro(1L)
            ));
            if (currentCompetitor.getComodinBomba() == 1L) actions.remove(0);
            if (currentCompetitor.getComodinDoble() == 1L) actions.remove(1);
            if (currentCompetitor.getComodinPasar() == 1L) actions.remove(2);
            if (currentCompetitor.getComodinTiro() == 1L) actions.remove(3);
            Random random = new Random();
            actions.get(random.nextInt(actions.size())).run();
            currentCompetitor.setAyudasOptenidas(currentCompetitor.getAyudasOptenidas() + 1);
        }
    }

    private void setPlayerCharacters(String type) {
        //PlayerCategories se llena con un get y los valores de cada categoria en la calse player
        switch (type) {
            case "Historia":
                currentCompetitor.setHistoria("A");
                break;
            case "Ciencia":
                currentCompetitor.setCiencias("A");
                break;
            case "Geografía":
                currentCompetitor.setGeografia("A");
                break;
            case "Entretenimiento":
                currentCompetitor.setEntretenimiento("A");
                break;
            case "Arte":
                currentCompetitor.setArte("A");
                break;
            case "Deporte":
                currentCompetitor.setDeporte("A");
                break;
        }

        String[] playerCategories = {currentCompetitor.getDeporte(), currentCompetitor.getEntretenimiento(), currentCompetitor.getCiencias(), currentCompetitor.getHistoria(), currentCompetitor.getGeografia(), currentCompetitor.getArte()};
        ImageView[] playerImages = getPlayerImages();
        amountAvatars = 0;
        for (int i = 0; i < playerCategories.length; i++) {
            if (playerCategories[i].equals("A")) {
                getCharacter(playerImages[i]);
                amountAvatars++;
            }
        }
        if (amountAvatars == 6) {
            finishGame("corona");
        }
    }

    private String crownAction() {
        String playerSelection;
        FlowController.getInstance().goViewInWindowModal("SelectCrownCategoryView", getStage(), true);
        SelectCrownCategoryController selectCrownCategory = (SelectCrownCategoryController) FlowController.getInstance().getController("SelectCrownCategoryView");
        playerSelection = selectCrownCategory.getPlayerSelection();
        FlowController.getInstance().delete("SelectCrownCategoryView");
        return playerSelection;
    }

    private CompetidorDto competitorsPlayer(int i) {
        CompetidorDto[] player = {player1, player2, player3, player4, player5, player6};
        return player[i];
    }

    private void loadGameData(PartidaDto Game) {
        if (!Game.getDificultad().equals("Facil")) {
            btnYieldTurn.setDisable(true);
        }
        Map<Integer, CompetidorDto> competitorMap = new HashMap<>();
        for (CompetidorDto c : Game.getCompetidorList()) {
            int index = c.getNumeroJugador().intValue() - 1;
            competitorMap.put(index, c);
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            if (competitorMap.containsKey(i)) {
                competitors.add(competitorMap.get(i));
            }
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            CompetidorDto competidorDto = competitors.get(i);
            currentPlayer = i + 1;
            String[] playerCategories = {competidorDto.getDeporte(), competidorDto.getEntretenimiento(), competidorDto.getCiencias(), competidorDto.getHistoria(), competidorDto.getGeografia(), competidorDto.getArte()};
            ImageView[] playerImages = getPlayerImages();
            for (int j = 0; j < playerCategories.length; j++) {
                if (playerCategories[j].equals("A")) {
                    getCharacter(playerImages[j]);
                }
            }
            ImageView[] avatars = {imgAvatarPlayer1, imgAvatarPlayer2, imgAvatarPlayer3, imgAvatarPlayer4, imgAvatarPlayer5, imgAvatarPlayer6};
            int[][][] playerPositions = {playerPosition.playerOnePositions(numberOfPlayers), playerPosition.playerTwoPositions(numberOfPlayers), playerPosition.playerThreePositions(numberOfPlayers), playerPosition.playerFourPositions(numberOfPlayers), playerPosition.playerFivePositions(numberOfPlayers), playerPosition.playerSixPositions6};
            if (i + 1 >= 1 && i + 1 <= avatars.length) {
                movePlayer(competidorDto.getPosicionFicha().intValue(), playerPositions[i], avatars[i]);
            }
        }
        currentPlayer = 1;
        if (game.getTiempoLimite() != null) {
            tiempoInicial = LocalTime.parse(game.getTiempoLimite(), formatter);
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarCronometro()));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    private void actualizarCronometro() {
        tiempoInicial = tiempoInicial.minusSeconds(1);
        String tiempo = tiempoInicial.format(formatter);
        lblTime.setText(tiempo);

        if (tiempoInicial.getHour() == 0 && tiempoInicial.getMinute() == 0 && tiempoInicial.getSecond() == 0) {
            timeline.stop();
            Platform.runLater(() -> finishGame("tiempo"));
        }
    }

    private void fillPlayersDto() {
        for (int i = 0; i < numberOfPlayers; i++) {
            CompetidorDto competidorDto = competitors.get(i);
            switch (i) {
                case 0:
                    player1 = competidorDto;
                    break;
                case 1:
                    player2 = competidorDto;
                    break;
                case 2:
                    player3 = competidorDto;
                    break;
                case 3:
                    player4 = competidorDto;
                    break;
                case 4:
                    player5 = competidorDto;
                    break;
                case 5:
                    player6 = competidorDto;
                    break;
            }

        }
    }

    private Label labelsNamePlayers(int i) {
        Label[] labels = {lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4, lblPlayer5, lblPlayer6};
        return labels[i];
    }

    private void fillPlayersLabels() {
        for (int i = 0; i < numberOfPlayers; i++) {
            labelsNamePlayers(i).setText(competitorsPlayer(i).getJugador().getNombre());
        }
    }

    private void fillPlayersAvatar() {
        for (int i = 0; i < numberOfPlayers; i++) {
            ImageView[] playersImages = getPlayersImagesAvatar();
            InputStream inputStream = App.class.getResourceAsStream(competitorsPlayer(i).getFicha());
            playersImages[i].setImage(new Image(inputStream));
        }
    }

    private String typeOfQuestion(int number) {
        String[] types = {"", "Historia", "Ciencia", "Geografía", "", "Entretenimiento", "Arte", "Deporte"};
        return number < types.length ? types[number] : "Deporte";
    }

    public void getCharacter(ImageView image) {
        image.setOpacity(1);
    }

    public ImageView[] getPlayerImages() {
        switch (currentPlayer) {
            case 1:
                return new ImageView[]{imgBonzoPlayer1, imgPopPlayer1, imgAlbertPlayer1, imgHectorPlayer1, imgTitoPlayer1, imgTinaPlayer1};
            case 2:
                return new ImageView[]{imgBonzoPlayer2, imgPopPlayer2, imgAlbertPlayer2, imgHectorPlayer2, imgTitoPlayer2, imgTinaPlayer2};
            case 3:
                return new ImageView[]{imgBonzoPlayer3, imgPopPlayer3, imgAlbertPlayer3, imgHectorPlayer3, imgTitoPlayer3, imgTinaPlayer3};
            case 4:
                return new ImageView[]{imgBonzoPlayer4, imgPopPlayer4, imgAlbertPlayer4, imgHectorPlayer4, imgTitoPlayer4, imgTinaPlayer4};
            case 5:
                return new ImageView[]{imgBonzoPlayer5, imgPopPlayer5, imgAlbertPlayer5, imgHectorPlayer5, imgTitoPlayer5, imgTinaPlayer5};
            case 6:
                return new ImageView[]{imgBonzoPlayer6, imgPopPlayer6, imgAlbertPlayer6, imgHectorPlayer6, imgTitoPlayer6, imgTinaPlayer6};

            default:
                throw new IllegalArgumentException("Invalid player number: " + currentPlayer);
        }
    }

    private ImageView[] getPlayersImagesAvatar() {
        return new ImageView[]{imgAvatarPlayer1, imgAvatarPlayer2, imgAvatarPlayer3, imgAvatarPlayer4, imgAvatarPlayer5, imgAvatarPlayer6};
    }

    private void positionPlayer(int position) {
        ImageView[] avatars = {imgAvatarPlayer1, imgAvatarPlayer2, imgAvatarPlayer3, imgAvatarPlayer4, imgAvatarPlayer5, imgAvatarPlayer6};
        int[][][] playerPositions = {playerPosition.playerOnePositions(numberOfPlayers), playerPosition.playerTwoPositions(numberOfPlayers), playerPosition.playerThreePositions(numberOfPlayers), playerPosition.playerFourPositions(numberOfPlayers), playerPosition.playerFivePositions(numberOfPlayers), playerPosition.playerSixPositions6};
        movePlayer(position, playerPositions[currentPlayer - 1], avatars[currentPlayer - 1]);
    }

    private void movePlayer(int position, int[][] playerPositions, ImageView avatarImage) {
        if (position >= 1 && position <= playerPositions.length) {
            int[] newPos = playerPositions[position - 1];
            moveAvatar(newPos[0], newPos[1], avatarImage);
        }
    }

    private void moveAvatar(int x, int y, ImageView avatar) {
        avatar.setLayoutX(x);
        avatar.setLayoutY(y);
    }

    private void darkenIcons() {
        List<List<ImageView>> icons = Arrays.asList(
                Arrays.asList(imgTinaPlayer1, imgBonzoPlayer1, imgAlbertPlayer1, imgPopPlayer1, imgTitoPlayer1, imgHectorPlayer1),
                Arrays.asList(imgTinaPlayer2, imgBonzoPlayer2, imgAlbertPlayer2, imgPopPlayer2, imgTitoPlayer2, imgHectorPlayer2),
                Arrays.asList(imgTinaPlayer3, imgBonzoPlayer3, imgAlbertPlayer3, imgPopPlayer3, imgTitoPlayer3, imgHectorPlayer3),
                Arrays.asList(imgTinaPlayer4, imgBonzoPlayer4, imgAlbertPlayer4, imgPopPlayer4, imgTitoPlayer4, imgHectorPlayer4),
                Arrays.asList(imgTinaPlayer5, imgBonzoPlayer5, imgAlbertPlayer5, imgPopPlayer5, imgTitoPlayer5, imgHectorPlayer5),
                Arrays.asList(imgTinaPlayer6, imgBonzoPlayer6, imgAlbertPlayer6, imgPopPlayer6, imgTitoPlayer6, imgHectorPlayer6)
        );

        for (int i = 0; i < numberOfPlayers; i++) {
            for (ImageView icon : icons.get(i)) {
                icon.setOpacity(0.5);
            }
        }
    }

    public PartidaDto getGame() {
        return game;
    }

    public void setGame(PartidaDto game) {
        this.game = game;
    }

    public CompetidorDto getCurrentCompetitor() {
        return currentCompetitor;
    }

    public void setCurrentCompetitor(CompetidorDto currentCompetitor) {
        this.currentCompetitor = currentCompetitor;
    }

    private void increaseRound() {
        round++;
        if (round == 26) {
            finishGame("rondas");
        } else {
            lblCurrentRound.setText(String.valueOf(round));
        }
    }

    public void finishGame(String typeFinish) {
        String winnerBy = "Fin del juego por " + typeFinish;
        String winner = "";
        savePlayerDto();
        if (typeFinish.equals("corona")) {
            winnerBy = "Ganador por corona";
            currentCompetitor.getJugador().setPartidasGanadas(currentCompetitor.getJugador().getPartidasGanadas() + 1);
            winner = "Ganador " + currentCompetitor.getJugador().getNombre();
        } else {
            winnerBy = "Fin del juego por " + typeFinish;
            winner = "Ganadores ";
            int maxAvatars = 0;
            List<CompetidorDto> maxAvatarCompetitors = new ArrayList<>();

            for (int i = 0; i < numberOfPlayers; i++) {
                CompetidorDto competidorDto = competitorsPlayer(i);
                int amountAvatars = (int) Arrays.stream(new String[]{competidorDto.getDeporte(), competidorDto.getEntretenimiento(), competidorDto.getCiencias(), competidorDto.getHistoria(),
                        competidorDto.getGeografia(), competidorDto.getArte()}).filter(category -> category.equals("A")).count();
                if (amountAvatars > maxAvatars) {
                    maxAvatars = amountAvatars;
                    maxAvatarCompetitors.clear();
                    maxAvatarCompetitors.add(competidorDto);
                } else if (amountAvatars == maxAvatars) {
                    maxAvatarCompetitors.add(competidorDto);
                }
            }

            for (CompetidorDto competitor : maxAvatarCompetitors) {
                currentCompetitor = competitor;
                currentCompetitor.getJugador().setPartidasGanadas(currentCompetitor.getJugador().getPartidasGanadas() + 1);
                currentPlayer = currentCompetitor.getNumeroJugador().intValue();
                savePlayerDto();
                winner += currentCompetitor.getJugador().getNombre() + " ";
            }
        }
        EndGameController endGameController = (EndGameController) FlowController.getInstance().getController("EndGameView");
        endGameController.getLblFinishBy().setText(winnerBy);
        endGameController.getLblWinner().setText(winner);
        FlowController.getInstance().goViewInWindowModal("EndGameView", getStage(), true);
        game.setEstado("F");
        safeGame();
        PrincipalController principalController = (PrincipalController) FlowController.getInstance().getController("PrincipalView");
        principalController.exit();
    }

    public void safeGame() {
        game.setRonda(round);
        if (game.getTiempoLimite() != null) {
            game.setTiempoLimite(lblTime.getText());
            timeline.stop();
        }
        AppContext.getInstance().set("PartidaSave", game);
        savePlayerDto();
        competitors.clear();
        for (int i = 0; i < numberOfPlayers; i++) {
            CompetidorDto competidorDto = competitorsPlayer(i);
            competitors.add(competidorDto);
        }
        AppContext.getInstance().set("CompetidoresSave", competitors);
        SaveGame saveGame = new SaveGame();
        saveGame.saveGame();
    }

    private void changeLabelPlayer() {
        lblCurrentPlayer.setText(competitorsPlayer(currentPlayer - 1).getJugador().getNombre());
    }

    public void getPlayers() {
        ObservableList<CompetidorDto> competitorsPlayer = FXCollections.observableArrayList();
        for (int i = 0; i < numberOfPlayers; i++) {
            CompetidorDto competidorDto = competitorsPlayer(i);
            competitorsPlayer.add(competidorDto);
        }
        AppContext.getInstance().set("competitorsPlayer", competitorsPlayer);
    }

    public void setPlayers() {
        competitors.clear();
        ObservableList<CompetidorDto> competitorsPlayer = (ObservableList<CompetidorDto>) AppContext.getInstance().get("competitorsPlayer");
        for (int i = 0; i < numberOfPlayers; i++) {
            CompetidorDto competidorDto = competitorsPlayer.get(i);
            competitors.add(competidorDto);
        }
        fillPlayersDto();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @FXML
    private void onActionBtnTurnAgain(ActionEvent event) {
        turnAgain = true;
        btnTurnAgain.setDisable(true);
        currentCompetitor.setComodinTiro(currentCompetitor.getComodinTiro() - 1);
    }

    @FXML
    private void onActionBtnYieldTurn(ActionEvent event) {
        if (currentCompetitor.getComodinBomba() == 0 && currentCompetitor.getComodinDoble() == 0 && currentCompetitor.getComodinPasar() == 0 && currentCompetitor.getComodinTiro() == 0) {
            List<Runnable> actions = new ArrayList<>(Arrays.asList(
                    () -> currentCompetitor.setComodinBomba(1L),
                    () -> currentCompetitor.setComodinDoble(1L),
                    () -> currentCompetitor.setComodinPasar(1L),
                    () -> currentCompetitor.setComodinTiro(1L)
            ));

            for (int i = 0; i < 2; i++) {
                if (currentCompetitor.getComodinBomba() == 1L) actions.remove(0);
                if (currentCompetitor.getComodinDoble() == 1L) actions.remove(1);
                if (currentCompetitor.getComodinPasar() == 1L) actions.remove(2);
                if (currentCompetitor.getComodinTiro() == 1L) actions.remove(3);
                actions.get(new Random().nextInt(actions.size())).run();
            }
            changePlayerTurn();
        }
    }
}
