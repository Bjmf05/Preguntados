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
@Table(name = "PLAM_JUGADORES", schema = "UNA")
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
    @NamedQuery(name = "Jugador.findByJugId", query = "SELECT j FROM Jugador j WHERE j.id = :id"),
    @NamedQuery(name = "Jugador.findByJugNombre", query = "SELECT j FROM Jugador j WHERE j.nombre = :nombre"), /*@NamedQuery(name = "Jugador.findByJugPartidasJugadas", query = "SELECT j FROM Jugador j WHERE j.jugPartidasJugadas = :jugPartidasJugadas"),
    @NamedQuery(name = "Jugador.findByJugPartidasGanadas", query = "SELECT j FROM Jugador j WHERE j.jugPartidasGanadas = :jugPartidasGanadas"),
     @NamedQuery(name = "Jugador.findByJugCantidadPreguntas", query = "SELECT j FROM Jugador j WHERE j.jugCantidadPreguntas = :jugCantidadPreguntas"),
    @NamedQuery(name = "Jugador.findByJugCantidadAciertos", query = "SELECT j FROM Jugador j WHERE j.jugCantidadAciertos = :jugCantidadAciertos"),
    @NamedQuery(name = "Jugador.findByJugCantidadADeporte", query = "SELECT j FROM Jugador j WHERE j.jugCantidadADeporte = :jugCantidadADeporte"),
    @NamedQuery(name = "Jugador.findByJugCantidadAHistoria", query = "SELECT j FROM Jugador j WHERE j.jugCantidadAHistoria = :jugCantidadAHistoria"),
    @NamedQuery(name = "Jugador.findByJugCantidadAArte", query = "SELECT j FROM Jugador j WHERE j.jugCantidadAArte = :jugCantidadAArte"),
    @NamedQuery(name = "Jugador.findByJugCantidadACiencia", query = "SELECT j FROM Jugador j WHERE j.jugCantidadACiencia = :jugCantidadACiencia"),
    @NamedQuery(name = "Jugador.findByJugCantidadAGeografia", query = "SELECT j FROM Jugador j WHERE j.jugCantidadAGeografia = :jugCantidadAGeografia"),
    @NamedQuery(name = "Jugador.findByJugCantidadAEntretenimiento", query = "SELECT j FROM Jugador j WHERE j.jugCantidadAEntretenimiento = :jugCantidadAEntretenimiento"),
    @NamedQuery(name = "Jugador.findByJugVersion", query = "SELECT j FROM Jugador j WHERE j.version = :version")*/})
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "PLAM_PLAM_JUGADORES_JUG_ID_GENERATOR", sequenceName = "una.PLAM_JUGADORES_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_PLAM_JUGADORES_JUG_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "JUG_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "JUG_NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "JUG_PARTIDAS_JUGADAS")
    private Long partidasJugadas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "JUG_PARTIDAS_GANADAS")
    private Long partidasGanadas;
    @Basic(optional = false)
    @Column(name = "JUG_CANTIDAD_PREGUNTAS")
    private Long cantidadPreguntas;
    @Basic(optional = false)
    @Column(name = "JUG_CANTIDAD_ACIERTOS")
    private Long cantidadAciertos;
    @Basic(optional = false)
    @Column(name = "JUG_CANTIDAD_A_DEPORTE")
    private Long cantidadADeporte;
    @Basic(optional = false)
    @Column(name = "JUG_CANTIDAD_A_HISTORIA")
    private Long cantidadAHistoria;
    @Basic(optional = false)
    @Column(name = "JUG_CANTIDAD_A_ARTE")
    private Long cantidadAArte;
    @Basic(optional = false)
    @Column(name = "JUG_CANTIDAD_A_CIENCIA")
    private Long cantidadACiencia;
    @Basic(optional = false)
    @Column(name = "JUG_CANTIDAD_A_GEOGRAFIA")
    private Long cantidadAGeografia;
    @Basic(optional = false)
    @Column(name = "JUG_CANTIDAD_A_ENTRETENIMIENTO")
    private Long cantidadAEntretenimiento;
    @Version
    @Column(name = "JUG_VERSION")
    private Long version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jugador", fetch = FetchType.LAZY)
    private List<Competidor> competidorList;

    public Jugador() {
    }

    public Jugador(Long id) {
        this.id = id;
    }

    public Jugador(JugadorDto jugadorDto) {
        this.id = jugadorDto.getId();
        actualizar(jugadorDto);
    }

    public void actualizar(JugadorDto jugadorDto) {
        this.nombre = jugadorDto.getNombre();
        this.partidasJugadas = jugadorDto.getPartidasJugadas();
        this.partidasGanadas = jugadorDto.getPartidasGanadas();
        this.cantidadPreguntas = jugadorDto.getCantidadPreguntas();
        this.cantidadAciertos = jugadorDto.getCantidadAciertos();
        this.cantidadADeporte = jugadorDto.getCantidadADeporte();
        this.cantidadAHistoria = jugadorDto.getCantidadAHistoria();
        this.cantidadAArte = jugadorDto.getCantidadAArte();
        this.cantidadACiencia = jugadorDto.getCantidadACiencia();
        this.cantidadAGeografia = jugadorDto.getCantidadAGeografia();
        this.cantidadAEntretenimiento = jugadorDto.getCantidadAEntretenimiento();
        this.version = jugadorDto.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(Long partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public Long getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(Long partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
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

    public List<Competidor> getCompetidorList() {
        return competidorList;
    }

    public void setCompetidorList(List<Competidor> competidorList) {
        this.competidorList = competidorList;
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
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.proyectopreguntados.model.Jugador[ jugId=" + id + " ]";
    }


}
