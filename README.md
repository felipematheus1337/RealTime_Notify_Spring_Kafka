# Sistema de Notificações em Tempo Real com Spring Cloud e Kafka

Este é um exemplo de um sistema de notificações em tempo real construído usando o ecossistema do Spring Cloud e o Apache Kafka. O sistema permite que as empresas se inscrevam para receber notificações específicas, como atualizações de preços, notícias ou eventos, e as recebam por email.

## Visão Geral do Projeto

O projeto é composto por vários microsserviços que desempenham funções diferentes:

- **Microsserviço de Cadastro de Empresas:** Este serviço permite o cadastro e gerenciamento de informações sobre empresas. É um Eureka Client registrado no Eureka Server.

- **Microsserviço de Envio de Emails:** Responsável por receber solicitações para enviar notificações por email e efetuar o envio real dos emails. Também é um Eureka Client.

- **Microsserviço de Armazenamento de Emails:** Este serviço recebe notificações enviadas com sucesso e as armazena em um banco de dados não relacional, como o MongoDB.

- **Microsserviço para Agendamento de Envio de Emails:** Este microsserviço permite agendar o envio de notificações em horários específicos ou em intervalos regulares usando a anotação `@Scheduled` do Spring.

## Como Usar

1. **Configuração do Ambiente:**
   - Certifique-se de ter o Java, o Spring Boot e o Apache Kafka instalados em seu ambiente de desenvolvimento.

2. **Configuração do Kafka:**
   - Configure o Apache Kafka com os tópicos necessários para o sistema de mensageria.

3. **Configuração do MongoDB:**
   - Configure o MongoDB para armazenar as notificações enviadas com sucesso.

4. **Clonando o Repositório:**
   - Clone este repositório em sua máquina local:

5. **Compilando e Executando:**
- Vá para cada diretório de microsserviço e execute-o usando o Spring Boot. Por exemplo:


Certifique-se de iniciar todos os microsserviços necessários.

6. **Testando o Sistema:**
- Use uma API ou uma interface de usuário para interagir com os microsserviços. Cadastre empresas, agende notificações e verifique o armazenamento de emails no MongoDB.

7. **Implantação em Produção:**
- Para implantar o sistema em um ambiente de produção, configure servidores e bancos de dados de produção, ajuste as configurações de segurança e dimensione os microsserviços conforme necessário.

## Contribuindo

Sinta-se à vontade para contribuir para este projeto abrindo problemas (issues) ou enviando pull requests. Se você tiver ideias para melhorias ou recursos adicionais, adoraríamos ouvir suas sugestões.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para obter detalhes.

========================================================================================================================================================================================

# Real-Time Notification System with Spring Cloud and Kafka

This is an example of a real-time notification system built using the Spring Cloud ecosystem and Apache Kafka. The system allows companies to subscribe to specific notifications, such as price updates, news, or events, and receive them via email.

## Project Overview

The project consists of several microservices that perform different functions:

- **Company Registration Microservice:** This service allows the registration and management of company information. It is an Eureka Client registered with the Eureka Server.

- **Email Sending Microservice:** Responsible for receiving requests to send email notifications and carrying out the actual email delivery. It is also an Eureka Client.

- **Email Storage Microservice:** This service receives successfully sent notifications and stores them in a non-relational database, such as MongoDB.

- **Scheduled Email Sending Microservice:** This microservice allows scheduling the sending of notifications at specific times or at regular intervals using the `@Scheduled` annotation from Spring.

## How to Use

1. **Environment Setup:**
   - Ensure you have Java, Spring Boot, and Apache Kafka installed in your development environment.

2. **Kafka Setup:**
   - Configure Apache Kafka with the necessary topics for the messaging system.

3. **MongoDB Setup:**
   - Configure MongoDB for storing successfully sent notifications.

4. **Cloning the Repository:**
   - Clone this repository to your local machine:

5. **Compiling and Running:**
- Go to each microservice directory and run it using Spring Boot. For example:


Ensure you start all necessary microservices.

6. **Testing the System:**
- Use an API or a user interface to interact with the microservices. Register companies, schedule notifications, and check email storage in MongoDB.

7. **Production Deployment:**
- To deploy the system in a production environment, configure production servers and databases, adjust security settings, and scale the microservices as needed.

## Contributing

Feel free to contribute to this project by opening issues or sending pull requests. If you have ideas for improvements or additional features, we'd love to hear your suggestions.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
