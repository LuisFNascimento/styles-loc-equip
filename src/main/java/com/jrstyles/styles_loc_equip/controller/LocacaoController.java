package com.jrstyles.styles_loc_equip.controller;

import com.jrstyles.styles_loc_equip.dto.LocacaoDTO;
import com.jrstyles.styles_loc_equip.model.Equipamento;
import com.jrstyles.styles_loc_equip.model.Locacao;
import com.jrstyles.styles_loc_equip.service.EquipamentoService;
import com.jrstyles.styles_loc_equip.service.LocacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/locacoes")
public class LocacaoController {
    private final LocacaoService service;
    private final EquipamentoService equipamentoService;

    public LocacaoController(LocacaoService service, EquipamentoService equipamentoService) {
        this.service = service;
        this.equipamentoService = equipamentoService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Locacao> registrar(@Valid @RequestBody LocacaoDTO dto) {
        Equipamento equipamento = equipamentoService.buscarPorId(dto.getIdEquipamento());
        Locacao loc = new Locacao(equipamento, dto.getCliente(), dto.getDataInicio() == null ? LocalDate.now() : dto.getDataInicio(), dto.getDias());
        Locacao salvo = service.registrar(loc);
        return ResponseEntity.created(URI.create("/api/locacoes/" + salvo.getId())).body(salvo);
    }

    @PostMapping("/{id}/devolver")
    public ResponseEntity<?> devolver(@PathVariable Long id) {
        service.devolver(id);
        return ResponseEntity.ok().build();
    }
}
