package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.WebApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = WebApplication.class)
@AutoConfigureMockMvc
public class UsuarioRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void registrarTest() throws Exception {
        Usuario usuario = Usuario.builder().nombre("Juanita").nickname("juana").password("1234").email("juana@email.com").build();

        mockMvc.perform(
                post("/api/usuarios")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(usuario)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

    }

    @Test
    @Transactional
    public void obtenerUsuarioTest() throws Exception {
        mockMvc.perform(get("/api/usuarios/{id}", 2)
                .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

}
