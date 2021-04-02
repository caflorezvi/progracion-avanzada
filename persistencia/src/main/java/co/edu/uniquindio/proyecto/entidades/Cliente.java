package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
public class Cliente extends Persona implements Serializable {

    @Column(name = "tiene_cuenta_otro_banco", nullable = false)
    private Boolean tieneCuentaOtroBanco;

    @OneToMany(mappedBy = "clientePrestamo")
    private List<Prestamo> prestamos;

    @OneToMany(mappedBy = "clienteCuenta")
    private List<CuentaAhorros> cuentaAhorros;

    @ManyToMany
    private List<Sede> sedes;

    public Cliente(){
        super();
    }

    public Cliente(String cedula, String nombreCompleto, String email, Genero genero, Date fechaNacimiento, Boolean tieneCuentaOtroBanco) {
        super(cedula, nombreCompleto, email, genero, fechaNacimiento);
        this.tieneCuentaOtroBanco = tieneCuentaOtroBanco;
    }
}
