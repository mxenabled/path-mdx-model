package com.mx.path.model.mdx.web.model.check_image;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
@Data
public class CheckImagesGetQueryParameters {

  private BigDecimal amount;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate posted_on;
}
