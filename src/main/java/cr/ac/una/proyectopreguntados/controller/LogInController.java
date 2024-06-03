package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.JugadorDto;
import cr.ac.una.proyectopreguntados.service.JugadorService;
import cr.ac.una.proyectopreguntados.util.FlowController;
import cr.ac.una.proyectopreguntados.util.Formato;
import cr.ac.una.proyectopreguntados.util.Mensaje;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
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
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LogInController extends Controller implements Initializable {

    @FXML
    private MFXButton btnExit;
    @FXML
    private MFXButton btnAbout;
    @FXML
    private MFXButton btnStatistics;
    @FXML
    private MFXButton btnMaintenanceQuestions;
    private MFXTextField txfNewPlayer;
    JugadorDto jugadorDto;
    private Label idPruebaTime;
        private Timeline timeline;
    private LocalTime tiempoInicial;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @FXML
    private VBox vbxRoot;
    @FXML
    private MFXButton btnLoadGame;
    @FXML
    private MFXButton btnNewGame;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

   // setTiempoInicial("23:59:00");
    }

    @Override
    public void initialize() {
        FlowController.getInstance().delete("SixPlayerBoardView");
    }



    private void onActionBtnPlay(ActionEvent event) {

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
                FlowController.getInstance().showViewInVBox("MaintenanceQuestionsView", vbxRoot);
    }

    @FXML
    private void onActionBtnLoadGame(ActionEvent event) {
        FlowController.getInstance().showViewInVBox("SearchGameView", vbxRoot);
    }

    @FXML
    private void onActionBtnNewGame(ActionEvent event) {
        FlowController.getInstance().showViewInVBox("NewGameView", vbxRoot);
    }
    public void exit(){
        getStage().close();
    }

}
