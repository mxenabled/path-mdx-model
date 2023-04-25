package com.mx.path.model.mdx.accessor.document;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.documents.Document;
import com.mx.path.model.mdx.model.documents.DocumentSearch;

/**
 * Accessor base for document operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/documents/#mdx-documents">Specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/documents/#mdx-documents")
public abstract class DocumentBaseAccessor extends Accessor {

  public DocumentBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a document
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a document")
  public AccessorResponse<Document> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * List documents
   * @param documentSearch
   * @return
   */
  @GatewayAPI
  @API(description = "List all documents")
  public AccessorResponse<MdxList<Document>> list(DocumentSearch documentSearch) {
    throw new AccessorMethodNotImplementedException();
  }

}
