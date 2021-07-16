package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.LugarServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class LugarBean implements Serializable {

    @Getter @Setter
    private Lugar lugar;
    private final LugarServicio lugarServicio;
    private final CiudadServicio ciudadServicio;
    private final UsuarioServicio usuarioServicio;

    @Value("${upload.url}")
    private String urlImagenes;
    private ArrayList<String> imagenes;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<TipoLugar> tipoLugares;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private Horario horario;

    @Value(value = "#{seguridadBean.persona}")
    private Persona personaLogin;

    public LugarBean(LugarServicio lugarServicio, CiudadServicio ciudadServicio, UsuarioServicio usuarioServicio) {
        this.lugarServicio = lugarServicio;
        this.ciudadServicio = ciudadServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @PostConstruct
    public void inicializar(){
        this.lugar = new Lugar();
        this.imagenes = new ArrayList<>();
        this.horarios = new ArrayList<>();
        this.horario = new Horario();
        this.ciudades = ciudadServicio.listarCiudades();
        this.tipoLugares = lugarServicio.listarTiposLugares();
    }

    public String crearLugar(){
        try{
            if(personaLogin!=null) {
                if (lugar.getLatitud() != null && lugar.getLongitud() != null && !imagenes.isEmpty() && !horarios.isEmpty()) {
                    lugar.setUsuarioCreador( (Usuario) personaLogin );
                    lugar.setImagenes(imagenes);
                    Lugar creado = lugarServicio.crearLugar(lugar);

                    for (Horario h : horarios) {
                        h.setLugar(creado);
                        lugarServicio.crearHorario(h);
                    }

                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El lugar se creó correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario ubicar el lugar dentro del mapa y subir al menos una imagen");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
                }
            }
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", msg);
        }
        return null;
    }

    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen!=null) {
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile file){
        try {
            InputStream input = file.getInputStream();
            String filename = FilenameUtils.getName(file.getFileName());
            String basename = FilenameUtils.getBaseName(filename) + "_";
            String extension = "." + FilenameUtils.getExtension(filename);
            File fileDest = File.createTempFile(basename, extension, new File(urlImagenes));
            FileOutputStream output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);
            return fileDest.getName();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void agregarHorario(){
        horarios.add(horario);
        horario = new Horario();
    }

}
