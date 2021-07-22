package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
public class MisLugaresBean implements Serializable {

    @Getter @Setter
    private List<Lugar> misLugares;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Getter @Setter
    private Lugar lugarSeleccionado;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private LugarServicio lugarServicio;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    @Getter @Setter
    private String respuesta;

    @Getter @Setter
    private Comentario comentarioSeleccionado;

    @PostConstruct
    public void inicializar(){
        this.comentarios = new ArrayList<>();
        if(personaLogin != null){
            this.misLugares = usuarioServicio.obtenerMisLugares( personaLogin.getId() );
        }
    }

    public void mostrarComentarios(Integer idLugar){
        this.comentarios = lugarServicio.listarComentariosSinResponder(idLugar);
    }

    public void responderComentario(){
        if(comentarioSeleccionado!=null){
            lugarServicio.responderComentario(comentarioSeleccionado.getId(), respuesta);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Se ha respondido el mensaje");
            FacesContext.getCurrentInstance().addMessage("respuesta-com", message);
            this.comentarios.remove(comentarioSeleccionado);
        }
    }

}
