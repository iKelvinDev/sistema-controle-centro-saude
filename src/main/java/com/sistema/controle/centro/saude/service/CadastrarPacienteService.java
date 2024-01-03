package com.sistema.controle.centro.saude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.controle.centro.saude.domain.Paciente;
import com.sistema.controle.centro.saude.repository.PacienteRepository;

@Service
public class CadastrarPacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrarPaciente(Paciente paciente) {
        if (pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        return pacienteRepository.save(paciente);
    }
}
