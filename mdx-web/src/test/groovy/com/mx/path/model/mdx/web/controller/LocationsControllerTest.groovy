package com.mx.path.model.mdx.web.controller

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.location.LocationGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.location.Location
import com.mx.path.model.mdx.model.location.LocationSearch

import org.mockito.Mockito

import spock.lang.Specification

class LocationsControllerTest extends Specification {
  LocationsController subject
  Gateway gateway
  LocationGateway locationGateway

  def setup() {
    subject = new LocationsController()
    locationGateway = spy(LocationGateway.builder().build())
    gateway = Gateway.builder().locations(locationGateway).build()
  }

  def cleanup() {
    LocationsController.clearRepository()
  }

  def "getLocation interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)
    Location location = new Location()


    when:
    Mockito.doReturn(new AccessorResponse<Location>().withResult(location)).when(locationGateway).get("id")
    def response = subject.getLocation("id")

    then:
    verify(locationGateway).get("id") || true
    response.getBody() == location
  }

  def "searchLocations interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def locationSearch = new LocationSearch()
    def locations = new MdxList<Location>()
    locations.add(new Location())

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Location>>().withResult(locations)).when(locationGateway).search(locationSearch)
    def response = subject.searchLocations(locationSearch)

    then:
    verify(locationGateway).search(locationSearch) || true
    response.getBody() == locations
  }
}
