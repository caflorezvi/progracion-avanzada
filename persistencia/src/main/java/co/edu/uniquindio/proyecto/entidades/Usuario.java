package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario extends Persona implements Serializable {

    @ManyToOne
    private Ciudad ciudad;

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

    public Usuario(){
        super();
    }

    public Usuario(String nombre, String nickname, String email, String password, Ciudad ciudad) {
        super(nombre, nickname, email, password);
        this.ciudad = ciudad;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Lugar> getLugaresFavoritos() {
        return lugaresFavoritos;
    }

    public void setLugaresFavoritos(List<Lugar> lugaresFavoritos) {
        this.lugaresFavoritos = lugaresFavoritos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ciudad=" + ciudad +
                "} " + super.toString();
    }
}
