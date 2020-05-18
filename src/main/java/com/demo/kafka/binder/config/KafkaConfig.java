package com.demo.kafka.binder.config;

import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {



  @Bean
  public Function<String, String> process() {
    // transformation here and then return as a stream.
    // If using avro , return type might change .Like  product.class , Function <String , Product> process.
    // this would need a schema registry and to be added in the binder .
    // This could also be retained as a string and do JSON parse in the consumer too.
    return (input) -> input.toUpperCase();
  }
}
