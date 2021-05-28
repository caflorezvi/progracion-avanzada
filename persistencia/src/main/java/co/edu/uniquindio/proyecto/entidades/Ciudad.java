package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Getter
    @Setter
    @ToString.Include
    private Integer id;

    @Column(nullable = false)
    @Getter
    @Setter
    @ToString.Include
    private String nombre;

    @OneToMany(mappedBy = "ciudad")
    private List<Lugar> lugares;

    @OneToMany(mappedBy = "ciudad")
    private List<Usuario> usuarios;

    @Builder
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

}
