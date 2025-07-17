package com.gabriel.portaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.portaria.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    boolean existsByCpf(String cpf);
   
}
