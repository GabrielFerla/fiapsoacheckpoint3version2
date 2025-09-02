package br.com.fiap3esa.spring_boot_project.agendamento;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoValidator {
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    
    public void validarHorarioFuncionamento(LocalDateTime dataHora) {
        if (dataHora.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new RuntimeException("Auto-escola não funciona aos domingos");
        }
        
        LocalTime hora = dataHora.toLocalTime();
        if (hora.isBefore(LocalTime.of(6, 0)) || hora.isAfter(LocalTime.of(21, 0))) {
            throw new RuntimeException("Horário fora do funcionamento (06:00-21:00)");
        }
    }
    
    public void validarAntecedenciaMinima(LocalDateTime dataHora) {
        LocalDateTime agora = LocalDateTime.now();
        Duration antecedencia = Duration.between(agora, dataHora);
        
        if (antecedencia.toMinutes() < 30) {
            throw new RuntimeException(
                "Agendamento deve ser feito com pelo menos 30 minutos de antecedência"
            );
        }
    }
    
    public void validarLimiteDiarioAluno(Long alunoId, LocalDateTime dataHora) {
        var data = dataHora.toLocalDate();
        long agendamentosDia = agendamentoRepository.countAgendamentosDiaAluno(alunoId, data);
        
        if (agendamentosDia >= 2) {
            throw new RuntimeException(
                "Aluno já possui 2 agendamentos no dia " + 
                data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            );
        }
    }
    
    public void validarConflitoInstrutor(Long instrutorId, LocalDateTime dataHora) {
        LocalDateTime inicio = dataHora;
        LocalDateTime fim = dataHora.plusHours(1);
        
        boolean conflito = agendamentoRepository.existsConflitoInstrutor(instrutorId, inicio, fim);
        
        if (conflito) {
            throw new RuntimeException("Instrutor já possui agendamento neste horário");
        }
    }
}
