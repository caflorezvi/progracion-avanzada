package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CiudadServicioImpl implements CiudadServicio {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad) throws Exception {
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer id) throws Exception {
        Optional<Ciudad> objeto = ciudadRepo.findById(id);

        if( objeto.isEmpty() ){
            throw new Exception("El id no es válido");
        }

        return objeto.get();
    }
}
