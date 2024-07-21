# Projeto de Automação de Testes com Selenium WebDriver

## Descrição
Este projeto realiza automação de testes no sistema MantisBT utilizando Java e Selenium WebDriver, integrado com o ambiente de desenvolvimento Intellij.

## Pré-requisitos
- Java JDK 8 ou superior instalado
- IDE Intellij IDEA configurada
- Dependências do Selenium WebDriver configuradas no projeto
- Acesso ao sistema MantisBT para testes

## Instalação e Configuração
1. Clone o repositório para sua máquina local.
2. Abra o projeto na IDE Intellij.
3. Configure as dependências do Selenium WebDriver no arquivo `pom.xml`.
   ```xml
   <!-- Exemplo de dependência do Selenium WebDriver -->
   <dependency>
       <groupId>org.seleniumhq.selenium</groupId>
       <artifactId>selenium-java</artifactId>
       <version>3.141.59</version>
   </dependency>

## Comandos report alure 

- Limpa o projeto e executa todos os testes:
 ```xml
mvn clean test
 ```

- Gera um relatório HTML dos resultados dos testes, salvo em allure-report:
 ```xml
 allure generate --clean target/allure-results -o target/allure-report
 ```

- Inicia um servidor local para visualizar o relatório Allure no navegador:
 ```xml
allure serve target/allure-results
 ```