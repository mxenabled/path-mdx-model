package com.mx.path.model.mdx.web.filter

import javax.servlet.FilterChain
import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.mx.path.core.common.accessor.AccessorUserException
import com.mx.path.core.common.accessor.PathResponseStatus

import spock.lang.Specification

class ErrorHandlerFilterTest extends Specification {
  FilterChain filterChain
  HttpServletRequest request
  HttpServletResponse response
  ErrorHandlerFilter subject
  ServletOutputStream outputStream
  String clientId = "client-123"
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
  }

  def "renders exception"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new AccessorUserException("Danger!", "Danger", PathResponseStatus.USER_ERROR)
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
      throw new RuntimeException("Start fan, insert crap", new AccessorUserException("Danger!", "Danger!", PathResponseStatus.USER_ERROR))
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
      throw new AccessorUserException("Danger!", "Danger!", PathResponseStatus.USER_ERROR).withCode("4011")
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
      throw new AccessorUserException("Danger!", "Danger!", PathResponseStatus.USER_ERROR).withCode("4011").withErrorTitle("errorTitle")
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * outputStream.write(_) >> { args -> responseBody = new String((byte[]) args[0]) }
    responseBody == '{"error":{"message":"Danger!","user_message":"Danger!","error_title":"errorTitle","error_code":"4011"}}'
  }

  def "adds header to exception caused by exception"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new RuntimeException("Start fan, insert crap",
      new AccessorUserException("Danger!", "Danger!", PathResponseStatus.USER_ERROR)
      .withHeader("header", "value"))
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * response.setHeader("header", "value")
  }

  def "adds header to exception"() {
    given:
    response.getOutputStream() >> outputStream
    filterChain.doFilter(_ as HttpServletRequest, _ as HttpServletResponse) >> { HttpServletRequest request, HttpServletResponse response ->
      throw new AccessorUserException("Danger!", "Danger!", PathResponseStatus.USER_ERROR).withHeader("header", "value")
    }

    when:
    subject.doFilter(request, response, filterChain)

    then:
    1 * response.setHeader("header", "value")
  }
}
