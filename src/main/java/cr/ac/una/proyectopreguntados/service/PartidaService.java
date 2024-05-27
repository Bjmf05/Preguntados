package cr.ac.una.proyectopreguntados.service;

import cr.ac.una.proyectopreguntados.model.Partida;
import cr.ac.una.proyectopreguntados.model.PartidaDto;
import cr.ac.una.proyectopreguntados.util.EntityManagerHelper;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author PC
 */
public class PartidaService {
    private EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public RespuestaEnt saveGame(PartidaDto partidaDto) {
        try {
            et = em.getTransaction();
            et.begin();
            Partida partida;
            if (partidaDto.getId() != null && partidaDto.getId() > 0) {
                partida = em.find(Partida.class, partidaDto.getId());
                if (partida == null) {
                    et.rollback();
                    return new RespuestaEnt(false, "No se encontró partida a modificar.", "guardarPartida NoResultException");
                }
                partida.actualizar(partidaDto);
                partida = em.merge(partida);
            } else {
                partida = new Partida(partidaDto);
                em.persist(partida);
            }
            et.commit();
            return new RespuestaEnt(true, "", "", "Partida", new PartidaDto(partida));
        } catch (Exception ex) {
            et.rollback();
            Logger.getLogger(JugadorService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar la partida.", ex);
            return new RespuestaEnt(false, "Ocurrio un error al guardar la partida.", "guardarPartida " + ex.getMessage());
        }
    }

    public RespuestaEnt getGames(String id, String nombre, String cantidadJugadores, String dificultad, String fecha) {
        try {
            Query query = em.createNamedQuery("Partida.findByIdNomJugaFecha", Partida.class);
            query.setParameter("id", id);
            query.setParameter("nombre", nombre);
            query.setParameter("jugadores", cantidadJugadores);
            query.setParameter("dificultad", dificultad);
            query.setParameter("fecha", fecha);
            List<Partida> games = (List<Partida>) query.getResultList();
            List<PartidaDto> gamesDto = new ArrayList<>();
            for (Partida game : games) {
                gamesDto.add(new PartidaDto(game));
            }
            return new RespuestaEnt(true, "", "", "Games", gamesDto);
        } catch (NoResultException ex) {
            return new RespuestaEnt(false, "No se encontraron partidas con los criterios ingresados", "getGames NoResultException");
        } catch (Exception ex) {
            Logger.getLogger(PartidaService.class.getName()).log(Level.SEVERE, "Ocurrio un error obteniendo las partidas", ex);
            return new RespuestaEnt(false, "Ocurrio un error obteniendo las partidas", "getGames " + ex.getMessage());
        }

    }
}
