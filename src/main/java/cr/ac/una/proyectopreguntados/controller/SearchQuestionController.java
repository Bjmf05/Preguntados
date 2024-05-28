package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.Pregunta;
import cr.ac.una.proyectopreguntados.model.PreguntaDto;
import cr.ac.una.proyectopreguntados.service.PreguntaService;
import cr.ac.una.proyectopreguntados.util.Formato;
import cr.ac.una.proyectopreguntados.util.Mensaje;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SearchQuestionController extends Controller implements Initializable {

    @FXML
    private MFXComboBox<String> cbxTypeQuestion;
    @FXML
    private TableView<PreguntaDto> tbvResults;
    @FXML
    private MFXButton btnAccept;
    ObservableList<PreguntaDto> questions = FXCollections.observableArrayList();
    Object result;
    @FXML
    private TableColumn<PreguntaDto, String> tbvClId;
    @FXML
    private TableColumn<PreguntaDto, String> tvcClQuestion;
    @FXML
    private MFXTextField txfIdQuestion;
    @FXML
    private MFXTextField txfContentQuestion;
    @FXML
    private MFXButton btnSearch;
    @FXML
    private MFXComboBox<String> cbxStatus;
    @FXML
    private TableColumn<PreguntaDto, String> tbsEstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txfIdQuestion.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txfContentQuestion.delegateSetTextFormatter(Formato.getInstance().textFormat(150));
        cbxTypeQuestion.getItems().addAll("Deporte", "Historia", "Geografía", "Arte", "Ciencia", "Entretenimiento","");
        cbxStatus.getItems().addAll("Activa", "Inactiva","");
        tbvClId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvcClQuestion.setCellValueFactory(new PropertyValueFactory<>("contenido"));
        tbsEstatus.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    @Override
    public void initialize() {
    }


    @FXML
    private void onMousePressenTbvResults(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            onActionBtnAccept(null);
        }
    }

    @FXML
    private void onActionBtnAccept(ActionEvent event) {
        result = tbvResults.getSelectionModel().getSelectedItem();
        this.getStage().close();
    }

    public Object getResult() {
        return result;
    }

    private void getQuestions() {
        String id = txfIdQuestion.getText().trim();
        String contenido = txfContentQuestion.getText().trim();
        String categoria = cbxTypeQuestion.getValue();
        String estado = cbxStatus.getValue();

        if (Objects.equals(estado, "Activa")) {
            estado = "A";
        } else if (Objects.equals(estado, "Inactiva")) {
            estado = "I";
        } else {
            estado = "";
        }
        id = id.isEmpty() ? "%" : "%" + id.toUpperCase() + "%";
        contenido = contenido.isEmpty() ? "%" : "%" + contenido.toUpperCase() + "%";
        categoria = (categoria == null || categoria.isEmpty()) ? "%" : "%" + categoria.toUpperCase() + "%";
        estado = (estado == null || estado.isEmpty()) ? "%" : "%" + estado.toUpperCase() + "%";


        PreguntaService service = new PreguntaService();
        RespuestaEnt respuesta = service.getQuestionsParameters(id, contenido, categoria, estado);
        if (respuesta.getEstado()) {
            questions.clear();
            questions.addAll((List<PreguntaDto>) respuesta.getResultado("Preguntas"));
            tbvResults.setItems(questions);
            tbvResults.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Preguntas", getStage(), respuesta.getMensaje());
        }

    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
        getQuestions();
    }

}
