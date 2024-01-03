package com.sistema.controle.centro.saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.controle.centro.saude.domain.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
}
