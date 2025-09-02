package br.com.fiap3esa.spring_boot_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap3esa.spring_boot_project.agendamento.Agendamento;
import br.com.fiap3esa.spring_boot_project.agendamento.AgendamentoRepository;
import br.com.fiap3esa.spring_boot_project.agendamento.AgendamentoService;
import br.com.fiap3esa.spring_boot_project.agendamento.DadosAgendamento;
import br.com.fiap3esa.spring_boot_project.agendamento.DadosListagemAgendamento;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    
    @Autowired
    private AgendamentoService service;
    
    @Autowired
    private AgendamentoRepository repository;
    
    @PostMapping
    public ResponseEntity<Agendamento> agendar(@RequestBody @Valid DadosAgendamento dados) {
        try {
            Agendamento agendamento = service.agendar(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public List<DadosListagemAgendamento> listarTodos() {
        return repository.findAll().stream()
            .map(DadosListagemAgendamento::new)
            .toList();
    }
    
    @GetMapping("/aluno/{alunoId}")
    public List<DadosListagemAgendamento> listarPorAluno(@PathVariable Long alunoId) {
        return repository.findByAlunoId(alunoId).stream()
            .map(DadosListagemAgendamento::new)
            .toList();
    }
    
    @GetMapping("/instrutor/{instrutorId}")
    public List<DadosListagemAgendamento> listarPorInstrutor(@PathVariable Long instrutorId) {
        return repository.findByInstrutorId(instrutorId).stream()
            .map(DadosListagemAgendamento::new)
            .toList();
    }
}
