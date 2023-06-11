package com.gm.pdv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/info")
public class PDVInfoController {

    @GetMapping()
    public ResponseEntity get(){
        return new ResponseEntity<>("PDV info", HttpStatus.OK);
    }
}
