CREATE TABLE ocorrencia (
    ocorrencia_id BIGINT NOT NULL AUTO_INCREMENT,
    descricao TEXT NOT NULL,
    data_registro DATETIME NOT NULL,
    entrega_id BIGINT NOT NULL,
    PRIMARY KEY (ocorrencia_id)
);

ALTER TABLE ocorrencia ADD CONSTRAINT fk_ocorrencia_entrega
FOREIGN KEY (entrega_id) REFERENCES entrega(entrega_id)
