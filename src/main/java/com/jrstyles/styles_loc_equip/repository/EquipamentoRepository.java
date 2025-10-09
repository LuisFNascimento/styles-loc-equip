package com.jrstyles.styles_loc_equip.repository;

import com.jrstyles.styles_loc_equip.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    List<Equipamento> findByDisponivelTrue();
}

