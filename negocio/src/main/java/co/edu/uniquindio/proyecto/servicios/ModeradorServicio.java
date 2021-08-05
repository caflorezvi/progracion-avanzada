package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModeradorServicio {

    @Autowired
    private ModeradorRepo moderadorRepo;

    @Autowired
    private LugarRepo lugarRepo;

    public void cambiarEstadoLugar(Integer idLugar, Integer idModerador, boolean valor) throws Exception {
        Optional<Lugar> l = lugarRepo.findById(idLugar);
        Optional<Moderador> m = moderadorRepo.findById(idModerador);

        if(l.isPresent() && m.isPresent()){
            Lugar lugarbd = l.get();
            lugarbd.setEstado(valor);
            lugarbd.setModerador( m.get() );
            lugarRepo.save(lugarbd);
        }else{
            throw new Exception("No existe el lugar ni el moderador");
        }
    }

    public List<Moderador> listarModeradores(){
        return moderadorRepo.findAll();
    }

    public List<Lugar> listarLugaresNoAprobados(){
        return lugarRepo.findAllByEstado(false);
    }
}
