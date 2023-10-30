package com.mx.path.service.web.splunk

import com.mx.path.service.splunk.SplunkLoggerFactory

import spock.lang.Specification

class SplunkTest extends Specification {

  def "SplunkLoggerFactory.getLogger() returns appropriate logger from map"() {
    given:
    def logger = SplunkLoggerFactory.getLogger("afcu")
    def sameLogger = SplunkLoggerFactory.getLogger("afcu")

    expect:
    logger.getName() == "splunk.afcu"
    logger.is(sameLogger)
  }
}
