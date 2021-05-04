package co.edu.uniquindio.proyecto.graphql;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.input.UsuarioInput;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    public Usuario createUser(UsuarioInput u){
        Optional<Ciudad> c = ciudadRepo.findById(u.getCiudadId());
        if(c.isPresent()){
            Usuario user = new Usuario(u.getNombre(), u.getNickname(), u.getEmail(), u.getPassword(), c.get());
            return usuarioRepo.save(user);
        }
        return null;
    }

    public boolean deleteUser(Integer id){
        Optional<Usuario> u = usuarioRepo.findById(id);
        if(u.isPresent()) {
            usuarioRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Usuario updateUser(UsuarioInput u){
        Optional<Usuario> findUser = usuarioRepo.findById(u.getId());
        if(findUser.isPresent()) {
            Usuario update = findUser.get();
            if(u.getCiudadId()!=null){
                Optional<Ciudad> c = ciudadRepo.findById(u.getCiudadId());
                c.ifPresent(update::setCiudad);
            }

            if(u.getNombre()!=null) update.setNombre(u.getNombre());
            if(u.getEmail()!=null) update.setEmail(u.getEmail());
            if(u.getNickname()!=null) update.setNickname(u.getNickname());
            if(u.getPassword()!=null) update.setPassword(u.getPassword());

            return usuarioRepo.save(update);
        }
        return null;
    }
}
