package controller;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
	private static final String TOPIC = "my-topic";
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	private static final String GROUP_ID = "p-group";

	private KafkaConsumer<String, String> consumer;
	private KafkaProducer<String, String> producer;
	@FXML
	private Button btnAction;
	@FXML
	private TextArea txtA;
	@FXML
	private TextField txtMS;

	@FXML
	private void initialize() {
		// Khởi tạo Consumer

		consumer = createConSumer();
		Thread consumerThread = new Thread(this::consumeMessages);
		consumerThread.setDaemon(true);
		consumerThread.start();
		System.out.println("haya haya");

		producer = createProducer();
	}

	@FXML
	private void consumeMessages() {
		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("Received message: " + record.value());
					String message = record.value();

					// Cập nhật giao diện người dùng trong luồng JavaFX
					Platform.runLater(() -> {
						txtA.appendText(message + "\n");
					});

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}

	@FXML
	public KafkaConsumer<String, String> createConSumer() {

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
		return consumer;
	}

	public void sendMessage() {
		String message ="A: "+ txtMS.getText();
		ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, message);

		// Gửi tin nhắn
		producer.send(record);
		System.out.println("Sent message: " + message);

		// Xóa nội dung trường tin nhắn sau khi gửi
		txtMS.clear();
	}

	public KafkaProducer<String, String> createProducer() {

		// Thiết lập các thuộc tính cho Producer
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");

		// Khởi tạo Producer
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		return producer;
	}

}
