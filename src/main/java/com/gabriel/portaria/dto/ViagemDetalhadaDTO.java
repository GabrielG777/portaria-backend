package com.gabriel.portaria.dto;

import java.time.LocalDateTime;

public class ViagemDetalhadaDTO {
    private final Long id;
    private final String placaVeiculo;
    private final String nomeMotorista;
    private final String destino;
    private final LocalDateTime dataSaida;
    private final LocalDateTime dataRetorno;

    public ViagemDetalhadaDTO(Long id, String placaVeiculo, String nomeMotorista,
            String destino, LocalDateTime dataSaida, LocalDateTime dataRetorno) {
        this.id = id;
        this.placaVeiculo = placaVeiculo;
        this.nomeMotorista = nomeMotorista;
        this.destino = destino;
        this.dataSaida = dataSaida;
        this.dataRetorno = dataRetorno;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public LocalDateTime getDataRetorno() {
        return dataRetorno;
    }
}
