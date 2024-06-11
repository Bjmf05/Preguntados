package cr.ac.una.proyectopreguntados.service;

import cr.ac.una.proyectopreguntados.model.*;
import cr.ac.una.proyectopreguntados.util.EntityManagerHelper;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                if (!partidaDto.getPreguntasEchas().isEmpty() || partidaDto.getPreguntasEchas() != null) {
                    for (PreguntaDto preguntaDto : partidaDto.getPreguntasEchas()) {
                        if (preguntaDto.isModificado()) {
                            Pregunta pregunta = em.find(Pregunta.class, preguntaDto.getId());
                            pregunta.actualizar(preguntaDto);
                            for (Respuesta respuesta : pregunta.getPlamRespuestasList()) {
                                Optional<RespuestaDto> resDto = preguntaDto.getRespuestasList().stream().filter(r -> r.getRespuestaPK().getId().equals(respuesta.getRespuestaPK().getId())).findFirst();
                                if (resDto.isPresent()) {
                                    respuesta.actualizar(resDto.get());
                                }
                            }
                            pregunta.getPartidasList().add(partida);
                            partida.getPreguntaList().add(pregunta);
                        }
                    }
                }
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

    public RespuestaEnt getGames(String id, String nombre, String cantidadJugadores, String dificultad, LocalDate fecha) {
        try {
            StringBuilder queryString = new StringBuilder("SELECT p FROM Partida p WHERE UPPER(p.id) LIKE :id AND UPPER(p.nombre) LIKE :nombre AND UPPER(p.dificultad) LIKE :dificultad AND UPPER(p.jugadores) LIKE :jugadores");
            if (fecha != null) {
                queryString.append(" AND p.fecha = :fecha");
            }
            Query query = em.createQuery(queryString.toString(), Partida.class);
            query.setParameter("id", id);
            query.setParameter("nombre", nombre);
            query.setParameter("jugadores", cantidadJugadores);
            query.setParameter("dificultad", dificultad);
            if (fecha != null) {
                query.setParameter("fecha", fecha);
            }
            List<Partida> games = (List<Partida>) query.getResultList();
            List<PartidaDto> gamesDto = new ArrayList<>();
            for (Partida game : games) {
                PartidaDto partidaDto = new PartidaDto(game);
                for (Pregunta pregunta : game.getPreguntaList()) {
                    partidaDto.getPreguntaList().add(new PreguntaDto(pregunta));
                }
                for (Competidor competidor : game.getCompetidorList()) {
                    partidaDto.getCompetidorList().add(new CompetidorDto(competidor));
                }
                gamesDto.add(partidaDto);
            }
            return new RespuestaEnt(true, "", "", "Games", gamesDto);
        } catch (NoResultException ex) {
            return new RespuestaEnt(false, "No se encontraron partidas con los criterios ingresados", "getGames NoResultException");
        } catch (Exception ex) {
            Logger.getLogger(PartidaService.class.getName()).log(Level.SEVERE, "Ocurrio un error obteniendo las partidas", ex);
            return new RespuestaEnt(false, "Ocurrio un error obteniendo las partidas", "getGames " + ex.getMessage());
        }

    }

    public RespuestaEnt getGame(Long id) {
        try {
            Query qryGame = em.createNamedQuery("Partida.findByPartId", Partida.class);
            qryGame.setParameter("id", id);
            Partida partida = (Partida) qryGame.getSingleResult();
            PartidaDto partidaDto = new PartidaDto(partida);
            for (Pregunta pregunta : partida.getPreguntaList()) {
                partidaDto.getPreguntaList().add(new PreguntaDto(pregunta));
            }
            for (Competidor competidor : partida.getCompetidorList()) {
                partidaDto.getCompetidorList().add(new CompetidorDto(competidor));
            }
            return new RespuestaEnt(true, "", "", "Game", partidaDto);
        } catch (NoResultException ex) {
            return new RespuestaEnt(false, "No existe una partida con las credenciales ingresadas.", "getGame NoResultException");
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(PreguntaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar la partida.", ex);
            return new RespuestaEnt(false, "Ocurrio un error al consultar la partida.", "getGame NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(PreguntaService.class.getName()).log(Level.SEVERE, "Error obteniendo la partida [" + id + "]", ex);
            return new RespuestaEnt(false, "Error obteniendo la partida.", "getPartida " + ex.getMessage());
        }
    }
}
