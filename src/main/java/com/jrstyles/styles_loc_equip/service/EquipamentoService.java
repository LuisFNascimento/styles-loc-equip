package com.jrstyles.styles_loc_equip.service;

import com.jrstyles.styles_loc_equip.exception.ResourceNotFoundException;
import com.jrstyles.styles_loc_equip.model.Equipamento;
import com.jrstyles.styles_loc_equip.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipamentoService {
    private final EquipamentoRepository repo;

    public EquipamentoService(EquipamentoRepository repo) {
        this.repo = repo;
    }

    public Equipamento criar(Equipamento equipamento) {
        return repo.save(equipamento);
    }

    public Equipamento atualizar(Long id, Equipamento dados) {
        Equipamento e = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado"));
        e.setNome(dados.getNome());
        e.setValorDiaria(dados.getValorDiaria());
        e.setDisponivel(dados.getDisponivel());
        return repo.save(e);
    }

    public List<Equipamento> listarTodos() {
        return repo.findAll();
    }

    public List<Equipamento> listarDisponiveis() {
        return repo.findByDisponivelTrue();
    }

    public Equipamento buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado"));
    }

    public void deletar(Long id) {
        Equipamento e = buscarPorId(id);
        repo.delete(e);
    }
}

