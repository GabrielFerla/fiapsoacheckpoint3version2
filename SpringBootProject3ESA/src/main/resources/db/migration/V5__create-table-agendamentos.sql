CREATE TABLE agendamentos(
    id bigint NOT NULL AUTO_INCREMENT,
    aluno_id bigint NOT NULL,
    instrutor_id bigint NOT NULL,
    data_hora_inicio datetime NOT NULL,
    data_hora_fim datetime NOT NULL,
    status varchar(20) NOT NULL DEFAULT 'CONFIRMADO',
    data_criacao datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY(id),
    FOREIGN KEY(aluno_id) REFERENCES alunos(id),
    FOREIGN KEY(instrutor_id) REFERENCES instrutores(id),
    
    INDEX idx_aluno_data (aluno_id, data_hora_inicio),
    INDEX idx_instrutor_data (instrutor_id, data_hora_inicio),
    INDEX idx_data_hora (data_hora_inicio, data_hora_fim)
)
