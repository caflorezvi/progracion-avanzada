package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer> {

    @Query("select l from Lugar l join l.horarios h where l.estado = true and h.diaSemana = :diaSemana and :horaActual between h.horaInicio and h.horaFin")
    List<Lugar> obtenerLugaresAbiertos(String diaSemana, Date horaActual);

    @Query("select l from Lugar l where l.nombre like concat('%', :nombre, '%') ")
    List<Lugar> buscarLugares(String nombre);

    @Query("select c from Comentario c where c.lugar.id = :idLugar")
    List<Comentario> listarComentarios(Integer idLugar);

    @Query("select c from Comentario c where c.lugar.id = :idLugar and c.respuesta is null")
    List<Comentario> listarComentariosSinResponder(Integer idLugar);

    @Query("select h from Horario h where h.lugar.id = :idLugar")
    List<Horario> listarHorarios(Integer idLugar);

    @Query("select avg(c.calificacion) from Comentario c where c.lugar.id = :idLugar")
    Float obtenerCalificacionPromedio(Integer idLugar);

}