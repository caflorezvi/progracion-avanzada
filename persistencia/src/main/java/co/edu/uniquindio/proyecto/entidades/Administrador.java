package co.edu.uniquindio.proyecto.entidades;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
public class Administrador extends Persona implements Serializable {

    @Builder
    public Administrador(String nombre, String nickname, String email, String password) {
        super(nombre, nickname, email, password);
    }

    @OneToMany(mappedBy = "admin")
    private List<Moderador> moderadores;

}
