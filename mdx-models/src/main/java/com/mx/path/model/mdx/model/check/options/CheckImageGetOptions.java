package com.mx.path.model.mdx.model.check.options;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CheckImageGetOptions {
  private BigDecimal amount;
  private LocalDate postedOn;
}
