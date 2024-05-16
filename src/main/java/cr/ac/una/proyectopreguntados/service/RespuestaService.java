package cr.ac.una.proyectopreguntados.service;

import cr.ac.una.proyectopreguntados.model.RespuestaDto;
import cr.ac.una.proyectopreguntados.util.EntityManagerHelper;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import cr.ac.una.proyectopreguntados.model.Respuesta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class RespuestaService {

    private EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public RespuestaEnt saveAnswer(RespuestaDto respuestaDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Respuesta respuesta;
            if (respuestaDto.getId() != null && respuestaDto.getId() > 0) {
                respuesta = em.find(Respuesta.class, respuestaDto.getId());
                if (respuesta == null) {
                    et.rollback();
                    return new RespuestaEnt(false, "No se encontró la respuesta a modificar.", "guardarRespuesta NoResultException");
                }
                respuesta.actualizar(respuestaDto);
                respuesta = em.merge(respuesta);
            } else {
                respuesta = new Respuesta(respuestaDto);
                em.persist(respuesta);
            }
            et.commit();
            return new RespuestaEnt(true, "", "", "Respuesta", new RespuestaDto(respuesta));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la respuesta.", ex);
            return new RespuestaEnt(false, "Ocurrio un error al guardar la respuesta", "guardarRespuesta " + ex.getMessage());
        }
    }
}
