package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.JugadorDto;
import cr.ac.una.proyectopreguntados.service.JugadorService;
import cr.ac.una.proyectopreguntados.util.FlowController;
import cr.ac.una.proyectopreguntados.util.Formato;
import cr.ac.una.proyectopreguntados.util.Mensaje;
import cr.ac.una.proyectopreguntados.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LogInController extends Controller implements Initializable {

    @FXML
    private MFXButton btnPlay;
    @FXML
    private MFXButton btnExit;
    @FXML
    private MFXButton btnAbout;
    @FXML
    private MFXButton btnStatistics;
    @FXML
    private MFXButton btnMaintenanceQuestions;
    @FXML
    private MFXTextField txfNewPlayer;
    @FXML
    private MFXButton btnSave;
    JugadorDto jugadorDto;
    @FXML
    private Label idPruebaTime;
        private Timeline timeline;
    private LocalTime tiempoInicial;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    txfNewPlayer.delegateSetTextFormatter(Formato.getInstance().letrasFormat(30));
    setTiempoInicial("23:59:00");
    }

    @Override
    public void initialize() {
    }



    @FXML
    private void onActionBtnPlay(ActionEvent event) {
        FlowController.getInstance().goMain();
        getStage().close();
    }


    @FXML
    private void onActionBtnExit(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAbout(ActionEvent event) {
                FlowController.getInstance().goViewInWindowModal("NewGameView", getStage(), true);
    }
    public void setTiempoInicial(String tiempo) {
        tiempoInicial = LocalTime.parse(tiempo, formatter);
        idPruebaTime.setText(tiempo);
    }
    @FXML
    private void onActionBtnStatistics(ActionEvent event) {
                if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarCronometro()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
        private void actualizarCronometro() {
        tiempoInicial = tiempoInicial.minusSeconds(1);
        String tiempo = tiempoInicial.format(formatter);
        idPruebaTime.setText(tiempo);

        if (tiempoInicial.getHour() == 0 && tiempoInicial.getMinute() == 0 && tiempoInicial.getSecond() == 0) {
            timeline.stop();
        }
    }

    @FXML
    private void onActionBtnMaintenanceQuestions(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("MaintenanceQuestionsView", getStage(), true);
    }

    @FXML
    private void onActionBtnSave(ActionEvent event) {
        try{
        if(txfNewPlayer.getText().isEmpty()){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar jugador", getStage(), "Digite el nombre del jugador");
        }else{
            jugadorDto = new JugadorDto();
            jugadorDto.setNombre(txfNewPlayer.getText());
            jugadorDto.setPartidasGanadas(0L);
            jugadorDto.setPartidasJugadas(0L);
            JugadorService service = new JugadorService();
                Respuesta respuesta = service.savePlayer(jugadorDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar jugador", getStage(), respuesta.getMensaje());
                } else {
                    jugadorDto = (JugadorDto) respuesta.getResultado("Jugador");
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar jugador", getStage(), "Jugador guardado correctamente.");
                }
                    }
        } catch (Exception ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, "Error guardando el jugador.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar jugador", getStage(), "Ocurrió un error guardando el jugador.");
        }
    }

}
