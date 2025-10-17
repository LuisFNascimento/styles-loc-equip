package com.jrstyles.styles_loc_equip.service;

import com.jrstyles.styles_loc_equip.model.Equipamento;
import com.jrstyles.styles_loc_equip.model.Locacao;
import com.jrstyles.styles_loc_equip.model.ItemLocacao;
import com.jrstyles.styles_loc_equip.repository.LocacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;

    public LocacaoService(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    public List<Locacao> listarTodos() {
        return locacaoRepository.findAll();
    }

    @Transactional
    public Locacao registrar(Locacao locacao) {
        // Garantir que equipamentos não estão locados
        for (ItemLocacao item : locacao.getItens()) {
            Equipamento eq = item.getEquipamento();
            if (!eq.getDisponivel()) {
                throw new RuntimeException("Equipamento " + eq.getNome() + " já está alugado.");
            }
            eq.setDisponivel(false); // marca como indisponível
        }

        locacao.calcularValoresTotais();
        return locacaoRepository.save(locacao);
    }

    @Transactional
    public void devolver(Long id) {
        Locacao loc = locacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locação não encontrada"));
        loc.setDevolvido(true);
        loc.getItens().forEach(item -> item.getEquipamento().setDisponivel(true));
        locacaoRepository.save(loc);
    }
    @Transactional(readOnly = true)
    public Locacao buscarPorId(Long id) {
        return locacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locação não encontrada"));
    }

}
