package com.jrstyles.styles_loc_equip.dto;

import com.jrstyles.styles_loc_equip.model.enums.TipoPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PagamentoDTO {
    private Long id;

    @NotNull(message = "Id da locação é obrigatório")
    private Long locacaoId;

    @NotNull(message = "Tipo de pagamento é obrigatório")
    private TipoPagamento tipo;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private Double valor;

    private String referencia;
}
