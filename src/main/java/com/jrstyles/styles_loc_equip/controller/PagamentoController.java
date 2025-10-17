package com.jrstyles.styles_loc_equip.controller;

import com.jrstyles.styles_loc_equip.model.Pagamento;
import com.jrstyles.styles_loc_equip.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // Listar todos os pagamentos
    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos() {
        List<Pagamento> pagamentos = pagamentoService.listarTodos();
        return ResponseEntity.ok(pagamentos);
    }

    // Listar pagamentos de uma locação específica
    @GetMapping("/locacao/{id}")
    public ResponseEntity<List<Pagamento>> listarPorLocacao(@PathVariable Long idLocacao) {
        List<Pagamento> pagamentos = pagamentoService.listarPorLocacao(idLocacao);
        return ResponseEntity.ok(pagamentos);
    }

    // Registrar um novo pagamento
    @PostMapping
    public ResponseEntity<Pagamento> registrar(@Valid @RequestBody Pagamento pagamento) {
        Pagamento salvo = pagamentoService.registrar(pagamento);
        return ResponseEntity.ok(salvo);
    }

    // Atualizar um pagamento existente
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(@PathVariable Long id, @Valid @RequestBody Pagamento pagamento) {
        Pagamento atualizado = pagamentoService.atualizar(id, pagamento);
        return ResponseEntity.ok(atualizado);
    }

    // Deletar um pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

