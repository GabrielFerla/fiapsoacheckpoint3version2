package br.com.fiap3esa.spring_boot_project.agendamento;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosAgendamento(
    @NotNull
    Long alunoId,           
    Long instrutorId,      
    
    @NotNull
    LocalDateTime dataHoraInstrucao  
) {
    public boolean isInstrutorAutomatico() {
        return instrutorId == null;
    }
}
