package br.com.fiap3esa.spring_boot_project.instrutor;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository(tipo de classe, tipo da chave primária)
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
}
