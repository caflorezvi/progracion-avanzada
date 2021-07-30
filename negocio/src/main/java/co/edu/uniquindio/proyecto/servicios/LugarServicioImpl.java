package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service
@Transactional
public class LugarServicioImpl implements LugarServicio{

    private final TipoLugarRepo tipoLugarRepo;
    private final LugarRepo lugarRepo;
    private final ComentarioRepo comentarioRepo;
    private final HorarioRepo horarioRepo;
    private final UsuarioRepo usuarioRepo;

    public LugarServicioImpl(LugarRepo lugarRepo, TipoLugarRepo tipoLugarRepo, ComentarioRepo comentarioRepo, HorarioRepo horarioRepo, UsuarioRepo usuarioRepo) {
        this.lugarRepo = lugarRepo;
        this.tipoLugarRepo = tipoLugarRepo;
        this.comentarioRepo = comentarioRepo;
        this.horarioRepo = horarioRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Lugar crearLugar(Lugar lugar) throws Exception {
        lugar.setEstado(false);
        lugar.setFechaCreacion(new Date());
        return lugarRepo.save(lugar);
    }

    @Override
    public void eliminarLugar(Integer id) throws Exception {
        Optional<Lugar> l = lugarRepo.findById(id);
        if(l.isEmpty()){
            throw new Exception("EL id no existe");
        }
        lugarRepo.deleteById(id);
    }

    @Override
    public Lugar actualizarLugar(Lugar lugar) throws Exception {
        Optional<Lugar> l = lugarRepo.findById(lugar.getId());
        if(l.isEmpty()){
            throw new Exception("EL id no existe");
        }
        return lugarRepo.save(lugar);
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
    public Lugar obtenerLugar(Integer id) throws Exception {
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

    @Override
    public List<TipoLugar> listarTiposLugares() {
        return tipoLugarRepo.findAll();
    }

    @Override
    public List<Comentario> listarComentarios(Integer idLugar) {
        return lugarRepo.listarComentarios(idLugar);
    }

    @Override
    public List<Comentario> listarComentariosSinResponder(Integer idLugar) {
        return lugarRepo.listarComentariosSinResponder(idLugar);
    }

    @Override
    public List<Horario> listarHorarios(Integer idLugar) {
        return lugarRepo.listarHorarios(idLugar);
    }

    @Override
    public void crearComentario(Comentario comentario) throws Exception {
        try {
            comentario.setFechaCreacion( new Date() );
            comentarioRepo.save(comentario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void crearHorario(Horario horario) throws Exception {
        try {
            horarioRepo.save(horario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Float obtenerCalificacionPromedio(Integer idLugar) throws Exception {
        Optional<Lugar> objeto = lugarRepo.findById(idLugar);
        if( objeto.isEmpty() ){
            throw new Exception("El id no es válido");
        }
        return lugarRepo.obtenerCalificacionPromedio(idLugar);
    }

    @Override
    public void marcarFavorito(Lugar lugar, Usuario usuario) {
        if( usuario.getLugaresFavoritos().contains(lugar) ){
            usuario.getLugaresFavoritos().remove(lugar);
        }else{
            usuario.getLugaresFavoritos().add(lugar);
        }
        usuarioRepo.save(usuario);
    }

    @Override
    public void responderComentario(Integer idComentario, String respuesta) {
        Comentario comentario = comentarioRepo.findById(idComentario).orElse(null);
        if(comentario!=null){
            comentario.setRespuesta(respuesta);
            comentarioRepo.save(comentario);
        }
    }

    @Override
    public Comentario obtenerComentario(Integer id) throws Exception {
        Optional<Comentario> objeto = comentarioRepo.findById(id);
        if( objeto.isEmpty() ){
            throw new Exception("El id no es válido");
        }
        return objeto.get();
    }

    @Override
    public List<Lugar> filtrarLugares( HashMap<String, Object> propiedades) {
        try {
            //TipoLugar tipoLugar = obtenerTipoLugar(3);
            //Ciudad ciudad = ciudadRepo.findById(1).get();
            Lugar.LugarBuilder l = Lugar.builder();
            ExampleMatcher matcher = ExampleMatcher.matchingAll();

            if( propiedades.get("nombre") != null ){
                l.nombre( propiedades.get("nombre").toString() );
                matcher = matcher.withMatcher("nombre", contains() ).withIgnoreCase();
            }

            if( propiedades.get("ciudad") != null ){
                l.ciudad( (Ciudad) propiedades.get("ciudad") );
                matcher = matcher.withMatcher("ciudad", contains().exact() );
            }

            if( propiedades.get("tipoLugar") != null ){
                l.tipo( (TipoLugar) propiedades.get("tipoLugar") );
                matcher = matcher.withMatcher("tipo", contains().exact() );
            }

            return lugarRepo.findAll(Example.of(l.build(), matcher));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
