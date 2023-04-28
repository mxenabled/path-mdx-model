package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.ach_transfer.options.FrequencyListOptions;
import com.mx.path.model.mdx.web.model.ach_transfer.AchScheduledTransferFrequencyListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AchScheduledTransferFrequenciesController extends BaseController {

  @RequestMapping(value = "/ach_scheduled_transfers/frequencies", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Frequency>> list(AchScheduledTransferFrequencyListQueryParameters queryParameters) {
    FrequencyListOptions options = new FrequencyListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    AccessorResponse<MdxList<Frequency>> response = gateway().achTransfers().scheduled().frequencies().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
