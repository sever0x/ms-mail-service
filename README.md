
# Email Service

This is a Spring Boot application that processes email messages received through Apache Kafka. It uses Elasticsearch as a data store and sends emails via SMTP.

## Prerequisites

- Docker
- Docker Compose

## Setup

1. Clone the repository:

```
git clone https://github.com/sever0x/ms-mail-service.git
```

2. Navigate to the project directory:

```
cd ms-mail-service
```

3. Create a `.env` file in the project root directory and add your SMTP credentials:

```
MAIL_PASSWORD=your_mail_password
MAIL_USERNAME=your_mail_username
MAIL_HOST=your_mail_host
MAIL_PORT=your_mail_port
```

## Running the Application

To build and run the application using Docker Compose, execute the following command:

```
docker-compose up -d
```

This command will start the following services:

- `emailservice`: The Spring Boot application
- `elasticsearch`: Elasticsearch database
- `kibana`: Kibana for Elasticsearch
- `zookeeper`: Apache ZooKeeper for Kafka
- `kafka`: Apache Kafka message broker

After starting the services, the `emailservice` will be available at `http://localhost:8090`.

## Stopping the Application

To stop the running services, use the following command:

```
docker-compose down
```

## Additional Notes

- The application uses port `8090` for the Spring Boot application, `9200` for Elasticsearch, `5601` for Kibana, `2181` for ZooKeeper, and `9092` for Kafka.
- Elasticsearch data is persisted in the `./data` directory within the project.
- Kafka topics and partitions can be configured in the `docker-compose.yml` file.