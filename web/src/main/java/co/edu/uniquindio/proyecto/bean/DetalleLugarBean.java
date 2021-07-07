package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Horario;
import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
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
public class DetalleLugarBean implements Serializable {

    @Value("#{param['lugar']}")
    private String idLugar;

    @Autowired
    private LugarServicio lugarServicio;

    @Getter @Setter
    private Float calificacionPromedio;

    @Getter @Setter
    private Lugar lugar;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private String icono;

    @PostConstruct
    public void init(){
        this.nuevoComentario = new Comentario();
        this.icono = "pi pi-star-o";
        if( idLugar!=null && !"".equals(idLugar) ){
            try {
                int id = Integer.parseInt(idLugar);
                this.lugar = lugarServicio.obtenerLugar(id);
                this.comentarios = lugarServicio.listarComentarios(id);
                this.horarios = lugarServicio.listarHorarios(id);
                this.calificacionPromedio = lugarServicio.obtenerCalificacionPromedio(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void crearComentario(){
        try {
            nuevoComentario.setLugar(this.lugar);
            nuevoComentario.setUsuario(new Usuario()); //sesión
            lugarServicio.crearComentario(nuevoComentario);
            this.nuevoComentario = new Comentario();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void marcarFavorito(){
        if(icono.equals("pi pi-star-o")){
            this.icono = "pi pi-star";
        }else{
            this.icono = "pi pi-star-o";
        }
        //lugarServicio.marcarFavorito(lugar, new Usuario()); //sesión
    }
}
