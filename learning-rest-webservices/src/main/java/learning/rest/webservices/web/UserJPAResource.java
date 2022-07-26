package learning.rest.webservices.web;

import learning.rest.webservices.dao.UserDAOService;
import learning.rest.webservices.model.Post;
import learning.rest.webservices.model.User;
import learning.rest.webservices.repository.PostRepository;
import learning.rest.webservices.repository.UserRepository;
import learning.rest.webservices.web.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

//    @Autowired
//    private UserDAOService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // GET /users
    // retrieveAllUsers
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    // GET /users/{id}
    //retrieveUser(int id)
    @GetMapping("/jpa/users/{id}")
    public EntityModel<?> retrieveUser(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id = "+id);
        }
        return createEntityModel(user.get(), "all-users", "retrieveAllUsers");
    }

    // POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();//.path("/").build().toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @DeleteMapping("/jpa/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsOfAUser(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id = "+id);
        }
        return userOptional.get().getPosts();
    }

    // POST /users
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createUserPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id = "+id);
        }
        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(post);
    }

    private EntityModel<?> createEntityModel(Object object, String linkName, String methodName){
        EntityModel<?> model = EntityModel.of(object);
        Optional<Method> methodToLink = Arrays.stream(this.getClass().getDeclaredMethods()).filter(method -> methodName.equalsIgnoreCase(method.getName())).findFirst();
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(methodToLink.get());
        model.add(linkBuilder.withRel(linkName));
        return model;
    }
}
