package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
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
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    private List<Lugar> lugares;

    @Autowired
    private LugarServicio lugarServicio;

    @PostConstruct
    public void inicializar(){
        if(busquedaParam!=null && !busquedaParam.isEmpty()){
            lugares = lugarServicio.buscarLugares(busquedaParam);
        }
    }

    public String buscar(){
        if(!busqueda.isEmpty()){
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
        }
        return "";
    }
}
