package com.jrstyles.styles_loc_equip.controller;

import com.jrstyles.styles_loc_equip.model.Equipamento;
import com.jrstyles.styles_loc_equip.service.EquipamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listarTodos() {
        return ResponseEntity.ok(equipamentoService.listarTodos());
    }

    // 🔹 Novo endpoint: listar apenas disponíveis
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Equipamento>> listarDisponiveis() {
        return ResponseEntity.ok(equipamentoService.listarDisponiveis());
    }
}
