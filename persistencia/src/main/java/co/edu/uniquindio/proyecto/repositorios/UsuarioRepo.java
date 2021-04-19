package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios();

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Pageable pageable);

    @Query("select u from Usuario u")
    List<Usuario> obtenerUsuarios(Sort pageable);

    @Query("select u from Usuario u where u.email = :email and u.nombre = :nombre")
    Usuario obtenerUsario( @Param("email") String email, @Param("nombre") String nombre);

    Usuario findByEmailAndNombre(String email, String nombre);

    Usuario findByNickname(String nickname);

    List<Usuario> findByNombre(String nombre);

    Usuario findByEmailAndPassword(String email, String password);

    @Query("select f from Usuario u, IN(u.lugaresFavoritos) f where u.id = :id")
    List<Lugar> obtenerLugaresFavoritos(Integer id);

    @Query("select f from Usuario u join u.lugaresFavoritos f where u.id = :id")
    List<Lugar> obtenerLugaresFavoritos2(Integer id);

    @Query("select u.email, l from Usuario u left join u.lugaresCreados l")
    List<Object[]> obtenerLugaresPublicadosUsuarios();

}
