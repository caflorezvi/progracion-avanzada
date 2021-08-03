package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Moderador extends Persona implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Include
    private Administrador admin;

    @OneToMany(mappedBy = "moderador")
    @JsonIgnore
    private List<Lugar> lugares;

    public Moderador(String nombre, String nickname, String email, String password, Administrador admin) {
        super(nombre, nickname, email, password);
        this.admin = admin;
    }
}
