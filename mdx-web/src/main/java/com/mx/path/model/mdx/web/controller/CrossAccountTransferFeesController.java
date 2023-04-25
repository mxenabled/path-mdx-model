package com.mx.path.model.mdx.web.controller;

import com.mx.common.accessors.AccessorResponse;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.cross_account_transfer.options.FeeListOptions;
import com.mx.path.model.mdx.model.transfer.Fee;
import com.mx.path.model.mdx.web.model.cross_account_transfer.CrossAccountTransferFeeListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class CrossAccountTransferFeesController extends BaseController {

  @RequestMapping(value = "/users/{userId}/cross_account_transfers/fees", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Fee>> list(CrossAccountTransferFeeListQueryParameters queryParameters) {
    FeeListOptions options = new FeeListOptions();
    options.setAccountTypeId(queryParameters.getAccount_type_id());
    options.setAccountTypeNumber(queryParameters.getAccount_type_number());
    options.setAmount(queryParameters.getAmount());
    options.setDestinationId(queryParameters.getDestination_id());
    options.setFromAccountId(queryParameters.getFrom_account_id());
    AccessorResponse<MdxList<Fee>> response = gateway().crossAccount().fees().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
