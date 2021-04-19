package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Lugar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaCreacion;

    @Column(nullable = false)
    private Float latitud;

    @Column(nullable = false)
    private Float longitud;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAprobacion;

    private Boolean estado;

    @ElementCollection
    @JoinColumn(nullable = false)
    @Column(name="url_imagen")
    private List<String> imagenes;

    @ElementCollection
    @CollectionTable(
            name = "lugar_telefonos",
            joinColumns=@JoinColumn(name = "id_lugar", referencedColumnName = "id")
    )
    @Column(name="numero_telefono")
    private List<String> telefonos;

    @ManyToOne
    private Moderador moderador;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TipoLugar tipo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioCreador;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "lugar")
    private List<Horario> horarios;

    @OneToMany(mappedBy = "lugar")
    private List<Comentario> comentarios;

    @ManyToMany(mappedBy = "lugaresFavoritos")
    private List<Usuario> usuariosFavoritos;

    public Lugar(){
        super();
    }

    public Lugar(String nombre, String descripcion, Date fechaCreacion, Float latitud, Float longitud, List<String> imagenes, List<String> telefonos, TipoLugar tipo, Usuario usuarioCreador, Ciudad ciudad, List<Horario> horarios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.imagenes = imagenes;
        this.telefonos = telefonos;
        this.tipo = tipo;
        this.usuarioCreador = usuarioCreador;
        this.ciudad = ciudad;
        this.horarios = horarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public Moderador getModerador() {
        return moderador;
    }

    public void setModerador(Moderador moderador) {
        this.moderador = moderador;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    public void setTipo(TipoLugar tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lugar lugar = (Lugar) o;

        return id.equals(lugar.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Lugar{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", fechaAprobacion=" + fechaAprobacion +
                ", estado=" + estado +
                ", imagenes=" + imagenes +
                ", telefonos=" + telefonos +
                ", moderador=" + moderador +
                ", tipo=" + tipo +
                ", usuarioCreador=" + usuarioCreador +
                ", ciudad=" + ciudad +
                ", horarios=" + horarios +
                '}';
    }
}
