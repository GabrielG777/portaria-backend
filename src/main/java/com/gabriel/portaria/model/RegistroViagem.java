package com.gabriel.portaria.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RegistroViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Veiculo veiculo;

    @ManyToOne
    private Funcionario motorista;

    private LocalDateTime dataSaida;
    private LocalDateTime dataRetorno;

    private String destino;

    @Column(columnDefinition = "TEXT")
    private String passageiros;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Funcionario getMotorista() {
        return motorista;
    }

    public void setMotorista(Funcionario motorista) {
        this.motorista = motorista;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDateTime getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(LocalDateTime dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
