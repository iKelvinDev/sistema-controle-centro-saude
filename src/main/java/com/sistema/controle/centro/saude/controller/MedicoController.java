package com.sistema.controle.centro.saude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sistema.controle.centro.saude.domain.Medico;
import com.sistema.controle.centro.saude.repository.MedicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Medico medico,
            UriComponentsBuilder uriBuilder) {
        Medico medicoLocal = repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(medico);
    }

    @GetMapping
    public ResponseEntity<Page<Medico>> listar(@PageableDefault(size = 30, sort = { "nome" }) Pageable paginacao) {
        var medicos = repository.findAll(paginacao);
        return ResponseEntity.ok(medicos);
    }

    @PutMapping
    public ResponseEntity<Medico> atualizar(@RequestBody @Valid Medico medico) {
        Medico medicoLocal = repository.findById(medico.getId()).get();

        medicoLocal.setNome(medico.getNome());

        return ResponseEntity.ok(medicoLocal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        repository.delete(medico);
        return ResponseEntity.noContent().build();
    }

}

