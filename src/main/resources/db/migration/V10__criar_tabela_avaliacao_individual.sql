CREATE TABLE avaliadores(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
    cref VARCHAR(20),
    escolaridade VARCHAR(20),
    pessoadef BIGINT(20) NOT NULL,
    FOREIGN KEY (pessoadef) REFERENCES pessoasdef(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE avaliacoes_individuais (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),    
    dataAvaliacao DATE,
    praticaTipoAtividade TINYINT(1) NOT NULL,
    praticaTipoAtividadeQual VARCHAR(40),
    frequencia VARCHAR(40) NOT NULL,
    duracao VARCHAR(40) NOT NULL,
    objetivo VARCHAR(30) NOT NULL,
    objetivoOutro VARCHAR(30),
    restricao TINYINT(1) NOT NULL,
    restricaoQual VARCHAR(30),
    fuma TINYINT(1) NOT NULL,
    bebidaAlcoolica TINYINT(1) NOT NULL,
    medicamento TINYINT(1) NOT NULL,
    medicamentoQual VARCHAR(30),
    medicamentoMotivo VARCHAR(30),
    medicamentoDosagem VARCHAR(30),
    problemaOutro VARCHAR(30),
    problemaArticulacaoQual VARCHAR(30),
    pessoadef BIGINT(20) NOT NULL,
    avaliador BIGINT(20) NOT NULL,
    FOREIGN KEY (pessoadef) REFERENCES pessoasdef(id),
    FOREIGN KEY (avaliador) REFERENCES avaliadores(id)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE avaliacao_problemas(
	id BIGINT(20) NOT NULL,
	problemas VARCHAR(40),
	FOREIGN KEY (id) REFERENCES avaliacoes_individuais(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE medicoes(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	torax DECIMAL(5,2),
	cintura DECIMAL(5,2),
	abdominal DECIMAL(5,2),
	quadril DECIMAL(5,2),
	bracoe DECIMAL(5,2),
	bracod DECIMAL(5,2),
	antibracoe DECIMAL(5,2),
	antibracod DECIMAL(5,2),
	coxae DECIMAL(5,2),
	coxad DECIMAL(5,2),
	pernad DECIMAL(5,2),
	pernae DECIMAL(5,2),
	avaliacaoindividual BIGINT(20) NOT NULL,
	FOREIGN KEY (avaliacaoindividual) REFERENCES avaliacoes_individuais(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



