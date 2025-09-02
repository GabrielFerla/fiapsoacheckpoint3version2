package br.com.fiap3esa.spring_boot_project.aluno;

import br.com.fiap3esa.spring_boot_project.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "aluno")
@Table(name = "alunos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    String email;

    String telefone;

    String cpf;

    @Embedded()
    Endereco endereco;
    
    Boolean ativo = true;

    public Aluno(DadosCadastroAluno dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }

    public void atualizarInformacoes(DadosAtualizacaoAluno dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco = new Endereco(dados.endereco());
        }
    }
    
    public void excluir() {
        this.ativo = false;
    }
}
