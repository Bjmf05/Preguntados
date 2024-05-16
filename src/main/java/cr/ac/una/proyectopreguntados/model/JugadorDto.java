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
    public SimpleStringProperty partidasJugadas;
    public SimpleStringProperty partidasGanadas;
    public Long version;
    public boolean modificado;

    public JugadorDto() {
        this.id = new SimpleStringProperty("");
        this.nombre = new SimpleStringProperty("");
        this.partidasJugadas = new SimpleStringProperty("");
        this.partidasGanadas = new SimpleStringProperty("");
        this.modificado=false;
    }
        public JugadorDto(Jugador jugador) {
        this();
        this.id.set(jugador.getId().toString());
        this.nombre.set(jugador.getNombre());
        this.partidasJugadas.set(jugador.getPartidasJugadas().toString());
        this.partidasGanadas.set( jugador.getPartidasGanadas().toString());
        this.version = jugador.getVersion();
    }

    public Long getId() {
          if(id.get()!=null && !id.get().isEmpty())
            return Long.valueOf(id.get());
        else
            return null;
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
                  if(partidasJugadas.get()!=null && !partidasJugadas.get().isEmpty())
            return Long.valueOf(partidasJugadas.get());
        else
            return null;
    }

    public void setPartidasJugadas(Long partidasJugadas) {
        this.partidasJugadas.set(partidasJugadas.toString());
    }

    public Long getPartidasGanadas() {

                          if(partidasGanadas.get()!=null && !partidasGanadas.get().isEmpty())
            return Long.valueOf(partidasGanadas.get());
        else
            return null;
    }

    public void setPartidasGanadas(Long partidasGanadas) {
        this.partidasGanadas.set( partidasGanadas.toString());
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
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
        return  nombre.get();
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
