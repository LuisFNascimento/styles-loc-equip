package com.jrstyles.styles_loc_equip.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LocacaoDTO {

    private Long id;

    @NotNull(message = "O ID do equipamento é obrigatório")
    private Long idEquipamento;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String cliente;

    private LocalDate dataInicio;

    @NotNull(message = "O número de dias é obrigatório")
    @Min(value = 1, message = "A locação deve ter pelo menos 1 dia")
    private Integer dias;
}
