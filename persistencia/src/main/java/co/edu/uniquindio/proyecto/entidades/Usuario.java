package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable {

    @Getter @Setter @ManyToOne
    private Ciudad ciudad;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "favorito",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_lugar"))
    private List<Lugar> lugaresFavoritos;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuarioCreador")
    private List<Lugar> lugaresCreados;

    public Usuario(String nombre, String nickname, String email, String password, Ciudad ciudad) {
        super(nombre, nickname, email, password);
        this.ciudad = ciudad;
    }

}