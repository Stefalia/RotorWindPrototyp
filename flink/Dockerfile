FROM flink:latest

RUN apt-get update && apt-get install -y openjdk-11-jdk maven

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

COPY pom.xml /opt/flink/usrlib/pom.xml
COPY src /opt/flink/usrlib/src

WORKDIR /opt/flink/usrlib

RUN mvn clean package

CMD ["flink", "run", "/opt/flink/usrlib/target/flink-job-1.0-SNAPSHOT.jar"]