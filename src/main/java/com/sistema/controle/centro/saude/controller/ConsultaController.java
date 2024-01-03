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

import com.sistema.controle.centro.saude.domain.Consulta;
import com.sistema.controle.centro.saude.repository.ConsultaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Consulta consulta,
            UriComponentsBuilder uriBuilder) {
        Consulta consultaLocal = repository.save(consulta);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consultaLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var consulta = repository.getReferenceById(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping
    public ResponseEntity<Page<Consulta>> listar(@PageableDefault(size = 30, sort = { "nome" }) Pageable paginacao) {
        var consultas = repository.findAll(paginacao);
        return ResponseEntity.ok(consultas);
    }

    @PutMapping
    public ResponseEntity<Consulta> atualizar(@RequestBody @Valid Consulta consulta) {
        Consulta consultaLocal = repository.findById(consulta.getId()).get();

        consultaLocal.setId(consulta.getId());

        return ResponseEntity.ok(consultaLocal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var consulta = repository.getReferenceById(id);
        repository.delete(consulta);
        return ResponseEntity.noContent().build();
    }

}

