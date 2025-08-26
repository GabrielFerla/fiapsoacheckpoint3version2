package br.com.fiap3esa.spring_boot_project.instrutor;

import br.com.fiap3esa.spring_boot_project.endereco.Endereco;
import jakarta.persistence.*;
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

    String cnh;

    @Enumerated(EnumType.STRING)
    Especialidade Especialidade;

    @Embedded()
    Endereco endereco;

    public Instrutor(DadosCadastroInstrutor dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cnh = dados.cnh();
        this.Especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public String getNome() {
        return nome;
    }


    public String getEmail() {
        return email;
    }

    public String getCnh() {
        return cnh;
    }

    public Especialidade getEspecialidade() {
        return Especialidade;
    }
}
