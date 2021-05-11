package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Test
    public void registrarUsuarioTest(){
        try {

            Ciudad ciudad = new Ciudad("Armenia");
            ciudadServicio.registrarCiudad(ciudad);

            Usuario usuario = new Usuario("Carlos", "carlosxd", "carlos@email.com", "1234", ciudad);
            Usuario usuarioRegistrado = usuarioServicio.registrarUsuario(usuario);

            Assertions.assertNotNull(usuarioRegistrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void eliminarUsuarioTest(){
        try {
            usuarioServicio.eliminarUsuario(1);
            Usuario usuarioEliminado = usuarioServicio.obtenerUsuario(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:unilocal.sql")
    public void listarUsuariosTest(){

        try {
            List<Usuario> lista = usuarioServicio.listarUsuarios();
            for (Usuario u : lista){
                System.out.println(u);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

