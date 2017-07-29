CREATE TABLE cargos (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    criadoem DATETIME,
    criadopor VARCHAR(50),
    atualizadoem DATETIME,
    atualizadopor VARCHAR(50),
    nome VARCHAR(50) NOT NULL,
    abreviacao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','SOLDADO PM', 'SD PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','CABO PM', 'CB PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','3° SARGENTO PM', '3° SGT PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','2° SARGENTO PM', '2° SGT PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','1° SARGENTO PM', '1° SGT PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','SUBTENENTE PM', 'ST PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','CADETE PM', 'CAD PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','2° TENENTE PM', '2° TEN PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','1° TENENTE PM', '1° TEN PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','CAPITÃO PM', 'CAP PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','MAJOR PM', 'MJ PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','TENENTE CORONEL PM', 'TEN CEL PM');
INSERT INTO cargos(criadopor, nome, abreviacao) VALUES ('fireberg2500@hotmail.com','CORONEL PM', 'CEL PM');



