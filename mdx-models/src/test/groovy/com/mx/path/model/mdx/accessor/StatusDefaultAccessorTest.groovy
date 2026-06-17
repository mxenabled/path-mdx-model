package com.mx.path.model.mdx.accessor

import com.mx.path.core.common.accessor.PathResponseStatus

import spock.lang.Specification

class StatusDefaultAccessorTest extends Specification {
  def subject = new StatusDefaultAccessor()

  def "get returns NO_CONTENT"() {
    when:
    def response = subject.get()
    then:
    response.status == PathResponseStatus.NO_CONTENT
  }
}
