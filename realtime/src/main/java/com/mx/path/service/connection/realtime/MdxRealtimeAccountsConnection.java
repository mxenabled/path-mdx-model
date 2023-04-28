package com.mx.path.service.connection.realtime;

import lombok.Getter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.core.common.accessor.UnauthorizedException;
import com.mx.path.core.common.http.HttpStatus;
import com.mx.path.core.common.http.MediaType;
import com.mx.path.service.connection.realtime.model.MdxAccount;
import com.mx.path.service.connection.realtime.model.MdxAccountWrapper;
import com.mx.path.service.connection.realtime.model.MdxRealtimeResponse;

public class MdxRealtimeAccountsConnection {
  @Getter
  private MdxRealtimeConnection connection;
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  public MdxRealtimeAccountsConnection(MdxRealtimeConnection connection) {
    this.connection = connection;
  }

  /**
   * Creates an account
   *
   * @param userId
   * @param memberId
   * @param account
   */
  public MdxRealtimeResponse<MdxAccount> create(String userId, String memberId, MdxAccount account) {
    MdxAccountWrapper mdxAccountWrapper = new MdxAccountWrapper();
    mdxAccountWrapper.setAccount(account);

    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users/" + userId + "/members/" + memberId + "/accounts.json")
        .withContentType(mdxType.toString())
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withBody(GSON.toJson(mdxAccountWrapper))
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error creating MDX account", "Encountered an authorization error creating MDX account").withReport(true);
          }
        })
        .withProcessor(response -> {
          MdxRealtimeResponse<MdxAccount> mdxRealtimeResponse = new MdxRealtimeResponse<>(response.getStatus());
          if (response.getStatus() == HttpStatus.OK) {
            mdxRealtimeResponse.setObject(GSON.fromJson(response.getBody(), MdxAccountWrapper.class).getAccount());
          }
          return mdxRealtimeResponse;
        })
        .post()
        .throwException()
        .getObject();
  }
}
