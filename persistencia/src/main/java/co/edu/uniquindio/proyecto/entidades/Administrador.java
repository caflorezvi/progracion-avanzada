package co.edu.uniquindio.proyecto.entidades;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
public class Administrador extends Persona implements Serializable {

    @OneToMany(mappedBy = "admin")
    private List<Moderador> moderadores;

}
