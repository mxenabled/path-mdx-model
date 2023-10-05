package com.mx.path.service.connection.realtime;

import lombok.Getter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.core.common.accessor.UnauthorizedException;
import com.mx.path.core.common.http.HttpStatus;
import com.mx.path.core.common.http.MediaType;
import com.mx.path.service.connection.realtime.model.MdxMember;
import com.mx.path.service.connection.realtime.model.MdxMemberWrapper;
import com.mx.path.service.connection.realtime.model.MdxRealtimeResponse;

public class MdxRealtimeMembersConnection {
  @Getter
  private MdxRealtimeConnection connection;
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  public MdxRealtimeMembersConnection(MdxRealtimeConnection connection) {
    this.connection = connection;
  }

  /**
   * Gets a member
   *
   * @param userId
   * @param memberId
   * @return
   */
  public MdxRealtimeResponse<MdxMember> get(String userId, String memberId) {
    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users/" + userId + "/members/" + memberId + ".json")
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error getting MDX member", "Encountered an authorization error getting MDX member").withReport(true);
          }
        })
        .withProcessor(response -> {
          MdxRealtimeResponse<MdxMember> mdxRealtimeResponse = new MdxRealtimeResponse<>(response.getStatus());
          if (response.getStatus() == HttpStatus.OK) {
            mdxRealtimeResponse.setObject(GSON.fromJson(response.getBody(), MdxMemberWrapper.class).getMember());
          }
          return mdxRealtimeResponse;
        })
        .get()
        .throwException()
        .getObject();
  }

  /**
   * Creates a member
   *
   * @param userId
   * @param memberId
   * @param userKey
   * @return
   */
  public MdxRealtimeResponse<MdxMember> create(String userId, String memberId, String userKey) {
    return create(userId, buildMember(memberId, userKey));
  }

  /**
   * Creates a member
   *
   * @param userId
   * @param memberId
   * @param login
   * @param password
   * @return
   */
  public MdxRealtimeResponse<MdxMember> create(String userId, String memberId, String login, char[] password) {
    return create(userId, buildMember(memberId, login, password));
  }

  /**
   * Creates a member
   *
   * @param userId
   * @param mdxMember
   * @return
   */
  public MdxRealtimeResponse<MdxMember> create(String userId, MdxMember mdxMember) {
    MdxMemberWrapper mdxMemberWrapper = new MdxMemberWrapper();
    mdxMemberWrapper.setMember(mdxMember);

    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users/" + userId + "/members.json")
        .withContentType(mdxType.toString())
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withBody(GSON.toJson(mdxMemberWrapper))
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error getting MDX member", "Encountered an authorization error getting MDX member").withReport(true);
          }
        })
        .withProcessor(response -> {
          MdxRealtimeResponse<MdxMember> mdxRealtimeResponse = new MdxRealtimeResponse<>(response.getStatus());
          if (response.getStatus() == HttpStatus.OK) {
            mdxRealtimeResponse.setObject(GSON.fromJson(response.getBody(), MdxMemberWrapper.class).getMember());
          }
          return mdxRealtimeResponse;
        })
        .post()
        .throwException()
        .getObject();
  }

  /**
   * Updates a member
   *
   * @param userId
   * @param mdxMember
   * @return
   */
  public MdxRealtimeResponse<MdxMember> update(String userId, MdxMember mdxMember) {
    MdxMemberWrapper mdxMemberWrapper = new MdxMemberWrapper();
    mdxMemberWrapper.setMember(mdxMember);

    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users/" + userId + "/members/" + mdxMember.getId() + ".json")
        .withContentType(mdxType.toString())
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withBody(GSON.toJson(mdxMemberWrapper))
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error updating MDX member", "Encountered an authorization error updating MDX member").withReport(true);
          }
        })
        .withProcessor(response -> {
          MdxRealtimeResponse<MdxMember> mdxRealtimeResponse = new MdxRealtimeResponse<>(response.getStatus());
          if (response.getStatus() == HttpStatus.OK) {
            mdxRealtimeResponse.setObject(GSON.fromJson(response.getBody(), MdxMemberWrapper.class).getMember());
          }
          return mdxRealtimeResponse;
        })
        .put()
        .throwException()
        .getObject();
  }

  private MdxMember buildMember(String memberId, String login, char[] password) {
    MdxMember mdxMember = new MdxMember();
    mdxMember.setId(memberId);
    mdxMember.setLogin(login);
    mdxMember.setPassword(new String(password));

    return mdxMember;
  }

  private MdxMember buildMember(String memberId, String userkey) {
    MdxMember mdxMember = new MdxMember();
    mdxMember.setId(memberId);
    mdxMember.setUserKey(userkey);

    return mdxMember;
  }
}
