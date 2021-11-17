package co.edu.uniquindio.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Módulo web de la aplicación unilocal del espacio académico Programación avanzada del programa
 * de Ingeniería de Sistemas y Computación de la Universidad del Quindío.
 *
 * @author Carlos Andrés Flórez Villarraga
 * @version 1.0
 * License GPLv3
 */

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
