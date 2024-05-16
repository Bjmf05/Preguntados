package cr.ac.una.proyectopreguntados.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class CompetidorDto implements Serializable {

    protected CompetidorPK competidorPK;
    private Long numeroJugador;
    private Long posicionFicha;
    private String turno;
    private String geografia;
    private String arte;
    private String ciencias;
    private String historia;
    private String entretenimiento;
    private String deporte;
    private Long version;
    private boolean modificado;

    public CompetidorDto() {
        this.competidorPK = new CompetidorPK();
        this.numeroJugador = Long.valueOf(0);
        this.posicionFicha = Long.valueOf(0);
        this.turno = "";
        this.geografia = "";
        this.arte = "";
        this.ciencias = "";
        this.historia = "";
        this.entretenimiento = "";
        this.deporte = "";
        this.modificado = false;
    }

    public CompetidorDto(Competidor competidor) {
        this();
        this.competidorPK = competidor.getCompetidorPK();
        this.numeroJugador = competidor.getNumeroJugador();
        this.posicionFicha = competidor.getPosicionFicha();
        this.turno = competidor.getTurno();
        this.geografia = competidor.getGeografia();
        this.arte = competidor.getArte();
        this.ciencias = competidor.getCiencias();
        this.historia = competidor.getHistoria();
        this.entretenimiento = competidor.getEntretenimiento();
        this.deporte = competidor.getDeporte();
        this.version = competidor.getVersion();
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "CompetidorDto{" + "competidorPK=" + competidorPK + ", numeroJugador=" + numeroJugador + ", posicionFicha=" + posicionFicha + ", turno=" + turno + ", geografia=" + geografia + ", arte=" + arte + ", ciencias=" + ciencias + ", historia=" + historia + ", entretenimiento=" + entretenimiento + ", deporte=" + deporte + ", version=" + version + ", modificado=" + modificado + '}';
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
        final CompetidorDto other = (CompetidorDto) obj;
        return Objects.equals(this.numeroJugador, other.numeroJugador)&&Objects.equals(this.competidorPK, other.competidorPK);
    }


}
