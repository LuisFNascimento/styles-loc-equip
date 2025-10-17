package com.jrstyles.styles_loc_equip.repository;

import com.jrstyles.styles_loc_equip.model.Locacao;
import com.jrstyles.styles_loc_equip.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByLocacao(Locacao locacao);
}
