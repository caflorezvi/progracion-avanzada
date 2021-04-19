package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Autowired
    private LugarRepo lugarRepo;

    @Test
    public void registrarUsuarioTest(){
        try {

            Ciudad ciudad = new Ciudad("Armenia");
            ciudadRepo.save(ciudad);

            Usuario usuarioNuevo = new Usuario("Pepito", "pepe123", "pepe@gmail.com", "1234", ciudad);
            Usuario usuarioGuardado = usuarioRepo.save(usuarioNuevo);

            System.out.println(usuarioGuardado);

            Assertions.assertNotNull(usuarioGuardado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarUsuarioTest(){
        try {

            Ciudad ciudad = new Ciudad("Armenia");
            ciudadRepo.save(ciudad);

            Usuario usuarioNuevo = new Usuario("Pepito", "pepe123", "pepe@gmail.com", "1234", ciudad);
            usuarioRepo.save(usuarioNuevo);

            usuarioRepo.delete(usuarioNuevo);

            Usuario usuarioBorrado = usuarioRepo.findById(1).orElse(null);
            Assertions.assertNull(usuarioBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void actualizarUsuarioTest(){
        try {

            Ciudad ciudad = new Ciudad("Armenia");
            ciudadRepo.save(ciudad);

            Usuario usuarioNuevo = new Usuario("Pepito", "pepe123", "pepe@gmail.com", "1234", ciudad);
            Usuario usuarioGuardado = usuarioRepo.save(usuarioNuevo);

            usuarioGuardado.setEmail("pepito@mail.com");
            usuarioRepo.save(usuarioGuardado);

            Usuario usuarioBuscado = usuarioRepo.findById(1).orElse(null);
            Assertions.assertEquals("pepito@mail.com", usuarioBuscado.getEmail());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void listarUsuariosTest(){
        List<Usuario> lista = usuarioRepo.findAll();
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void iniciarSesionTest(){
        Usuario u = usuarioRepo.findByEmailAndPassword("carlos@email.com", "1234");
        Assertions.assertNotNull(u);
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void listarUsuariosPaginadosTest(){
        List<Usuario> lista = usuarioRepo.obtenerUsuarios(PageRequest.of(1, 3));
        for(Usuario u : lista){
            System.out.println(u);
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void listarUsuariosOrdenadosTest(){
        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));
        for(Usuario u : lista){
            System.out.println(u);
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void obtenerTipoLugarTest(){
        List<Object[]> infoLugar = lugarRepo.obtenerTiposLugares();

        for ( Object[]  arr: infoLugar) {
            System.out.println(arr[0]+ ", "+arr[1]+", "+arr[2]);
        }

    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void obtenerLugaresFavoritos(){
        List<Lugar> lugares = usuarioRepo.obtenerLugaresFavoritos2(1);

        for ( Lugar l: lugares) {
            System.out.println(l);
        }

    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void obtenerUsuariosTest(){
        List<ComentariosLugarDTO> usuarios = lugarRepo.obtenerComentariosLugares();

        for ( ComentariosLugarDTO l: usuarios) {
            System.out.println( l.getLugar().getNombre()+" "+l.getComentario().getComentario() );
        }

    }
}
