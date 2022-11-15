package com.mx.web.mdx.controllers;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.transfer.TransferAmountOption;
import com.mx.models.transfer.options.TransferAmountOptionListOptions;
import com.mx.web.mdx.models.Transfers.TransferAmountOptionsListQueryParameters;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class TransferAmountOptionsController extends BaseController {

  @RequestMapping(value = "/users/{user_id}/amount_options/transfer", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<TransferAmountOption>> list(TransferAmountOptionsListQueryParameters queryParameters) throws Exception {
    TransferAmountOptionListOptions options = new TransferAmountOptionListOptions();
    options.setSourceAccountId(queryParameters.getSource_account_id());
    options.setDestinationAccountId(queryParameters.getDestination_account_id());
    options.setTransferType(queryParameters.getTransfer_type());
    AccessorResponse<MdxList<TransferAmountOption>> response = gateway().transfers().transferAmountOptions().list(options);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
