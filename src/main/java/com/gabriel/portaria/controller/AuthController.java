package com.gabriel.portaria.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.portaria.dto.ErroResponse;
import com.gabriel.portaria.model.Funcionario;
import com.gabriel.portaria.model.LoginRequest;
import com.gabriel.portaria.repository.FuncionarioRepository;
import com.gabriel.portaria.util.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final FuncionarioRepository funcionarioRepository;

    public AuthController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findByNomeAndCnh(loginRequest.getNome(), loginRequest.getCnh());

        if (funcionarioOpt.isPresent()) {
            String token = JwtUtil.gerarToken(funcionarioOpt.get());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(new ErroResponse("Funcionário não encontrado ou CNH inválida"));
        }
    }
}
