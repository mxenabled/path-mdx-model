package com.mx.web.mdx.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Used to pass all search attributes to document search function.
 * Binds to incoming document search query string parameters
 */
@SuppressWarnings({ "checkstyle:MemberName", "checkstyle:ParameterName", "checkstyle:MethodName" })
public class DocumentSearch {

  // Fields

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate end_on;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate start_on;
  private String type;
  private String account_id;

  // NOTE: Non standard naming to assist with parameter binding

  public final LocalDate getEnd_on() {
    return end_on;
  }

  public final void setEnd_on(LocalDate end_on) {
    this.end_on = end_on;
  }

  public final LocalDate getStart_on() {
    return start_on;
  }

  public final void setStart_on(LocalDate start_on) {
    this.start_on = start_on;
  }

  public final String getType() {
    return type;
  }

  public final void setType(String type) {
    this.type = type;
  }

  public final String getAccount_id() {
    return account_id;
  }

  public final void setAccount_id(String account_id) {
    this.account_id = account_id;
  }
}
