DevForum API

Sobre o Projeto

O DevForum é uma API RESTful simples, construída com Spring Boot, projetada para ser um fórum de perguntas e respostas focado em dúvidas de programação. Ela permite que usuários postem suas dúvidas sobre uma linguagem ou tecnologia e recebam respostas de outros membros da comunidade.

Este projeto utiliza Java 21, Spring Boot 3, Spring Security, JPA/Hibernate, MySQL e Flyway, e inclui um serviço de notificação por e-mail.

Tecnologias Utilizadas

Java 21

Spring Boot 3.3.4

Spring Web: Criação de endpoints REST.

Spring Data JPA: Persistência de dados.

Spring Security: Camada de segurança (configurada para permitAll neste exemplo).

MySQL: Banco de dados relacional.

Flyway: Versionamento e migração do esquema do banco de dados.

Maven: Gerenciamento do projeto e dependências.

Spring Boot Starter Mail: Serviço de envio de e-mail assíncrono.

Lombok: Redução de boilerplate (via pom.xml).

Estrutura do Banco de Dados

O banco é gerenciado pelo Flyway e possui duas tabelas:

duvidas: Armazena as perguntas.

id (PK)

nome_autor (String)

linguagem (String)

titulo (String)

corpo_duvida (Text)

data_criacao (Timestamp)

status (Enum: ABERTA, RESPONDIDA, FECHADA)

respostas: Armazena as respostas.

id (PK)

nome_autor (String)

corpo_resposta (Text)

data_criacao (Timestamp)

duvida_id (FK para duvidas, com ON DELETE CASCADE)

Endpoints da API

Dúvidas (/duvidas)

POST /duvidas

Descrição: Cadastra uma nova dúvida.

Body: DadosCadastroDuvida

GET /duvidas

Descrição: Lista todas as dúvidas de forma paginada.

Query Params: ?page=0&size=10&sort=dataCriacao,desc&status=ABERTA (status é opcional)

Retorna: Page<DadosListagemDuvida>

GET /duvidas/{id}

Descrição: Detalha uma dúvida específica e lista todas as suas respostas.

Retorna: DadosDetalhesDuvida

PUT /duvidas

Descrição: Atualiza os dados de uma dúvida (título, corpo, linguagem).

Body: DadosAtualizacaoDuvida

Retorna: DadosListagemDuvida

DELETE /duvidas/{id}

Descrição: Exclui uma dúvida e todas as suas respostas (cascade).

Retorna: 204 No Content

PATCH /duvidas/{id}/fechar

Descrição: Altera o status de uma dúvida para FECHADA.

Retorna: 204 No Content

Respostas

POST /duvidas/{idDuvida}/respostas

Descrição: Cadastra uma nova resposta para uma dúvida. Notifica o autor da dúvida por e-mail (simulado).

Body: DadosCadastroResposta

Retorna: 201 Created com DadosListagemResposta

PUT /respostas

Descrição: Atualiza o texto de uma resposta existente.

Body: DadosAtualizacaoResposta

Retorna: DadosListagemResposta

DELETE /respostas/{id}

Descrição: Exclui uma resposta específica.

Retorna: 204 No Content

Como Executar

Clone o repositório:

git clone <seu-repositorio>
cd devforum


Configure o application.properties:

Abra src/main/resources/application.properties.

Configure a URL do seu banco MySQL, seu usuário e senha.

spring.datasource.url=jdbc:mysql://localhost/dev_forum?createDatabaseIfNotExist=true
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha


Configure suas credenciais de e-mail (ex: Gmail) como variáveis de ambiente EMAIL_USERNAME e EMAIL_PASSWORD.

Execute a Aplicação:

O Flyway criará as tabelas automaticamente na primeira inicialização.

Use o Maven:

mvn spring-boot:run


Ou execute a classe DevForumApplication.java pela sua IDE.

Acesse:
A API estará disponível em http://localhost:8080.
