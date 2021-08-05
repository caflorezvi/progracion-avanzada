package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.MarkerDTO;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.TipoLugar;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import com.google.gson.Gson;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Getter @Setter
    private List<TipoLugar> tipos;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private LugarServicio lugarServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Integer distanciaFiltro;

    @Getter @Setter
    private TipoLugar tipoFiltro;

    @Getter @Setter
    private Ciudad ciudadFiltro;

    @Getter @Setter
    private float latActual, lngActual;

    @PostConstruct
    public void inicializar(){
        if(busquedaParam!=null && !busquedaParam.isEmpty()){
            lugares = lugarServicio.buscarLugares(busquedaParam);
            tipos = lugarServicio.listarTiposLugares();
            ciudades = ciudadServicio.listarCiudades();
            PrimeFaces.current().executeScript("crearMapa("+new Gson().toJson(this.lugares.stream().map(l -> new MarkerDTO(l.getId(), l.getNombre(), l.getDescripcion(), l.getLatitud(), l.getLongitud()) ).collect(Collectors.toList()))+");");
        }
    }

    public String buscar(){
        if(!busqueda.isEmpty()){
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
        }
        return "";
    }

    public void obtenerPosicion(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        lngActual = Float.parseFloat( params.get("lng") );
        latActual = Float.parseFloat( params.get("lat") );
    }

    public void aplicarFiltro(){
        HashMap<String, Object> filtros = new HashMap();
        if(busquedaParam!=null){
            filtros.put("nombre", busquedaParam);
        }
        if(tipoFiltro!=null){
            filtros.put("tipoLugar", tipoFiltro);
        }
        if(ciudadFiltro!=null){
            filtros.put("ciudad", ciudadFiltro);
        }

        List<Lugar> lugaresFiltro = lugarServicio.filtrarLugares(filtros);

        if(distanciaFiltro!=null){
            lugaresFiltro = lugaresFiltro.stream().filter(l -> (Math.sqrt(Math.pow(lngActual - l.getLongitud(), 2) + Math.pow(latActual - l.getLatitud(), 2)))*100 < distanciaFiltro ).collect(Collectors.toList());
        }

        this.lugares = lugaresFiltro;

        if(lugaresFiltro!=null && !lugares.isEmpty()){
            PrimeFaces.current().executeScript("actualizarMapa("+new Gson().toJson(lugaresFiltro.stream().map(l -> new MarkerDTO(l.getId(), l.getNombre(), l.getDescripcion(), l.getLatitud(), l.getLongitud()) ).collect(Collectors.toList()))+");");
        }else{
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "No hay lugares");
            FacesContext.getCurrentInstance().addMessage("msj-filtro", m);
        }


    }
}
