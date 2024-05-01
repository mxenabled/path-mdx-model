package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.device.DeviceGateway
import com.mx.path.gateway.api.device.VerificationMethodsGateway
import com.mx.path.gateway.api.document.DocumentGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.device.VerificationMethod
import com.mx.path.model.mdx.model.documents.DeliveryPreferences
import com.mx.path.model.mdx.model.documents.Document
import com.mx.path.model.mdx.model.documents.DocumentSearch
import com.mx.path.model.mdx.web.model.document.DocumentDeliveryGetQueryParameters

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class VerificationMethodsControllerTest extends Specification {
  VerificationMethodsController subject
  Gateway gateway
  VerificationMethodsGateway verificationMethodGateway
  DeviceGateway deviceGateway

  def setup() {
    subject = new VerificationMethodsController()
    verificationMethodGateway = spy(VerificationMethodsGateway.builder().build())
    deviceGateway = DeviceGateway.builder().verificationMethods(verificationMethodGateway).build()
    gateway = Gateway.builder().devices(deviceGateway).build()
  }

  def cleanup() {
    BaseController.clearGateway()
  }

  def "list verification methods interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def verificationMethod = new VerificationMethod()
    def verificationMethods = new MdxList<VerificationMethod>().tap { add(verificationMethod) }

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<VerificationMethod>>().withResult(verificationMethods)).when(verificationMethodGateway).list()
    def response = subject.list()

    then:
    verify(verificationMethodGateway).list() || true
    HttpStatus.OK == response.getStatusCode()
  }
}
