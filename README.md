

---

# README: Microserviço de Pagamento para Delivery

Este é um microserviço de pagamento para um sistema de delivery, desenvolvido em Java com Spring Boot. Este README fornece instruções sobre como configurar e executar o microserviço, incluindo a configuração de uma API REST, um serviço de descoberta, um gateway e a implementação de resiliência com o Resilience4j.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Cloud Netflix (Eureka Service Discovery)
- Spring Cloud Netflix (Zuul API Gateway)
- Spring Cloud OpenFeign (Cliente HTTP para comunicação entre microserviços)
- Resilience4j (Circuit Breaker)
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

## Implementação do Resilience4j (Circuit Breaker)

O Resilience4j será utilizado para implementar o circuit breaker e melhorar a resiliência do sistema em caso de falhas de comunicação entre microserviços.

1. Adicione a dependência do Resilience4j no arquivo `pom.xml` do microserviço de pagamento.

```xml
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot2</artifactId>
    <version>1.7.0</version>
</dependency>
```

2. Configure o circuit breaker do Resilience4j no arquivo `application.properties`:

```properties
# Configuração do circuit breaker
resilience4j.circuitbreaker.instances.payment-service.registerHealthIndicator=true
resilience4j.circuitbreaker.configs.default.slidingWindowSize=10
resilience4j.circuitbreaker.configs.default.minimumNumberOfCalls=5
resilience4j.circuitbreaker.configs.default.waitDurationInOpenState=5s
resilience4j.circuitbreaker.configs.default.permittedNumberOfCallsInHalfOpenState=3
resilience4j.circuitbreaker.configs.default.failureRateThreshold=50
```

## Execução do Microserviço de Pagamento

1. Inicie o Eureka Service Discovery.

2. Inicie o Zuul API Gateway.

3. Inicie várias instâncias do microserviço de pagamento.

4. Teste o funcionamento do microserviço acessando as rotas definidas na API REST através do gateway.

## Contribuindo

Se você encontrar problemas ou tiver sugestões de melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.

Este README fornece uma visão geral sobre como configurar e executar o microserviço de pagamento para um sistema de delivery. Certifique-se de verificar a documentação oficial das tecnologias utilizadas para obter informações mais detalhadas sobre suas funcionalidades e configurações.

---

