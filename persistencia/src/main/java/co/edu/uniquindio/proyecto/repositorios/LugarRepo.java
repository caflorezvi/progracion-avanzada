package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface LugarRepo extends JpaRepository<Lugar, Integer> {

    @Query("select l from Lugar l join l.horarios h where l.estado = true and h.diaSemana = :diaSemana and :horaActual between h.horaInicio and h.horaFin ")
    List<Lugar> obtenerLugaresAbiertos(String diaSemana, Date horaActual);

}