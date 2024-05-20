package cr.ac.una.proyectopreguntados.service;

import cr.ac.una.proyectopreguntados.model.RespuestaDto;
import cr.ac.una.proyectopreguntados.util.EntityManagerHelper;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import cr.ac.una.proyectopreguntados.model.Respuesta;
import cr.ac.una.proyectopreguntados.model.RespuestaPK;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
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
            if (respuestaDto.getRespuestaPK().getId() != null && respuestaDto.getRespuestaPK().getId() > 0) {
                respuesta = em.find(Respuesta.class, respuestaDto.getRespuestaPK());
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

    public RespuestaEnt getAnswer(Long id) {
        try {
            Query qryRespuesta = em.createNamedQuery("Respuesta.findByResIdPregunta", Respuesta.class);
            qryRespuesta.setParameter("id", id);
            RespuestaDto respuestaDto = new RespuestaDto((Respuesta) qryRespuesta.getSingleResult());
            return new RespuestaEnt(true, "", "", "Respuesta", respuestaDto);
        } catch (NoResultException ex) {
            return new RespuestaEnt(false, "No existe una Respuesta con las credenciales ingresadas.", "getRespuesta NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(PreguntaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la Respuesta.", ex);
            return new RespuestaEnt(false, "Ocurrio un error al consultar la Respuesta.", "getRespuesta NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(PreguntaService.class.getName()).log(Level.SEVERE, "Error obteniendo la pregunta [" + id + "]", ex);
            return new RespuestaEnt(false, "Error obteniendo la Respuesta.", "getRespuesta " + ex.getMessage());
        }
    }
}
