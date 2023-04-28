package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.documents.Document;
import com.mx.path.model.mdx.model.documents.DocumentSearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class DocumentsController extends BaseController {

  public DocumentsController() {
  }

  @RequestMapping(value = "/users/{userId}/documents", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Document>> getDocumentsList(DocumentSearch documentSearch) {
    AccessorResponse<MdxList<Document>> response = gateway().documents().list(documentSearch);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/documents/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Document> getDocument(@PathVariable("id") String id) {
    AccessorResponse<Document> response = gateway().documents().get(id);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
