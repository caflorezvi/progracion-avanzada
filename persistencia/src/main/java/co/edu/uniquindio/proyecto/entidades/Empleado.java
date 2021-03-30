package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
public class Empleado extends Persona implements Serializable {

    @Column(name = "sueldo_base", nullable = false)
    private int sueldoBase;

    @ManyToOne
    private Sede sede;

    public Empleado(){
        super();
    }


}
