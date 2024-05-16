package cr.ac.una.proyectopreguntados.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "PLAM_PREGUNTAS", schema = "UNA")
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByPreId", query = "SELECT p FROM Pregunta p WHERE p.id = :id"),
    @NamedQuery(name = "Pregunta.findByPreContenido", query = "SELECT p FROM Pregunta p WHERE p.contenido = :contenido"),
    @NamedQuery(name = "Pregunta.findByPreCategoria", query = "SELECT p FROM Pregunta p WHERE p.categoria = :categoria"),
    @NamedQuery(name = "Pregunta.findByPreEstado", query = "SELECT p FROM Pregunta p WHERE p.estado = :estado"),
    @NamedQuery(name = "Pregunta.findByPreCantidadLlamadas", query = "SELECT p FROM Pregunta p WHERE p.cantidadLlamadas = :cantidadLlamadas"),
    @NamedQuery(name = "Pregunta.findByPreVersion", query = "SELECT p FROM Pregunta p WHERE p.version = :version")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PLAM_PLAM_PREGUNTAS_PRE_ID_GENERATOR", sequenceName = "una.PLAM_PREGUNTAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_PLAM_PREGUNTAS_PRE_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "PRE_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "PRE_CONTENIDO")
    private String contenido;
    @Basic(optional = false)
    @Column(name = "PRE_CATEGORIA")
    private String categoria;
    @Basic(optional = false)
    @Column(name = "PRE_ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "PRE_CANTIDAD_LLAMADAS")
    private Long cantidadLlamadas;
    @Version
    @Column(name = "PRE_VERSION")
    private Long version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta", fetch = FetchType.LAZY)
    private List<Respuesta> plamRespuestasList;

    public Pregunta() {
    }

    public Pregunta(Long id) {
        this.id = id;
    }

    public Pregunta(PreguntaDto preguntaDto) {
        this.id = preguntaDto.getId();
        actualizar(preguntaDto);
    }

    public void actualizar(PreguntaDto preguntaDto) {
        this.contenido = preguntaDto.getContenido();
        System.out.println(this.contenido);
        this.categoria = preguntaDto.getCategoria();
        this.estado = preguntaDto.getEstado();
        this.cantidadLlamadas = preguntaDto.getCantidadLlamadas();
        this.version = preguntaDto.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getCantidadLlamadas() {
        return cantidadLlamadas;
    }

    public void setCantidadLlamadas(Long cantidadLlamadas) {
        this.cantidadLlamadas = cantidadLlamadas;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Respuesta> getPlamRespuestasList() {
        return plamRespuestasList;
    }

    public void setPlamRespuestasList(List<Respuesta> plamRespuestasList) {
        this.plamRespuestasList = plamRespuestasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.proyectopreguntados.model.Pregunta[ preId=" + id + " ]";
    }

}
