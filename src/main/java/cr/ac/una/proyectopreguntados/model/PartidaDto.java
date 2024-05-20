package cr.ac.una.proyectopreguntados.model;

import java.time.LocalDate;
import static java.time.LocalDate.now;
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
    private Long version;
    private boolean modificado;

    /*Sin base de datos*/
    private String tiempo;
    private Boolean limiteTiempo;

    public PartidaDto() {
        id = Long.valueOf(0);
        fecha = now();
        nombre = "";
        jugadores = Long.valueOf(0);
        modificado = false;
        tiempo = "";
        limiteTiempo = false;
    }

    public PartidaDto(Partida partida) {
        this();
        this.id = partida.getId();
        this.fecha = partida.getFecha();
        this.nombre = partida.getNombre();
        this.jugadores = partida.getJugadores();
        this.version = partida.getVersion();
    }

    public PartidaDto(String nombre, Long jugadores, Boolean limiteTiempo,String tiempo,LocalDate fecha) {   
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.tiempo = tiempo;
        this.limiteTiempo = limiteTiempo;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getLimiteTiempo() {
        return limiteTiempo ? "A" : "I";
    }

    public void setLimiteTiempo(String limiteTiempo) {
        if (limiteTiempo.equals("A")) {
            this.limiteTiempo = true;
        } else {
            this.limiteTiempo = false;
        }

    }

    @Override
    public String toString() {
        return "PartidaDto{" + "id=" + id + ", fecha=" + fecha + ", nombre=" + nombre + ", jugadores=" + jugadores + ", version=" + version + ", modificado=" + modificado + '}';
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
