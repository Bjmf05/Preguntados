package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.model.JugadorDto;
import cr.ac.una.proyectopreguntados.service.JugadorService;
import cr.ac.una.proyectopreguntados.util.Formato;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class NewGameController extends Controller implements Initializable {

    @FXML
    private MFXTextField txfNameGame;
    @FXML
    private MFXComboBox<String> cbxAmountPlayer;
    @FXML
    private MFXComboBox<String> cbxDifficulty;
    @FXML
    private MFXComboBox<JugadorDto> cbxPlayer1;
    @FXML
    private MFXComboBox<JugadorDto> cbxPlayer2;
    @FXML
    private MFXComboBox<JugadorDto> cbxPlayer3;
    @FXML
    private MFXComboBox<JugadorDto> cbxPlayer4;
    @FXML
    private MFXComboBox<JugadorDto> cbxPlayer5;
    @FXML
    private MFXComboBox<JugadorDto> cbxPlayer6;
    @FXML
    private MFXButton btnSelectAvatar;
    @FXML
    private ImageView imgBoard;
    @FXML
    private MFXButton btnExit;
    @FXML
    private MFXButton btnLoadGame;
    @FXML
    private MFXButton btnPlay;
    @FXML
    private MFXCheckbox chkTime;
    @FXML
    private MFXTextField txfHour;
    @FXML
    private MFXTextField txfMinute;
    private ObservableList<JugadorDto> players = FXCollections.observableArrayList();
    JugadorDto jugadorDto;
    List<Node> required = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cbxAmountPlayer.getItems().addAll("2", "3", "4", "5", "6");
        cbxDifficulty.getItems().addAll("Facil", "Intermedio", "Dificil");
        txfHour.delegateSetTextFormatter(Formato.getInstance().integerFormat(24));
        txfMinute.delegateSetTextFormatter(Formato.getInstance().integerFormat(60));
        txfNameGame.setTextFormatter(Formato.getInstance().letrasFormat(50));
        fillCbxPlayer();
        indicateRequired();
    }

    @Override
    public void initialize() {
        clear();
    }

    @FXML
    private void onActionCbxAmountPlayer(ActionEvent event) {
        String numberPlayer = cbxAmountPlayer.getValue();
        int numPlayers = Integer.parseInt(numberPlayer);
        imageBoard(numPlayers);
        for (int i = numPlayers + 1; i <= 6; i++) {
            getPlayerCbx(i).setDisable(true);
            required.remove(getPlayerCbx(i));
        }
        for (int i = 1; i <= numPlayers; i++) {
            getPlayerCbx(i).setDisable(false);
            if (i >= 3 && !required.contains(getPlayerCbx(i))) {
                required.add(getPlayerCbx(i));
            }
        }
    }

    private MFXComboBox getPlayerCbx(int i) {
        MFXComboBox[] playerCbx = {cbxPlayer1, cbxPlayer2, cbxPlayer3, cbxPlayer4, cbxPlayer5, cbxPlayer6};
        return playerCbx[i - 1];
    }

    private void imageBoard(int number) {
        String[] imageBoards = {"boardSixPlayer.jpg", "boardSixPlayer.jpg", "boardSixPlayer.jpg", "boardSixPlayer.jpg", "boardSixPlayer.jpg"};
        InputStream inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/" + imageBoards[number - 2]);
        imgBoard.setImage(new Image(inputStream));
    }

    private void clear() {
        txfNameGame.clear();
        imageBoard(2);
        cbxAmountPlayer.setValue("2");
        cbxDifficulty.setValue("Intermedio");
        txfHour.setDisable(true);
        txfHour.clear();
        txfMinute.setDisable(true);
        txfMinute.clear();
        chkTime.setSelected(false);
        for (int i = 3; i <= 6; i++) {
            MFXComboBox currentPlayer = getPlayerCbx(i);
            currentPlayer.clear();
            currentPlayer.setDisable(true);
        }
    }

    @FXML
    private void onActionCbxDifficulty(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSelectAvatar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnExit(ActionEvent event) {
        getStage().close();
    }

    @FXML
    private void onActionBtnLoadGame(ActionEvent event) {
    }

    @FXML
    private void onActionBtnPlay(ActionEvent event) {
        
    }

    @FXML
    private void chkTimeOnAction(ActionEvent event) {
        validateTime();
    }

    private void validateTime() {
        if (chkTime.isSelected()) {
            txfHour.setDisable(false);
            txfMinute.setDisable(false);
            required.addAll(Arrays.asList(txfHour, txfMinute));
        } else {
            required.removeAll(Arrays.asList(txfHour, txfMinute));
            txfHour.validate();
            txfHour.validate();
            txfHour.setDisable(true);
            txfHour.clear();
            txfMinute.setDisable(true);
            txfMinute.clear();
        }
    }

    private void fillCbxPlayer() {
        JugadorService jugadorService = new JugadorService();
        RespuestaEnt respuesta = jugadorService.getAllPlayers();
        players.addAll((List<JugadorDto>) respuesta.getResultado("Jugadores"));
        for (int i = 1; i <= 6; i++) {
            getPlayerCbx(i).setItems(players);
        }

    }

    public void indicateRequired() {
        required.clear();
        required.addAll(Arrays.asList(txfNameGame, cbxAmountPlayer, cbxDifficulty, cbxPlayer1, cbxPlayer2));
    }
    public String validateRequired() {
        Boolean valid = true;
        String invalid = "";
        for (Node node : required) {
            if (node instanceof MFXTextField && (((MFXTextField) node).getText() == null || ((MFXTextField) node).getText().isEmpty())) {
                if (valid) {
                    invalid += ((MFXTextField) node).getPromptText();
                } else {
                    invalid += "," + ((MFXTextField) node).getPromptText();
                }
                valid = false;
            } else if (node instanceof MFXComboBox && ((MFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (valid) {
                    invalid += ((MFXComboBox) node).getPromptText();
                } else {
                    invalid += "," + ((MFXComboBox) node).getPromptText();
                }
                valid = false;
            }
        }
        if (valid) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalid + "].";
        }
    }
}
