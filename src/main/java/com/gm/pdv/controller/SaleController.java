package com.gm.pdv.controller;

import com.gm.pdv.dto.ResponseDTO;
import com.gm.pdv.dto.SaleDTO;
import com.gm.pdv.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/listAll")
    public ResponseEntity listAll(){
        return new ResponseEntity<>(saleService.findAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable long id){
        try {
            return  new ResponseEntity<>(saleService.findById(id),HttpStatus.OK);
        }catch (Exception error){
            return  new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity post(@Valid @RequestBody SaleDTO saleDTO){
        try {
            saleService.save(saleDTO);
            return  new ResponseEntity<>(new ResponseDTO("Sale successfully carried out!"), HttpStatus.CREATED);
        }
        catch (Exception error){
            return new ResponseEntity<>(new ResponseDTO(error.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
