package com.jrstyles.styles_loc_equip.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "locacoes")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Equipamento equipamento;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false)
    private Integer dias;

    @Column(nullable = false)
    private Double valorTotal;

    @Column(nullable = false)
    private Boolean devolvido = false;

    public Locacao() {}

    public Locacao(Equipamento equipamento, String cliente, LocalDate dataInicio, Integer dias) {
        this.equipamento = equipamento;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.dias = dias;
        this.dataFim = dataInicio.plusDays(dias);
        this.valorTotal = calcularValorTotal();
        this.devolvido = false;
    }

    public Double calcularValorTotal() {
        return equipamento.getValorDiaria() * dias;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public Equipamento getEquipamento() { return equipamento; }
    public void setEquipamento(Equipamento equipamento) { this.equipamento = equipamento; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public Integer getDias() { return dias; }
    public void setDias(Integer dias) { this.dias = dias; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Boolean getDevolvido() { return devolvido; }
    public void setDevolvido(Boolean devolvido) { this.devolvido = devolvido; }
}
