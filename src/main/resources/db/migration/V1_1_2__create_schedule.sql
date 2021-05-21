CREATE TABLE IF NOT EXISTS agendamento
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  paciente_id BIGINT NOT NULL,
  medico_id BIGINT NOT NULL,
  time datetime NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (paciente_id) REFERENCES paciente(id),
  FOREIGN KEY (medico_id) REFERENCES medico(id)
);
