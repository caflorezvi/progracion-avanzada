package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.TipoLugar;
import co.edu.uniquindio.proyecto.repositorios.LugarRepo;
import co.edu.uniquindio.proyecto.repositorios.TipoLugarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LugarServicioImpl implements LugarServicio{

    private final TipoLugarRepo tipoLugarRepo;
    private final LugarRepo lugarRepo;

    public LugarServicioImpl(LugarRepo lugarRepo, TipoLugarRepo tipoLugarRepo) {
        this.lugarRepo = lugarRepo;
        this.tipoLugarRepo = tipoLugarRepo;
    }

    @Override
    public Lugar crearLugar(Lugar lugar) throws Exception {

        if( lugar.getNombre().length() > 200 ){
            throw new Exception("El nombre del lugar solo puede tener 200 caracteres");
        }

        lugar.setEstado(false);
        lugar.setFechaCreacion(new Date());
        Lugar lugarGuardado = lugarRepo.save(lugar);
        return lugarGuardado;
    }

    @Override
    public void eliminarLugar(Integer id) throws Exception {

    }

    @Override
    public Lugar actualizarLugar(Lugar lugar) throws Exception {
        return null;
    }

    @Override
    public List<Lugar> listarLugares() {
        return lugarRepo.findAll();
    }

    @Override
    public List<Lugar> buscarLugares(String nombre) {
        return lugarRepo.buscarLugares(nombre);
    }

    @Override
    public Lugar obtenerLugat(Integer id) throws Exception {
        Optional<Lugar> objeto = lugarRepo.findById(id);

        if( objeto.isEmpty() ){
            throw new Exception("El id no es válido");
        }

        return objeto.get();
    }

    @Override
    public TipoLugar crearTipoLugar(TipoLugar tipoLugar) throws Exception {
        return tipoLugarRepo.save(tipoLugar);
    }

    @Override
    public TipoLugar obtenerTipoLugar(Integer id) throws Exception {
        Optional<TipoLugar> objeto = tipoLugarRepo.findById(id);

        if( objeto.isEmpty() ){
            throw new Exception("El id no es válido");
        }

        return objeto.get();
    }
}
