FROM python:3.9-slim

WORKDIR /app

COPY TemperaturProducer.py .

RUN pip install kafka-python

CMD ["python", "TemperaturProducer.py"]