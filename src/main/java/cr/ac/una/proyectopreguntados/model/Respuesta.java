package cr.ac.una.proyectopreguntados.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "PLAM_RESPUESTAS", schema = "UNA")
@NamedQueries({
    @NamedQuery(name = "Respuesta.findAll", query = "SELECT p FROM Respuesta p"), /*  @NamedQuery(name = "Respuesta.findByResIdPregunta", query = "SELECT p FROM PlamRespuestas p WHERE p.plamRespuestasPK.resIdPregunta = :resIdPregunta"),
        @NamedQuery(name = "Respuesta.findByResId", query = "SELECT p FROM PlamRespuestas p WHERE p.plamRespuestasPK.resId = :resId"),
        @NamedQuery(name = "Respuesta.findByResContenido", query = "SELECT p FROM PlamRespuestas p WHERE p.contenido = :contenido"),
        @NamedQuery(name = "Respuesta.findByResTipo", query = "SELECT p FROM PlamRespuestas p WHERE p.tipo = :tipo"),
        @NamedQuery(name = "Respuesta.findByResCantidadSelecciones", query = "SELECT p FROM PlamRespuestas p WHERE p.cantidadSelecciones = :cantidadSelecciones"),
        @NamedQuery(name = "Respuesta.findByResVersion", query = "SELECT p FROM PlamRespuestas p WHERE p.version = :version")*/})

public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespuestaPK RespuestaPK;
    @Basic(optional = false)
    @Column(name = "RES_CONTENIDO")
    private String contenido;
    @Basic(optional = false)
    @Column(name = "RES_TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "RES_CANTIDAD_SELECCIONES")
    private Long cantidadSelecciones;
    @Version
    @Column(name = "RES_VERSION")
    private Long version;
    @JoinColumn(name = "RES_ID_PREGUNTA", referencedColumnName = "PRE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pregunta pregunta;

    public Respuesta() {
    }

    public Respuesta(RespuestaPK RespuestaPK) {
        this.RespuestaPK = RespuestaPK;
    }

    public Respuesta(RespuestaDto respuestaDto) {
        this.RespuestaPK = respuestaDto.getRespuestaPK();
        actualizar(respuestaDto);
    }

    public void actualizar(RespuestaDto respuestaDto) {
        this.contenido = respuestaDto.getContenido();
        this.tipo = respuestaDto.getTipo();
        this.cantidadSelecciones = respuestaDto.getCantidadSelecciones();
        this.version = respuestaDto.getVersion();
    }

    public Respuesta(Long resIdPregunta, Long resId) {
        this.RespuestaPK = new RespuestaPK(resIdPregunta, resId);
    }

    public RespuestaPK getRespuestaPK() {
        return RespuestaPK;
    }

    public void setRespuestaPK(RespuestaPK RespuestaPK) {
        this.RespuestaPK = RespuestaPK;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getCantidadSelecciones() {
        return cantidadSelecciones;
    }

    public void setCantidadSelecciones(Long cantidadSelecciones) {
        this.cantidadSelecciones = cantidadSelecciones;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (RespuestaPK != null ? RespuestaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        if ((this.RespuestaPK == null && other.RespuestaPK != null) || (this.RespuestaPK != null && !this.RespuestaPK.equals(other.RespuestaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.proyectopreguntados.model.PlamRespuestas[ plamRespuestasPK=" + RespuestaPK + " ]";
    }

}
