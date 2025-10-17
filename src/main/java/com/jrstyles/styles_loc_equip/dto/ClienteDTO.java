package com.jrstyles.styles_loc_equip.dto;

import com.jrstyles.styles_loc_equip.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    private String email;

    private Endereco endereco;
}
