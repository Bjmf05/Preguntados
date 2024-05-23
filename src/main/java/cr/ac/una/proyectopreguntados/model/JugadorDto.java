package cr.ac.una.proyectopreguntados.model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class JugadorDto implements Serializable {

    public SimpleStringProperty id;
    public SimpleStringProperty nombre;
    public Long partidasJugadas;
    public Long partidasGanadas;
    private Long cantidadPreguntas;
    private Long cantidadAciertos;
    private Long cantidadADeporte;
    private Long cantidadAHistoria;
    private Long cantidadAArte;
    private Long cantidadACiencia;
    private Long cantidadAGeografia;
    private Long cantidadAEntretenimiento;
    public Long version;
    public boolean modificado;

    public JugadorDto() {
        this.id = new SimpleStringProperty("");
        this.nombre = new SimpleStringProperty("");
        this.partidasJugadas = 0L;
        this.partidasGanadas = 0L;
        this.cantidadAciertos = 0L;
        this.cantidadADeporte = 0L;
        this.cantidadAHistoria = 0L;
        this.cantidadAArte = 0L;
        this.cantidadACiencia = 0L;
        this.cantidadAGeografia = 0L;
        this.cantidadAEntretenimiento = 0L;
        this.modificado = false;
    }

    public JugadorDto(Jugador jugador) {
        this();
        this.id.set(jugador.getId().toString());
        this.nombre.set(jugador.getNombre());
        this.partidasJugadas = jugador.getPartidasJugadas();
        this.partidasGanadas = jugador.getPartidasGanadas();
        this.cantidadAciertos = jugador.getCantidadAciertos();
        this.cantidadADeporte = jugador.getCantidadADeporte();
        this.cantidadAHistoria = jugador.getCantidadAHistoria();
        this.cantidadAArte = jugador.getCantidadAArte();
        this.cantidadACiencia = jugador.getCantidadACiencia();
        this.cantidadAGeografia = jugador.getCantidadAGeografia();
        this.cantidadAEntretenimiento = jugador.getCantidadAEntretenimiento();
        this.version = jugador.getVersion();
    }

    public Long getId() {
        if (id.get() != null && !id.get().isEmpty()) {
            return Long.valueOf(id.get());
        } else {
            return null;
        }
    }

    public void setId(Long id) {
        this.id.set(id.toString());
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public Long getPartidasJugadas() {

            return partidasJugadas;
        
    }

    public void setPartidasJugadas(Long partidasJugadas) {
        this.partidasJugadas=partidasJugadas;
    }

    public Long getPartidasGanadas() {

        
            return partidasGanadas;
        
    }

    public void setPartidasGanadas(Long partidasGanadas) {
        this.partidasGanadas=partidasGanadas;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public Long getCantidadPreguntas() {
        return cantidadPreguntas;
    }

    public void setCantidadPreguntas(Long cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
    }

    public Long getCantidadAciertos() {
        return cantidadAciertos;
    }

    public void setCantidadAciertos(Long cantidadAciertos) {
        this.cantidadAciertos = cantidadAciertos;
    }

    public Long getCantidadADeporte() {
        return cantidadADeporte;
    }

    public void setCantidadADeporte(Long cantidadADeporte) {
        this.cantidadADeporte = cantidadADeporte;
    }

    public Long getCantidadAHistoria() {
        return cantidadAHistoria;
    }

    public void setCantidadAHistoria(Long cantidadAHistoria) {
        this.cantidadAHistoria = cantidadAHistoria;
    }

    public Long getCantidadAArte() {
        return cantidadAArte;
    }

    public void setCantidadAArte(Long cantidadAArte) {
        this.cantidadAArte = cantidadAArte;
    }

    public Long getCantidadACiencia() {
        return cantidadACiencia;
    }

    public void setCantidadACiencia(Long cantidadACiencia) {
        this.cantidadACiencia = cantidadACiencia;
    }

    public Long getCantidadAGeografia() {
        return cantidadAGeografia;
    }

    public void setCantidadAGeografia(Long cantidadAGeografia) {
        this.cantidadAGeografia = cantidadAGeografia;
    }

    public Long getCantidadAEntretenimiento() {
        return cantidadAEntretenimiento;
    }

    public void setCantidadAEntretenimiento(Long cantidadAEntretenimiento) {
        this.cantidadAEntretenimiento = cantidadAEntretenimiento;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return nombre.get();
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
        final JugadorDto other = (JugadorDto) obj;
        return Objects.equals(this.id.get(), other.id.get()) && Objects.equals(this.nombre.get(), other.nombre.get());
    }

}
