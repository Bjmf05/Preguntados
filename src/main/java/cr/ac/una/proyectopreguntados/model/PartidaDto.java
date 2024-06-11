package cr.ac.una.proyectopreguntados.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

import static java.time.LocalDate.now;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author PC
 */
public class PartidaDto {

    private Long id;
    private LocalDate fecha;
    private String nombre;
    private Long jugadores;
    private String dificultad;
    private String tiempoLimite;
    private List<PreguntaDto> preguntaList;
    private List<CompetidorDto> competidorList;
    private List<PreguntaDto> preguntasEchas;
    private Integer ronda;
    private String estado;
    private Long version;
    private boolean modificado;

    public PartidaDto() {
        this.id = Long.valueOf(0);
        this.fecha = now();
        this.nombre = "";
        this.jugadores = Long.valueOf(0);
        this.modificado = false;
        this.dificultad = "";
        this.ronda = 1;
        this.estado = "A";
        this.tiempoLimite = null;
        this.preguntasEchas = new ArrayList<>();
        this.preguntaList = new ArrayList<>();
        this.competidorList = new ArrayList<>();
    }

    public PartidaDto(Partida partida) {
        this();
        this.id = partida.getId();
        this.fecha = partida.getFecha();
        this.nombre = partida.getNombre();
        this.jugadores = partida.getJugadores();
        this.tiempoLimite = partida.getTiempoLimite();
        this.dificultad = partida.getDificultad();
        this.ronda = partida.getRonda();
        this.estado = partida.getEstado();
        this.version = partida.getVersion();
    }

    public PartidaDto(String nombre, Long jugadores, String limiteTiempo, String dificultad, LocalDate fecha) {
        this();
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

    public List<PreguntaDto> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<PreguntaDto> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public List<CompetidorDto> getCompetidorList() {
        return competidorList;
    }

    public List<PreguntaDto> getPreguntasEchas() {
        return preguntasEchas;
    }

    public void setPreguntasEchas(ObservableList<PreguntaDto> preguntasEchas) {
        this.preguntasEchas = preguntasEchas;
    }

    public Integer getRonda() {
        return ronda;
    }

    public void setRonda(Integer ronda) {
        this.ronda = ronda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    @Override
    public String toString() {
        return id.toString() + fecha + nombre + jugadores + dificultad;
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
