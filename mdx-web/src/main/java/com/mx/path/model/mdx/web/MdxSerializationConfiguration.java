package com.mx.path.model.mdx.web;

import java.util.List;

import com.google.gson.Gson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SuppressWarnings("checkstyle:designforextension")
public class MdxSerializationConfiguration implements WebMvcConfigurer {
  public MdxSerializerFactoryBean gsonBeanFactory() {
    MdxSerializerFactoryBean factory = new MdxSerializerFactoryBean();
    return factory;
  }

  @Bean
  public Gson gson() {
    return gsonBeanFactory().getObject();
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
    gsonConverter.setGson(gson());
    converters.add(gsonConverter);
  }
}
