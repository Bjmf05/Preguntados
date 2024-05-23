package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.util.AppContext;
import cr.ac.una.proyectopreguntados.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SelectAvatarController extends Controller implements Initializable {

    @FXML
    private VBox vbxPlayer1;
    @FXML
    private VBox vbxPlayer2;
    @FXML
    private VBox vbxPlayer3;
    @FXML
    private VBox vbxPlayer4;
    @FXML
    private VBox vbxPlayer5;
    @FXML
    private VBox vbxPlayer6;
    @FXML
    private MFXButton btnSave;
    @FXML
    private ImageView imgSelectPlayer1;
    @FXML
    private ImageView imgSelectPlayer2;
    @FXML
    private ImageView imgSelectPlayer3;
    @FXML
    private ImageView imgSelectPlayer4;
    @FXML
    private ImageView imgSelectPlayer5;
    @FXML
    private ImageView imgSelectPlayer6;

    private String routeAvatarPlayer1 = "";
    private String routeAvatarPlayer2 = "";
    private String routeAvatarPlayer3 = "";
    private String routeAvatarPlayer4 = "";
    private String routeAvatarPlayer5 = "";
    private String routeAvatarPlayer6 = "";

    private final String routeBrush = "/cr/ac/una/proyectopreguntados/resources/Pincel.png";
    private final String routeBallon = "/cr/ac/una/proyectopreguntados/resources/Globo.png";
    private final String routeBook = "/cr/ac/una/proyectopreguntados/resources/Libro.png";
    private final String routeCrown = "/cr/ac/una/proyectopreguntados/resources/Corona.png";
    private final String routeJester = "/cr/ac/una/proyectopreguntados/resources/Bufon.png";
    private final String routeCinema = "/cr/ac/una/proyectopreguntados/resources/Cine.png";
    private final String routeBall = "/cr/ac/una/proyectopreguntados/resources/Balon.png";
    private final String routeGlass = "/cr/ac/una/proyectopreguntados/resources/Vaso.png";
    ObservableList<String> routesAvatars = FXCollections.observableArrayList();
    private int numberOfPlayer = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numberOfPlayer = (int) AppContext.getInstance().get("NumberOfPlayers");
        imagesRoute();

    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        if (areAvatarRoutesValid()) {
            for (int i = 0; i < numberOfPlayer; i++) {
                routesAvatars.add(getRouteAvatarPlayers(i));
            }
            AppContext.getInstance().set("Rutes",routesAvatars);
            getStage().close();
        }
    else{

            new Mensaje().showModal(Alert.AlertType.ERROR, "Seleccionar Avatar", getStage(), "Algun jugador no a seleccionado Avatar");
        }
    }

    private void imagesRoute() {
        for (int i = 0; i < numberOfPlayer; i++) {
            for (int j = 0; j < 7; j++) {
                VBox container = containerAvatar(i);
                InputStream inputStream = App.class.getResourceAsStream(routeAvatar(j));
                Image imagen = new Image(inputStream);
                ImageView imageView = new ImageView(imagen);
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
       
                int playerIndex = i;
                int avatarIndex = j;
                imageView.setOnMouseClicked(event -> {
                    String selectedAvatar = routeAvatar(avatarIndex);
                    setRouteAvatarPlayers(playerIndex, selectedAvatar);
                    
                    InputStream inputStream2 = App.class.getResourceAsStream(selectedAvatar);
                    imgSelect(playerIndex).setImage(new Image(inputStream2));
                });
                container.getChildren().add(imageView);
            }
        }
    }

    private VBox containerAvatar(int i) {
        VBox[] vbxPlayers = {vbxPlayer1, vbxPlayer2, vbxPlayer3, vbxPlayer4, vbxPlayer5, vbxPlayer6};
        return vbxPlayers[i];
    }

    private ImageView imgSelect(int i) {
        ImageView[] imgSelections = {imgSelectPlayer1, imgSelectPlayer2, imgSelectPlayer3, imgSelectPlayer4, imgSelectPlayer5, imgSelectPlayer6};
        return imgSelections[i];
    }

    private String routeAvatar(int i) {
        String[] avatar = {routeBrush, routeBallon, routeBook, routeCrown, routeJester, routeCinema, routeBall, routeGlass};
        return avatar[i];
    }

    private void setRouteAvatarPlayers(int i, String route) {
        switch (i) {
            case 0 -> this.routeAvatarPlayer1= route;
            case 1 -> this.routeAvatarPlayer2 = route;
            case 2 -> this.routeAvatarPlayer3 = route;
            case 3 -> this.routeAvatarPlayer4 = route;
            case 4 -> this.routeAvatarPlayer5 = route;
            case 5 -> this.routeAvatarPlayer6 = route;
        }

    }

    private String getRouteAvatarPlayers(int i) {
        String[] routeAvatar = {routeAvatarPlayer1, routeAvatarPlayer2, routeAvatarPlayer3, routeAvatarPlayer4, routeAvatarPlayer5, routeAvatarPlayer6};
        return routeAvatar[i];
    }

    private boolean areAvatarRoutesValid() {
        for (int i = 0; i < numberOfPlayer; i++) {
            if (getRouteAvatarPlayers(i) == null || getRouteAvatarPlayers(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    @Override
    public void initialize() {
    }
}
