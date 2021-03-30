package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Persona implements Serializable {

    @Id
    @Column(name = "cedula", length = 10)
    private String cedula;
    @Column(name = "nombre_completo", length = 200, nullable = false)
    private String nombreCompleto;
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "genero", nullable = false)
    private Genero genero;
    @ElementCollection
    @JoinColumn(name = "num_telefono")
    private Map<String, String> numTelefono;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    public Persona(){
        super();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Map<String, String> getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(Map<String, String> numTelefono) {
        this.numTelefono = numTelefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        return cedula != null ? cedula.equals(persona.cedula) : persona.cedula == null;
    }

    @Override
    public int hashCode() {
        return cedula != null ? cedula.hashCode() : 0;
    }
}
