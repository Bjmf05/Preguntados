package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.PlayerPosition;
import cr.ac.una.proyectopreguntados.util.FlowController;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
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
    private final int numberOfPlayers = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        darkenIcons();
    }

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
            System.out.println(topSection);
            checkAnswer(true);
            // Llama a rouletteNumber() después de que la rotación haya sido completada
            Platform.runLater(() -> rouletteNumber(topSection));
        }
    }

    @Override
    public void initialize() {
    }

    private void rouletteNumber(int number) {
        if (number == 4) {
            //Llama a corona 
            System.out.println(crownAction());
        } else {
            System.out.println(typeOfQuestion(number));
        }
        //Agregar accion de optener respuesta true o False
        checkAnswer(true);
    }

    private void checkAnswer(boolean answer) {
        int position = 1;
        if (answer && position != 4) {
            position++;
            positionPlayer(position);
        } //pasa al siguiente jugador si la fallo, crear funcion para nuevo jugador 
        else if (!answer) {

            currentPlayer++;
        }
        if (position == 4) {
            //Accion para cuando haya corona por responder cuatro preguntas bien
            String corona = crownAction();
        }
    }

    private void checkAnswerCrown(boolean answer, String type) {
        if (answer) {
//hacer un set al valor del tipo en la clase del jugador y luego llama para actualizar los personages
            setPlayerCharacters();
        }
        //Mover la posicion del jugador a 1

        //pasa al siguiente jugador si la fallo, crear funcion para nuevo jugador 
        if (!answer) {
            currentPlayer++;
        }
    }

    private void setPlayerCharacters() {
        //PlayerCategories se llena con un get y los valores de cada categoria en la calse player
        String[] playerCategories = {"T", "F", "T", "F", "T", "F"};
        ImageView[] playerImages = getPlayerImages();
        for (int i = 0; i < playerCategories.length; i++) {
            if (playerCategories[i].equals("T")) {
                getCharacter(playerImages[i]);
            }
        }
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

    private String crownAction() {
        String playerSelection;
        FlowController.getInstance().goViewInWindowModal("SelectCrownCategoryView", getStage(), true);
        SelectCrownCategoryController selectCrownCategory = (SelectCrownCategoryController) FlowController.getInstance().getController("SelectCrownCategoryView");
        playerSelection = selectCrownCategory.getPlayerSelection();
        FlowController.getInstance().delete("SelectCrownCategoryView");
        return playerSelection;
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
