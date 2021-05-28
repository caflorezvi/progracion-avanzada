package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TipoLugar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "tipo")
    @ToString.Exclude
    private List<Lugar> lugares;

    public TipoLugar(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
