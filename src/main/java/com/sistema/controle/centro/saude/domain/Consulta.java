package com.sistema.controle.centro.saude.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "consulta")
@Entity(name = "consulta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private LocalDate dataConsulta;
    private LocalDateTime dataConsulta;

    private String diagnostico;

    private String prescricao;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

}
