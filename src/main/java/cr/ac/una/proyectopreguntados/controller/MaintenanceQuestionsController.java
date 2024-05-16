package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.PreguntaDto;
import cr.ac.una.proyectopreguntados.model.RespuestaDto;
import cr.ac.una.proyectopreguntados.model.RespuestaPK;
import cr.ac.una.proyectopreguntados.service.PreguntaService;
import cr.ac.una.proyectopreguntados.service.RespuestaService;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    private MFXButton btnExit;
    @FXML
    private MFXCheckbox chkActive;
    PreguntaDto preguntaDto;
    RespuestaDto respuestaDtoCorrect;
    RespuestaDto respuestaDtoIncorrect1;
    RespuestaDto respuestaDtoIncorrect2;
    RespuestaDto respuestaDtoIncorrect3;
    List<Node> required = new ArrayList<>();

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
        preguntaDto = new PreguntaDto();
        respuestaDtoCorrect = new RespuestaDto();
        respuestaDtoIncorrect1 = new RespuestaDto();
        respuestaDtoIncorrect2 = new RespuestaDto();
        respuestaDtoIncorrect3 = new RespuestaDto();
        newQuestion();
        indicateRequired();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onKeyPressedTxfId(KeyEvent event) {
    }

    @FXML
    private void onActionBtnNew(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar pregunta", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            newQuestion();
        }
    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
    }

    @FXML
    private void onActionBtnDelete(ActionEvent event) {
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
    private void onActionBtnExit(ActionEvent event) {
        getStage().close();
    }

    @FXML
    private void onActionChkTypeQuestion(ActionEvent event) {
    }

    private void safeAnswers(RespuestaEnt respuestaQuestion) {
            try {
        PreguntaDto questionDto = (PreguntaDto) respuestaQuestion.getResultado("Pregunta");
       RespuestaPK respuestaPkTrue = new RespuestaPK(questionDto.getId());
        RespuestaPK respuestaPkFalse1 = new RespuestaPK(questionDto.getId());
      RespuestaPK respuestaPkFalse2 = new RespuestaPK(questionDto.getId());
       RespuestaPK respuestaPkFalse3 = new RespuestaPK(questionDto.getId());
        RespuestaService service = new RespuestaService();

        RespuestaEnt[] respuestas = new RespuestaEnt[4];
        RespuestaDto[] respuestaDtos = {respuestaDtoCorrect, respuestaDtoIncorrect1, respuestaDtoIncorrect2, respuestaDtoIncorrect3};
        String[] tipos = {"V", "F", "F", "F"};
        RespuestaPK[] respuestaPKs = {respuestaPkTrue,respuestaPkFalse1,respuestaPkFalse2,respuestaPkFalse3};
        for (int i = 0; i < respuestas.length; i++) {
            RespuestaDto respuestaDto = respuestaDtos[i];
            respuestaDto.setRespuestaPK(respuestaPKs[i]);
            respuestaDto.setTipo(tipos[i]);
            respuestas[i] = service.saveAnswer(respuestaDto);
        }

        if (Arrays.stream(respuestas).allMatch(RespuestaEnt::getEstado)) {
            unbindQuestion();
                    for (int i = 0; i < respuestas.length; i++) {
            respuestaDtos[i]=(RespuestaDto) respuestas[i].getResultado("Respuesta");
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
//        PreguntaDto questionDto = (PreguntaDto) respuestaQuestion.getResultado("Pregunta");
//        RespuestaPK respuestaPkTrue = new RespuestaPK(questionDto.getId());
//        RespuestaPK respuestaPkFalse1 = new RespuestaPK(questionDto.getId());
//        RespuestaPK respuestaPkFalse2 = new RespuestaPK(questionDto.getId());
//        RespuestaPK respuestaPkFalse3 = new RespuestaPK(questionDto.getId());
//        
//
//        RespuestaService serviceAswerTrue = new RespuestaService();
//        respuestaDtoCorrect.setRespuestaPK(respuestaPkTrue);
//        respuestaDtoCorrect.setTipo("V");
//        RespuestaEnt respuestaTrue = serviceAswerTrue.saveAnswer(respuestaDtoCorrect);
//
//        RespuestaService serviceAswerFalse1 = new RespuestaService();
//        respuestaDtoIncorrect1.setRespuestaPK(respuestaPkFalse1);
//        respuestaDtoIncorrect1.setTipo("F");
//        RespuestaEnt respuestaFalse1 = serviceAswerFalse1.saveAnswer(respuestaDtoIncorrect1);
//
//        RespuestaService serviceAswerFalse2 = new RespuestaService();
//        respuestaDtoIncorrect2.setRespuestaPK(respuestaPkFalse2);
//        respuestaDtoIncorrect2.setTipo("F");
//        RespuestaEnt respuestaFalse2 = serviceAswerFalse2.saveAnswer(respuestaDtoIncorrect2);
//
//        RespuestaService serviceAswerFalse3 = new RespuestaService();
//        respuestaDtoIncorrect3.setRespuestaPK(respuestaPkFalse3);
//        respuestaDtoIncorrect3.setTipo("F");
//        RespuestaEnt respuestaFalse3 = serviceAswerFalse3.saveAnswer(respuestaDtoIncorrect3);
//
//        if (respuestaTrue.getEstado() && respuestaFalse1.getEstado() && respuestaFalse2.getEstado() && respuestaFalse3.getEstado()) {
//            unbindQuestion();
//            preguntaDto = questionDto;
//            respuestaDtoCorrect = (RespuestaDto) respuestaTrue.getResultado("Respuesta");
//            respuestaDtoIncorrect1 = (RespuestaDto) respuestaFalse1.getResultado("Respuesta");
//            respuestaDtoIncorrect2 = (RespuestaDto) respuestaFalse2.getResultado("Respuesta");
//            respuestaDtoIncorrect3 = (RespuestaDto) respuestaFalse3.getResultado("Respuesta");
//            bindQuestion(false);
//            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar pregunta", getStage(), "Pregunta guardada correctamente.");
//
//        } else {
//            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Respuesta", getStage(), respuestaTrue.getMensaje());
//        }

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
        txfCorrectAnswer.textProperty().bindBidirectional(respuestaDtoCorrect.contenido);
        txfIncorrectAnswer1.textProperty().bindBidirectional(respuestaDtoIncorrect1.contenido);
        txfIncorrectAnswer2.textProperty().bindBidirectional(respuestaDtoIncorrect2.contenido);
        txfIncorrectAnswer3.textProperty().bindBidirectional(respuestaDtoIncorrect3.contenido);
        if(preguntaDto.getCategoria()!=""){
            cbxTypeQuestion.setValue(preguntaDto.getCategoria());
        }
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
}
