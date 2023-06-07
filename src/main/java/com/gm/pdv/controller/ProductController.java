package com.gm.pdv.controller;

import com.gm.pdv.dto.ResponseDTO;
import com.gm.pdv.entity.Product;
import com.gm.pdv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/listAll")
    public ResponseEntity listAll(){
        return  new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity insert (@Valid @RequestBody Product product){
        try {
            return  new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
        }catch (Exception error){
            return  new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Product product){
        try {
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
        }catch (Exception error){
            return  new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public  ResponseEntity delete(@PathVariable Long id){
        try {
            productRepository.deleteById(id);
            return  new ResponseEntity<>(new ResponseDTO("Product with id " + id + ", successfully removed!"), HttpStatus.OK);

        } catch (EmptyResultDataAccessException error){
            return  new ResponseEntity<>(new ResponseDTO("The product could not be found!"),HttpStatus.BAD_REQUEST);
        }
        catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()), HttpStatus.NO_CONTENT);
        }
    }
}
