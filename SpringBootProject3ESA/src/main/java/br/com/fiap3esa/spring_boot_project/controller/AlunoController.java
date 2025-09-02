package br.com.fiap3esa.spring_boot_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

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
    public List<DadosListagemAluno> listarAlunos(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        PageRequest pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome"
        );

        return repository.findAllAtivos(pageable).stream().map(DadosListagemAluno::new).toList();
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
