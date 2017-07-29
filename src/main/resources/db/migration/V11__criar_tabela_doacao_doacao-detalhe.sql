CREATE TABLE doacoes(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	doador VARCHAR(60) NOT NULL,
	dataDoacao DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE doacoes_detalhes(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	doacao BIGINT(20) NOT NULL,
	quantidade DECIMAL(10,2) NOT NULL,
	tipo VARCHAR(40) NOT NULL,
	descricao VARCHAR(60),
	FOREIGN KEY (doacao) REFERENCES doacoes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
