package kafka;



import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerExample {
	private static final String TOPIC = "my-topic";
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";

	public KafkaProducer<String, String> createProducer(String[] args) {

		// Thiết lập các thuộc tính cho Producer
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");

		// Khởi tạo Producer
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		return producer;
//		try {
//			// Gửi tin nhắn
//			for (int i = 0; i < 10; i++) {
//				String message = "Message " + i;
//				ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, message);
//				producer.send(record);
//				System.out.println("Sent message: " + message);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// Đóng Producer
//			producer.close();
//		}
	}
}
