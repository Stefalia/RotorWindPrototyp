from time import sleep
import random
from kafka import KafkaProducer
import sys

producer = KafkaProducer(bootstrap_servers='kafka:9092')

print(f"Temperatur producer started...")
sys.stdout.flush()

while True:
    temp = random.uniform(20.0, 100.0)  
    message = f"temperature value={temp}"
    producer.send('temperature', message.encode('utf-8'))
    print(f"Temperatur sent: {message}")
    sys.stdout.flush()
    sleep(5)  