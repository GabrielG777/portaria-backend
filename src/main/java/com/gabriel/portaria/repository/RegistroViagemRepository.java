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
            "v.id, veiculo.placa, motorista.nome, v.destino, v.dataSaida, v.dataRetorno) " +
            "FROM RegistroViagem v " +
            "JOIN v.veiculo veiculo " +
            "JOIN v.motorista motorista " +
            "ORDER BY v.dataSaida DESC")
    List<ViagemDetalhadaDTO> buscarHistorico();

    Optional<RegistroViagem> findTopByVeiculoOrderByDataSaidaDesc(Veiculo veiculo);

}
