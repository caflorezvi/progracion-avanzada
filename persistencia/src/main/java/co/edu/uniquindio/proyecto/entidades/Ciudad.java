package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Lugar> lugares;

    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    private List<Usuario> usuarios;

    @Builder
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

}
