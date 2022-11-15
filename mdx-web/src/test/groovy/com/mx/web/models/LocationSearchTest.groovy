package com.mx.web.mdx.models

import com.mx.models.location.LocationSearch

import spock.lang.Specification

class LocationSearchTest extends Specification {

  def latitude = 47.316681
  def locationType = "BRANCH"
  def longitude = 122.226782
  def searchRadius = 1

  def "LocationSearch builds as expected"() {
    given:
    def subject = new LocationSearch()
    subject.setHas_atm(true)
    subject.setHas_service_fee(true)
    subject.setLatitude(latitude)
    subject.setLocation_type(locationType)
    subject.setLongitude(longitude)
    subject.setSearch_radius(searchRadius)

    expect:
    subject.getHas_atm()
    subject.getHas_service_fee()
    subject.getLatitude() == latitude
    subject.getLocation_type() == locationType
    subject.getLatitude() == latitude
    subject.getSearch_radius() == searchRadius
  }
}
