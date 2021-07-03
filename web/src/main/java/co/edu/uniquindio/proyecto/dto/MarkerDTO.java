package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class MarkerDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private float lat, lng;
}
