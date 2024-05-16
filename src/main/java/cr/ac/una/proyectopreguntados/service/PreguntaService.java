package cr.ac.una.proyectopreguntados.service;

import cr.ac.una.proyectopreguntados.model.Pregunta;
import cr.ac.una.proyectopreguntados.model.PreguntaDto;
import cr.ac.una.proyectopreguntados.util.EntityManagerHelper;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class PreguntaService {

    private EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public RespuestaEnt saveQuestion(PreguntaDto preguntaDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Pregunta pregunta;
            if (preguntaDto.getId() != null && preguntaDto.getId() > 0) {
                pregunta = em.find(Pregunta.class, preguntaDto.getId());
                if (pregunta == null) {
                    et.rollback();
                    return new RespuestaEnt(false, "No se encontró la pregunta a modificar.", "guardarPregunta NoResultException");
                }
                pregunta.actualizar(preguntaDto);
                pregunta = em.merge(pregunta);
            } else {
                pregunta = new Pregunta(preguntaDto);
                em.persist(pregunta);
            }
            et.commit();
            return new RespuestaEnt(true, "", "", "Pregunta", new PreguntaDto(pregunta));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la pregunta.", ex);
            return new RespuestaEnt(false, "Ocurrio un error al guardar la pregunta", "guardarPregunta " + ex.getMessage());
        }
    }
}
