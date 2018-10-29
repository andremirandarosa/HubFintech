# HubFintech
### Sistema de Transações de Cartões Pré-Pagos
> André Luiz Miranda da Rosa (andremirandarosa@gmail.com)

*Linguagem de Programação: Java (JDK: 1.8)*

------------

Sistema para processamento Multi-Thread de requisições via Socket TCP para cartões pré-pagos. Utiliza um mecanismo de Cache para gerenciamento das transações em Memória oferecendo uma melhor performance de processamento, além de utilizar uma Lista para o salvamento das transações no Banco de Dados (H2) de forma assíncrona. O sistema também pode ser executado via Container Docker, além de possuir uma API REST (com documentação via Swagger) para visualização das informações dos cartões.

------------

**Cartões Previamente Cadastrados (Valor R$ 1.000,00):**
- 1234567890123456
- 6543210987654321

**Porta para Transações Socket TCP:** 8081

------------

## Instruções para Execução da Aplicação:

------------
### DOCKER:

**Criar Package:** mvn package

**Criar Imagem Docker:** sudo docker build -t hubfintech .

**Criar e Executar Container:** sudo docker run --network host -d --name hubfintech hubfintech

------------

### CONSOLE H2:


**URL:** http://localhost:8080/h2

---
*Dados de Acesso:*

**JDBC URL:** jdbc:h2:mem:hubfintech

**Username:** admin

**Password:** admin

------------

### API REST:

**Listar Cartões:** GET: http://localhost:8080/api/card/list

**Verificar Cartão pelo Número:** GET: http://localhost:8080/api/card/*NUMERO_CARTAO*

------------

### Swagger API Docs:


**URL:** http://localhost:8080/swagger-ui.html
