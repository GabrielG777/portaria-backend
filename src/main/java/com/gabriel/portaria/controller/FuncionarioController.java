package com.gabriel.portaria.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.gabriel.portaria.dto.ErroResponse;
import com.gabriel.portaria.model.Funcionario;
import com.gabriel.portaria.repository.FuncionarioRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios") // nome da minha classe, http//:localhost/funcionarios

public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/listar")
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarFuncionario(@RequestBody Funcionario funcionario) {
        if (funcionarioRepository.existsByCnh(funcionario.getCnh())) {
            return ResponseEntity.status(409).body(new ErroResponse("Funcionário com essa CNH já existe"));
        }
        return ResponseEntity.ok(funcionarioRepository.save(funcionario));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return funcionarioRepository.findById(id).<ResponseEntity<?>>map(funcionario -> {
            try {
                updates.forEach((chave, valor) -> {
                    switch (chave) {
                        case "nome" -> funcionario.setNome((String) valor);
                        case "cargo" -> funcionario.setCargo((String) valor);
                        case "cnh" -> funcionario.setCnh((String) valor);
                    }
                });

                return ResponseEntity.ok(funcionarioRepository.save(funcionario));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new ErroResponse("Erro ao atualizar funcionário"));
            }
        }).orElse(ResponseEntity.status(404).body(new ErroResponse("Funcionário não encontrado")));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id) {
        if (!funcionarioRepository.existsById(id)) {
            return ResponseEntity.status(404).body(new ErroResponse("Funcionário com esse ID não foi encontrado"));
        }
        funcionarioRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Funcionáario deletado com sucesso"));
    }

}
