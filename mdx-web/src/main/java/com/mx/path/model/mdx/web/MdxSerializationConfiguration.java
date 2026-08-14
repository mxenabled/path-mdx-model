package com.mx.path.model.mdx.web;

import com.google.gson.Gson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MdxSerializationConfiguration {

  /**
   * Provides the customized Gson instance for the application.
   * Spring Boot automatically detects this Bean and uses it to configure
   * the default GsonHttpMessageConverter for all JSON serialization.
   *
   * @return a fully configured Gson instance
   */
  @Bean
  public Gson gson() {
    return new MdxSerializerFactoryBean().getObject();
  }
}
