CREATE TABLE testes_fisicos(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	data DATE,
	objetivo VARCHAR(40) NOT NULL,
	tipo VARCHAR(20),
	notaAprovacao DECIMAL(10,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






CREATE TABLE comissoes(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
    avaliador BIGINT(20) NOT NULL,
    testeFisico BIGINT(20) NOT NULL,
    funcao VARCHAR(20),
    FOREIGN KEY (avaliador) REFERENCES avaliadores(id),
    FOREIGN KEY (testeFisico) REFERENCES testes_fisicos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




