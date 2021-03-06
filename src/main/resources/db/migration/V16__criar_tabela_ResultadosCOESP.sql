CREATE TABLE resultadosTafEspecial(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	pessoa BIGINT(20) NOT NULL,
	teste BIGINT(20) NOT NULL,
	inscricao INT(10),
	flexaoBarra INT(10),
	flexaoBarraPts INT(10), 
	abdominal INT(10),
	abdominalPts INT(10),
	corrida50m VARCHAR(10),
	corrida50mPts INT(10),
	corrida12min INT(10),
	corrida12minPts INT(10),
	notaFinal DECIMAL(10,2),
	FOREIGN KEY (pessoa) REFERENCES pessoasdef(id),
	FOREIGN KEY (teste) REFERENCES testes_fisicos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE resultadosTheCoesp(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
	pessoa BIGINT(20) NOT NULL,
	teste BIGINT(20) NOT NULL,
	inscricao INT(10),
	corrida10km VARCHAR(10),
	corrida10kmPts INT(10),
	subidaCorda VARCHAR(20),
	transporteCarga VARCHAR(10),
	transporteCargaPts INT(10),
	saltoPlataforma VARCHAR(10),
	apneiaEstatica VARCHAR(10),
	natacao200m VARCHAR(10),
	natacao200mPts INT(10),
	deslocMeioLiquido VARCHAR(10),
	flutuacao VARCHAR(10),
	mergulhoLivre VARCHAR(10),
	notaFinal DECIMAL(10,2),
	FOREIGN KEY (pessoa) REFERENCES pessoasdef(id),
	FOREIGN KEY (teste) REFERENCES testes_fisicos(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;