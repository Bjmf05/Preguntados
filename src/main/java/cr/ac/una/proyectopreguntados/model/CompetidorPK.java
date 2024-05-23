package cr.ac.una.proyectopreguntados.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import java.io.Serializable;

/**
 *
 * @author PC
 */
@Embeddable
public class CompetidorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "COM_ID_PARTIDA")
    private Long idPartida;
    @Basic(optional = false)
    @Column(name = "COM_ID_JUGADOR")
    private Long idJugador;
    @Basic(optional = false)
    @SequenceGenerator(name = "PLAM_COMPETIDORES_COM_ID_GENERATOR", sequenceName = "una.PLAM_COMPETIDORES_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_COMPETIDORES_COM_ID_GENERATOR")
    @Column(name = "COM_ID")
    private Long id;

    public CompetidorPK() {
    }

    public CompetidorPK(Long idPartida, Long idJugador, Long id) {
        this.idPartida = idPartida;
        this.idJugador = idJugador;
        this.id = id;
    }

    public CompetidorPK(Long idPartida, Long idJugador) {
        this.idPartida = idPartida;
        this.idJugador = idJugador;
    }
    

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        hash += (idJugador != null ? idJugador.hashCode() : 0);
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetidorPK)) {
            return false;
        }
        CompetidorPK other = (CompetidorPK) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        if ((this.idJugador == null && other.idJugador != null) || (this.idJugador != null && !this.idJugador.equals(other.idJugador))) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.proyectopreguntados.model.CompetidorPK[ comIdPartida=" + idPartida + ", comIdJugador=" + idJugador + ", comId=" + id + " ]";
    }

}
