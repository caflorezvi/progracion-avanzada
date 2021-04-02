package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Cliente;
import co.edu.uniquindio.proyecto.entidades.Genero;
import co.edu.uniquindio.proyecto.repositorios.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void registrarClienteTest(){
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("1996/02/14");

            Cliente clienteNuevo = new Cliente("123", "Pepito", "pepito@mail.com", Genero.MASCULINO, fecha, false);
            Cliente clienteGuardado = clienteRepo.save(clienteNuevo);
            Assertions.assertNotNull(clienteGuardado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarClienteTest(){
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("1996/02/14");

            Cliente clienteNuevo = new Cliente("123", "Pepito", "pepito@mail.com", Genero.MASCULINO, fecha, false);
            clienteRepo.save(clienteNuevo);

            clienteRepo.delete(clienteNuevo);

            Cliente clienteBorrado = clienteRepo.findById("123").orElse(null);
            Assertions.assertNull(clienteBorrado);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void actualizarClienteTest(){
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = sdf.parse("1996/02/14");

            Cliente clienteNuevo = new Cliente("123", "Pepito", "pepito@mail.com", Genero.MASCULINO, fecha, false);
            Cliente clienteGuardado = clienteRepo.save(clienteNuevo);

            clienteGuardado.setEmail("pape@gmail.com");
            clienteRepo.save(clienteGuardado);

            Cliente clienteBuscado = clienteRepo.findById("123").orElse(null);
            Assertions.assertEquals("pape@gmail.com", clienteBuscado.getEmail());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:clientes.sql")
    public void listarClientes(){

        List<Cliente> lista = clienteRepo.findAll();
        System.out.println(lista.get(0).getEmail());
    }
}
