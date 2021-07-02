package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.TipoLugar;
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
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class LugarBean implements Serializable {

    @Getter @Setter
    private Lugar lugar;
    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<TipoLugar> tipoLugares;

    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.lugar = new Lugar();
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipoLugares = lugarServicio.listarTiposLugares();
    }

    public String crearLugar(){
        try{
            if( lugar.getLatitud()!=null && lugar.getLongitud()!=null ) {
                lugar.setUsuarioCreador(usuarioServicio.obtenerUsuario(9));
                lugarServicio.crearLugar(lugar);
                return "lugarCreado?faces-redirect=true";
            }else{
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", "Es necesario ubicar el lugar dentro del mapa");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
            }
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
        }
        return null;
    }

}
