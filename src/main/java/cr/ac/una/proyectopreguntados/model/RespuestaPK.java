
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
public class RespuestaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "RES_ID_PREGUNTA")
    private Long idPregunta;
    @Basic(optional = false)
    @SequenceGenerator(name = "PLAM_PLAM_RESPUESTAS_ID_GENERATOR", sequenceName = "una.PLAM_RESPUESTAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_PLAM_RESPUESTAS_ID_GENERATOR")
    @Column(name = "RES_ID")
    private Long id;

    public RespuestaPK() {
    }

    public RespuestaPK(Long resIdPregunta, Long resId) {
        this.idPregunta = resIdPregunta;
        this.id = resId;
    }
    public RespuestaPK(Long resIdPregunta) {
        this.idPregunta = resIdPregunta;
    }
    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
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
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaPK)) {
            return false;
        }
        RespuestaPK other = (RespuestaPK) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.proyectopreguntados.model.PlamRespuestasPK[ resIdPregunta=" + idPregunta + ", resId=" + id + " ]";
    }
    
}
