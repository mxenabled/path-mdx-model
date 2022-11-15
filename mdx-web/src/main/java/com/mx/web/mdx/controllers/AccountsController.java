package com.mx.web.mdx.controllers;

import javax.servlet.http.HttpSession;

import com.mx.common.accessors.AccessorResponse;
import com.mx.common.models.MdxList;
import com.mx.models.account.Account;
import com.mx.models.account.AccountTransactions;
import com.mx.models.account.Transaction;
import com.mx.models.account.TransactionSearchRequest;
import com.mx.models.account.TransactionsPage;
import com.mx.models.account.options.TransactionListOptions;
import com.mx.models.ondemand.MdxListWrapper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "{clientId}", produces = BaseController.MDX_MEDIA)
public class AccountsController extends BaseController {

  public AccountsController() {
  }

  @RequestMapping(value = "/users/{userId}/accounts", method = RequestMethod.GET, produces = BaseController.MDX_MEDIA)
  public final ResponseEntity<MdxList<Account>> getAllAccounts()
      throws Exception {
    AccessorResponse<MdxList<Account>> response = gateway().accounts().list();
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = BaseController.MDX_ONDEMAND_MEDIA)
  public final ResponseEntity<MdxListWrapper> getOnDemandAccounts()
      throws Exception {
    return new ResponseEntity<>(new MdxListWrapper("accounts", gateway().accounts().list().getResult().wrapped()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{id}", method = RequestMethod.GET)
  public final ResponseEntity<Account> getAccount(@PathVariable("id") String accountId) throws Exception {
    AccessorResponse<Account> response = gateway().accounts().get(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{id}", method = RequestMethod.DELETE)
  public final ResponseEntity<?> deleteAccount(@PathVariable("id") String accountId) throws Exception {
    AccessorResponse<Account> response = gateway().accounts().delete(accountId);
    return new ResponseEntity<>(createMultiMapForResponse(response.getHeaders()), HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/users/{userId}/accounts", method = RequestMethod.POST)
  public final ResponseEntity<Account> createAccount(@RequestBody Account accountRequest) throws Exception {
    AccessorResponse<Account> response = gateway().accounts().create(accountRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{id}", method = RequestMethod.PUT)
  public final ResponseEntity<Account> updateAccount(HttpSession session, @RequestHeader HttpHeaders headers,
      @PathVariable("id") String accountId, @RequestBody Account accountRequest) throws Exception {
    accountRequest.setId(accountId);
    AccessorResponse<Account> response = gateway().accounts().update(accountId, accountRequest);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/accounts/{id}/transactions", method = RequestMethod.GET, produces = BaseController.MDX_ONDEMAND_MEDIA)
  public final ResponseEntity<AccountTransactions> searchTransactions(
      TransactionSearchRequest searchRequest,
      @PathVariable("id") String accountId) throws Exception {

    AccessorResponse<TransactionsPage> response = gateway().accounts().transactions().search(accountId, searchRequest);

    AccountTransactions accountTransactions = new AccountTransactions();
    accountTransactions.setId(accountId);
    accountTransactions.setTransactions(response.getResult());

    return new ResponseEntity<>(accountTransactions.wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{accountId}/transactions", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Transaction>> listTransactions(@PathVariable("accountId") String accountId, @RequestParam("check_number") String checkNumber) throws Exception {
    TransactionListOptions transactionListOptions = new TransactionListOptions();
    transactionListOptions.setCheckNumber(checkNumber);
    AccessorResponse<MdxList<Transaction>> response = gateway().accounts().transactions().list(accountId, transactionListOptions);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }

  @RequestMapping(value = "/users/{userId}/accounts/{id}/transactions/recent", method = RequestMethod.GET)
  public final ResponseEntity<MdxList<Transaction>> recentTransactions(@PathVariable("id") String accountId) throws Exception {
    AccessorResponse<MdxList<Transaction>> response = gateway().accounts().transactions().recent(accountId);
    return new ResponseEntity<>(response.getResult().wrapped(), createMultiMapForResponse(response.getHeaders()), HttpStatus.OK);
  }
}
