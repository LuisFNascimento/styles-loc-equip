package com.jrstyles.styles_loc_equip.model;

import com.jrstyles.styles_loc_equip.model.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locacao_id")
    @JsonBackReference
    private Locacao locacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipo;

    @Column(nullable = false)
    private Double valor;

    private String referencia; // ex: nº do boleto, chave PIX, últimos 4 do cartão

    @Column(nullable = false)
    private LocalDateTime dataPagamento = LocalDateTime.now();

    public Pagamento() {}

    public Pagamento(Locacao locacao, TipoPagamento tipo, Double valor, String referencia) {
        this.locacao = locacao;
        this.tipo = tipo;
        this.valor = valor;
        this.referencia = referencia;
        this.dataPagamento = LocalDateTime.now();
    }
}
