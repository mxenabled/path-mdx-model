package com.mx.path.model.mdx.web.controller;

import com.mx.path.gateway.accessor.AccessorResponse;
import com.mx.path.model.mdx.model.Frequency;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.transfer.options.FrequencyListOptions;
import com.mx.path.model.mdx.web.model.transfer.RecurringTransferFrequencyListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class RecurringTransferFrequenciesController extends BaseController {

  @RequestMapping(value = "/frequencies/recurring_transfer", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Frequency>> list(RecurringTransferFrequencyListQueryParameters queryParameters) {
    FrequencyListOptions options = new FrequencyListOptions();
    options.setTransferType(queryParameters.getTransfer_type());
    options.setFlow(queryParameters.getFlow());
    AccessorResponse<MdxList<Frequency>> response = gateway().transfers().recurring().frequencies().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
