sleep 10

/opt/kafka/bin/kafka-topics.sh --create --topic temperature --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1