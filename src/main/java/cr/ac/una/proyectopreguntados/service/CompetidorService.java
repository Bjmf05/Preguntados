package cr.ac.una.proyectopreguntados.service;

import cr.ac.una.proyectopreguntados.model.Competidor;
import cr.ac.una.proyectopreguntados.model.CompetidorDto;
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
public class CompetidorService {
        private EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    
      public RespuestaEnt saveCompetitor(CompetidorDto competidorDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Competidor competidor;
            if (competidorDto.getCompetidorPK().getId() != null && competidorDto.getCompetidorPK().getId() > 0) {
                competidor = em.find(Competidor.class, competidorDto.getCompetidorPK());
                    if (competidor == null) {
                    et.rollback();
                    return new RespuestaEnt(false, "No se encontró competidor a modificar.", "guardarCompetidor NoResultException");
                }
                competidor.actualizar(competidorDto);
                competidor = em.merge(competidor);
            } else {
                competidor = new Competidor(competidorDto);
                em.persist(competidor);
            }
            et.commit();
            return new RespuestaEnt(true, "", "", "Competidor", new CompetidorDto(competidor));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar competidor.", ex);
            return new RespuestaEnt(false, "Ocurrio un error al guardar competidor.", "guardarCompetidor " + ex.getMessage());
        }
    }
    
}
