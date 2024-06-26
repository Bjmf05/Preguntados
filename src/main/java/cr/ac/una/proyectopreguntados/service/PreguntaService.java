package cr.ac.una.proyectopreguntados.service;

import cr.ac.una.proyectopreguntados.model.Pregunta;
import cr.ac.una.proyectopreguntados.model.PreguntaDto;
import cr.ac.una.proyectopreguntados.model.Respuesta;
import cr.ac.una.proyectopreguntados.model.RespuestaDto;
import cr.ac.una.proyectopreguntados.util.EntityManagerHelper;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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

    public RespuestaEnt getQuestion(Long id) {
        try {
            Query qryPregunta = em.createNamedQuery("Pregunta.findById", Pregunta.class);
            qryPregunta.setParameter("id", id);
            Pregunta pregunta = (Pregunta) qryPregunta.getSingleResult();
            PreguntaDto preguntaDto = new PreguntaDto((pregunta));
            for (Respuesta respuesta : pregunta.getPlamRespuestasList()) {
                preguntaDto.getRespuestasList().add(new RespuestaDto(respuesta));
            }
            return new RespuestaEnt(true, "", "", "Pregunta", preguntaDto);
        } catch (NoResultException ex) {
            return new RespuestaEnt(false, "No existe una pregunta con las credenciales ingresadas.", "getPregunta NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(PreguntaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la pregunta.", ex);
            return new RespuestaEnt(false, "Ocurrio un error al consultar la pregunta.", "getPregunta NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(PreguntaService.class.getName()).log(Level.SEVERE, "Error obteniendo la pregunta [" + id + "]", ex);
            return new RespuestaEnt(false, "Error obteniendo la pregunta.", "getPregunta " + ex.getMessage());
        }
    }

    public RespuestaEnt getQuestions() {
        try {
            Query query = em.createNamedQuery("Pregunta.findAll", Pregunta.class);
            List<Pregunta> preguntas = (List<Pregunta>) query.getResultList();
            List<PreguntaDto> preguntasDto = new ArrayList<>();
            for (Pregunta pre : preguntas){
                if (pre.getEstado().equals("A")) {
                    PreguntaDto preg = new PreguntaDto(pre);
                    for (Respuesta respuesta : pre.getPlamRespuestasList()) {
                        preg.getRespuestasList().add(new RespuestaDto(respuesta));
                    }
                    preguntasDto.add(preg);
                }
            }
            return new RespuestaEnt(true, "", "", "Preguntas", preguntasDto);
        } catch (NoResultException ex) {
            return new RespuestaEnt(false, "No existen Preguntas.", "getPreguntas NoResultException");
        } catch (Exception ex) {
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error obteniendo Preguntas.", ex);
            return new RespuestaEnt(false, "Error obteniendo Preguntas.", "getPreguntas " + ex.getMessage());
        }
    }


    public RespuestaEnt getQuestionsParameters(String id, String contenido, String categoria, String estado) {
        try {
            Query query = em.createNamedQuery("Pregunta.findByIdContCatEsta", Pregunta.class);
            query.setParameter("id", id);
            query.setParameter("contenido", contenido);
            query.setParameter("categoria", categoria);
            query.setParameter("estado", estado);
            List<Pregunta> preguntas = (List<Pregunta>) query.getResultList();
            List<PreguntaDto> preguntaDto = new ArrayList<>();
            for (Pregunta pre : preguntas) {
                preguntaDto.add(new PreguntaDto(pre));
            }
            return new RespuestaEnt(true, "", "", "Preguntas", preguntaDto);
        } catch (NoResultException ex) {
            return new RespuestaEnt(false, "No existen Preguntas.", "getPreguntas NoResultException");
        } catch (Exception ex) {
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error obteniendo Preguntas.", ex);
            return new RespuestaEnt(false, "Error obteniendo Preguntas.", "getPreguntas " + ex.getMessage());
        }
    }

    public RespuestaEnt deleteQuestion(Long id) {
        try {
            et = em.getTransaction();
            et.begin();
            Pregunta pregunta;
            if (id != null && id > 0) {
                pregunta = em.find(Pregunta.class, id);
                if (pregunta == null) {
                    // et.rollback();
                    return new RespuestaEnt(false, "No se encrontró la pregunta a eliminar.", "eliminarPregunta NoResultException");
                }
                em.remove(pregunta);
            } else {
                // et.rollback();
                return new RespuestaEnt(false, "Favor consultar la pregunta a eliminar.", "");
            }
            et.commit();
            return new RespuestaEnt(true, "", "");
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(PreguntaService.class.getName()).log(Level.SEVERE, "Error eliminando la pregunta.", ex);
            return new RespuestaEnt(false, "Error eliminando la pregunta.", "eliminarPregunta " + ex.getMessage());
        }
    }
}
