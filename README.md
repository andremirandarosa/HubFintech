# HubFintech
### Sistema de Transações de Cartões Pré-Pagos
> André Luiz Miranda da Rosa (andremirandarosa@gmail.com)

*Linguagem de Programação: Java (JDK: 1.8)*

------------

**Cartões Previamente Cadastrados (Valor R$ 1.000,00):**
- 1234567890123456
- 6543210987654321

**Porta de Transações TCP:** 8081

------------

**Instruções para Execução da Aplicação:**

------------
**DOCKER:**

**Gerar Package JAR:** mvn package

**Criar Imagem Docker:** sudo docker build -t hubfintech .

**Criar e Executar Container:** sudo docker run --network host -d --name hubfintech hubfintech

------------

**CONSOLE H2:**


**URL:** http://localhost:8080/h2

---

**Dados de Acesso:**


**JDBC URL:** jdbc:h2:mem:hubfintech

**Username:** admin

**Password:** admin

------------

**API:**

**Listar Cartões:** GET: http://localhost:8080/api/card/list

**Verificar Cartão pelo Número:** GET: http://localhost:8080/api/card/*NUMERO_CARTAO*

------------

**SWAGGER API DOCS:**


**URL:** http://localhost:8080/swagger-ui.html
