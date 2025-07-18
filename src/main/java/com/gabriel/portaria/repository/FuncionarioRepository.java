package com.gabriel.portaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.portaria.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    boolean existsByCnh(String cnh);

    Optional<Funcionario> findByNomeAndCnh(String nome, String cnh);

}
