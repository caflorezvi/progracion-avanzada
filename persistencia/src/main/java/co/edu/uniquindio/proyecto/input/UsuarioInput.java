package co.edu.uniquindio.proyecto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInput {

    private Integer id;
    private String nombre, email, nickname, password;
    private Integer ciudadId;

}
