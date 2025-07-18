package com.gabriel.portaria.repository;

import com.gabriel.portaria.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    
    Veiculo findByPlaca(String placa);

    boolean existsByPlaca(String placa);

}
