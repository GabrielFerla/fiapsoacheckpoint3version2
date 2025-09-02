package br.com.fiap3esa.spring_boot_project.aluno;

public record DadosListagemAluno(
        String nome,
        String email,
        String telefone,
        String cpf,
        Boolean ativo
) {
public DadosListagemAluno(Aluno aluno){
    this(aluno.getNome(), aluno.getEmail(), aluno.getTelefone(), aluno.getCpf(), aluno.getAtivo());

}
}
