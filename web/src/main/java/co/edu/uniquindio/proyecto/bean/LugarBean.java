package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@RequestScope
public class LugarBean implements Serializable {

    @Getter @Setter
    private Lugar lugar;
    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;

    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @PostConstruct
    public void inicializar(){
        lugar = new Lugar();
    }

    public String crearLugar(){
        try{

            lugar.setCiudad( ciudadServicio.obtenerCiudad(4) );
            lugar.setUsuarioCreador( usuarioServicio.obtenerUsuario(6) );
            lugar.setTipo( lugarServicio.obtenerTipoLugar(3) );

            lugarServicio.crearLugar(lugar);

            return "lugarCreado?faces-redirect=true";
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return null;
    }

}
