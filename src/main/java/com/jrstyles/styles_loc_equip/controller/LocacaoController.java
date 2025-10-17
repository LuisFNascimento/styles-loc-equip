package com.jrstyles.styles_loc_equip.controller;

import com.jrstyles.styles_loc_equip.dto.LocacaoDTO;
import com.jrstyles.styles_loc_equip.model.Cliente;
import com.jrstyles.styles_loc_equip.model.Equipamento;
import com.jrstyles.styles_loc_equip.model.Locacao;
import com.jrstyles.styles_loc_equip.service.ClienteService;
import com.jrstyles.styles_loc_equip.service.EquipamentoService;
import com.jrstyles.styles_loc_equip.service.LocacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;
    private final ClienteService clienteService;
    private final EquipamentoService equipamentoService;

    public LocacaoController(LocacaoService locacaoService, ClienteService clienteService,
                             EquipamentoService equipamentoService) {
        this.locacaoService = locacaoService;
        this.clienteService = clienteService;
        this.equipamentoService = equipamentoService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(locacaoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Locacao> registrar(@Valid @RequestBody LocacaoDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.getIdCliente());
        Locacao loc = new Locacao();
        loc.setCliente(cliente);
        loc.setDias(dto.getDias());
        loc.setDataInicio(dto.getDataInicio() != null ? dto.getDataInicio() : java.time.LocalDate.now());

        dto.getEquipamentos().forEach(itemDto -> {
            Equipamento eq = equipamentoService.buscarPorId(itemDto.getIdEquipamento());
            loc.adicionarEquipamento(eq, itemDto.getQuantidade());
        });

        Locacao salvo = locacaoService.registrar(loc);
        return ResponseEntity.created(URI.create("/api/locacoes/" + salvo.getId())).body(salvo);
    }

    @PostMapping("/{id}/devolver")
    public ResponseEntity<?> devolver(@PathVariable Long id) {
        locacaoService.devolver(id);
        return ResponseEntity.ok().build();
    }
}
