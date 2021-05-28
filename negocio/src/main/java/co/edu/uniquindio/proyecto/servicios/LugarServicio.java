package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.TipoLugar;

import java.util.List;

public interface LugarServicio {

    Lugar crearLugar(Lugar lugar) throws Exception;

    void eliminarLugar(Integer id) throws Exception;

    Lugar actualizarLugar(Lugar lugar) throws Exception;

    List<Lugar> listarLugares();

    List<Lugar> buscarLugares(String nombre);

    Lugar obtenerLugat(Integer id) throws Exception;

    TipoLugar crearTipoLugar(TipoLugar tipoLugar) throws Exception;

    TipoLugar obtenerTipoLugar(Integer id) throws Exception;


}
