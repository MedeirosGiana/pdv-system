package com.gm.pdv.controller;

import com.gm.pdv.entity.User;
import com.gm.pdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/listAll")
    public ResponseEntity listAll(){

        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody User user){
        try {
            user.setEnabled(true);
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED );
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody User user){
       Optional<User> userToEdit  = userRepository.findById(user.getId());
       if (userToEdit.isPresent()){
           userRepository.save(user);
           return new ResponseEntity<>(user,HttpStatus.OK);
       }
       return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return  new ResponseEntity<>("User with id " + id + ", successfully removed!",HttpStatus.OK);

        }catch (Exception error){
            return  new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
