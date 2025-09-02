package br.com.fiap3esa.spring_boot_project.agendamento;

import java.time.LocalDateTime;

public record DadosListagemAgendamento(
        Long id,
        String nomeAluno,
        String nomeInstrutor,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        String status
) {
    public DadosListagemAgendamento(Agendamento agendamento) {
        this(
            agendamento.getId(),
            agendamento.getAluno().getNome(),
            agendamento.getInstrutor().getNome(),
            agendamento.getDataHoraInicio(),
            agendamento.getDataHoraFim(),
            agendamento.getStatus().name()
        );
    }
}
