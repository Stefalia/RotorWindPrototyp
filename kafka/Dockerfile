FROM wurstmeister/kafka:latest

ENV KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092
ENV KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT
ENV KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092
ENV KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181

COPY server.properties /opt/kafka/config/server.properties
COPY create-topics.sh /usr/bin/create-topics.sh
RUN chmod +x /usr/bin/create-topics.sh

CMD ["sh", "-c", "/usr/bin/create-topics.sh & /opt/kafka/bin/kafka-server-start.sh /opt/kafka/config/server.properties"]