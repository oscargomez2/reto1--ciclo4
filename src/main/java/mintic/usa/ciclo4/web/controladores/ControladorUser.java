
package mintic.usa.ciclo4.web.controladores;

import java.util.List;
import mintic.usa.ciclo4.web.modelos.User;
import mintic.usa.ciclo4.web.servicios.ServicioUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ControladorUser {
    /**
     * Atributo de la clase ServicioUser
     */
    @Autowired
    private ServicioUser servicios;
    
    /**
     * Metodo para obtener los registros de la bd
     * @return 
     */
    @GetMapping("/all")
    public List<User> getUsers() {
        return servicios.getAll();
    }
    
    /**
     * Metodo para registrar un usuario
     * @param user
     * @return 
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return servicios.save(user);
    }
    
    /**
     * Metodo para consultar un usuario por email
     * @param email
     * @return 
     */
    @GetMapping("/{email}")
    public boolean existe(@PathVariable("email") String email) {
        return servicios.existe(email);
    }
    
    /**
     * Metodo para consultar un usuario por email y password
     * @param email
     * @param password
     * @return 
     */
    @GetMapping("/{email}/{password}")
    public User existeUser(@PathVariable("email") String email, @PathVariable("password") String password) {
        return servicios.existeUser(email, password);
    }
}
