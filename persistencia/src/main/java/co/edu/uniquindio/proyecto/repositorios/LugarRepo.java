package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO;
import co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer> {

    @Query("select l.nombre, l.latitud, l.longitud from Lugar l")
    List<Object[]> obtenerTiposLugares();

    @Query("select new co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO(l, c) from Lugar l left join l.comentarios c")
    List<ComentariosLugarDTO> obtenerComentariosLugares();

    @Query("select l.nombre, l.descripcion, l.ciudad.nombre, l.tipo.nombre from Lugar l where l.moderador.email = :emailModerador and l.estado = true")
    List<Object[]> obtenerLugaresAprobados(String emailModerador);

    @Query("select l.nombre, l.descripcion, l.latitud, l.longitud from Lugar l join l.horarios h where h.diaSemana = :diaSemana and h.horaInicio = :horaInicio and h.horaFin = :horaFin ")
    List<Object[]> obtenerLugaresHorario(String diaSemana, String horaInicio, String horaFin);

    @Query("select count(c) from Lugar l join l.comentarios c where l.id = :idLugar")
    int obtenerCantidadComentarios(Integer idLugar);

    @Query("select new co.edu.uniquindio.proyecto.dto.NumeroLugaresPorCategoriaDTO( l.tipo.nombre, count(l) ) from Lugar l group by l.tipo")
    List<NumeroLugaresPorCategoriaDTO> obtenerCantidadLugaresPorCategoria();

    @Query("select l from Lugar l where l.horarios is empty")
    List<Lugar> obtenerLugaresSinHorarios();

    @Query("select l.ciudad.nombre, count(l) from Lugar l group by l.ciudad")
    List<Object[]> obtenerCantidadLugaresPorCiudad();

    @Query("select l from Lugar l join l.horarios h where l.estado = true and h.diaSemana = :diaSemana and :horaActual between h.horaInicio and h.horaFin ")
    List<Lugar> obtenerLugaresAbiertos(String diaSemana, Date horaActual);

    @Query("select l.tipo.nombre, count(l) as total from Lugar l where l.estado = true group by l.tipo order by total desc")
    List<Object[]> obtenerTipoLugarPopular();

    @Query("select avg(c.calificacion) from Lugar l join l.comentarios c where l.id = :idLugar")
    float obtenerCalificacionPromedio(Integer idLugar);
}