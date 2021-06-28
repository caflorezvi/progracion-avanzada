package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    public void registrarAdmin(Administrador ad){
        adminRepo.save(ad);
    }

    public boolean existenAdmins(){
        return !adminRepo.findAll().isEmpty();
    }

}
