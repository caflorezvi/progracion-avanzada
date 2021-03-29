package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CuentaAhorros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private int numero;
    @Column(name = "saldo", nullable = false)
    @Positive
    private int saldo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_apertura", nullable = false)
    private Date fechaApertura;

    public CuentaAhorros(){
        super();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuentaAhorros that = (CuentaAhorros) o;

        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return numero;
    }
}

