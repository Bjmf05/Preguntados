package cr.ac.una.proyectopreguntados.model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author PC
 */
public class PreguntaDto implements Serializable {

    public SimpleStringProperty id;
    public SimpleStringProperty contenido;
    public SimpleStringProperty categoria;
    public SimpleBooleanProperty estado;
    public SimpleStringProperty cantidadLlamadas;
    public Long version;
    public boolean modificado;

    public PreguntaDto() {
        this.id = new SimpleStringProperty("");
        this.contenido = new SimpleStringProperty("");
        this.categoria = new SimpleStringProperty("");
        this.estado = new SimpleBooleanProperty(true);
        this.cantidadLlamadas = new SimpleStringProperty("");
    }

    public PreguntaDto(Pregunta pregunta) {
        this();
        this.id.set(pregunta.getId().toString());
        this.contenido.set(pregunta.getContenido());
        this.categoria.set(pregunta.getCategoria());
        this.estado.setValue(pregunta.getEstado().equalsIgnoreCase("A"));
        this.cantidadLlamadas.set(pregunta.getCantidadLlamadas().toString());
        this.version = pregunta.getVersion();
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
    
    public String getContenido() {
        return contenido.get();
    }

    public void setContenido(String contenido) {
        this.contenido.set(contenido);
    }

    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public String getEstado() {
       return estado.getValue()?"A":"I";
    }

    public void setEstado(String estado) {
        this.estado.setValue(estado.equalsIgnoreCase("A"));
    }

    public Long getCantidadLlamadas() {
        return Long.valueOf(cantidadLlamadas.get());
    }

    public void setCantidadLlamadas(Long cantidadLlamadas) {
        this.cantidadLlamadas.set(cantidadLlamadas.toString()); 
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "PreguntaDto{" + "id=" + id + ", contenido=" + contenido + ", categoria=" + categoria + ", estado=" + estado + ", cantidadLlamadas=" + cantidadLlamadas + ", version=" + version + ", modificado=" + modificado + '}';
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
        final PreguntaDto other = (PreguntaDto) obj;
        return Objects.equals(this.contenido, other.contenido)&&Objects.equals(this.id, other.id);
    }
    
}
