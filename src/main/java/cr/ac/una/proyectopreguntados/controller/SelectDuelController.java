package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.model.CompetidorDto;
import cr.ac.una.proyectopreguntados.util.AppContext;
import cr.ac.una.proyectopreguntados.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SelectDuelController extends Controller implements Initializable {

    @FXML
    private Label lblJugador1;
    @FXML
    private VBox vbxPlayer1;
    @FXML
    private ImageView imgSelectPlayer1;
    @FXML
    private Label lblJugador2;
    @FXML
    private VBox vbxPlayer2;
    @FXML
    private ImageView imgSelectPlayer2;
    @FXML
    private Label lblJugador3;
    @FXML
    private VBox vbxPlayer3;
    @FXML
    private ImageView imgSelectPlayer3;
    @FXML
    private Label lblJugador4;
    @FXML
    private VBox vbxPlayer4;
    @FXML
    private ImageView imgSelectPlayer4;
    @FXML
    private Label lblJugador5;
    @FXML
    private VBox vbxPlayer5;
    @FXML
    private ImageView imgSelectPlayer5;
    @FXML
    private VBox vbxPlayer6;
    @FXML
    private ImageView imgSelectPlayer6;
    @FXML
    private MFXButton btnDuel;
    private int numberOfPlayer = 0;
    private ObservableList<CompetidorDto> competitorsDuel;
    private CompetidorDto challenging;
    private CompetidorDto player1;
    private CompetidorDto player2;
    private CompetidorDto player3;
    private CompetidorDto player4;
    private CompetidorDto player5;
    private final String routeArte = "/cr/ac/una/proyectopreguntados/resources/Arte.png";
    private final String routeGeografia = "/cr/ac/una/proyectopreguntados/resources/Geograf_a.png";
    private final String routeHistoria = "/cr/ac/una/proyectopreguntados/resources/Historia.png";
    private final String routeCiencia = "/cr/ac/una/proyectopreguntados/resources/Ciencia.png";
    private final String routeDeporte = "/cr/ac/una/proyectopreguntados/resources/Bonzo.png";
    private final String routeEntretenimiento = "/cr/ac/una/proyectopreguntados/resources/Entretenimiento.png";
    private String routechallengin = "";
    private String challengedRoute = "";
    private int numberChallenged;
    private boolean isChallenginAvatarSelected = false;
    private boolean isSecondAvatarSelected = false;
    private int selectedAvatarCount = 0;
    private int selectedOtherPlayerIndex = -1;
    @FXML
    private Label lblJugador6;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     competitorsDuel = (ObservableList<CompetidorDto>) AppContext.getInstance().get("competitorsDuel");
        numberOfPlayer = competitorsDuel.size();
       fillPlayersDto();
      avatarsOptions();
    }

    @Override
    public void initialize() {
    }

    private void avatarsOptions() {
        for (int i = 0; i < numberOfPlayer; i++) {
            CompetidorDto player = competitorsPlayer(i);
            lblPlayer(i).setText(player.getJugador().getNombre());
            VBox container = containerAvatar(i);
           addAvatarOptions(player, container, i);
        }
            }
    private void addAvatarOptions(CompetidorDto player, VBox container, int playerIndex) {
        if (player.getArte().equals("A")) {
            addAvatarImage(container, routeArte, playerIndex);
        }
        if (player.getGeografia().equals("A")) {
            addAvatarImage(container, routeGeografia, playerIndex);
        }
        if (player.getHistoria().equals("A")) {
            addAvatarImage(container, routeHistoria, playerIndex);
        }
        if (player.getCiencias().equals("A")) {
            addAvatarImage(container, routeCiencia, playerIndex);
        }
        if (player.getDeporte().equals("A")) {
            addAvatarImage(container, routeDeporte, playerIndex);
        }
        if (player.getEntretenimiento().equals("A")) {
            addAvatarImage(container, routeEntretenimiento, playerIndex);
        }
    }
    private void addAvatarImage(VBox container, String route, int playerIndex) {
        InputStream inputStream = App.class.getResourceAsStream(route);
        Image imagen = new Image(inputStream);
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(75);
imageView.setFitHeight(75);

imageView.setOnMouseClicked(event -> {
handleAvatarSelection(playerIndex, route);
        });

          container.getChildren().add(imageView);
    }
    private void handleAvatarSelection(int playerIndex, String route) {
        if (selectedAvatarCount == 2) {
            // No se pueden seleccionar más de dos avatares
            return;
        }

        if (playerIndex == 0) { // Challengin's avatar selection
            if (!isChallenginAvatarSelected) {
                isChallenginAvatarSelected = true;
                routechallengin = route;
                imgSelect(playerIndex).setImage(new Image(App.class.getResourceAsStream(route)));
                selectedAvatarCount++;
            }
        } else { // Other players' avatar selection
            if (!isChallenginAvatarSelected) {
                // Challengin debe seleccionar su avatar primero
                return;
            }

            if (selectedOtherPlayerIndex == -1) {
                // Primera selección de otro jugador
                selectedOtherPlayerIndex = playerIndex;
                numberChallenged = playerIndex;
                challengedRoute = route;
                imgSelect(playerIndex).setImage(new Image(App.class.getResourceAsStream(route)));
                selectedAvatarCount++;
            } else if (selectedOtherPlayerIndex == playerIndex) {
                // Cambiar avatar del mismo jugador
                numberChallenged = playerIndex;
                challengedRoute = route;
                imgSelect(playerIndex).setImage(new Image(App.class.getResourceAsStream(route)));
            } else {
                // No se puede cambiar de jugador
                return;
            }
        }}

    private void fillChallengin() {
        SixPlayerBoardController sixPlayerBoardController = (SixPlayerBoardController)FlowController.getInstance().getControllerBoard();
        CompetidorDto currentPlayer = sixPlayerBoardController.getCurrentCompetitor();
        for (CompetidorDto player : competitorsDuel) {
            if (player.getCompetidorPK().equals(currentPlayer.getCompetidorPK())) {
                challenging= player;
                break;
            }}
        competitorsDuel.remove(challenging);
    }

    private void fillPlayersDto() {
        fillChallengin();
        for (int i = 0; i < numberOfPlayer - 1; i++) {
            switch (i) {
                case 0:
                    player1 = competitorsDuel.get(0);
                    break;
                case 1:
                    player2 = competitorsDuel.get(1);
                    break;
                case 2:
                    player3 = competitorsDuel.get(2);
                    break;
                case 3:
                    player4 = competitorsDuel.get(3);
                    break;
                case 4:
                    player5 = competitorsDuel.get(4);
                    break;
            }
        }
    }

    @FXML
    private void onActionBtnDuel(ActionEvent event) {
        FlowController.getInstance().delete("DuelView");
        prepareDuel();
        FlowController.getInstance().goViewInWindowModal("DuelView", getStage(),true);
        getStage().close();
    }

    private VBox containerAvatar(int i) {
        VBox[] vbxPlayers = {vbxPlayer1, vbxPlayer2, vbxPlayer3, vbxPlayer4, vbxPlayer5, vbxPlayer6};
        return vbxPlayers[i];
    }

    private ImageView imgSelect(int i) {
        ImageView[] imgSelections = {imgSelectPlayer1, imgSelectPlayer2, imgSelectPlayer3, imgSelectPlayer4, imgSelectPlayer5, imgSelectPlayer6};
        return imgSelections[i];
    }

    private CompetidorDto competitorsPlayer(int i) {
        CompetidorDto[] player = {challenging, player1, player2, player3, player4, player5};
        return player[i];
    }
    private Label lblPlayer(int i) {
        Label[] lblPlayers = {lblJugador1, lblJugador2, lblJugador3, lblJugador4, lblJugador5, lblJugador6};
        return lblPlayers[i];
    }
    private String getTypeAvatar(String routeAvatar) {
        switch (routeAvatar) {
            case routeArte:
                return "Arte";
            case routeGeografia:
                return "Geografía";
            case routeHistoria:
                return "Historia";
            case routeCiencia:
                return "Ciencia";
            case routeDeporte:
                return "Deporte";
            case routeEntretenimiento:
                return "Entretenimiento";
            default:
                return "";
        }
    }
    private void prepareDuel() {
       String typeAvatarChallengin = getTypeAvatar(routechallengin);
       String typeAvatarChallenged = getTypeAvatar(challengedRoute);
       AppContext.getInstance().set("typeAvatarChallenging", typeAvatarChallengin);
       AppContext.getInstance().set("typeAvatarChallenged", typeAvatarChallenged);
       CompetidorDto challenged = competitorsPlayer(numberChallenged);
       AppContext.getInstance().set("challenging", challenging);
       AppContext.getInstance().set("challenged", challenged);
    }

   }
