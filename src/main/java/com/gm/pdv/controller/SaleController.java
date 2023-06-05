package com.gm.pdv.controller;

import com.gm.pdv.dto.SaleDTO;
import com.gm.pdv.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/listAll")
    public ResponseEntity listAll(){
        return new ResponseEntity<>(saleService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity post(@RequestBody SaleDTO saleDTO){
        try {
            long id = saleService.save(saleDTO);
            return  new ResponseEntity<>("Sale successfully carried out: "+ id, HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
