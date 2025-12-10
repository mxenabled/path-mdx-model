package com.mx.path.model.mdx.web;

import com.google.gson.Gson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("checkstyle:designforextension")
public class MdxSerializationConfiguration {
  public MdxSerializerFactoryBean gsonBeanFactory() {
    MdxSerializerFactoryBean factory = new MdxSerializerFactoryBean();
    return factory;
  }

  @Bean
  public Gson gson() throws Exception {
    return gsonBeanFactory().getObject();
  }
}
