CREATE TABLE consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_consulta timestamp,
    diagnostico VARCHAR(200),
    prescricao VARCHAR(150),
    paciente_id INT,
    medico_id INT,
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (medico_id) REFERENCES medico(id)
);