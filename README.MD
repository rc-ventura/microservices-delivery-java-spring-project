# README: Microserviço de Pagamento para Delivery

Este é um microserviço de pagamento para um sistema de delivery, desenvolvido em Java com Spring Boot. Este README fornece instruções sobre como configurar e executar o microserviço, incluindo a configuração de uma API REST, um serviço de descoberta, um balanceador de carga e um gateway.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Cloud Netflix (Eureka Service Discovery)
- Spring Cloud Netflix (Zuul API Gateway)
- Spring Cloud Netflix (Ribbon Load Balancer)
- Maven

## Configuração do Ambiente

Certifique-se de ter o Java JDK e o Maven instalados em sua máquina.

1. Clone o repositório do microserviço de pagamento:

```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
```

2. Importe o projeto em sua IDE preferida (Eclipse, IntelliJ, etc.).

3. Certifique-se de ter todas as dependências configuradas corretamente no arquivo `pom.xml`.

## Configuração do Eureka Service Discovery

O Eureka será usado para registrar e descobrir instâncias do microserviço.

1. Adicione a dependência do Eureka Client no arquivo `pom.xml` do microserviço de pagamento.

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

2. Configure as propriedades do Eureka Client no arquivo `application.properties`:

```properties
spring.application.name=payment-service
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
```

## Configuração do Zuul API Gateway

O Zuul será usado como um gateway para rotear solicitações para o microserviço de pagamento.

1. Adicione a dependência do Zuul no arquivo `pom.xml` do microserviço de pagamento.

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```

2. Configure as propriedades do Zuul no arquivo `application.properties`:

```properties
spring.application.name=gateway-service
server.port=8080
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
```

## Configuração do Ribbon Load Balancer

O Ribbon será usado como um balanceador de carga para distribuir solicitações entre várias instâncias do microserviço de pagamento.

1. Adicione a dependência do Ribbon no arquivo `pom.xml` do microserviço de pagamento.

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

2. Configure as propriedades do Ribbon no arquivo `application.properties`:

```properties
payment-service.ribbon.listOfServers=http://localhost:8081,http://localhost:8082
```

## Execução do Microserviço de Pagamento

1. Inicie o Eureka Service Discovery.

2. Inicie o Zuul API Gateway.

3. Inicie várias instâncias do microserviço de pagamento.

4. Teste o funcionamento do microserviço acessando as rotas definidas na API REST através do gateway.

## Contribuindo

Se você encontrar problemas ou tiver sugestões de melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.

Este README fornece uma visão geral sobre como configurar e executar o microserviço de pagamento para um sistema de delivery. Certifique-se de verificar a documentação oficial das tecnologias utilizadas para obter informações mais detalhadas sobre suas funcionalidades e configurações.