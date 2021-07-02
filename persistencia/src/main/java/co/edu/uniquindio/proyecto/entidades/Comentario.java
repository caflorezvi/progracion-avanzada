package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Lob
    @Column(nullable = false)
    @NotBlank
    private String comentario;

    @Column(nullable = false)
    @Positive
    private Integer calificacion;

    @Lob
    private String respuesta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Lugar lugar;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Builder
    public Comentario(String comentario, Integer calificacion, Lugar lugar, Usuario usuario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.lugar = lugar;
        this.usuario = usuario;
    }

}
