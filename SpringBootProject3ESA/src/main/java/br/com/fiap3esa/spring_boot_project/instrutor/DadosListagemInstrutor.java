package br.com.fiap3esa.spring_boot_project.instrutor;

public record DadosListagemInstrutor(
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade,
        Boolean ativo
) {
public DadosListagemInstrutor(Instrutor instrutor){
    this(instrutor.getNome(), instrutor.getEmail(), instrutor.getTelefone(), instrutor.getCnh(), instrutor.getEspecialidade(), instrutor.getAtivo());

}
}
