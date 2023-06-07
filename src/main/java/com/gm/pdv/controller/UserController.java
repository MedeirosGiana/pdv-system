package com.gm.pdv.controller;

import com.gm.pdv.dto.ResponseDTO;
import com.gm.pdv.dto.UserDTO;
import com.gm.pdv.entity.User;
import com.gm.pdv.exceptions.NoItemException;
import com.gm.pdv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity insert(@Valid @RequestBody UserDTO user){
        try {
            user.setEnabled(true);
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED );
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody UserDTO user){
       try {
           return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
       } catch (Exception error){
           return new ResponseEntity<>(new ResponseDTO(error.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return  new ResponseEntity<>(new ResponseDTO("User with id " + id + ", successfully removed!"), HttpStatus.OK);

        } catch (EmptyResultDataAccessException error){
            return  new ResponseEntity<>(new ResponseDTO("The user could not be found!"),HttpStatus.BAD_REQUEST);
        }
        catch (Exception error){
            return  new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
