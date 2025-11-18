Integrantes:
Micael Santos Azarias | RM552699

Eduardo Mazelli | RM553236

Felipe Megumi Nakama| RM552821


-------------------------------------------------------------




DevForum API

📖 Sobre o Projeto

O DevForum é uma API RESTful simples, construída com Spring Boot, projetada para ser um fórum de perguntas e respostas focado em dúvidas de programação. Ela permite que usuários postem suas dúvidas sobre uma linguagem ou tecnologia e recebam respostas de outros membros da comunidade, contribuindo diretamente para os Objetivos de Desenvolvimento Sustentável (ODS) da ONU 4 (Educação de Qualidade), 8 (Trabalho Decente e Crescimento Econômico) e 9 (Indústria, Inovação e Infraestrutura).

Este projeto foi construído utilizando as práticas modernas de desenvolvimento Java, incluindo arquitetura em camadas, DTOs (Data Transfer Objects) na forma de Records, e gerenciamento de banco de dados com Flyway.

🛠️ Tecnologias Utilizadas

A stack principal do projeto inclui:

Java 21 

Spring Boot 3.3.4 

Spring Web: Para criação de endpoints REST. 

Spring Data JPA: Para persistência de dados. 

Spring Security: Camada de segurança 

MySQL: Banco de dados relacional. 

Flyway: Para versionamento e migração do esquema do banco de dados. 

Maven: Gerenciador do projeto e dependências.

Spring Boot Starter Mail: Serviço de envio de e-mail 

Lombok: Para redução de boilerplate

🚀 Como Executar

Pré-requisitos

Java JDK 21 ou superior.

Maven 3.8 ou superior.

Um servidor MySQL em execução (ex: localhost:3306).

1. Clone o Repositório

git clone <url-do-seu-repositorio>
cd devforum


2. Configure o Banco de Dados

Esta é a etapa mais importante. A aplicação precisa se conectar ao seu banco MySQL.

⚠️ ATENÇÃO: Configure sua Senha!

3. (Opcional) Configure o Serviço de E-mail

O serviço de e-mail (EmailService) está configurado para ler credenciais de variáveis de ambiente. Se você quiser testar esta funcionalidade, configure as seguintes variáveis no seu sistema ou na sua IDE:

EMAIL_USERNAME: Seu e-mail (ex: seu.email@gmail.com)

EMAIL_PASSWORD: Sua senha de app do Gmail (ou a senha do seu provedor)

4. Execute a Aplicação

O Flyway cuidará automaticamente de criar as tabelas duvidas e respostas ao iniciar.

Você pode rodar a aplicação de duas formas:

Pelo Maven:

mvn spring-boot:run


Pela sua IDE (IntelliJ):

Encontre a classe DevForumApplication.java.

Clique com o botão direito e selecione Run 'DevForumApplication.main()'.

A API estará disponível em http://localhost:8080.

🗄️ Estrutura do Banco de Dados

O banco é gerenciado pelo Flyway e possui duas tabelas:

duvidas 

id (PK)

nome_autor (String)

linguagem (String)

titulo (String)

corpo_duvida (Text)

data_criacao (Timestamp)

status (Enum: ABERTA, RESPONDIDA, FECHADA) 

respostas 

id (PK)

nome_autor (String)

corpo_resposta (Text)

data_criacao (Timestamp)

duvida_id (FK para duvidas, com ON DELETE CASCADE)

Endpoints da API

A segurança está configurada com permitAll(), então todos os endpoints estão abertos para teste.

Dúvidas (Prefixo: /duvidas)

Método

Endpoint

Descrição

Body (JSON)

POST

/

Cadastra uma nova dúvida.

DadosCadastroDuvida

GET

/

Lista todas as dúvidas (paginado).

N/A

GET

/{id}

Detalha uma dúvida e suas respostas.

N/A

PUT

/

Atualiza uma dúvida (título, corpo, etc.).

DadosAtualizacaoDuvida

DELETE

/{id}

Exclui uma dúvida (e suas respostas).

N/A

PATCH

/{id}/fechar

Altera o status da dúvida para FECHADA.

N/A

POST

/{idDuvida}/respostas

Cadastra uma nova resposta para uma dúvida.

DadosCadastroResposta

Exemplo GET /duvidas (com filtros):

http://localhost:8080/duvidas?page=0&size=10&sort=dataCriacao,desc&status=ABERTA

Respostas (Prefixo: /respostas)

Método

Endpoint

Descrição

Body (JSON)

PUT

/

Atualiza o texto de uma resposta.

DadosAtualizacaoResposta

DELETE

/{id}

Exclui uma resposta específica.

N/A
