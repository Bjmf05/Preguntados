package cr.ac.una.proyectopreguntados.model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class RespuestaDto implements Serializable {

    protected RespuestaPK RespuestaPK;
    private SimpleStringProperty contenido;
    private SimpleStringProperty tipo;
    private SimpleStringProperty cantidadSelecciones;
    private Long version;
    private boolean modificado;

    public RespuestaDto() {
        this.RespuestaPK = new RespuestaPK();
        this.contenido = new SimpleStringProperty("");
        this.tipo = new SimpleStringProperty("");
        this.cantidadSelecciones = new SimpleStringProperty("");
        this.modificado = false;
    }

    public RespuestaDto(Respuesta respuesta) {
        this();
        this.RespuestaPK = respuesta.getRespuestaPK();
        this.contenido.set(respuesta.getContenido());
        this.tipo.set(respuesta.getTipo());
        this.cantidadSelecciones.set(respuesta.getCantidadSelecciones().toString());
        this.version = respuesta.getVersion();
    }

    public RespuestaPK getRespuestaPK() {
        return RespuestaPK;
    }

    public void setRespuestaPK(RespuestaPK RespuestaPK) {
        this.RespuestaPK = RespuestaPK;
    }

    public String getContenido() {
        return contenido.get();
    }

    public void setContenido(String contenido) {
        this.contenido.set(contenido);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public Long getCantidadSelecciones() {
        return Long.valueOf(cantidadSelecciones.get());
    }

    public void setCantidadSelecciones(Long cantidadSelecciones) {
        this.cantidadSelecciones.set(cantidadSelecciones.toString());
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "RespuestaDto{" + "RespuestaPK=" + RespuestaPK + ", contenido=" + contenido + ", tipo=" + tipo + ", cantidadSelecciones=" + cantidadSelecciones + ", version=" + version + ", modificado=" + modificado + '}';
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
        final RespuestaDto other = (RespuestaDto) obj;
        return Objects.equals(this.RespuestaPK, other.RespuestaPK) && Objects.equals(this.contenido.get(), other.contenido.get());
    }

}
