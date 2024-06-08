package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.PreguntaDto;
import cr.ac.una.proyectopreguntados.model.Respuesta;
import cr.ac.una.proyectopreguntados.model.RespuestaDto;
import cr.ac.una.proyectopreguntados.model.RespuestaPK;
import cr.ac.una.proyectopreguntados.service.PreguntaService;
import cr.ac.una.proyectopreguntados.service.RespuestaService;
import cr.ac.una.proyectopreguntados.util.FlowController;
import cr.ac.una.proyectopreguntados.util.Formato;
import cr.ac.una.proyectopreguntados.util.Mensaje;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class MaintenanceQuestionsController extends Controller implements Initializable {

    @FXML
    private MFXTextField txfId;
    @FXML
    private MFXComboBox<String> cbxTypeQuestion;
    @FXML
    private MFXTextField txfQuestion;
    @FXML
    private MFXTextField txfCorrectAnswer;
    @FXML
    private Label lblQuantityCorrectAnswer;
    @FXML
    private MFXTextField txfIncorrectAnswer1;
    @FXML
    private Label lblQuantityIncorrectAnswer1;
    @FXML
    private MFXTextField txfIncorrectAnswer2;
    @FXML
    private Label lblQuantityIncorrectAnswer2;
    @FXML
    private MFXTextField txfIncorrectAnswer3;
    @FXML
    private Label lblQuantityIncorrectAnswer3;
    @FXML
    private Label lblNumberOfTimeAsked;
    @FXML
    private MFXButton btnNew;
    @FXML
    private MFXButton btnSearch;
    @FXML
    private MFXButton btnDelete;
    @FXML
    private MFXButton btnSave;
    @FXML
    private MFXCheckbox chkActive;
    PreguntaDto preguntaDto;
    RespuestaDto respuestaDtoCorrect;
    RespuestaDto respuestaDtoIncorrect1;
    RespuestaDto respuestaDtoIncorrect2;
    RespuestaDto respuestaDtoIncorrect3;
    List<Node> required = new ArrayList<>();
    ObservableList<RespuestaDto> answers = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbxTypeQuestion.getItems().addAll("Deporte", "Historia", "Geografía", "Arte", "Ciencia", "Entretenimiento");
        txfId.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txfQuestion.delegateSetTextFormatter(Formato.getInstance().textFormat(150));
        txfCorrectAnswer.delegateSetTextFormatter(Formato.getInstance().textFormat(50));
        txfIncorrectAnswer1.delegateSetTextFormatter(Formato.getInstance().textFormat(50));
        txfIncorrectAnswer2.delegateSetTextFormatter(Formato.getInstance().textFormat(50));
        txfIncorrectAnswer3.delegateSetTextFormatter(Formato.getInstance().textFormat(50));
        newQuestion();
        indicateRequired();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onKeyPressedTxfId(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && !txfId.getText().isEmpty()) {
            getQuestion(Long.valueOf(txfId.getText()));
        }

    }

    @FXML
    private void onActionBtnNew(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar pregunta", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            newQuestion();
        }
    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
        SearchQuestionController searchController = (SearchQuestionController) FlowController.getInstance().getController("SearchQuestionView");
        FlowController.getInstance().goViewInWindowModal("SearchQuestionView", getStage(), true);
        PreguntaDto preguntaDto = (PreguntaDto) searchController.getResult();
        if (preguntaDto != null) {
            newQuestion();
            getQuestion(preguntaDto.getId());
        }
    }

    @FXML
    private void onActionBtnDelete(ActionEvent event) {
        try {
            if (preguntaDto.getId() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar pregunta", getStage(), "Debe cargar la pregunta que desea eliminar.");
            } else {

                PreguntaService service = new PreguntaService();
                RespuestaEnt respuesta = service.deleteQuestion(preguntaDto.getId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar pregunta", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar pregunta", getStage(), "Pregunta eliminada correctamente.");
                    newQuestion();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error eliminando la pregunta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar pregunta", getStage(), "Ocurrio un error eliminando la pregunta.");
        }
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        try {
            String invalid = validateRequired();
            if (!invalid.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pregunta", getStage(), invalid);
            } else {

                PreguntaService serviceQuestion = new PreguntaService();
                preguntaDto.setCategoria(cbxTypeQuestion.getValue());
                RespuestaEnt respuestaQuestion = serviceQuestion.saveQuestion(preguntaDto);
                if (!respuestaQuestion.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pregunta", getStage(), respuestaQuestion.getMensaje());
                } else {
                    safeAnswers(respuestaQuestion);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error guardando la pregunta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pregunta", getStage(), "Ocurrió un error guardando la pregunta.");
        }
    }

    @FXML
    private void onActionChkTypeQuestion(ActionEvent event) {
    }

    private void safeAnswers(RespuestaEnt respuestaQuestion) {
        try {
            PreguntaDto questionDto = (PreguntaDto) respuestaQuestion.getResultado("Pregunta");
            RespuestaService service = new RespuestaService();
            RespuestaEnt[] respuestas = new RespuestaEnt[4];
            RespuestaDto[] respuestaDtos = {respuestaDtoCorrect, respuestaDtoIncorrect1, respuestaDtoIncorrect2, respuestaDtoIncorrect3};
            String[] tipos = {"V", "F", "F", "F"};

            if (respuestaDtoCorrect.getRespuestaPK().getId() == null) {
                RespuestaPK respuestaPkTrue = new RespuestaPK(questionDto.getId());
                RespuestaPK respuestaPkFalse1 = new RespuestaPK(questionDto.getId());
                RespuestaPK respuestaPkFalse2 = new RespuestaPK(questionDto.getId());
                RespuestaPK respuestaPkFalse3 = new RespuestaPK(questionDto.getId());
                RespuestaPK[] respuestaPKs = {respuestaPkTrue, respuestaPkFalse1, respuestaPkFalse2, respuestaPkFalse3};
                for (int i = 0; i < respuestas.length; i++) {
                    RespuestaDto respuestaDto = respuestaDtos[i];
                    respuestaDto.setRespuestaPK(respuestaPKs[i]);
                }
            }
            for (int i = 0; i < respuestas.length; i++) {
                RespuestaDto respuestaDto = respuestaDtos[i];
                respuestaDto.setTipo(tipos[i]);
                respuestas[i] = service.saveAnswer(respuestaDto);
            }

            if (Arrays.stream(respuestas).allMatch(RespuestaEnt::getEstado)) {
                unbindQuestion();
                for (int i = 0; i < respuestas.length; i++) {
                    respuestaDtos[i] = (RespuestaDto) respuestas[i].getResultado("Respuesta");
                }
                preguntaDto = questionDto;
                bindQuestion(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar pregunta", getStage(), "Pregunta guardada correctamente.");
            } else {
                throw new RuntimeException("Error al guardar las respuestas.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Respuesta", getStage(), "Error al guardar las respuestas: " + ex.getMessage());
        }
    }

    private void indicateRequired() {
        required.clear();
        required.addAll(Arrays.asList(txfQuestion, txfCorrectAnswer, txfIncorrectAnswer1, txfIncorrectAnswer2, txfIncorrectAnswer3, cbxTypeQuestion));
    }

    private String validateRequired() {
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

    private void newQuestion() {
        preguntaDto = new PreguntaDto();
        respuestaDtoCorrect = new RespuestaDto();
        respuestaDtoIncorrect1 = new RespuestaDto();
        respuestaDtoIncorrect2 = new RespuestaDto();
        respuestaDtoIncorrect3 = new RespuestaDto();
        answers = FXCollections.observableArrayList();
        unbindQuestion();
        bindQuestion(true);
        txfId.clear();
        txfId.requestFocus();
    }

    private void bindQuestion(Boolean newQuestion) {
        if (!newQuestion) {
            txfId.textProperty().bind(preguntaDto.id);
        }
        txfQuestion.textProperty().bindBidirectional(preguntaDto.contenido);
        chkActive.selectedProperty().bindBidirectional(preguntaDto.estado);
        lblNumberOfTimeAsked.textProperty().bind(preguntaDto.cantidadLlamadas);
        txfCorrectAnswer.textProperty().bindBidirectional(respuestaDtoCorrect.contenido);
        lblQuantityCorrectAnswer.textProperty().bind(respuestaDtoCorrect.cantidadSelecciones);
        txfIncorrectAnswer1.textProperty().bindBidirectional(respuestaDtoIncorrect1.contenido);
        lblQuantityIncorrectAnswer1.textProperty().bind(respuestaDtoIncorrect1.cantidadSelecciones);
        txfIncorrectAnswer2.textProperty().bindBidirectional(respuestaDtoIncorrect2.contenido);
        lblQuantityIncorrectAnswer2.textProperty().bind(respuestaDtoIncorrect2.cantidadSelecciones);
        txfIncorrectAnswer3.textProperty().bindBidirectional(respuestaDtoIncorrect3.contenido);
        lblQuantityIncorrectAnswer3.textProperty().bind(respuestaDtoIncorrect3.cantidadSelecciones);
        cbxTypeQuestion.textProperty().bindBidirectional(preguntaDto.categoria);
    }

    private void unbindQuestion() {
        txfId.textProperty().unbind();
        txfQuestion.textProperty().unbindBidirectional(preguntaDto.contenido);
        chkActive.selectedProperty().unbindBidirectional(preguntaDto.estado);
        txfCorrectAnswer.textProperty().unbindBidirectional(respuestaDtoCorrect.contenido);
        txfIncorrectAnswer1.textProperty().unbindBidirectional(respuestaDtoCorrect.contenido);
        txfIncorrectAnswer2.textProperty().unbindBidirectional(respuestaDtoCorrect.contenido);
        txfIncorrectAnswer3.textProperty().unbindBidirectional(respuestaDtoCorrect.contenido);
    }

    private void getQuestion(Long id) {
        try {
            PreguntaService service = new PreguntaService();
            RespuestaEnt respuesta = service.getQuestion(id);
            if (respuesta.getEstado()) {
                unbindQuestion();
                this.preguntaDto = (PreguntaDto) respuesta.getResultado("Pregunta");
                getAnswer(preguntaDto);
                bindQuestion(false);
                validateRequired();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Pregunta", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error consultando la pregunta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Pregunta", getStage(), "Ocurrio un error consultando la pregunta.");
        }
    }

    private void getAnswer(PreguntaDto preguntaDto) {
        answers.addAll(preguntaDto.getRespuestasList());
        for (RespuestaDto answer : answers) {
            if (answer.getTipo().equals("V")) {
                respuestaDtoCorrect = answer;
            } else if (respuestaDtoIncorrect1.getId() == null) {
                respuestaDtoIncorrect1 = answer;
            } else if (respuestaDtoIncorrect2.getId() == null) {
                respuestaDtoIncorrect2 = answer;
            } else if (respuestaDtoIncorrect3.getId() == null) {
                respuestaDtoIncorrect3 = answer;
            }
        }

    }
}
