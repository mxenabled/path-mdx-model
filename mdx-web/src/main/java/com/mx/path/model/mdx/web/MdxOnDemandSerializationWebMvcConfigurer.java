package com.mx.path.model.mdx.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mx.path.model.mdx.model.Resources;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.xml.JacksonXmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.dataformat.xml.XmlMapper;

@Configuration
public class MdxOnDemandSerializationWebMvcConfigurer implements WebMvcConfigurer {
  /**
   * List of all configured converts. All others will be removed before inserting custom MappingJackson2XmlHttpMessageConverter
   */
  static final List<Class<?>> CONVERT_CLASSES;
  static {
    CONVERT_CLASSES = new ArrayList<>();
    CONVERT_CLASSES.add(GsonHttpMessageConverter.class);
    CONVERT_CLASSES.add(StringHttpMessageConverter.class);
    CONVERT_CLASSES.add(ByteArrayHttpMessageConverter.class);
  }

  @Override
  public final void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    // Get rid of unused converters
    // These get in the way of the other
    List<HttpMessageConverter<?>> toRemove = converters.stream().filter(t -> !CONVERT_CLASSES.contains(t.getClass())).collect(Collectors.toCollection(ArrayList::new));
    converters.removeAll(toRemove);

    // XML
    converters.add(new JacksonXmlHttpMessageConverter(XmlMapper.builder()
        .addModule(payloadModule())
        .enable(SerializationFeature.INDENT_OUTPUT)
        .build()));
  }

  public final SimpleModule payloadModule() {
    SimpleModule module = new SimpleModule();

    Resources.registerOnDemandResources(module);

    return module;
  }
}
