package learning.rest.webservices.web;

import learning.rest.webservices.dao.UserDAOService;
import learning.rest.webservices.model.User;
import learning.rest.webservices.web.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    @Autowired
    private UserDAOService service;

    // GET /users
    // retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // GET /users/{id}
    //retrieveUser(int id)
    @GetMapping("/users/{id}")
    public EntityModel<?> retrieveUser(@PathVariable Integer id){
        User user = service.findOne(id);
        if(null == user){
            throw new UserNotFoundException("id-"+id);
        }
        return createEntityModel(user, "all-users", "retrieveAllUsers");
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();//.path("/").build().toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer id){
        User user = service.deleteById(id);
        if(null == user){
            throw new UserNotFoundException("id-"+id);
        }
        return ResponseEntity.noContent().build();
    }

    private EntityModel<?> createEntityModel(Object object, String linkName, String methodName){
        EntityModel<?> model = EntityModel.of(object);
        Optional<Method> methodToLink = Arrays.stream(this.getClass().getDeclaredMethods()).filter(method -> methodName.equalsIgnoreCase(method.getName())).findFirst();
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(methodToLink.get());
        model.add(linkBuilder.withRel(linkName));
        return model;
    }
}
