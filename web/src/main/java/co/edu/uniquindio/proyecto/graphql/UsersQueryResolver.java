package co.edu.uniquindio.proyecto.graphql;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UsersQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UsuarioRepo usuarioRepo;

    public List<Usuario> getUsers(DataFetchingEnvironment environment){
        //DataFetchingFieldSelectionSet s = environment.getSelectionSet();
        //List<Specification<Usuario>> specifications = new ArrayList<>();

        return usuarioRepo.findAll();

    }

    public Usuario getUser(Integer id, String nombre, DataFetchingEnvironment environment){
        return usuarioRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
