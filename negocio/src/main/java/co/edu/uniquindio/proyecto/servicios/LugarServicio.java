package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.HashMap;
import java.util.List;

public interface LugarServicio {

    Lugar crearLugar(Lugar lugar) throws Exception;

    void eliminarLugar(Integer id) throws Exception;

    Lugar actualizarLugar(Lugar lugar) throws Exception;

    List<Lugar> listarLugares();

    List<Lugar> buscarLugares(String nombre);

    Lugar obtenerLugar(Integer id) throws Exception;

    TipoLugar crearTipoLugar(TipoLugar tipoLugar) throws Exception;

    TipoLugar obtenerTipoLugar(Integer id) throws Exception;

    List<TipoLugar> listarTiposLugares();

    List<Comentario> listarComentarios(Integer idLugar);

    List<Comentario> listarComentariosSinResponder(Integer idLugar);

    List<Horario> listarHorarios(Integer idLugar);

    void crearComentario(Comentario comentario) throws Exception;

    void crearHorario(Horario horario) throws Exception;

    Float obtenerCalificacionPromedio(Integer idLugar) throws Exception;

    void marcarFavorito(Lugar lugar, Usuario usuario);

    void responderComentario(Integer idComentario, String respuesta);

    Comentario obtenerComentario(Integer id) throws Exception;

    List<Lugar> filtrarLugares( HashMap<String, Object> propiedades );
}
