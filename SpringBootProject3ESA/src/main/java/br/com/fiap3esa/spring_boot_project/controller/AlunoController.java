package br.com.fiap3esa.spring_boot_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap3esa.spring_boot_project.aluno.Aluno;
import br.com.fiap3esa.spring_boot_project.aluno.AlunoRepository;
import br.com.fiap3esa.spring_boot_project.aluno.DadosAtualizacaoAluno;
import br.com.fiap3esa.spring_boot_project.aluno.DadosCadastroAluno;
import br.com.fiap3esa.spring_boot_project.aluno.DadosListagemAluno;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoRepository repository;

    @PostMapping()
    @Transactional
    public void cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados) {
        System.out.println(dados);
        repository.save(new Aluno(dados));
    }

    @GetMapping
    public List<DadosListagemAluno> listarAlunos(){
        return repository.findAllAtivos().stream().map(DadosListagemAluno::new).toList();
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizarAluno(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoAluno dados) {
        var aluno = repository.getReferenceById(id);
        aluno.atualizarInformacoes(dados);
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public void excluirAluno(@PathVariable Long id) {
        var aluno = repository.getReferenceById(id);
        aluno.excluir();
    }
}
