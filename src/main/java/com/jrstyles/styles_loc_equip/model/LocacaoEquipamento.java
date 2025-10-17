package com.jrstyles.styles_loc_equip.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "locacao_equipamento")
public class LocacaoEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @Column(nullable = false)
    private Integer quantidade = 1;

    @Column(nullable = false)
    private Double valorUnitario;

    @Column(nullable = false)
    private Double valorTotal;
}
