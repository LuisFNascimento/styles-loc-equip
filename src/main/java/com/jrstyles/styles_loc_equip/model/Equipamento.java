package com.jrstyles.styles_loc_equip.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipamentos")
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double valorDiaria;

    @Column(nullable = false)
    private Boolean disponivel = true;

    public Equipamento() {}

    public Equipamento(String nome, Double valorDiaria) {
        this.nome = nome;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getValorDiaria() { return valorDiaria; }
    public void setValorDiaria(Double valorDiaria) { this.valorDiaria = valorDiaria; }
    public Boolean getDisponivel() { return disponivel; }
    public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
}
