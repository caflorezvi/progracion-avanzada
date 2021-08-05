package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class ModeradorBean implements Serializable {

    @Getter @Setter
    private List<Lugar> lugares;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Autowired
    private ModeradorServicio moderadorServicio;

    @PostConstruct
    public void inicializar(){
        this.lugares = moderadorServicio.listarLugaresNoAprobados();
        System.out.println("Mods"+ moderadorServicio.listarModeradores() );
    }

    public void aprobarLugar(Integer idLugar){
        if(personaLogin!=null){
            try {
                moderadorServicio.cambiarEstadoLugar(idLugar, personaLogin.getId(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void rechazarLugar(Integer idLugar){
        if(personaLogin!=null){
            try {
                moderadorServicio.cambiarEstadoLugar(idLugar, personaLogin.getId(), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
