package br.com.fiap3esa.spring_boot_project.agendamento;

import java.time.LocalDateTime;

import br.com.fiap3esa.spring_boot_project.aluno.Aluno;
import br.com.fiap3esa.spring_boot_project.instrutor.Instrutor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "agendamento")
@Table(name = "agendamentos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    Instrutor instrutor;

    @Column(name = "data_hora_inicio")
    LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim")
    LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    StatusAgendamento status = StatusAgendamento.CONFIRMADO;

    @Enumerated(EnumType.STRING)
    MotivoCancelamento motivoCancelamento;

    @Column(name = "data_cancelamento")
    LocalDateTime dataCancelamento;

    @Column(name = "data_criacao")
    LocalDateTime dataCriacao = LocalDateTime.now();

        public Agendamento(Aluno aluno, Instrutor instrutor, LocalDateTime dataHoraInicio) {
        this.aluno = aluno;
        this.instrutor = instrutor;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraInicio.plusHours(1);
        this.status = StatusAgendamento.CONFIRMADO;
        this.dataCriacao = LocalDateTime.now();
    }
    
    public void cancelar(MotivoCancelamento motivo) {
        this.status = StatusAgendamento.CANCELADO;
        this.motivoCancelamento = motivo;
        this.dataCancelamento = LocalDateTime.now();
    }
}
