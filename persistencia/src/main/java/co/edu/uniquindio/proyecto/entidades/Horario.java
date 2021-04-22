package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Horario(){
        super();
    }

    public Horario(Date horaInicio, Date horaFin, String diaSemana) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horario horario = (Horario) o;

        return id.equals(horario.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
