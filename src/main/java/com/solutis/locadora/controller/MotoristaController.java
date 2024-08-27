package com.solutis.locadora.controller;

import com.solutis.locadora.model.Motorista;
import com.solutis.locadora.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;

@RestController
@RequestMapping("/api/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @PostMapping

    public ResponseEntity<String> cadastrar(@RequestBody Motorista motorista){
        try {
            motoristaService.salvarMotorista(motorista);
            return new ResponseEntity<>("Cadastro realizado com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
