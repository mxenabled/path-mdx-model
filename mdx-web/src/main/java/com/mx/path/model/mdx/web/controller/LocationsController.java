package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.location.Location;
import com.mx.models.location.LocationSearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class LocationsController extends BaseController {

  @RequestMapping(value = "/locations", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Location>> searchLocations(LocationSearch locationSearch) {
    AccessorResponse<MdxList<Location>> response = gateway().locations().search(locationSearch);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/locations/{locationId}", method = RequestMethod.GET)
  public final ResponseEntity<Location> getLocation(@PathVariable("locationId") String locationId) {
    AccessorResponse<Location> response = gateway().locations().get(locationId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
