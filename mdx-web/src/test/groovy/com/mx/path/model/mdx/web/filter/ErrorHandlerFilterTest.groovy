package com.mx.path.model.mdx.web.filter

import com.mx.path.core.common.accessor.PathResponseStatus
import com.mx.path.core.common.accessor.RequestValidationException
import com.mx.path.core.context.RequestContext
import com.mx.path.core.context.ResponseContext

import spock.lang.Specification

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletOutputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class ErrorHandlerFilterTest extends Specification {
  FilterChain filterChain
  HttpServletRequest request
  HttpServletResponse response
  ErrorHandlerFilter subject
  ServletOutputStream outputStream
  ErrorHandler errorHandler

  String responseBody

  def setup() {
    filterChain = Mock()
    request = Mock()
    response = Mock()
    outputStream = Mock()

    errorHandler = new ErrorHandler()
    subject = new ErrorHandlerFilter()
    subject.setErrorHandler(errorHandler)

    ResponseContext.builder().build().register()
  }

  def cleanup() {
    RequestContext.clear()
    ResponseContext.clear()
  }

  def "renders exception"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new RequestValidationException("Danger!", "Danger").withStatus(PathResponseStatus.USER_ERROR)
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * outputStream.write(_) >> { args -> responseBody = new String((byte[]) args[0]) }
    responseBody == '{"error":{"message":"Danger!","user_message":"Danger"}}'
  }

  def "renders exception caused by PathRequestException"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new RuntimeException("Start fan, insert crap", new RequestValidationException("Danger!", "Danger!").withStatus(PathResponseStatus.USER_ERROR))
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * outputStream.write(_) >> { args -> responseBody = new String((byte[]) args[0]) }
    responseBody == '{"error":{"message":"Danger!","user_message":"Danger!"}}'
  }

  def "renders exception with code"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new RequestValidationException("Danger!", "Danger!").withStatus(PathResponseStatus.USER_ERROR).withCode("4011")
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * outputStream.write(_) >> { args -> responseBody = new String((byte[]) args[0]) }
    responseBody == '{"error":{"message":"Danger!","user_message":"Danger!","error_code":"4011"}}'
  }

  def "renders exception with Title Message"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new RequestValidationException("Danger!", "Danger!").withStatus(PathResponseStatus.USER_ERROR).withCode("4011").withErrorTitle("errorTitle")
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * outputStream.write(_) >> { args -> responseBody = new String((byte[]) args[0]) }
    responseBody == '{"error":{"message":"Danger!","user_message":"Danger!","error_title":"errorTitle","error_code":"4011"}}'
  }

  def "adds header to response from RequestValidationException when cause is RuntimeException"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new RuntimeException("Start fan, insert crap",
      new RequestValidationException("Danger!", "Danger!")
      .withStatus(PathResponseStatus.USER_ERROR)
      .withHeader("header", "value"))
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * response.setHeader("header", "value")
  }

  def "adds header to response from RequestValidationException"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new RequestValidationException("Danger!", "Danger!")
      .withStatus(PathResponseStatus.USER_ERROR)
      .withHeader("header", "value")
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * response.setHeader("header", "value")
  }

  def "adds headers to response from ResponseContext"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new RequestValidationException("Danger!", "Danger!")
      .withStatus(PathResponseStatus.USER_ERROR)
      .withHeader("header1", "value1")
    }

    ResponseContext.current().getHeaders().put("header2", "value2")

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * response.setHeader("header1", "value1")
    1 * response.setHeader("header2", "value2")
  }
}
