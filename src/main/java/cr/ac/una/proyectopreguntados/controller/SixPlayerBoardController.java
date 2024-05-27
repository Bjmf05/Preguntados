package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.CompetidorDto;
import cr.ac.una.proyectopreguntados.model.JugadorDto;
import cr.ac.una.proyectopreguntados.model.PartidaDto;
import cr.ac.una.proyectopreguntados.model.PlayerPosition;
import cr.ac.una.proyectopreguntados.util.AppContext;
import cr.ac.una.proyectopreguntados.util.FlowController;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

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
    private ObservableList<JugadorDto> competitorsNames = FXCollections.observableArrayList();
    private boolean isFirstGame = true;
    private CompetidorDto player1;
    private CompetidorDto player2;
    private CompetidorDto player3;
    private CompetidorDto player4;
    private CompetidorDto player5;
    private CompetidorDto player6;
    private int topSection;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        game = (PartidaDto) AppContext.getInstance().get("Partida");
        competitors = (ObservableList<CompetidorDto>) AppContext.getInstance().get("Competidores");
        competitorsNames = (ObservableList<JugadorDto>) AppContext.getInstance().get("Jugadores");
        darkenIcons();
        fillPlayersDto();
        fillPlayersLabels();
        checkGame();
        currentCompetitor = competitors.getFirst();
        System.out.println(currentCompetitor.getJugador().getNombre());
    }

    /////Nuevas funciones
    private void checkGame() {
        ObservableList<CompetidorDto> competitorsFiltered = competitors.filtered(competitor -> competitor.getTurno().equals("A"));
        if (competitorsFiltered.size() == 1) {
            isFirstGame = false;
        }
    }

    private CompetidorDto competitors(int i) {
        CompetidorDto[] player = {player1, player2, player3, player4, player5, player6};
        return player[i];
    }

    private Label labelsNamePlayers(int i) {
        Label[] labels = {lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4, lblPlayer5, lblPlayer6};
        return labels[i];
    }

    private void fillPlayersLabels() {
        for (int i = 0; i < numberOfPlayers; i++) {
            JugadorDto jugadorDto = competitorsNames.get(i);
            labelsNamePlayers(i).setText(jugadorDto.getNombre());
        }
    }

    private void changePlayerTurn() {

        currentCompetitor.setTurno("I");
        savePlayerDto();
        if (currentPlayer == numberOfPlayers) {
            currentPlayer = 1;
        } else {
            currentPlayer++;
        }
        currentCompetitor = competitors(currentPlayer - 1);
        currentCompetitor.setTurno("A");
    }
    private void savePlayerDto(){
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
        Random random = new Random();
        int randomAngle = random.nextInt(1081) + 1080;
    test(randomAngle);

       int topSectio = (int) ((imgWheel.getRotate() + 360 / 14) % 360) / (360 / 7) + 1;
    System.out.println("Fuera de test 2 = "+topSectio);


    }


