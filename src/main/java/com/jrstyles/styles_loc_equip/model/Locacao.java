package com.jrstyles.styles_loc_equip.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locacoes")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private Integer dias;

    @Column(nullable = false)
    private Boolean devolvido = false;

    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemLocacao> itens = new ArrayList<>();

    @Column(nullable = false)
    private Double valorTotal = 0.0;

    // Getters e Setters
    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public Integer getDias() { return dias; }
    public void setDias(Integer dias) { this.dias = dias; }
    public Boolean getDevolvido() { return devolvido; }
    public void setDevolvido(Boolean devolvido) { this.devolvido = devolvido; }
    public List<ItemLocacao> getItens() { return itens; }

    public Double getValorTotal() { return valorTotal; }

    // Métodos auxiliares
    public void adicionarEquipamento(Equipamento equipamento, Integer quantidade) {
        ItemLocacao item = new ItemLocacao();
        item.setLocacao(this);
        item.setEquipamento(equipamento);
        item.setQuantidade(quantidade);
        item.setValorUnitario(equipamento.getValorDiaria() * dias);
        item.setValorTotal(item.getValorUnitario() * quantidade);
        this.itens.add(item);
        calcularValoresTotais();
    }

    public void calcularValoresTotais() {
        this.valorTotal = itens.stream()
                .mapToDouble(ItemLocacao::getValorTotal)
                .sum();
    }
}
