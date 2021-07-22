package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class ComentarioConverter implements Converter<Comentario>, Serializable {

    @Autowired
    private LugarServicio lugarServicio;

    @Override
    public Comentario getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            if (s != null && !"".equals(s)) {
                int id = Integer.parseInt(s);
                return lugarServicio.obtenerComentario(id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Comentario comentario) {
        if(comentario!=null){
            return ""+comentario.getId();
        }
        return "";
    }
}
