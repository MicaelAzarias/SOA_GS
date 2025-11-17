-- Cria a tabela de Dúvidas
CREATE TABLE duvidas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_autor VARCHAR(100) NOT NULL,
    linguagem VARCHAR(100) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    corpo_duvida TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL
);