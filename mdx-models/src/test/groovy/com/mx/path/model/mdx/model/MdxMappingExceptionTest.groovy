package com.mx.path.model.mdx.model

import spock.lang.Specification

class MdxMappingExceptionTest extends Specification {
  def "constructor stores message"() {
    when:
    def ex = new MdxMappingException("mapping failed")

    then:
    ex.getMessage() == "mapping failed"
  }
}
