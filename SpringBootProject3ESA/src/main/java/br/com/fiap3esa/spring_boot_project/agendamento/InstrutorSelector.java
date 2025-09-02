package br.com.fiap3esa.spring_boot_project.agendamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap3esa.spring_boot_project.instrutor.Instrutor;
import br.com.fiap3esa.spring_boot_project.instrutor.InstrutorRepository;

@Component
public class InstrutorSelector {
    
    @Autowired
    private InstrutorRepository instrutorRepository;
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    
    public Instrutor selecionarInstrutorDisponivel(LocalDateTime dataHora) {
        List<Instrutor> instrutoresAtivos = instrutorRepository.findAtivos();
        
        if (instrutoresAtivos.isEmpty()) {
            throw new RuntimeException("Nenhum instrutor ativo encontrado no sistema");
        }
        
        List<Instrutor> disponiveis = instrutoresAtivos.stream()
            .filter(instrutor -> !temConflitoHorario(instrutor.getId(), dataHora))
            .collect(Collectors.toList());
        
        if (disponiveis.isEmpty()) {
            throw new RuntimeException(
                "Nenhum instrutor disponível no horário " + 
                dataHora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            );
        }
        
        Random random = new Random();
        Instrutor selecionado = disponiveis.get(random.nextInt(disponiveis.size()));
        
        return selecionado;
    }
    
    private boolean temConflitoHorario(Long instrutorId, LocalDateTime dataHora) {
        LocalDateTime inicio = dataHora;
        LocalDateTime fim = dataHora.plusHours(1);
        
        return agendamentoRepository.existsConflitoInstrutor(instrutorId, inicio, fim);
    }
}
