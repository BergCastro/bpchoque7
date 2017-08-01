CREATE TABLE resultadosTafGeral(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	pessoa BIGINT(20) NOT NULL,
	teste BIGINT(20) NOT NULL,
	flexaoBarra INT(10),
	flexaoBarraPts INT(10), 
	flexaoSolo INT(10),
	flexaoSoloPts INT(10),
	abdominal INT(10),
	absominalPts INT(10),
	corrida50m VARCHAR(10),
	corrida50mPts INT(10),
	corrida12min INT(10),
	corrida12minPts INT(10),
	notaFinal DECIMAL(10,2),
	idade INT(10),
	FOREIGN KEY (pessoa) REFERENCES pessoasdef(id),
	FOREIGN KEY (teste) REFERENCES testes_fisicos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE resultadosTheCdc(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	pessoa BIGINT(20) NOT NULL,
	teste BIGINT(20) NOT NULL,
	flutuacao VARCHAR(10),
	natacao50m VARCHAR(10),
	corrida5km VARCHAR(10),
	lancarGranada VARCHAR(10),
	FOREIGN KEY (pessoa) REFERENCES pessoasdef(id),
	FOREIGN KEY (teste) REFERENCES testes_fisicos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;