package com.gabriel.portaria.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.gabriel.portaria.model.Funcionario;
import com.gabriel.portaria.repository.FuncionarioRepository;
import org.springframework.web.bind.annotation.GetMapping;

public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

}
