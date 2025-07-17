package com.gabriel.portaria.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.portaria.dto.ErroResponse;
import com.gabriel.portaria.model.Veiculo;
import com.gabriel.portaria.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculos") // nome da minha classe, quando eu for acessar a rota, vai ser
                             // http//:localhost/veiculos

public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/teste")
    public String teste() {
        System.out.println(">>> chegou no /veiculos/teste");
        return "rota funcionando";
    }

    @GetMapping
    public List<Veiculo> lestarCarros() {
        return veiculoRepository.findAll();
    }

    @GetMapping("/placa/{placa}") // http://localhost:8080/veiculos/placa/
    public ResponseEntity<?> buscarPlaca(@PathVariable String placa) {
        Veiculo veiculo = veiculoRepository.findByPlaca(placa);
        if (veiculo == null) {
            return ResponseEntity.status(404).body(new ErroResponse("Veículo não encontrado"));
        }
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<?> criarCarro(@RequestBody Veiculo veiculo) { // passeo '?' ao invez de um objeto, para
                                                                        // conseguir retornar diferentes tipos de
                                                                        // resposta
        if (veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
            return ResponseEntity.status(409).body(new ErroResponse("Veículo com placa já cadastrado"));
        }

        veiculo.setStatus(com.gabriel.portaria.model.StatusVeiculo.NO_PATIO);
        Veiculo salvo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarCarro(@PathVariable Long id, @RequestBody Map<String, Object> updates) {

        return veiculoRepository.findById(id).<ResponseEntity<?>>map(veiculo -> {
            // if (veiculoDetalhe.getPlaca() != null) {
            // veiculo.setPlaca(veiculoDetalhe.getPlaca());
            // }
            // if (veiculoDetalhe.getModelo() != null) {
            // veiculo.setModelo(veiculoDetalhe.getModelo()); <- forma que achei que
            // funcionaria, deixarei salvo para caso de estudo
            // }
            // if (veiculoDetalhe.getStatus() != null) {
            // veiculo.setStatus(veiculoDetalhe.getStatus());
            // }

            updates.forEach((chave, valor) -> {
                switch (chave) {
                    case "placa" -> veiculo.setPlaca((String) valor);
                    case "modelo" -> veiculo.setModelo((String) valor);
                    case "status" ->
                        veiculo.setStatus(com.gabriel.portaria.model.StatusVeiculo.valueOf((String) valor));
                }
            });

            Veiculo atualizado = veiculoRepository.save(veiculo);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.status(404).body(new ErroResponse("Veículo não encontrado")));
    }

}