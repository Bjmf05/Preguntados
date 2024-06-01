package cr.ac.una.proyectopreguntados.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author PC
 */
public class CompetidorDto implements Serializable {

    protected CompetidorPK competidorPK;
    private Long numeroJugador;
    private Long posicionFicha;
    private String ficha;
    private String turno;
    private String geografia;
    private String arte;
    private String ciencias;
    private String historia;
    private String entretenimiento;
    private String deporte;
    private Long comodinDoble;
    private Long comodinPasar;
    private Long comodinBomba;
    private Long comodinTiro;
    private Long version;
    private Jugador jugador;
    private boolean modificado;

    public CompetidorDto() {
        this.competidorPK = new CompetidorPK();
        this.numeroJugador = Long.valueOf(0);
        this.ficha = "";
        this.posicionFicha = 1L;
        this.turno = "O";
        this.geografia = "I";
        this.arte = "I";
        this.ciencias = "I";
        this.historia = "I";
        this.entretenimiento = "I";
        this.deporte = "I";
        this.comodinDoble = 0L;
        this.comodinPasar = 0L;
        this.comodinBomba = 0L;
        this.comodinTiro = 0L;
        this.modificado = false;
    }

    public CompetidorDto(Competidor competidor) {
        this();
        this.competidorPK = competidor.getCompetidorPK();
        this.numeroJugador = competidor.getNumeroJugador();
        this.posicionFicha = competidor.getPosicionFicha();
        this.ficha = competidor.getFicha();
        this.turno = competidor.getTurno();
        this.geografia = competidor.getGeografia();
        this.arte = competidor.getArte();
        this.ciencias = competidor.getCiencias();
        this.historia = competidor.getHistoria();
        this.entretenimiento = competidor.getEntretenimiento();
        this.deporte = competidor.getDeporte();
        this.comodinBomba = competidor.getComodinBomba();
        this.comodinDoble = competidor.getComodinDoble();
        this.comodinPasar = competidor.getComodinPasar();
        this.comodinTiro = competidor.getComodinTiro();
        this.jugador = competidor.getJugador();
        this.version = competidor.getVersion();
    }

    public CompetidorDto(CompetidorPK competidorPK, int numeroJugador, int ayuda, String ficha) {
        this();
        this.competidorPK = competidorPK;
        this.numeroJugador = Long.valueOf(numeroJugador);
        this.ficha = ficha;
        this.comodinBomba = Long.valueOf(ayuda);
        this.comodinDoble = Long.valueOf(ayuda);
        this.comodinPasar = Long.valueOf(ayuda);
        this.comodinTiro = Long.valueOf(ayuda);

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

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
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

    public Long getComodinDoble() {
        return comodinDoble;
    }

    public void setComodinDoble(Long comodinDoble) {
        this.comodinDoble = comodinDoble;
    }

    public Long getComodinPasar() {
        return comodinPasar;
    }

    public void setComodinPasar(Long comodinPasar) {
        this.comodinPasar = comodinPasar;
    }

    public Long getComodinBomba() {
        return comodinBomba;
    }

    public void setComodinBomba(Long comodinBomba) {
        this.comodinBomba = comodinBomba;
    }

    public Long getComodinTiro() {
        return comodinTiro;
    }

    public void setComodinTiro(Long comodinTiro) {
        this.comodinTiro = comodinTiro;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Jugador getJugador() {
        return jugador;
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
        return Objects.equals(this.numeroJugador, other.numeroJugador) && Objects.equals(this.competidorPK, other.competidorPK);
    }

}
