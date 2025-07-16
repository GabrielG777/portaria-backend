package com.gabriel.portaria.controller;

import com.gabriel.portaria.model.Veiculo;
import com.gabriel.portaria.repository.VeiculoRepository;
import com.gabriel.portaria.dto.ErroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos") // nome da minha classe, quando eu for acessar a rota, vai ser
                             // http//:localhost/veiculos

public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<Veiculo> lestarCarros() {
        return veiculoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> criarCarro(@RequestBody Veiculo veiculo) { // passeo '?' ao invez de um objeto, para conseguir retornar diferentes tipos de resposta
        if (veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
            return ResponseEntity.status(409).body(new ErroResponse("Veículo com placa já cadastrado"));
        }

        veiculo.setStatus(com.gabriel.portaria.model.StatusVeiculo.NO_PATIO);
        Veiculo salvo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(salvo);
    }

}
