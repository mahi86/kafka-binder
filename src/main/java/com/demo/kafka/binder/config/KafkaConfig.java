package com.demo.kafka.binder.config;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.TransformerSupplier;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

  Predicate<String, String> isCatEntry = (k, v) -> v.contains("catentry");;
  Predicate<String, String> isCategory = (k, v) -> v.contains("category");
  Predicate<String, String> isPrice = (k, v) -> v.contains("price");


  @Bean
  public  Function<String , String> processSingleInput() {
    return input -> input.toUpperCase();
  }

  @Bean
  public Function<KStream<String,String> , KStream<String ,  String>[]> process() {
    // transformation here and then return as a stream.
    // If using avro , return type might change .Like  product.class , Function <String , Product> process.
    // this would need a schema registry and to be added in the binder .
    // This could also be retained as a string and do JSON parse in the consumer too.
    //return (input) -> input.branch(isCatEntry , isCategory , isPrice);  }
   // return input -> input.peek((key, value) -> {
  //  });

    return input -> input.branch(isCatEntry , isCategory , isPrice);


  }
}
