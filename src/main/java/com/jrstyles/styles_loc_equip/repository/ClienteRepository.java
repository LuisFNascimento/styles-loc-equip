package com.jrstyles.styles_loc_equip.repository;

import com.jrstyles.styles_loc_equip.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}

