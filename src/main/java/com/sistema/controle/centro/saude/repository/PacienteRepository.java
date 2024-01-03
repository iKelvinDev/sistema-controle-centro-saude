package com.sistema.controle.centro.saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.controle.centro.saude.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    boolean existsByCpf(String cpf);

}
