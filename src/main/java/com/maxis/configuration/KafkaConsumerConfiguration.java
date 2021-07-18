//package com.maxis.configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfiguration {
//
//	@Bean
//	public ConsumerFactory<String, String> consumerFactory() {
//
//		Map<String, Object> config = new HashMap<>();
//
//		config.put("bootstrap.servers", "my-cluster-kafka-bootstrap-maxis-kafka.nagadpay.com:443");
//		config.put("key.deserializer", StringDeserializer.class);
//		config.put("value.deserializer", StringDeserializer.class);
//		config.put("security.protocol", "SSL");
//
////--------------------> For Local Machine <-----------------------
//		config.put("ssl.keystore.location", "src/main/resources/keystore.jks");
//		config.put("ssl.keystore.password", "password");
//		config.put("ssl.truststore.location", "src/main/resources/keystore.jks");
//		config.put("ssl.truststore.password", "password");
//
////------------------->     For Docker     <-------------------------
//		config.put("ssl.keystore.location", "keystore.jks");
//		config.put("ssl.keystore.password", "password");
//		config.put("ssl.truststore.location", "keystore.jks");
//		config.put("ssl.truststore.password", "password");
//
//		return new DefaultKafkaConsumerFactory<>(config);
//	}
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
//		factory.setConsumerFactory(consumerFactory());
//		return factory;
//	}
//
//}
