package com.mx.path.model.mdx.accessor.document;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
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
  public DocumentBaseAccessor() {
  }

  /**
   * @param configuration
   * @deprecated Use the default constructor, the configuration is set by the accessor construction context code
   */
  @Deprecated
  public DocumentBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a document
   *
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
   *
   * @param documentSearch
   * @return
   */
  @GatewayAPI
  @API(description = "List all documents")
  public AccessorResponse<MdxList<Document>> list(DocumentSearch documentSearch) {
    throw new AccessorMethodNotImplementedException();
  }

}
