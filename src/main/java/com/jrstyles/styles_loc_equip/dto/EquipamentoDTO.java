package com.jrstyles.styles_loc_equip.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EquipamentoDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull
    @Min(value = 0, message = "Valor diário inválido")
    private Double valorDiaria;

    private Boolean disponivel = true;
}


