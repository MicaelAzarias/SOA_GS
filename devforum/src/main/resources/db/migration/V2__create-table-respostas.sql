CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_autor VARCHAR(100) NOT NULL,
    corpo_resposta TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    duvida_id BIGINT NOT NULL,
    CONSTRAINT fk_respostas_duvida_id FOREIGN KEY (duvida_id) REFERENCES duvidas(id) ON DELETE CASCADE
);