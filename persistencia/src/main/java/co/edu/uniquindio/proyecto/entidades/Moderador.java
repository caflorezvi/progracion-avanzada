package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Moderador extends Persona implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Administrador admin;

    @OneToMany(mappedBy = "moderador")
    private List<Lugar> lugares;

    public Moderador(){
        super();
    }

    public Moderador(String nombre, String nickname, String email, String password, Administrador admin) {
        super(nombre, nickname, email, password);
        this.admin = admin;
    }
}
