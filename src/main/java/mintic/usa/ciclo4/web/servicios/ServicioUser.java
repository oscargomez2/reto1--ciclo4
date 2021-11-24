
package mintic.usa.ciclo4.web.servicios;

import java.util.List;
import java.util.Optional;
import mintic.usa.ciclo4.web.modelos.User;
import mintic.usa.ciclo4.web.repositorios.crud.RepositorioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */
@Service
public class ServicioUser {
    @Autowired
    private RepositorioUser repositorioUser;
    
    public List<User> getAll(){
        return (List<User>) repositorioUser.getAll();
    }
    
    public Optional<User> getUser(int idUser){
        return repositorioUser.getUser(idUser);
    }
    
    public User save(User user){
        if(user.getEmail()==null || user.getPassword()==null || user.getName()==null){
            return user;
        }else{
            List<User> existe= repositorioUser.getUsersByNameOrEmail(user.getName(), user.getEmail());
            if(existe.isEmpty()){
                if(user.getId()==null){
                    return repositorioUser.save(user);
                }else{
                    Optional<User> existe1 = repositorioUser.getUser(user.getId());
                    if(existe1.isEmpty()){
                        return repositorioUser.save(user);
                    }else{
                        return user;
                    }
                }
                
            }else{
                return user;
            }
        }
        
    }

    public boolean existe(String email){
        return repositorioUser.existe(email).isPresent();
    }
   
    public User existeUser(String email, String password){
        Optional<User> userExiste= repositorioUser.existeUser(email, password);
        //System.out.println(objeto.toString());
        if(userExiste.isEmpty()){
            User user= new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName("NO DEFINIDO");
            //System.out.println(user.toString());
            return user;
        }
        return userExiste.get();
    }
}
