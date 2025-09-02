package br.com.fiap3esa.spring_boot_project.agendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    
    @Query("""
        SELECT COUNT(a) FROM agendamento a 
        WHERE a.aluno.id = :alunoId 
        AND DATE(a.dataHoraInicio) = :data
        AND a.status = 'CONFIRMADO'
    """)
    long countAgendamentosDiaAluno(@Param("alunoId") Long alunoId, @Param("data") LocalDate data);
    
    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM agendamento a 
        WHERE a.instrutor.id = :instrutorId 
        AND a.dataHoraInicio < :dataHoraFim 
        AND a.dataHoraFim > :dataHoraInicio
        AND a.status = 'CONFIRMADO'
    """)
    boolean existsConflitoInstrutor(@Param("instrutorId") Long instrutorId, 
                                   @Param("dataHoraInicio") LocalDateTime dataHoraInicio,
                                   @Param("dataHoraFim") LocalDateTime dataHoraFim);
    
    @Query("SELECT a FROM agendamento a WHERE a.aluno.id = :alunoId ORDER BY a.dataHoraInicio")
    List<Agendamento> findByAlunoId(@Param("alunoId") Long alunoId);
    
    @Query("SELECT a FROM agendamento a WHERE a.instrutor.id = :instrutorId ORDER BY a.dataHoraInicio")
    List<Agendamento> findByInstrutorId(@Param("instrutorId") Long instrutorId);
    
    @Query("SELECT a FROM agendamento a WHERE a.status = 'CONFIRMADO' AND a.dataHoraInicio > :agora")
    List<Agendamento> findAgendamentosConfirmadosFuturos(@Param("agora") LocalDateTime agora);
    
    @Query("SELECT a FROM agendamento a WHERE a.status = 'CANCELADO'")
    List<Agendamento> findAgendamentosCancelados();
}
