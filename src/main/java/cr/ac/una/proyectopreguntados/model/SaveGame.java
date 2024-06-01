package cr.ac.una.proyectopreguntados.model;

import cr.ac.una.proyectopreguntados.controller.MaintenanceQuestionsController;
import cr.ac.una.proyectopreguntados.service.CompetidorService;
import cr.ac.una.proyectopreguntados.service.PartidaService;
import cr.ac.una.proyectopreguntados.util.AppContext;
import cr.ac.una.proyectopreguntados.util.Mensaje;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveGame {

    public void saveGame() {
        try {
            PartidaDto game = (PartidaDto) AppContext.getInstance().get("PartidaSave");
            PartidaService serviceGame = new PartidaService();
            RespuestaEnt respuestaGame = serviceGame.saveGame(game);
            safeCompetitors();
        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error al guardar juego.", ex);
            new Mensaje().show(Alert.AlertType.ERROR, "Guardar Juego",  "Ocurrió un error al guardar juego.");
        }
    }

    private void safeCompetitors(){
        ObservableList<CompetidorDto> competitors = (ObservableList<CompetidorDto>) AppContext.getInstance().get("CompetidoresSave");
        for (CompetidorDto competitor : competitors) {
            saveCompetitor(competitor);
        }

    }

    private void saveCompetitor(CompetidorDto competitor) {
        try {
            CompetidorService serviceCompetitor = new CompetidorService();
            RespuestaEnt respuestaCompetitor = serviceCompetitor.saveCompetitor(competitor);
            if (!respuestaCompetitor.getEstado()) {
                new Mensaje().show(Alert.AlertType.ERROR, "Guardar Competidor", respuestaCompetitor.getMensaje());
            } else {
                CompetidorDto sCompetitor = (CompetidorDto) respuestaCompetitor.getResultado("Competidor");
            }

        } catch (Exception ex) {
            Logger.getLogger(MaintenanceQuestionsController.class.getName()).log(Level.SEVERE, "Error al guardar competidor.", ex);
            new Mensaje().show(Alert.AlertType.ERROR, "Guardar Juego",  "Ocurrió un error al guardar competidor.");
        }
    }

}
