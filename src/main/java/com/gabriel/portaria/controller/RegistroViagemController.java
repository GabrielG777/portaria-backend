package com.gabriel.portaria.controller;

import com.gabriel.portaria.dto.RetornoVeiculoDTO;
import com.gabriel.portaria.dto.SaidaVeiculoDTO;
import com.gabriel.portaria.service.RegistroViagemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gabriel.portaria.dto.ViagemDetalhadaDTO;

@RestController
@RequestMapping("/registro")
public class RegistroViagemController {

    @Autowired
    private RegistroViagemService registroViagemService;

    @GetMapping("/busca")
    public List<ViagemDetalhadaDTO> listarViagenns() {
        return registroViagemService.listarFullViagens();
    }

    @PostMapping("/saida")
    public ResponseEntity<?> registrarSaida(@RequestBody SaidaVeiculoDTO dto) {
        registroViagemService.registrarSaida(dto);
        return ResponseEntity.status(201).body("Saída registrada com sucesso");
    }

    @PostMapping("/retorno")
    public ResponseEntity<?> registrarRetorno(@RequestBody RetornoVeiculoDTO dto) {
        try {
            registroViagemService.registrarRetorno(dto);
            return ResponseEntity.ok("Retorno registrado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
