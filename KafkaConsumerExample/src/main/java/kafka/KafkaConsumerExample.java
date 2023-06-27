package kafka;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {

    private static final String TOPIC = "my-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "my-group";

    public static void main(String[] args) {

        // Thiết lập các thuộc tính cho Consumer
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Khởi tạo Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Đăng ký cho các topic muốn nhận tin nhắn
        consumer.subscribe(Collections.singletonList(TOPIC));

//        try {
//            // Nhận tin nhắn
//            while (true) {
//                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//                for (ConsumerRecord<String, String> record : records) {
//                    System.out.println("Received message: " + record.value());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // Đóng Consumer
//            consumer.close();
//        }
    }
}
