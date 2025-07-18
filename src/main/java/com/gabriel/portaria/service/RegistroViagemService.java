package com.gabriel.portaria.service;

import com.gabriel.portaria.model.RegistroViagem;
import com.gabriel.portaria.model.StatusVeiculo;
import com.gabriel.portaria.model.Veiculo;
import com.gabriel.portaria.dto.RetornoVeiculoDTO;
import com.gabriel.portaria.dto.SaidaVeiculoDTO;
import com.gabriel.portaria.dto.ViagemDetalhadaDTO;
import com.gabriel.portaria.model.Funcionario;
import com.gabriel.portaria.repository.FuncionarioRepository;
import com.gabriel.portaria.repository.RegistroViagemRepository;
import com.gabriel.portaria.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroViagemService {

    @Autowired
    private RegistroViagemRepository registroViagemRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<ViagemDetalhadaDTO> listarFullViagens() {
        return registroViagemRepository.findAllDetalhado();
    }

    public void registrarRetorno(RetornoVeiculoDTO dto) {
        Veiculo veiculo = veiculoRepository.findByPlaca(dto.getPlacaVeiculo());
        if (veiculo == null) {
            throw new RuntimeException("Veículo não encontrado");
        }

        if (veiculo.getStatus() != StatusVeiculo.EM_VIAGEM) {
            throw new RuntimeException("Veículo não está em viagem");
        }

        RegistroViagem viagem = registroViagemRepository
                .findTopByVeiculoOrderByDataSaidaDesc(veiculo)
                .orElseThrow(() -> new RuntimeException("Registro de viagem não encontrado"));

        if (viagem.getDataRetorno() != null) {
            throw new RuntimeException("Essa viagem já foi encerrada");
        }

        viagem.setDataRetorno(LocalDateTime.now());
        veiculo.setStatus(StatusVeiculo.NO_PATIO);

        registroViagemRepository.save(viagem);
        veiculoRepository.save(veiculo);
    }

    public void registrarSaida(SaidaVeiculoDTO dto) {
        Veiculo veiculo = veiculoRepository.findByPlaca(dto.getPlacaVeiculo());
        if (veiculo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
        }

        if (veiculo.getStatus() != StatusVeiculo.NO_PATIO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Veículo não está no pátio, verifique com a portaria");
        }

        Funcionario motorista = funcionarioRepository.findById(dto.getIdMotorista())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Motorista - funcionário não encontrado"));

        veiculo.setStatus(StatusVeiculo.EM_VIAGEM);
        veiculoRepository.save(veiculo);

        RegistroViagem registro = new RegistroViagem();
        registro.setVeiculo(veiculo);
        registro.setMotorista(motorista);
        registro.setDestino(dto.getDestino());
        registro.setPassageiros(dto.getPassageiros());
        registro.setDataSaida(LocalDateTime.now());

        registroViagemRepository.save(registro);
    }
}
