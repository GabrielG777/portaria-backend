package com.gabriel.portaria.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.gabriel.portaria.dto.ErroResponse;
import com.gabriel.portaria.model.Funcionario;
import com.gabriel.portaria.repository.FuncionarioRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios") // nome da minha classe, http//:localhost/funcionarios

public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/listar")
    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarFuncionario(@RequestBody Funcionario funcionario) {
        if (funcionarioRepository.existsByCpf(funcionario.getCpf())) {
            return ResponseEntity.status(409).body(new ErroResponse("Funcionário com esse CPF já existe"));
        }
        return ResponseEntity.ok(funcionarioRepository.save(funcionario));
    }

}
