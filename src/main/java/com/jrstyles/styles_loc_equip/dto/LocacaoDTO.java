package com.jrstyles.styles_loc_equip.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class LocacaoDTO {

    @NotNull
    private Long idCliente;

    @NotNull
    @Min(1)
    private Integer dias;

    private LocalDate dataInicio;

    @NotNull
    private List<ItemLocacaoDTO> equipamentos;

    // Getters e Setters
    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public Integer getDias() { return dias; }
    public void setDias(Integer dias) { this.dias = dias; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public List<ItemLocacaoDTO> getEquipamentos() { return equipamentos; }
    public void setEquipamentos(List<ItemLocacaoDTO> equipamentos) { this.equipamentos = equipamentos; }

    public static class ItemLocacaoDTO {
        @NotNull
        private Long idEquipamento;
        @NotNull
        @Min(1)
        private Integer quantidade;

        // Getters e Setters
        public Long getIdEquipamento() { return idEquipamento; }
        public void setIdEquipamento(Long idEquipamento) { this.idEquipamento = idEquipamento; }
        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    }
}
