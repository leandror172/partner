package com.leandror.ze.partner.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@Configuration
public class JacksonConfiguration {

  @Bean
  @Primary
  @Autowired
  public ObjectMapper jackson2ObjectMapperBuilder(final Jackson2ObjectMapperBuilder builder) {
    return builder.modules(new Jdk8Module(), new JtsModule()).build();
  }
}
