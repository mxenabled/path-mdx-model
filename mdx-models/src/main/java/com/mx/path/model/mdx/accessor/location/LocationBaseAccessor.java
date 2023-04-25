package com.mx.path.model.mdx.accessor.location;

import com.mx.common.accessors.API;
import com.mx.common.accessors.Accessor;
import com.mx.common.accessors.AccessorConfiguration;
import com.mx.common.accessors.AccessorMethodNotImplementedException;
import com.mx.common.accessors.AccessorResponse;
import com.mx.common.gateway.GatewayAPI;
import com.mx.common.gateway.GatewayClass;
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
