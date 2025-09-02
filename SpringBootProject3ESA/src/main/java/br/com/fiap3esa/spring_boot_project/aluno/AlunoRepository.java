package br.com.fiap3esa.spring_boot_project.aluno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//JpaRepository(tipo de classe, tipo da chave primária)
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
    @Query("SELECT a FROM aluno a WHERE a.ativo = true")
    List<Aluno> findAllAtivos();
}
