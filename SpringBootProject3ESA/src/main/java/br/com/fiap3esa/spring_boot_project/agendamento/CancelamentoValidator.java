package br.com.fiap3esa.spring_boot_project.agendamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class CancelamentoValidator {
    
    public void validarCancelamento(Agendamento agendamento) {
        validarStatusCancelavel(agendamento);
        validarAntecedenciaCancelamento(agendamento);
    }
    
    private void validarStatusCancelavel(Agendamento agendamento) {
        if (agendamento.getStatus() != StatusAgendamento.CONFIRMADO) {
            throw new RuntimeException("Apenas agendamentos confirmados podem ser cancelados");
        }
    }
    
    private void validarAntecedenciaCancelamento(Agendamento agendamento) {
        LocalDateTime agora = LocalDateTime.now();
        Duration antecedencia = Duration.between(agora, agendamento.getDataHoraInicio());
        
        if (antecedencia.toHours() < 24) {
            throw new RuntimeException(
                "Cancelamento deve ser feito com pelo menos 24 horas de antecedência"
            );
        }
    }
}
