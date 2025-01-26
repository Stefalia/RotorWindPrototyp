# Rotor

This project sets up a Kafka instance, a Python producer that sends random temperature values to a Kafka topic, Telegraf to consume the Kafka topic and write to InfluxDB, Grafana to visualize the data, Apache Flink to process the data and send email alerts, and Splunk to monitor and analyze logs. It uses Docker and Docker Compose for easy deployment.

## Project Structure

```
Rotor 
├── docker-compose.yml 
├── kafka 
│ ├── Dockerfile 
│ ├── server.properties 
│ └── create-topics.sh 
├── producer 
│ ├── TemperaturProducer.py 
│ └── Dockerfile 
├── telegraf 
│ ├── telegraf.conf 
│ └── Dockerfile 
├── influxdb 
│ ├── influxdb.conf 
│ └── Dockerfile 
├── grafana 
│ ├── grafana.ini 
│ └── Dockerfile 
├── flink 
│ ├── Dockerfile 
│ ├── pom.xml 
│ └── src/main/java/com/example/FlinkJob.java 
├── splunk 
│ └── Dockerfile 
└── README.md
```

## Requirements

- Docker
- Docker Compose

## Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd Rotor
   ```

2. **Start all services:**

   Run the following command at the top level of the project directory:

   ```bash
   docker-compose up -d
   ```

   This command will start Kafka, Zookeeper, Telegraf, InfluxDB, Grafana, Flink, and Splunk services in detached mode.

3. **Build and run the Python producer:**

   Navigate to the python-producer directory and build the Docker image:

   ```bash
   docker-compose build python-producer
   docker-compose up -d python-producer
   ```

   The producer will start sending random temperature values to the Kafka topic named 'temperature' every 5 seconds.

4. **Build and run the Flink job:**

   Navigate to the flink directory and build the Docker image:

   ```bash
   docker-compose build flink-jobmanager flink-taskmanager
   docker-compose up -d flink-jobmanager flink-taskmanager
   ```

   The Flink job will monitor the temperature values and send an email if the temperature exceeds 95 degrees.

5. **Access Grafana:**

   Once all services are running, you can access Grafana by navigating to `http://localhost:3000` in your web browser. The default login is `admin` for both username and password.

6. **Configure Grafana:**

   - Add InfluxDB as a data source in Grafana.
   - Create dashboards to visualize the temperature data collected from Kafka.

7. **Access Splunk:**

   Once all services are running, you can access Splunk by navigating to 'http://localhost:8000' in your web browser. The default login is 'admin' and the password is 'changeme'.

## Stopping the Services

To stop all services, run the following command in the `kafka` directory:

```bash
docker-compose down
```

## Additional Notes

- Ensure that the necessary configurations in the `.conf` files are set according to your requirements.
- You can create dashboards in Grafana to visualize the temperature data collected from Kafka.
- Ensure that the email configuration in the Flink job is correct to send email notifications.