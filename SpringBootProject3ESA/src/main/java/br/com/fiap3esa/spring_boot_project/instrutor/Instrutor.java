package br.com.fiap3esa.spring_boot_project.instrutor;

import br.com.fiap3esa.spring_boot_project.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity (name = "instrutor")
@Table (name = "instrutores")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    String email;

    String telefone;

    String cnh;

    @Enumerated(EnumType.STRING)
    Especialidade Especialidade;

    @Embedded()
    Endereco endereco;
    
    Boolean ativo = true;

    public Instrutor(DadosCadastroInstrutor dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cnh = dados.cnh();
        this.Especialidade = dados.especialidade();
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

    public String getCnh() {
        return cnh;
    }

    public Especialidade getEspecialidade() {
        return Especialidade;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }

    public void atualizarInformacoes(DadosAtualizacaoInstrutor dados) {
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
