package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.transfer.Repayment;
import com.mx.path.model.mdx.model.transfer.options.RepaymentListOptions;
import com.mx.path.model.mdx.web.model.transfer.TransferRepaymentsQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class TransferRepaymentsController extends BaseController {

  public TransferRepaymentsController() {
  }

  @RequestMapping(value = "/users/{user_id}/transfers/{id}/repayments", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Repayment>> getRepayments(@PathVariable("id") String transferId, TransferRepaymentsQueryParameters queryParameters) {
    RepaymentListOptions options = new RepaymentListOptions();
    options.setStartOn(queryParameters.getStart_on());
    options.setFrequencyId(queryParameters.getFrequency_id());
    AccessorResponse<MdxList<Repayment>> response = gateway().transfers().repayments().list(transferId, options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
