package com.gm.pdv.controller;

import com.gm.pdv.entity.Product;
import com.gm.pdv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(@Autowired  ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/listAll")
    public ResponseEntity listAll(){
        return  new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity insert (@RequestBody Product product){
        try {
            return  new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);

        }catch (Exception error){
            return  new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Product product){
        try {
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);

        }catch (Exception error){
            return  new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public  ResponseEntity delete(@PathVariable Long id){
        try {
            productRepository.deleteById(id);
            return  new ResponseEntity<>("Product with id " + id + ", successfully removed!", HttpStatus.OK);

        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

}
