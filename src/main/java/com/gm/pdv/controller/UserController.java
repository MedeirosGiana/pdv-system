package com.gm.pdv.controller;

import com.gm.pdv.dto.ResponseDTO;
import com.gm.pdv.dto.UserDTO;
import com.gm.pdv.entity.User;
import com.gm.pdv.exceptions.NoItemException;
import com.gm.pdv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listAll")
    public ResponseEntity listAll(){

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody User user){
        try {
            user.setEnabled(true);
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED );
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody User user){
       try {
           return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
       }catch (NoItemException error){
           return new ResponseEntity<>(new ResponseDTO(error.getMessage(), user),HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return  new ResponseEntity<>("User with id " + id + ", successfully removed!",HttpStatus.OK);

        }catch (Exception error){
            return  new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
