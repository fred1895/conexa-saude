CREATE TABLE IF NOT EXISTS medico
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  senha VARCHAR(250) NOT NULL,
  especialidade VARCHAR(250) NULL,
  PRIMARY KEY (ID)
);

INSERT INTO hospital.medico (email, nome, senha, especialidade)
VALUES ('medico@email.com', 'Dr. Daniel Santos', '$2a$10$qVPovrEvhCV.wlucv4TphO3emrSDZnozoyGayFyvQlPJr2P9UwvFG', 'Cardiologia');
