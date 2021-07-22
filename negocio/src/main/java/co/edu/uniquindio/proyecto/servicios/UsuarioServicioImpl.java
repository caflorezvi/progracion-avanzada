package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Lugar;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public boolean estaDisponible(String email){
        Optional<Usuario> usuarioEmail = usuarioRepo.findByEmail(email);
        return usuarioEmail.isEmpty();
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {

        Optional<Usuario> usuarioNick = usuarioRepo.findByNickname(u.getNickname());
        if(usuarioNick.isPresent()){
            throw new Exception("El nickname ya existe");
        }

        if(!estaDisponible(u.getEmail())){
            throw new Exception("El email ya existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(u.getId());

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el id dado");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public void eliminarUsuario(Integer id) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el id dado");
        }

        usuarioRepo.deleteById(id);
    }

    @Override
    public Usuario obtenerUsuario(Integer id) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findById(id);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con el id dado");
        }

        return usuario.get();
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Lugar> listarFavoritosUsuario(Integer id) {
        return usuarioRepo.obtenerLugaresFavoritos(id);
    }

    @Override
    public List<Lugar> obtenerMisLugares(Integer idUsuario) {
        return usuarioRepo.obtenerLugaresCreados(idUsuario);
    }

}