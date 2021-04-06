package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Administrador extends Persona implements Serializable {

    @OneToMany(mappedBy = "admin")
    private List<Moderador> moderadores;

    public Administrador(){
        super();
    }
}
