package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date horaInicio;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date horaFin;

    @Column(nullable = false)
    private String diaSemana;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Lugar lugar;

    public Horario(Date horaInicio, Date horaFin, String diaSemana) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
    }

}
