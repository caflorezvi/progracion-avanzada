package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.TipoLugar;

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

    List<Horario> listarHorarios(Integer idLugar);

    void crearComentario(Comentario comentario) throws Exception;


}
