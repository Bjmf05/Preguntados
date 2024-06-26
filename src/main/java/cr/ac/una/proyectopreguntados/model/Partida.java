package cr.ac.una.proyectopreguntados.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "PLAM_PARTIDAS", schema = "UNA")
@NamedQueries({
    @NamedQuery(name = "Partida.findAll", query = "SELECT p FROM Partida p"),
    @NamedQuery(name = "Partida.findByPartId", query = "SELECT p FROM Partida p WHERE p.id = :id",hints = @QueryHint(name= "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "Partida.findByPartFecha", query = "SELECT p FROM Partida p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Partida.findByPartNombre", query = "SELECT p FROM Partida p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Partida.findByPartJugadores", query = "SELECT p FROM Partida p WHERE p.jugadores = :jugadores"),
    @NamedQuery(name = "Partida.findByIdNomJugaFecha", query = "SELECT p FROM Partida p WHERE UPPER(p.id) like :id and UPPER(p.nombre) LIKE :nombre and UPPER(p.dificultad) LIKE :dificultad and UPPER(p.jugadores)  like :jugadores and p.fecha = :fecha",hints = @QueryHint(name= "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "Partida.findByPartVersion", query = "SELECT p FROM Partida p WHERE p.version = :version")})
public class Partida implements Serializable {



    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PLAM_PARTIDAS_PART_ID_GENERATOR", sequenceName = "una.PLAM_PARTIDAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAM_PARTIDAS_PART_ID_GENERATOR")

    @Basic(optional = false)
    @Column(name = "PART_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "PART_NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "PART_DIFICULTAD")
    private String dificultad;
    @Column(name = "PART_TIEMPO_LIMITE")
    private String tiempoLimite;
    @Basic(optional = false)
    @Column(name = "PART_FECHA")
    private LocalDate fecha;
    @Basic(optional = false)
    @Column(name = "PART_JUGADORES")
    private Long jugadores;
    @Basic(optional = false)
    @Column(name = "PART_ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "PART_RONDA")
    private Integer ronda;
    @Version
    @Column(name = "PART_VERSION")
    private Long version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partida", fetch = FetchType.LAZY)
    private List<Competidor> competidorList;
    @JoinTable(name = "PLAM_PREGUNTAS_PARTIDAS", joinColumns = {
            @JoinColumn(name = "EXT_ID_PARTIDA", referencedColumnName = "PART_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "EXT_ID_PREGUNTA", referencedColumnName = "PRE_ID")})
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<Pregunta> preguntaList;

    public Partida() {
        this.preguntaList = new ArrayList<>();
        this.competidorList = new ArrayList<>();
    }

    public Partida(Long id) {
        this.id = id;
    }

    public Partida(PartidaDto partidaDto) {
        this.id = partidaDto.getId();
        actualizar(partidaDto);
    }

    public void actualizar(PartidaDto partidaDto) {
        this.fecha = partidaDto.getFecha();
        this.nombre = partidaDto.getNombre();
        this.jugadores = partidaDto.getJugadores();
        this.tiempoLimite = partidaDto.getTiempoLimite();
        this.dificultad = partidaDto.getDificultad();
        this.ronda = partidaDto.getRonda();
        this.estado = partidaDto.getEstado();
        this.version = partidaDto.getVersion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getJugadores() {
        return jugadores;
    }

    public void setJugadores(Long jugadores) {
        this.jugadores = jugadores;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(String tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getRonda() {
        return ronda;
    }

    public void setRonda(Integer ronda) {
        this.ronda = ronda;
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

    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
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
        if (!(object instanceof Partida)) {
            return false;
        }
        Partida other = (Partida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.proyectopreguntados.model.Partida[ partId=" + id + " ]";
    }
    }