package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.*;
import cr.ac.una.proyectopreguntados.service.PartidaService;
import cr.ac.una.proyectopreguntados.service.PreguntaService;
import cr.ac.una.proyectopreguntados.util.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SearchGameController extends Controller implements Initializable {

    @FXML
    private DatePicker dtpDate;
    @FXML
    private MFXTextField txfIdGame;
    @FXML
    private MFXTextField txfGameName;
    @FXML
    private MFXComboBox<String> cbxAmountPlayers;
    @FXML
    private MFXComboBox<String> cbxDificulty;
    @FXML
    private MFXButton btnSearch;
    @FXML
    private TableView<PartidaDto> tbvGame;
    @FXML
    private TableColumn<PartidaDto, String> tbcIdGame;
    @FXML
    private TableColumn<PartidaDto, String> tbcGameName;
    @FXML
    private TableColumn<PartidaDto, String> tbcAmountPlayers;
    @FXML
    private TableColumn<PartidaDto, String> tbcDificulty;
    @FXML
    private TableColumn<PartidaDto, LocalDate> tbcDate;
    @FXML
    private MFXButton btnAccept;
    private ObservableList<PartidaDto> games = FXCollections.observableArrayList();
    private PartidaDto selectedGame;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbxAmountPlayers.getItems().addAll("2", "3", "4", "5", "6", "");
        cbxDificulty.getItems().addAll("Facil", "Intermedio", "Dificil", "");
        tbcIdGame.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbcGameName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcAmountPlayers.setCellValueFactory(new PropertyValueFactory<>("jugadores"));
        tbcDificulty.setCellValueFactory(new PropertyValueFactory<>("dificultad"));
        tbcDate.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        txfIdGame.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txfGameName.delegateSetTextFormatter(Formato.getInstance().letrasFormat(50));
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
        makeSearch();
    }

    @FXML
    private void onActionBtnAccept(ActionEvent event) {
        selectedGame = tbvGame.getSelectionModel().getSelectedItem();
        if (selectedGame == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Partida", getStage(), "Debe seleccionar una partida");
        } else {
            loadGameData(selectedGame);
            getQuestions();
            AppContext.getInstance().set("Partida", selectedGame);
            FlowController.getInstance().goMain();
        }
    }

    private void makeSearch() {
        String id = txfIdGame.getText().trim();
        String name = txfGameName.getText().trim();
        String players = cbxAmountPlayers.getValue();
        String dificulty = cbxDificulty.getValue();
        LocalDate date = dtpDate.getValue();

        id = id.isEmpty() ? "%" : "%" + id.toUpperCase() + "%";
        name = name.isEmpty() ? "%" : "%" + name.toUpperCase() + "%";
        players = (players == null || players.isEmpty()) ? "%" : "%" + players.toUpperCase() + "%";
        dificulty = (dificulty == null || dificulty.isEmpty()) ? "%" : "%" + dificulty.toUpperCase() + "%";
        date = (date == null) ? null : date;


        PartidaService service = new PartidaService();
        RespuestaEnt respuesta = service.getGames(id, name, players, dificulty, date);
        if (respuesta.getEstado()) {
            games.clear();
            games.addAll((List<PartidaDto>) respuesta.getResultado("Games"));
            tbvGame.setItems(games);
            tbvGame.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Partidas", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void onMousePressedTbvGame(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            onActionBtnAccept(null);
        }
    }

    private void loadGameData(PartidaDto Game) {
        Map<Integer, CompetidorDto> competitorMap = new HashMap<>();

        for (Competidor c : Game.getCompetidorList()) {
            CompetidorDto comp = new CompetidorDto(c);
            int index = comp.getNumeroJugador().intValue() - 1;
            competitorMap.put(index, comp);
        }
        ObservableList<CompetidorDto> competitors = FXCollections.observableArrayList();

        for (int i = 0; i < Game.getJugadores().intValue(); i++) {
            if (competitorMap.containsKey(i)) {
                competitors.add(competitorMap.get(i));
            }
        }
        AppContext.getInstance().set("Competidores", competitors);
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
    }

}
