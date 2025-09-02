package br.com.fiap3esa.spring_boot_project.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CancelamentoService {
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    
    @Autowired
    private CancelamentoValidator validator;
    
    public Agendamento cancelarAgendamento(DadosCancelamentoAgendamento dados) {
        Agendamento agendamento = agendamentoRepository.findById(dados.agendamentoId())
            .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        
        validator.validarCancelamento(agendamento);
        
        agendamento.cancelar(dados.motivo(), dados.observacao());
        
        return agendamentoRepository.save(agendamento);
    }
}
