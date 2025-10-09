package com.jrstyles.styles_loc_equip.service;

import com.jrstyles.styles_loc_equip.exception.ResourceNotFoundException;
import com.jrstyles.styles_loc_equip.model.Equipamento;
import com.jrstyles.styles_loc_equip.model.Locacao;
import com.jrstyles.styles_loc_equip.repository.LocacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LocacaoService {
    private final LocacaoRepository repo;
    private final EquipamentoService equipamentoService;

    public LocacaoService(LocacaoRepository repo, EquipamentoService equipamentoService) {
        this.repo = repo;
        this.equipamentoService = equipamentoService;
    }

    @Transactional
    public Locacao registrar(Locacao locacao) {
        Equipamento equipamento = equipamentoService.buscarPorId(locacao.getEquipamento().getId());
        if (!equipamento.getDisponivel()) {
            throw new IllegalStateException("Equipamento não disponível para locação");
        }
        equipamento.setDisponivel(false);
        equipamentoService.atualizar(equipamento.getId(), equipamento);
        return repo.save(locacao);
    }

    @Transactional
    public Locacao devolver(Long locacaoId) {
        Locacao loc = repo.findById(locacaoId).orElseThrow(() -> new ResourceNotFoundException("Locação não encontrada"));
        Equipamento e = loc.getEquipamento();
        e.setDisponivel(true);
        equipamentoService.atualizar(e.getId(), e);
        // opcional: remover a locação ou marcar data de devolucao
        return loc;
    }

    public List<Locacao> listarTodos() {
        return repo.findAll();
    }

    public Locacao buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Locação não encontrada"));
    }
}

