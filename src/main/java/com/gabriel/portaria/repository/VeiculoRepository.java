package com.gabriel.portaria.repository;

import com.gabriel.portaria.model.StatusVeiculo;
import com.gabriel.portaria.model.Veiculo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByStatus(StatusVeiculo status);

    Veiculo findByPlaca(String placa);

    boolean existsByPlaca(String placa);

}
