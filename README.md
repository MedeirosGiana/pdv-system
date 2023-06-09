<h1 align="center">PDV-SYSTEM</h1>

<p align='center'> 
    <img src="https://img.shields.io/badge/Spring_Boot  V2.7.12-F2F4F9?style=for-the-badge&logo=spring-boot"/>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>  
</p>

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/MedeirosGiana/pdv-system.git/blob/main/LICENSE)

# Sobre o Projeto

PDV-SYSTEM(Ponto de Vendas) é uma aplicação back end construída em Java com Spring Boot para o gerenciamento de vendas, que pode ser aplicado para diferentes seguimentos. Esse projeto em específico foi desenvolvido pensando no segmento de supermercados,
onde contruí um cadastro de produtos, vendas, usuários, validações de acesso do usuário, Token JWT para segurança dos acessos e rotas da API, cálculo da venda, tratamento personalizado de erros, encriptação de senha, validações para o registro de vendas, povoamento do banco de dados e conexão com o SGBD MYSQL.

Esse projeto é pessoal e realizei com o objetivo de agregar meus conhecimentos com a tecnologia Java, caso queira contribuir com ideias que me ajudem nessa evolução e aprendizado, sinta-se à vontade e faça um  Pull Request.

## Tecnologias e ferramentas utilizadas
### Back end
- Java
- Spring Boot
- Spring Security
- Spring Tools
- JPA / Hibernate
- Maven
- MySQL
- Postman

## Como executar o projeto
### Back end
Pré-requisitos: Java 11, Maven, Postman, Git, MySQL

```bash
## clonar repositório
git clone https://github.com/MedeirosGiana/pdv-system.git

## entrar na pasta  [src-main](https://github.com/MedeirosGiana/pdv-system/blob/main/src/main/java/com/gm/pdv/PdvApplication.java)

## executar o projeto
./mvnw spring-boot:run
```

1. Utilizar a interface do Postman para testar os endpoints.

2. Para acessar as rotas, utilizar o login: maria.helena e senha:123 para acessar o sistema conforme abaixo e gerar o token de autorização, assim será possível acessar as demais rotas, visto que a segurança de todas as rotas estão ativas.

![image](https://github.com/MedeirosGiana/pdv-system/assets/100285143/edb1089b-fc30-40e5-8614-01b19644b3bb)


3. Após realizar o passo 2, será possíveis acessar todas as demais rotas, para isso, selecionar a aba **Headers** do **Postman**, inserir no KEY - **Authorization** e no VALUE - **Bearer** + espaço + o token gerado conforme orientado no passo 2. Siga exemplo abaixo:

![image](https://github.com/MedeirosGiana/pdv-system/assets/100285143/84881373-f13e-4048-822a-6d9318cd318e)


4. Após executar o passo 3,  pode ser acessado um navegador da Web em **http://localhost:8080/ e as rotas desejadas(user, product, sale)**, siga abaixo exemplo para cadastro de usuário, cadastrar produto em estoque e realizar venda, o formato do arquivo deve estar em JSON.

![image](https://github.com/MedeirosGiana/pdv-system/assets/100285143/ed6b0940-d69f-4dbf-be52-8dbcff9843b7)


![image](https://github.com/MedeirosGiana/pdv-system/assets/100285143/6d41817e-b743-4d28-8197-615bdda7d3d1)


![image](https://github.com/MedeirosGiana/pdv-system/assets/100285143/64dea1a8-0e61-4ebf-ac6c-c64d91c125fc)


## Autora
Giana Medeiros
- https://www.linkedin.com/in/gianamedeiros/
