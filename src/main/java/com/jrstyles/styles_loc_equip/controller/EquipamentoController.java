package com.jrstyles.styles_loc_equip.controller;

import com.jrstyles.styles_loc_equip.dto.EquipamentoDTO;
import com.jrstyles.styles_loc_equip.model.Equipamento;
import com.jrstyles.styles_loc_equip.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {
    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) { this.service = service; }

    @GetMapping
    public List<Equipamento> listar() { return service.listarTodos(); }

    @GetMapping("/disponiveis")
    public List<Equipamento> listarDisponiveis() { return service.listarDisponiveis(); }

    @PostMapping
    public ResponseEntity<Equipamento> criar(@Valid @RequestBody EquipamentoDTO dto) {
        Equipamento e = new Equipamento(dto.getNome(), dto.getValorDiaria());
        e.setDisponivel(dto.getDisponivel());
        Equipamento salvo = service.criar(e);
        return ResponseEntity.created(URI.create("/api/equipamentos/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public Equipamento atualizar(@PathVariable Long id, @Valid @RequestBody EquipamentoDTO dto) {
        Equipamento dados = new Equipamento(dto.getNome(), dto.getValorDiaria());
        dados.setDisponivel(dto.getDisponivel());
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
