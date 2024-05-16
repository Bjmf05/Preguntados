package cr.ac.una.proyectopreguntados.service;

import cr.ac.una.proyectopreguntados.model.Jugador;
import cr.ac.una.proyectopreguntados.model.JugadorDto;
import cr.ac.una.proyectopreguntados.util.EntityManagerHelper;
import cr.ac.una.proyectopreguntados.util.Respuesta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class JugadorService {

    private EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Respuesta savePlayer(JugadorDto jugadorDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Jugador jugador;
            if (jugadorDto.getId() != null && jugadorDto.getId() > 0) {
                jugador = em.find(Jugador.class, jugadorDto.getId());
                if (jugador == null) {
                    et.rollback();
                    return new Respuesta(false, "No se encontró el jugador a modificar.", "guardarJugador NoResultException");
                }
                jugador.actualizar(jugadorDto);
                jugador = em.merge(jugador);
            } else {
                jugador = new Jugador(jugadorDto);
                em.persist(jugador);
            }
            et.commit();
            return new Respuesta(true, "", "", "Jugador", new JugadorDto(jugador));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el jugador.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el jugador", "guardarJugador " + ex.getMessage());
        }
    }
     public Respuesta getAllPlayers() {
        try {
            Query query = em.createNamedQuery("Jugador.findAll",Jugador.class);
            List<Jugador> jugadores = (List<Jugador>) query.getResultList();
            List<JugadorDto> jugadorDto = new ArrayList<>();
            for (Jugador jug : jugadores) {
                jugadorDto.add(new JugadorDto(jug));
            }
            return new Respuesta(true, "", "", "Jugadores", jugadorDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existen Jugadores.", "getJugadores NoResultException");
        } catch (Exception ex) {
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Error obteniendo empleados.", ex);
            return new Respuesta(false, "Error obteniendo empleados.", "getJugadores " + ex.getMessage());
        }
    }
}
