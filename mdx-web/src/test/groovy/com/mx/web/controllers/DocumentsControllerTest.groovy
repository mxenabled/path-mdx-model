package com.mx.web.mdx.controllers

import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.documents.Document
import com.mx.models.documents.DocumentSearch
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.document.DocumentGateway

import org.mockito.Mockito
import org.springframework.http.HttpStatus

import spock.lang.Specification

class DocumentsControllerTest extends Specification {
  DocumentsController subject
  Gateway gateway
  DocumentGateway documentGateway

  def setup() {
    subject = new DocumentsController()
    documentGateway = spy(DocumentGateway.builder().build())
    gateway = Gateway.builder().documents(documentGateway).build()
  }

  def cleanup() {
    BaseController.clearRepository()
  }

  def "getDocument interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def document = new Document()

    when:
    Mockito.doReturn(new AccessorResponse<Document>().withResult(document)).when(documentGateway).get("id")
    def response = subject.getDocument("id")

    then:
    verify(documentGateway).get("id") || true
    HttpStatus.OK == response.getStatusCode()
  }

  def "listDocuments interacts with gateway"() {
    given:
    BaseController.setGateway(gateway)

    def document = new Document()
    def documents = new MdxList<>().tap { add(document) }
    def documentSearch = new DocumentSearch()

    when:
    Mockito.doReturn(new AccessorResponse<MdxList<Document>>().withResult(documents)).when(documentGateway).list(documentSearch)
    def response = subject.getDocumentsList(documentSearch)

    then:
    verify(documentGateway).list(documentSearch) || true
    HttpStatus.OK == response.getStatusCode()
  }
}
