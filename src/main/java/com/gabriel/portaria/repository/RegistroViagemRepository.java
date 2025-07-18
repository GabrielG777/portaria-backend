package com.gabriel.portaria.repository;

import java.util.List;
import java.util.Optional;

import com.gabriel.portaria.dto.ViagemDetalhadaDTO;
import com.gabriel.portaria.model.RegistroViagem;
import com.gabriel.portaria.model.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistroViagemRepository extends JpaRepository<RegistroViagem, Long> {
    @Query("SELECT new com.gabriel.portaria.dto.ViagemDetalhadaDTO(" +
            "rv.id, v.placa, f.nome, rv.destino, rv.dataSaida, rv.dataRetorno) " +
            "FROM RegistroViagem rv " +
            "JOIN rv.veiculo v " +
            "JOIN rv.motorista f")
    List<ViagemDetalhadaDTO> findAllDetalhado();

    Optional<RegistroViagem> findTopByVeiculoOrderByDataSaidaDesc(Veiculo veiculo);

}
