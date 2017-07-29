

CREATE TABLE pessoasdef (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
    nome VARCHAR(80) NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    etnia VARCHAR(20),
    telefone1 VARCHAR(20),
    telefone2 VARCHAR(20),
    datanasc DATE NOT NULL,
    altura DECIMAL(4,2),
    email VARCHAR(60),
    tipo VARCHAR(20) NOT NULL,
    matricula VARCHAR(15) NOT NULL,
    numero_pm VARCHAR(6),
    nome_guerra VARCHAR(20),
    cargo BIGINT(20) NOT NULL,
    opm_orgao BIGINT(20) NOT NULL,
    FOREIGN KEY (cargo) REFERENCES cargos(id),
    FOREIGN KEY (opm_orgao) REFERENCES opms_orgaos(id),
    CONSTRAINT matricula_uk UNIQUE (matricula)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pessoas (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    criadoem DATETIME,
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
    nome VARCHAR(80) NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    
    telefone1 VARCHAR(20),
    telefone2 VARCHAR(20),
    datanasc DATE NOT NULL,
    altura DECIMAL(4,2) NOT NULL,
    email VARCHAR(60),
    matricula VARCHAR(15) NOT NULL,
    numero_pm VARCHAR(6),
    nome_guerra VARCHAR(20),
    cargo BIGINT(20) NOT NULL,
    opm BIGINT(20) NOT NULL,
    FOREIGN KEY (cargo) REFERENCES cargos(id),
    FOREIGN KEY (opm) REFERENCES opms_orgaos(id),
    CONSTRAINT matricula_uk UNIQUE (matricula)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
