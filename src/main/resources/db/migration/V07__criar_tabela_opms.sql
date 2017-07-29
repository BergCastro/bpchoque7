CREATE TABLE opms_orgaos (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
    nome VARCHAR(50) NOT NULL,
    localizacao VARCHAR(60),
    tipo VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








