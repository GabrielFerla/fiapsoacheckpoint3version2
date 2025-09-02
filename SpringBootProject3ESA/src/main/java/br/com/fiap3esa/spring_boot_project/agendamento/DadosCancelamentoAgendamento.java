package br.com.fiap3esa.spring_boot_project.agendamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoAgendamento(
    @NotNull
    Long agendamentoId,
    
    @NotNull
    MotivoCancelamento motivo
) {
}
