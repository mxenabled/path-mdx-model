package com.mx.path.model.mdx.web

import com.mx.path.core.common.connect.ConnectException

import org.springframework.beans.TypeMismatchException
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver

import spock.lang.Specification
import spock.lang.Unroll

class ExceptionResolverWebMvcConfigurerTest extends Specification {

  ExceptionResolverWebMvcConfigurer subject

  def setup() {
    subject = new ExceptionResolverWebMvcConfigurer()
  }

  def "replaces the existing DefaultHandlerExceptionResolver with our custom implementation"() {
    given:
    def originalResolver = new DefaultHandlerExceptionResolver()
    def resolvers = [originalResolver]

    when:
    subject.extendHandlerExceptionResolvers(resolvers)

    then:
    resolvers.size() == 1
    def newResolver = resolvers[0]
    newResolver instanceof DefaultHandlerExceptionResolver
    !newResolver.is(originalResolver)
  }

  @Unroll
  def "custom resolver returns null ModelAndView: #expectNullMav for #exception.class.simpleName"() {
    given:
    def resolvers = [
      new DefaultHandlerExceptionResolver()
    ]
    subject.extendHandlerExceptionResolvers(resolvers)
    def customResolver = resolvers[0]

    def request = new MockHttpServletRequest()
    def response = new MockHttpServletResponse()

    when:
    def mav = customResolver.resolveException(request, response, null, exception)

    then:
    expectNullMav ? mav == null : mav != null
    response.status == expectedStatus

    where:
    exception                                      | expectNullMav | expectedStatus
    new EOFException()                             | true          | 200
    new IOException("Broken pipe")                 | true          | 200
    new IOException("Connection reset by peer")    | true          | 200
    new ConnectException("", new EOFException())   | true          | 200
    new TypeMismatchException("bad value", String) | false         | 400
  }
}
