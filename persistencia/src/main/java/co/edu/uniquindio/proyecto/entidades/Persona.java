package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 200, nullable = false)
    @NotBlank
    @Size(max = 200)
    private String nombre;

    @Column(length = 100, nullable = false, unique = true)
    @NotBlank
    @Size(max = 100, message = "El nickname solo puede tener 100 caracteres")
    private String nickname;

    @Column(length = 200, nullable = false, unique = true)
    @NotBlank
    @Email(message = "El email está mal escrito")
    @Size(max = 200)
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public Persona(String nombre, String nickname, String email, String password) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

}
