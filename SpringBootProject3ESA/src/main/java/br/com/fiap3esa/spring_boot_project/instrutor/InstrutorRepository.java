package br.com.fiap3esa.spring_boot_project.instrutor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//JpaRepository(tipo de classe, tipo da chave primária)
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    
    @Query("SELECT i FROM instrutor i WHERE i.ativo = true")
    List<Instrutor> findAllAtivos();
}
