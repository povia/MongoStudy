package com.tistory.povia.mq;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

public @Component @Slf4j class Producer {
	private KafkaProducer<String, String> producer = null;
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	@Value("${spring.kafka.producer.key-serializer}")
	private String keySerializer;
	
	@Value("${spring.kafka.producer.value-serializer}")
	private String valueSerializer;
	
	@Value("${spring.kafka.template.default-topic}")
	private String topic;
	
	@PostConstruct
	public void build() {
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
		producer = new KafkaProducer<>(props);
	}
	
	public void send(String message) throws Exception{
		String result = "SEND FAIL";
		ProducerRecord<String, String> prd = new ProducerRecord<String, String>(this.topic, message);
		
	}
}
