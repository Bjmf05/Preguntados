package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.App;
import cr.ac.una.proyectopreguntados.model.*;
import cr.ac.una.proyectopreguntados.service.CompetidorService;
import cr.ac.una.proyectopreguntados.service.JugadorService;
import cr.ac.una.proyectopreguntados.service.PartidaService;
import cr.ac.una.proyectopreguntados.service.PreguntaService;
import cr.ac.una.proyectopreguntados.util.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    List<Node> required = new ArrayList<>();
    private boolean isSelectAvatar = false;
    @FXML
    private MFXTextField txfNewPlayer;
    @FXML
    private MFXButton btnSavePlayer;
    JugadorDto jugadorDto;
    LogInController controller = (LogInController) FlowController.getInstance().getController("LogInView");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txfNewPlayer.delegateSetTextFormatter(Formato.getInstance().letrasFormat(30));
        cbxAmountPlayer.getItems().addAll("2", "3", "4", "5", "6");
        cbxDifficulty.getItems().addAll("Facil", "Intermedio", "Dificil");
        txfHour.delegateSetTextFormatter(Formato.getInstance().integerFormat(24));
        txfMinute.delegateSetTextFormatter(Formato.getInstance().integerFormat(60));
        txfNameGame.setTextFormatter(Formato.getInstance().letrasFormat(50));
        clear();
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
        isSelectAvatar = false;
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
        isSelectAvatar = false;
    }

    @FXML
    private void onActionCbxDifficulty(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSelectAvatar(ActionEvent event) {
        if (cbxAmountPlayer.getValue() != null) {
            int numberOfPlayers = Integer.parseInt(cbxAmountPlayer.getValue());
            AppContext.getInstance().set("NumberOfPlayers", numberOfPlayers);
            FlowController.getInstance().goViewInWindowModal("SelectAvatarView", getStage(), true);
            isSelectAvatar = true;
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Seleccionar Avatar", getStage(), "Seleccione la cantidad de jugadores");
        }
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
        try {
            String invalid = validateRequired();
            if (!invalid.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Iniciar partida", getStage(), invalid);
            } else {

                PartidaService serviceGame = new PartidaService();
                RespuestaEnt respuestaGame = serviceGame.saveGame(safeNewGame());
                if (!respuestaGame.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pregunta", getStage(), respuestaGame.getMensaje());
                } else {
                    PartidaDto partidaDto = (PartidaDto) respuestaGame.getResultado("Partida");
                    safePlayers(partidaDto.getId());
                    searchGame(partidaDto.getId());
                    FlowController.getInstance().goMain();
                    controller.exit();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error al iniciar partida.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Iniciar Partida", getStage(), "Ocurrió un error al iniciar partida.");
        }

    }

    @FXML
    private void chkTimeOnAction(ActionEvent event) {
        validateTime();
    }

    private void validateTime() {
        if (chkTime.isSelected()) {
            txfHour.setDisable(false);
            txfMinute.setDisable(false);
            txfHour.setText("00");
            txfMinute.setText("00");
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
                    invalid += ((MFXTextField) node).getFloatingText();
                } else {
                    invalid += "," + ((MFXTextField) node).getFloatingText();
                }
                valid = false;
            } else if (node instanceof MFXComboBox && ((MFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (valid) {
                    invalid += ((MFXComboBox) node).getFloatingText();
                } else {
                    invalid += "," + ((MFXComboBox) node).getFloatingText();
                }
                valid = false;
            }
        }
        if (!isSelectAvatar) {
            if (valid) {
                invalid += "Selecciona Avatar";
            } else {
                invalid += ",Selecciona Avatar";
            }
            valid = false;
        }
        if (!valid) {
            return "Campos requeridos o con problemas de formato [" + invalid + "].";
        } else if (!areAllValuesDifferent(getPlayerCbx(1), getPlayerCbx(2), getPlayerCbx(3), getPlayerCbx(4), getPlayerCbx(5), getPlayerCbx(6))) {
            return "Hay algunos jugadores repetidos, seleccione un jugador diferente";
        } else {
            return "";
        }
    }

    private boolean areAllValuesDifferent(MFXComboBox<JugadorDto>... comboBoxes) {
        Set<JugadorDto> uniqueValues = new HashSet<>();
        for (MFXComboBox<JugadorDto> comboBox : comboBoxes) {
            JugadorDto value = comboBox.getValue();
            if (value == null) {
                continue;
            }
            if (!uniqueValues.add(value)) {
                return false;
            }
        }
        return true;
    }

private void safePlayers(Long idGame) {

    ObservableList<String> playersAvatar = (ObservableList<String>) AppContext.getInstance().get("Rutes");
    int numPlayer = cbxAmountPlayer.getValue() == null ? 2 : Integer.parseInt(cbxAmountPlayer.getValue());
    String difficulty = cbxDifficulty.getValue();
    int help = 0;
    if (difficulty.equals("Facil")) {
        help = 1;
    }
    JugadorService service = new JugadorService(); // Mover fuera del bucle
    for (int i = 1; i <= numPlayer; i++) {
        JugadorDto jugador = (JugadorDto) getPlayerCbx(i).getValue();
        if (jugador != null) {
            String route = playersAvatar.get(i - 1);
            CompetidorPK competidorPK = new CompetidorPK(idGame, jugador.getId());
            CompetidorDto competitor = new CompetidorDto(competidorPK, i, help, route);
            jugador.setPartidasJugadas(jugador.getPartidasJugadas() + 1);
            RespuestaEnt respuesta = service.savePlayer(jugador); // Reutilizar el servicio
            jugador = (JugadorDto) respuesta.getResultado("Jugador");
            safeCompetitors(competitor);
        }
    }
    getQuestions();
}

    private PartidaDto safeNewGame() {
        String name = txfNameGame.getText();
        Long numberPlayers = 2L;
        if (cbxAmountPlayer.getValue() != null) {
            numberPlayers = Long.parseLong(cbxAmountPlayer.getValue());
        }
        boolean limitTime = chkTime.isSelected();
        String difficulty = cbxDifficulty.getValue();
        String time = null;
        if (limitTime) {
            time = txfHour.getText() + ":" + txfMinute.getText() + ":00";
        }
        LocalDate dateNow = LocalDate.now();
        PartidaDto partidaDto = new PartidaDto(name, numberPlayers, time, difficulty, dateNow);

        return partidaDto;
    }

    private void safeCompetitors(CompetidorDto competidor) {
        try {
            CompetidorService serviceCompetitor = new CompetidorService();
            RespuestaEnt respuestaCompetitor = serviceCompetitor.saveCompetitor(competidor);
            if (!respuestaCompetitor.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Competidor", getStage(), respuestaCompetitor.getMensaje());
            } else {
                CompetidorDto competitor = (CompetidorDto) respuestaCompetitor.getResultado("Competidor");
            }

        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error al guardar competidor.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Iniciar Partida", getStage(), "Ocurrió un error al guardar competidor.");
        }

    }

    @FXML
    private void onActionBtnSavePlayer(ActionEvent event) {
        try {
            if (txfNewPlayer.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar jugador", getStage(), "Digite el nombre del jugador");
            } else {
                jugadorDto = new JugadorDto();
                jugadorDto.setNombre(txfNewPlayer.getText());
                JugadorService service = new JugadorService();
                RespuestaEnt respuesta = service.savePlayer(jugadorDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar jugador", getStage(), respuesta.getMensaje());
                } else {
                    jugadorDto = (JugadorDto) respuesta.getResultado("Jugador");
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar jugador", getStage(), "Jugador guardado correctamente.");
                    players.clear();
                    fillCbxPlayer();
                    txfNewPlayer.clear();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, "Error guardando el jugador.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar jugador", getStage(), "Ocurrió un error guardando el jugador.");
        }
    }
    private void getQuestions() {
        ObservableList<PreguntaDto> questions = FXCollections.observableArrayList();
        try {
            PreguntaService service = new PreguntaService();
            RespuestaEnt respuesta = service.getQuestions();
            if (respuesta.getEstado()) {
                questions = FXCollections.observableList((List<PreguntaDto>) respuesta.getResultado("Preguntas"));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Preguntas", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error consultando las preguntas.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Preguntas", getStage(), "Ocurrio un error consultando las preguntas.");
        }
        AppContext.getInstance().set("PreguntasList", questions);
        ObservableList<PreguntaDto> questionsAsked = FXCollections.observableArrayList();
        AppContext.getInstance().set("PreguntasEchas", questionsAsked);
    }
    private void searchGame(Long id){
        try {
            PartidaDto partidaDto = new PartidaDto();
            PartidaService service = new PartidaService();
            RespuestaEnt respuesta = service.getGame(id);
            if (respuesta.getEstado()) {
                partidaDto = (PartidaDto) respuesta.getResultado("Game");
                AppContext.getInstance().set("Partida", partidaDto);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Partida", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error consultando la partida.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar partida", getStage(), "Ocurrio un error consultando la partida.");
        }
    }
}
