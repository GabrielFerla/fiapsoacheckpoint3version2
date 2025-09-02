package br.com.fiap3esa.spring_boot_project.instrutor;

import br.com.fiap3esa.spring_boot_project.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoInstrutor(
        @NotBlank
        String nome,
        
        String telefone,
        
        @Valid
        DadosEndereco endereco
) {
}
