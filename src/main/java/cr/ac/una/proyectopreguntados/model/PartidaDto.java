package cr.ac.una.proyectopreguntados.model;

import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class PartidaDto {

    private Long id;
    private LocalDate fecha;
    private String nombre;
    private Long jugadores;
    private String dificultad;
    private String tiempoLimite;
    private List<Pregunta> preguntaList;
    private List<Competidor> competidorList;
    private Long version;
    private boolean modificado;
    
    public PartidaDto() {
        id = Long.valueOf(0);
        fecha = now();
        nombre = "";
        jugadores = Long.valueOf(0);
        modificado = false;
        dificultad = "";
        tiempoLimite = null;
    }

    public PartidaDto(Partida partida) {
        this();
        this.id = partida.getId();
        this.fecha = partida.getFecha();
        this.nombre = partida.getNombre();
        this.jugadores = partida.getJugadores();
        this.tiempoLimite = partida.getTiempoLimite();
        this.dificultad = partida.getDificultad();
        this.preguntaList = partida.getPreguntaList();
        this.competidorList = partida.getCompetidorList();
        this.version = partida.getVersion();
    }

    public PartidaDto(String nombre, Long jugadores, String limiteTiempo, String dificultad, LocalDate fecha) {
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.dificultad = dificultad;
        this.tiempoLimite = limiteTiempo;
        this.fecha = fecha;
    }

    public Long getId() {
        if (id != null) {
            return id;
        } else {
            return null;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getJugadores() {
        return jugadores;
    }

    public void setJugadores(Long jugadores) {
        this.jugadores = jugadores;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(String tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public List<Competidor> getCompetidorList() {
        return competidorList;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    @Override
    public String toString() {
        return  id.toString() + fecha + nombre +  jugadores + dificultad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PartidaDto other = (PartidaDto) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.nombre, other.nombre);
    }

}
