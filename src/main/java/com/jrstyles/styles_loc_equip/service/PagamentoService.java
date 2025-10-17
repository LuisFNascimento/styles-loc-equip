package com.jrstyles.styles_loc_equip.service;

import com.jrstyles.styles_loc_equip.model.Locacao;
import com.jrstyles.styles_loc_equip.model.Pagamento;
import com.jrstyles.styles_loc_equip.model.enums.TipoPagamento;
import com.jrstyles.styles_loc_equip.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final LocacaoService locacaoService;

    public PagamentoService(PagamentoRepository pagamentoRepository, LocacaoService locacaoService) {
        this.pagamentoRepository = pagamentoRepository;
        this.locacaoService = locacaoService;
    }

    // Listar todos os pagamentos
    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    // Listar pagamentos por locação
    public List<Pagamento> listarPorLocacao(Long idLocacao) {
        Locacao locacao = locacaoService.buscarPorId(idLocacao);
        return pagamentoRepository.findByLocacao(locacao);
    }

    // Registrar novo pagamento
    @Transactional
    public Pagamento registrar(Pagamento pagamento) {
        // Buscar a locação
        Locacao locacao = locacaoService.buscarPorId(pagamento.getLocacao().getId());
        pagamento.setLocacao(locacao);

        // Validar tipo de pagamento
        if (pagamento.getTipo() == null) {
            throw new IllegalArgumentException("Tipo de pagamento inválido");
        }

        return pagamentoRepository.save(pagamento);
    }

    // Atualizar pagamento existente
    @Transactional
    public Pagamento atualizar(Long id, Pagamento pagamentoAtualizado) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado"));

        pagamento.setValor(pagamentoAtualizado.getValor());
        pagamento.setTipo(pagamentoAtualizado.getTipo());
        pagamento.setReferencia(pagamentoAtualizado.getReferencia());
        pagamento.setDataPagamento(pagamentoAtualizado.getDataPagamento());

        return pagamentoRepository.save(pagamento);
    }

    // Deletar pagamento
    @Transactional
    public void deletar(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pagamento não encontrado");
        }
        pagamentoRepository.deleteById(id);
    }
}

