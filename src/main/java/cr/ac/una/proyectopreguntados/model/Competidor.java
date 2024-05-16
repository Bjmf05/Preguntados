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
@Table(name = "PLAM_COMPETIDORES", schema = "UNA")
@NamedQueries({
    @NamedQuery(name = "Competidor.findAll", query = "SELECT c FROM Competidor c"),
  /*  @NamedQuery(name = "Competidor.findByComIdPartida", query = "SELECT c FROM Competidor c WHERE c.competidorPK.comIdPartida = :comIdPartida"),
    @NamedQuery(name = "Competidor.findByComIdJugador", query = "SELECT c FROM Competidor c WHERE c.competidorPK.comIdJugador = :comIdJugador"),
    @NamedQuery(name = "Competidor.findByComId", query = "SELECT c FROM Competidor c WHERE c.competidorPK.comId = :comId"),
    @NamedQuery(name = "Competidor.findByComNumeroJugador", query = "SELECT c FROM Competidor c WHERE c.numeroJugador = :numeroJugador"),
    @NamedQuery(name = "Competidor.findByComPosicionFicha", query = "SELECT c FROM Competidor c WHERE c.posicionFicha = :posicionFicha"),
    @NamedQuery(name = "Competidor.findByComTurno", query = "SELECT c FROM Competidor c WHERE c.turno = :turno"),
    @NamedQuery(name = "Competidor.findByComGeografia", query = "SELECT c FROM Competidor c WHERE c.geografia = :geografia"),
    @NamedQuery(name = "Competidor.findByComArte", query = "SELECT c FROM Competidor c WHERE c.arte = :arte"),
    @NamedQuery(name = "Competidor.findByComCiencias", query = "SELECT c FROM Competidor c WHERE c.ciencias = :ciencias"),
    @NamedQuery(name = "Competidor.findByComHistoria", query = "SELECT c FROM Competidor c WHERE c.historia = :historia"),
    @NamedQuery(name = "Competidor.findByComEntretenimiento", query = "SELECT c FROM Competidor c WHERE c.entretenimiento = :entretenimiento"),
    @NamedQuery(name = "Competidor.findByComDeporte", query = "SELECT c FROM Competidor c WHERE c.deporte = :deporte"),
    @NamedQuery(name = "Competidor.findByComVersion", query = "SELECT c FROM Competidor c WHERE c.version = :version")*/})
public class Competidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompetidorPK competidorPK;
    @Basic(optional = false)
    @Column(name = "COM_NUMERO_JUGADOR")
    private Long numeroJugador;
    @Basic(optional = false)
    @Column(name = "COM_POSICION_FICHA")
    private Long posicionFicha;
    @Basic(optional = false)
    @Column(name = "COM_TURNO")
    private String turno;
    @Basic(optional = false)
    @Column(name = "COM_GEOGRAFIA")
    private String geografia;
    @Basic(optional = false)
    @Column(name = "COM_ARTE")
    private String arte;
    @Basic(optional = false)
    @Column(name = "COM_CIENCIAS")
    private String ciencias;
    @Basic(optional = false)
    @Column(name = "COM_HISTORIA")
    private String historia;
    @Basic(optional = false)
    @Column(name = "COM_ENTRETENIMIENTO")
    private String entretenimiento;
    @Basic(optional = false)
    @Column(name = "COM_DEPORTE")
    private String deporte;
    @Version
    @Column(name = "COM_VERSION")
    private Long version;
    @JoinColumn(name = "COM_ID_JUGADOR", referencedColumnName = "JUG_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Jugador jugador;
    @JoinColumn(name = "COM_ID_PARTIDA", referencedColumnName = "PART_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Partida partida;

    public Competidor() {
    }

    public Competidor(CompetidorPK competidorPK) {
        this.competidorPK = competidorPK;
    }

    public Competidor(CompetidorDto competidorDto) {
        this.competidorPK = competidorDto.getCompetidorPK();
        actualizar(competidorDto);
    }

    private void actualizar(CompetidorDto competidorDto) {
        this.numeroJugador = competidorDto.getNumeroJugador();
        this.posicionFicha = competidorDto.getPosicionFicha();
        this.turno = competidorDto.getTurno();
        this.geografia = competidorDto.getGeografia();
        this.arte = competidorDto.getArte();
        this.ciencias = competidorDto.getCiencias();
        this.historia = competidorDto.getHistoria();
        this.entretenimiento = competidorDto.getEntretenimiento();
        this.deporte = competidorDto.getDeporte();
        this.version = competidorDto.getVersion();
    }

    public Competidor(Long comIdPartida, Long comIdJugador, Long comId) {
        this.competidorPK = new CompetidorPK(comIdPartida, comIdJugador, comId);
    }

    public CompetidorPK getCompetidorPK() {
        return competidorPK;
    }

    public void setCompetidorPK(CompetidorPK competidorPK) {
        this.competidorPK = competidorPK;
    }

    public Long getNumeroJugador() {
        return numeroJugador;
    }

    public void setNumeroJugador(Long numeroJugador) {
        this.numeroJugador = numeroJugador;
    }

    public Long getPosicionFicha() {
        return posicionFicha;
    }

    public void setPosicionFicha(Long posicionFicha) {
        this.posicionFicha = posicionFicha;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

   

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getGeografia() {
        return geografia;
    }

    public void setGeografia(String geografia) {
        this.geografia = geografia;
    }

    public String getArte() {
        return arte;
    }

    public void setArte(String arte) {
        this.arte = arte;
    }

    public String getCiencias() {
        return ciencias;
    }

    public void setCiencias(String ciencias) {
        this.ciencias = ciencias;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getEntretenimiento() {
        return entretenimiento;
    }

    public void setEntretenimiento(String entretenimiento) {
        this.entretenimiento = entretenimiento;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }


    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competidorPK != null ? competidorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competidor)) {
            return false;
        }
        Competidor other = (Competidor) object;
        if ((this.competidorPK == null && other.competidorPK != null) || (this.competidorPK != null && !this.competidorPK.equals(other.competidorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.proyectopreguntados.model.Competidor[ competidorPK=" + competidorPK + " ]";
    }

}
