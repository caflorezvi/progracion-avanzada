package co.edu.uniquindio.proyecto.dto;

public class NumeroLugaresPorCategoriaDTO {

    private String nombreTipoLugar;
    private Long cantidadLugares;

    public NumeroLugaresPorCategoriaDTO(String nombreTipoLugar, Long cantidadLugares) {
        this.nombreTipoLugar = nombreTipoLugar;
        this.cantidadLugares = cantidadLugares;
    }
}
