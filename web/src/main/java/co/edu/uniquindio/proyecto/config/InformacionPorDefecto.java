package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.AdminService;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public void run(String... args) throws Exception {

        if( !adminService.existenAdmins() ){
            Administrador ad1 = Administrador.builder().nombre("Admin 1").email("admin1@admin.com").nickname("admin1").password("123").build();
            adminService.registrarAdmin(ad1);

            Administrador ad2 = Administrador.builder().nombre("Admin 2").email("admin2@admin.com").nickname("admin2").password("456").build();
            adminService.registrarAdmin(ad2);
        }

        if( ciudadServicio.listarCiudades().isEmpty() ){

            Ciudad c1 = new Ciudad("Armenia");
            Ciudad c2 = new Ciudad("Calarcá");
            Ciudad c3 = new Ciudad("Pereira");
            Ciudad c4 = new Ciudad("Cartago");

            ciudadServicio.registrarCiudad(c1);
            ciudadServicio.registrarCiudad(c2);
            ciudadServicio.registrarCiudad(c3);
            ciudadServicio.registrarCiudad(c4);

        }

        if( lugarServicio.listarTiposLugares().isEmpty() ) {

            TipoLugar t1 = new TipoLugar("Restaurante");
            TipoLugar t2 = new TipoLugar("Hotel");
            TipoLugar t3 = new TipoLugar("Café");
            TipoLugar t4 = new TipoLugar("Bar");

            lugarServicio.crearTipoLugar(t1);
            lugarServicio.crearTipoLugar(t2);
            lugarServicio.crearTipoLugar(t3);
            lugarServicio.crearTipoLugar(t4);

        }

        if( lugarServicio.listarLugares().isEmpty() ){

            Lugar l1 = Lugar.builder()
                    .nombre("Café de la 25")
                    .ciudad( ciudadServicio.obtenerCiudad(6) )
                    .descripcion("Es un café muy rico")
                    .latitud(1.22222F)
                    .longitud(1.34555F)
                    .usuarioCreador( usuarioServicio.obtenerUsuario( 7 ) )
                    .imagenes( new ArrayList<>())
                    .tipo( lugarServicio.obtenerTipoLugar(5) )
                    .horarios( new ArrayList<>() ).build();

            Lugar l2 = Lugar.builder()
                    .nombre("Hotel de la 25")
                    .ciudad( ciudadServicio.obtenerCiudad(6) )
                    .descripcion("Es un hotel muy económico")
                    .latitud(1.22222F)
                    .longitud(1.34555F)
                    .usuarioCreador( usuarioServicio.obtenerUsuario( 7 ) )
                    .imagenes( new ArrayList<>())
                    .tipo( lugarServicio.obtenerTipoLugar(4) )
                    .horarios( new ArrayList<>() ).build();

            lugarServicio.crearLugar(l1);
            lugarServicio.crearLugar(l2);

        }

        /*Comentario c1 = Comentario.builder()
                .comentario("Este lugar es muy intersante")
                .calificacion(4)
                .lugar( lugarServicio.obtenerLugar(5) )
                .usuario( usuarioServicio.obtenerUsuario(10) ).build();

        Comentario c2 = Comentario.builder()
                .comentario("Este lugar es regular")
                .calificacion(2)
                .lugar( lugarServicio.obtenerLugar(5) )
                .usuario( usuarioServicio.obtenerUsuario(9) ).build();

        lugarServicio.crearComentario(c1);
        lugarServicio.crearComentario(c2);*/

    }
}
