package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.Frequency;
import com.mx.models.ach_transfer.options.FrequencyListOptions;
import com.mx.web.mdx.models.AchTransfers.AchScheduledTransferFrequencyListQueryParameters;

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
