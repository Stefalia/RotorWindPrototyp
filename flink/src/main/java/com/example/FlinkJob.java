import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class FlinkJob {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "kafka:9092");
        properties.setProperty("group.id", "flink");

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>("temperature", new SimpleStringSchema(), properties);
        DataStream<String> stream = env.addSource(consumer);

        stream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                String[] parts = value.split(" ");
                double temperature = Double.parseDouble(parts[1].split("=")[1]);
                if (temperature > 95.0) {
                    sendEmail("Temperature Alert", "Temperature is above 95 degrees: " + temperature);
                }
            }
        });

        env.execute("Flink Temperature Alert Job");
    }

    public static void sendEmail(String subject, String body) {
        String to = "recipient@example.com";
        String from = "sender@example.com";
        String host = "smtp.example.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}