package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.model.*;
import cr.ac.una.proyectopreguntados.util.AppContext;
import cr.ac.una.proyectopreguntados.util.FlowController;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SixPlayerBoardController extends Controller implements Initializable {

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
    private Button btnSpinWheel;
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
    private final int numberOfPlayers = 6;
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



    private int round = 1;
    @FXML
    private Label lblCurrentRound;
    @FXML
    private Label lblCurrentPlayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        darkenIcons();

        game = (PartidaDto) AppContext.getInstance().get("Partida");
        loadGameData(game);
        fillPlayersDto();
        fillPlayersAvatar();
        fillPlayersLabels();
        checkGame();


    }

    /////Nuevas funciones
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

    /////////////
    @FXML
    private void onActionBtnSpinWheel(ActionEvent event) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), imgWheel);
        Random random = new Random();
        int randomAngle = random.nextInt(1081) + 1080;
        rotateTransition.setByAngle(randomAngle);
        rotateTransition.setCycleCount(1);

        // Crea una instancia de la clase interna que implementa EventHandler<ActionEvent>
        EventHandler<ActionEvent> eventHandler = new TransitionFinishedEventHandler();

        // Asigna la instancia como el oyente de la transición
        rotateTransition.setOnFinished(eventHandler);

        // Inicia la rotación
        rotateTransition.play();
    }

    class TransitionFinishedEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            double currentRotation = imgWheel.getRotate();
            int topSection = (int) ((currentRotation + 360 / 14) % 360) / (360 / 7) + 1;
            // Llama a rouletteNumber() después de que la rotación haya sido completada
            Platform.runLater(() -> {
                rouletteNumber(topSection);
            });
        }
    }

    @Override
    public void initialize() {
    }

    private void rouletteNumber(int number) {
        FlowController.getInstance().delete("CardView");
        CardController cardController = (CardController) FlowController.getInstance().getController("CardView");
        if (isFirstGame) {
            if (number == 4) {
                currentCompetitor = competitorsPlayer(currentPlayer - 1);
                currentCompetitor.setTurno("A");
                changeLabelPlayer();
                isFirstGame = false;
            } else {
                //Crear Funcion para pasar al siguiente jugador
                if (currentPlayer == numberOfPlayers) {
                    currentPlayer = 1;
                } else {
                    currentPlayer++;
                }
                currentCompetitor = competitorsPlayer(currentPlayer - 1);
                changeLabelPlayer();
            }
        } else if (number == 4) {
            //Llama a corona

            checkAnswerCrown(crownAction(), cardController);

        } else {
            cardController.setTypeOfCard(typeOfQuestion(number));
            FlowController.getInstance().goViewInWindowModalOfCard("CardView", getStage(), true);
            checkAnswer(cardController.isAnswer(), cardController);

        }

    }


    private void checkAnswer(boolean answer,CardController cardController) {
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
    }

    private void checkAnswerCrown( String type,CardController cardController) {
       // FlowController.getInstance().delete("CardView");
        cardController.setTypeOfCard(type);
        FlowController.getInstance().goViewInWindowModalOfCard("CardView", getStage(), true);
        boolean answer = cardController.isAnswer();
        if (answer) {
//hacer un set al valor del tipo en la clase del jugador y luego llama para actualizar los personages
            setPlayerCharacters(type);
            currentCompetitor.setPosicionFicha(1L);
            positionPlayer(1);
            savePlayerDto();
        }
        //Mover la posicion del jugador a 1

        //pasa al siguiente jugador si la fallo, crear funcion para nuevo jugador
        if (!answer) {
            currentCompetitor.setPosicionFicha(1L);
            positionPlayer(1);
            changePlayerTurn();
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
        for (int i = 0; i < playerCategories.length; i++) {
            if (playerCategories[i].equals("A")) {
                getCharacter(playerImages[i]);
            }
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
        Map<Integer, CompetidorDto> competitorMap = new HashMap<>();

        for (Competidor c : Game.getCompetidorList()) {
            CompetidorDto comp = new CompetidorDto(c);
            int index = comp.getNumeroJugador().intValue() - 1;
            competitorMap.put(index, comp);
        }

        for (int i = 0; i < Game.getJugadores().intValue(); i++) {
            if (competitorMap.containsKey(i)) {
                competitors.add(competitorMap.get(i));
            }
        }
        for(int i=0;i<game.getJugadores().intValue();i++){
            CompetidorDto competidorDto = competitors.get(i);
            currentPlayer = i+1;
            String[] playerCategories = {competidorDto.getDeporte(), competidorDto.getEntretenimiento(), competidorDto.getCiencias(), competidorDto.getHistoria(), competidorDto.getGeografia(), competidorDto.getArte()};
            ImageView[] playerImages = getPlayerImages();
            for (int j = 0; j < playerCategories.length; j++) {
                if (playerCategories[j].equals("A")) {
                    getCharacter(playerImages[j]);
                }
            }
            ImageView[] avatars = {imgAvatarPlayer1, imgAvatarPlayer2, imgAvatarPlayer3, imgAvatarPlayer4, imgAvatarPlayer5, imgAvatarPlayer6};
            int[][][] playerPositions = {playerPosition.playerOnePositions6, playerPosition.playerTwoPositions6, playerPosition.playerThreePositions6, playerPosition.playerFourPositions6, playerPosition.playerFivePositions6, playerPosition.playerSixPositions6};
            if (i+1 >= 1 && i+1 <= avatars.length) {
                movePlayer(competidorDto.getPosicionFicha().intValue(), playerPositions[i], avatars[i]);
            }
        }
        currentPlayer = 1;
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
        return switch (number) {
            case 1 -> "Historia";
            case 2 -> "Ciencia";
            case 3 -> "Geografía";
            case 5 -> "Entretenimiento";
            case 6 -> "Arte";
            case 7 -> "Deporte";
            default -> "";
        };
    }

    private void getCharacter(ImageView image) {
        image.setOpacity(1);
    }

    private ImageView[] getPlayerImages() {
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
        int[][][] playerPositions = {playerPosition.playerOnePositions6, playerPosition.playerTwoPositions6, playerPosition.playerThreePositions6, playerPosition.playerFourPositions6, playerPosition.playerFivePositions6, playerPosition.playerSixPositions6};

        if (currentPlayer >= 1 && currentPlayer <= avatars.length) {
            movePlayer(position, playerPositions[currentPlayer - 1], avatars[currentPlayer - 1]);
        }
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
        List<ImageView> icons = Arrays.asList(
                imgTinaPlayer1, imgBonzoPlayer1, imgAlbertPlayer1, imgPopPlayer1, imgTitoPlayer1, imgHectorPlayer1,
                imgTinaPlayer3, imgBonzoPlayer3, imgHectorPlayer3, imgTitoPlayer3, imgAlbertPlayer3, imgPopPlayer3,
                imgTinaPlayer5, imgBonzoPlayer5, imgHectorPlayer5, imgTitoPlayer5, imgAlbertPlayer5, imgPopPlayer5,
                imgTinaPlayer6, imgBonzoPlayer6, imgHectorPlayer6, imgTitoPlayer6, imgAlbertPlayer6, imgPopPlayer6,
                imgTinaPlayer4, imgBonzoPlayer4, imgHectorPlayer4, imgTitoPlayer4, imgAlbertPlayer4, imgPopPlayer4,
                imgTinaPlayer2, imgBonzoPlayer2, imgHectorPlayer2, imgTitoPlayer2, imgAlbertPlayer2, imgPopPlayer2
        );

        for (ImageView icon : icons) {
            icon.setOpacity(0.5);
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
            finishGame();
        } else {
            lblCurrentRound.setText(String.valueOf(round));
        }
    }

    private void finishGame() {

    }

    public void safeGame() {
        AppContext.getInstance().set("PartidaSave", game);
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
public void getPlayers(){
        ObservableList <CompetidorDto> competitorsPlayer = FXCollections.observableArrayList();
    for(int i=0;i<numberOfPlayers;i++){
        CompetidorDto competidorDto = competitorsPlayer(i);
        competitorsPlayer.add(competidorDto);
    }
    AppContext.getInstance().set("competitorsPlayer", competitorsPlayer);
}
public void setPlayers(){
        competitors.clear();
        ObservableList<CompetidorDto> competitorsPlayer = (ObservableList<CompetidorDto>) AppContext.getInstance().get("competitorsPlayer");
        for(int i=0;i<numberOfPlayers;i++){
            CompetidorDto competidorDto = competitorsPlayer.get(i);
            competitors.add(competidorDto);
        }
        fillPlayersDto();
}
}

