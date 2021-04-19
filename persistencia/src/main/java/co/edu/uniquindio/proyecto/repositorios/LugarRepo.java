package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ComentariosLugarDTO;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
