package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Lugar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
    @ToString.Exclude
    private List<Horario> horarios;

    @OneToMany(mappedBy = "lugar")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @ManyToMany(mappedBy = "lugaresFavoritos")
    @ToString.Exclude
    private List<Usuario> usuariosFavoritos;

    @Builder
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

}
