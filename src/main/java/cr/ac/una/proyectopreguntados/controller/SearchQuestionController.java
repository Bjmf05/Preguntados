package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.Pregunta;
import cr.ac.una.proyectopreguntados.model.PreguntaDto;
import cr.ac.una.proyectopreguntados.service.PreguntaService;
import cr.ac.una.proyectopreguntados.util.Mensaje;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cbxTypeQuestion.getItems().addAll("Deporte", "Historia", "Geografía", "Arte", "Ciencia", "Entretenimiento");
        tbvClId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tvcClQuestion.setCellValueFactory(new PropertyValueFactory<>("contenido"));
        getQuestions();
        tbvResults.setItems(questions);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionCbxTypeQuestion(ActionEvent event) {

        ObservableList<PreguntaDto> filterList = questions.filtered(question
                -> Objects.equals(question.getCategoria(), cbxTypeQuestion.getValue()));
        tbvResults.setItems(filterList);

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
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Preguntas", getStage(), "Ocurrio un error consultando las preguntas.");
        }
    }

}
