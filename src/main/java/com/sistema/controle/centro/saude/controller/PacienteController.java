package com.sistema.controle.centro.saude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.controle.centro.saude.domain.Paciente;
import com.sistema.controle.centro.saude.repository.PacienteRepository;
import com.sistema.controle.centro.saude.service.CadastrarPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    
    @Autowired
    private CadastrarPacienteService cadastrarPacienteService;

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody @Valid Paciente paciente) {
        try {
            Paciente newPaciente = cadastrarPacienteService.cadastrarPaciente(paciente);
            return new ResponseEntity<>(newPaciente, HttpStatus.CREATED);
        } catch (RuntimeException execao) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> listar(@PageableDefault(size = 30, sort = { "nome" }) Pageable paginacao) {
        var pacientes = repository.findAll(paginacao);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping
    public ResponseEntity<Paciente> atualizar(@RequestBody @Valid Paciente paciente) {
        Paciente pacienteLocal = repository.findById(paciente.getId()).get();

        pacienteLocal.setNome(paciente.getNome());

        return ResponseEntity.ok(pacienteLocal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        repository.delete(paciente);
        return ResponseEntity.noContent().build();
    }

}
