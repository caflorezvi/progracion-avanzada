package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNickname(String nickname);

    Optional<Usuario> findByEmail(String email);

    @Query("select f from Usuario u, IN(u.lugaresFavoritos) f where u.id = :id")
    List<Lugar> obtenerLugaresFavoritos(Integer id);

}