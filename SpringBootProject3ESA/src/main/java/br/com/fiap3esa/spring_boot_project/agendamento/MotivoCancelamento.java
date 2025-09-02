package br.com.fiap3esa.spring_boot_project.agendamento;

public enum MotivoCancelamento {
    ALUNO_DESISTIU("Aluno desistiu"),
    INSTRUTOR_CANCELOU("Instrutor cancelou"),
    OUTROS("Outros");
    
    private final String descricao;
    
    MotivoCancelamento(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}
