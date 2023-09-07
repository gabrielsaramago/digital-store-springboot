package com.gsaramago.digitalstorespring.resources;

import com.gsaramago.digitalstorespring.model.DTO.UserDto;
import com.gsaramago.digitalstorespring.model.User;
import com.gsaramago.digitalstorespring.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> list  = userService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user){
        UserDto userDto = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User user){
        UserDto userDto = userService.updateUser(id, user);
        return ResponseEntity.ok().body(userDto);
    }

}
