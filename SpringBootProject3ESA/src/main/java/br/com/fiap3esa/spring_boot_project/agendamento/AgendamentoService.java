package br.com.fiap3esa.spring_boot_project.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap3esa.spring_boot_project.aluno.Aluno;
import br.com.fiap3esa.spring_boot_project.aluno.AlunoRepository;
import br.com.fiap3esa.spring_boot_project.instrutor.Instrutor;
import br.com.fiap3esa.spring_boot_project.instrutor.InstrutorRepository;

@Service
@Transactional
public class AgendamentoService {
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private InstrutorRepository instrutorRepository;
    
    @Autowired
    private AgendamentoValidator validator;
    
    @Autowired
    private InstrutorSelector instrutorSelector;
    
    public Agendamento agendar(DadosAgendamento dados) {
        validator.validarHorarioFuncionamento(dados.dataHoraInstrucao());
        validator.validarAntecedenciaMinima(dados.dataHoraInstrucao());
        
        Aluno aluno = validarEbuscarAluno(dados.alunoId());
        
        Instrutor instrutor;
        if (dados.isInstrutorAutomatico()) {
            instrutor = instrutorSelector.selecionarInstrutorDisponivel(
                dados.dataHoraInstrucao()
            );
        } else {
            instrutor = validarEbuscarInstrutor(dados.instrutorId());
        }
        
        validator.validarLimiteDiarioAluno(aluno.getId(), dados.dataHoraInstrucao());
        validator.validarConflitoInstrutor(instrutor.getId(), dados.dataHoraInstrucao());
        
        Agendamento agendamento = new Agendamento(aluno, instrutor, dados.dataHoraInstrucao());
        return agendamentoRepository.save(agendamento);
    }
    
    private Aluno validarEbuscarAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        
        if (!aluno.getAtivo()) {
            throw new RuntimeException("Não é possível agendar com aluno inativo");
        }
        
        return aluno;
    }
    
    private Instrutor validarEbuscarInstrutor(Long instrutorId) {
        Instrutor instrutor = instrutorRepository.findById(instrutorId)
            .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));
        
        if (!instrutor.getAtivo()) {
            throw new RuntimeException("Não é possível agendar com instrutor inativo");
        }
        
        return instrutor;
    }
}
