package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
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
    @NotBlank
    private String diaSemana;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Lugar lugar;

    public Horario(Date horaInicio, Date horaFin, String diaSemana) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
    }

    public String getHoraI(){
        return new SimpleDateFormat("HH:mm").format(horaInicio);
    }

    public String getHoraF(){
        return new SimpleDateFormat("HH:mm").format(horaFin);
    }
}
