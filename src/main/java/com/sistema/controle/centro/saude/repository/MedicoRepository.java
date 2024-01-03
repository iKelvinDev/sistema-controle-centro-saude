package com.sistema.controle.centro.saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.controle.centro.saude.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
}
