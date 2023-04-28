package com.mx.path.model.mdx.accessor.location;

import com.mx.path.core.common.accessor.API;
import com.mx.path.core.common.accessor.AccessorMethodNotImplementedException;
import com.mx.path.core.common.gateway.GatewayAPI;
import com.mx.path.core.common.gateway.GatewayClass;
import com.mx.path.gateway.accessor.Accessor;
import com.mx.path.gateway.accessor.AccessorConfiguration;
import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.location.Location;
import com.mx.path.model.mdx.model.location.LocationSearch;

/**
 * Accessor base for location operations
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/location/#mdx-location">specifications</a>
 */
@GatewayClass
@API(specificationUrl = "https://developer.mx.com/drafts/mdx/location/#mdx-location")
public abstract class LocationBaseAccessor extends Accessor {

  public LocationBaseAccessor(AccessorConfiguration configuration) {
    super(configuration);
  }

  /**
   * Get a location
   * @param id
   * @return
   */
  @GatewayAPI
  @API(description = "Get a location")
  public AccessorResponse<Location> get(String id) {
    throw new AccessorMethodNotImplementedException();
  }

  /**
   * Search for locations using {@link LocationSearch}
   * @param locationSearch
   * @return
   */
  @GatewayAPI
  @API(description = "Search for locations using LocationSearch")
  public AccessorResponse<MdxList<Location>> search(LocationSearch locationSearch) {
    throw new AccessorMethodNotImplementedException();
  }

}
