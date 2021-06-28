package co.edu.uniquindio.proyecto.config;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.servicios.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        if( !adminService.existenAdmins() ){
            Administrador ad1 = Administrador.builder().nombre("Admin 1").email("admin1@admin.com").nickname("admin1").password("123").build();
            adminService.registrarAdmin(ad1);

            Administrador ad2 = Administrador.builder().nombre("Admin 2").email("admin2@admin.com").nickname("admin2").password("456").build();
            adminService.registrarAdmin(ad2);
        }
    }
}