private void test(int randomAngle){
    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), imgWheel);

    rotateTransition.setByAngle(randomAngle);
    rotateTransition.setCycleCount(1);

    // Usar una expresión lambda para manejar el evento de finalización de la transición
    rotateTransition.setOnFinished(e -> {
        double currentRotation = imgWheel.getRotate();
        topSection = (int) ((currentRotation + 360 / 14) % 360) / (360 / 7) + 1;
        System.out.println("Dentro de test = "+topSection);
rouletteNumber(topSection);
    });

    // Inicia la rotación
    rotateTransition.play();
}
    @Override
    public void initialize() {
    }

    private void rouletteNumber(int number) {
        if (isFirstGame) {
            if (number == 4) {
                currentCompetitor = competitors(currentPlayer - 1);
                System.out.println("Jugador Actual= "+currentPlayer);
                System.out.println("Cometidor nombre "+competitors(currentPlayer - 1).getJugador().getNombre()+" Numero= " +competitors(currentPlayer - 1).getNumeroJugador());
                System.out.println("Jugador actual: " + currentCompetitor.getJugador().getNombre());
                currentCompetitor.setTurno("A");
                isFirstGame = false;
            } else {
                //Crear Funcion para pasar al siguiente jugador
                currentPlayer++;
                currentCompetitor = competitors(currentPlayer - 1);
            }
        } else if (number == 4) {
            System.out.println("Ya hay jugador actual");
            //Llama a corona 
            System.out.println(crownAction());
        } else {
            System.out.println(typeOfQuestion(number));
        }
        //Agregar accion de optener respuesta true o False
       // checkAnswer(true);
    }


    private void checkAnswer(boolean answer) {
        int position = currentCompetitor.getPosicionFicha().intValue();
        if (answer && position != 4) {
            position++;
            positionPlayer(position);
        } //pasa al siguiente jugador si la fallo, crear funcion para nuevo jugador 
        else if (!answer) {

            changePlayerTurn();
        }
        if (position == 4) {
            //Accion para cuando haya corona por responder cuatro preguntas bien
            String corona = crownAction();
        }
    }

    private void checkAnswerCrown(boolean answer, String type) {
        if (answer) {
//hacer un set al valor del tipo en la clase del jugador y luego llama para actualizar los personages
            setPlayerCharacters(type);
        }
        //Mover la posicion del jugador a 1

        //pasa al siguiente jugador si la fallo, crear funcion para nuevo jugador 
        if (!answer) {
            currentCompetitor.setPosicionFicha(1L);
            changePlayerTurn();
        }
    }

    private void setPlayerCharacters(String type) {
        //PlayerCategories se llena con un get y los valores de cada categoria en la calse player
        switch (type) {
            case "history":
                currentCompetitor.setHistoria("A");
                break;
            case "science":
                currentCompetitor.setCiencias("A");
                break;
            case "geography":
                currentCompetitor.setGeografia("A");
                break;
            case "entertainment":
                currentCompetitor.setEntretenimiento("A");
                break;
            case "art":
                currentCompetitor.setArte("A");
                break;
            case "sport":
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

    private void fillPlayersDto() {
        player1 = competitors(0);
        player2 = competitors(1);
        player3 = competitors(2);
        player4 = competitors(3);
        player5 = competitors(4);
        player6 = competitors(5);
    }

    private String typeOfQuestion(int number) {
        switch (number) {
            case 1:
                return "history";
            case 2:
                return "science";
            case 3:
                return "geography";
            case 5:
                return "entertainment";
            case 6:
                return "art";
            case 7:
                return "sport";
        }
        return "";
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

    private void positionPlayer(int position) {
        switch (currentPlayer) {
            case 1:
                playerOnePosition(position);
                break;
            case 2:
                playerTwoPosition(position);
                break;
            case 3:
                playerThreePosition(position);
                break;
            case 4:
                playerFourPosition(position);
                break;
            case 5:
                playerFivePosition(position);
                break;
            case 6:
                playerSixPosition(position);
                break;
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

    private void playerOnePosition(int position) {
        movePlayer(position, playerPosition.playerOnePositions6, imgAvatarPlayer1);
    }

    private void playerTwoPosition(int position) {
        movePlayer(position, playerPosition.playerTwoPositions6, imgAvatarPlayer1);
    }

    private void playerThreePosition(int position) {
        movePlayer(position, playerPosition.playerThreePositions6, imgAvatarPlayer1);
    }

    private void playerFourPosition(int position) {
        movePlayer(position, playerPosition.playerFourPositions6, imgAvatarPlayer1);
    }

    private void playerFivePosition(int position) {
        movePlayer(position, playerPosition.playerFivePositions6, imgAvatarPlayer1);
    }

    private void playerSixPosition(int position) {
        movePlayer(position, playerPosition.playerSixPositions6, imgAvatarPlayer1);
    }

    private void darkenIcons() {
        imgTinaPlayer1.setOpacity(0.5);
        imgBonzoPlayer1.setOpacity(0.5);
        imgAlbertPlayer1.setOpacity(0.5);
        imgPopPlayer1.setOpacity(0.5);
        imgTitoPlayer1.setOpacity(0.5);
        imgHectorPlayer1.setOpacity(0.5);
        imgTinaPlayer3.setOpacity(0.5);
        imgBonzoPlayer3.setOpacity(0.5);
        imgHectorPlayer3.setOpacity(0.5);
        imgTitoPlayer3.setOpacity(0.5);
        imgAlbertPlayer3.setOpacity(0.5);
        imgPopPlayer3.setOpacity(0.5);
        imgTinaPlayer5.setOpacity(0.5);
        imgBonzoPlayer5.setOpacity(0.5);
        imgHectorPlayer5.setOpacity(0.5);
        imgTitoPlayer5.setOpacity(0.5);
        imgAlbertPlayer5.setOpacity(0.5);
        imgPopPlayer5.setOpacity(0.5);
        imgTinaPlayer6.setOpacity(0.5);
        imgBonzoPlayer6.setOpacity(0.5);
        imgHectorPlayer6.setOpacity(0.5);
        imgTitoPlayer6.setOpacity(0.5);
        imgAlbertPlayer6.setOpacity(0.5);
        imgPopPlayer6.setOpacity(0.5);
        imgTinaPlayer4.setOpacity(0.5);
        imgBonzoPlayer4.setOpacity(0.5);
        imgHectorPlayer4.setOpacity(0.5);
        imgTitoPlayer4.setOpacity(0.5);
        imgAlbertPlayer4.setOpacity(0.5);
        imgPopPlayer4.setOpacity(0.5);
        imgTinaPlayer2.setOpacity(0.5);
        imgBonzoPlayer2.setOpacity(0.5);
        imgHectorPlayer2.setOpacity(0.5);
        imgTitoPlayer2.setOpacity(0.5);
        imgAlbertPlayer2.setOpacity(0.5);
        imgPopPlayer2.setOpacity(0.5);
    }
}
