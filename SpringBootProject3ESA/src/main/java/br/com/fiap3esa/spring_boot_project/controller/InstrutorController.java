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

import br.com.fiap3esa.spring_boot_project.instrutor.DadosAtualizacaoInstrutor;
import br.com.fiap3esa.spring_boot_project.instrutor.DadosCadastroInstrutor;
import br.com.fiap3esa.spring_boot_project.instrutor.DadosListagemInstrutor;
import br.com.fiap3esa.spring_boot_project.instrutor.Instrutor;
import br.com.fiap3esa.spring_boot_project.instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {

    @Autowired
    InstrutorRepository repository;

    @PostMapping()
    @Transactional
    public void cadastrarInstrutor(@RequestBody @Valid DadosCadastroInstrutor dados) {
        System.out.println(dados);
        repository.save(new Instrutor(dados));
    }

    @GetMapping
    public List<DadosListagemInstrutor> listarInstrutor(){
        return repository.findAllAtivos().stream().map(DadosListagemInstrutor::new).toList();
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizarInstrutor(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoInstrutor dados) {
        var instrutor = repository.getReferenceById(id);
        instrutor.atualizarInformacoes(dados);
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public void excluirInstrutor(@PathVariable Long id) {
        var instrutor = repository.getReferenceById(id);
        instrutor.excluir();
    }
}