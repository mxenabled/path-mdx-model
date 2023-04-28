package com.mx.path.service.connection.realtime;

import lombok.Getter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mx.path.connect.messaging.remote.models.RemoteUser;
import com.mx.path.core.common.accessor.UnauthorizedException;
import com.mx.path.core.common.http.HttpStatus;
import com.mx.path.core.common.http.MediaType;
import com.mx.path.core.common.lang.Strings;
import com.mx.path.service.connection.realtime.model.MdxRealtimeResponse;
import com.mx.path.service.connection.realtime.model.MdxUser;
import com.mx.path.service.connection.realtime.model.MdxUserWrapper;

public class MdxRealtimeUsersConnection {
  @Getter
  private MdxRealtimeConnection connection;
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  public MdxRealtimeUsersConnection(MdxRealtimeConnection connection) {
    this.connection = connection;
  }

  /**
   * Creates a user
   *
   * @param userId
   * @return
   */
  public MdxRealtimeResponse<MdxUser> get(String userId) {
    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users/" + userId + ".json")
        .withContentType(mdxType.toString())
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error getting MDX user", "Encountered an authorization error getting MDX user").withReport(true);
          }
        })
        .withProcessor(response -> {
          MdxRealtimeResponse<MdxUser> mdxRealtimeResponse = new MdxRealtimeResponse<>(response.getStatus());
          if (response.getStatus() == HttpStatus.OK) {
            mdxRealtimeResponse.setObject(GSON.fromJson(response.getBody(), MdxUserWrapper.class).getUser());
          }
          return mdxRealtimeResponse;
        })
        .get()
        .throwException()
        .getObject();
  }

  /**
   * Creates a user
   *
   * @param userId
   * @return
   */
  public MdxRealtimeResponse<MdxUser> create(String userId) {
    return create(userId, null);
  }

  /**
   * Creates a user
   *
   * @param userId
   * @param user
   * @return
   */
  public MdxRealtimeResponse<MdxUser> create(String userId, RemoteUser user) {
    MdxUser mdxUser = new MdxUser();
    mdxUser.setId(userId);
    if (user != null) {
      if (Strings.isNotBlank(user.getFirstName())) {
        mdxUser.setFirstName(user.getFirstName());
      }
      if (Strings.isNotBlank(user.getEmail())) {
        mdxUser.setEmail(user.getEmail());
      }
      if (Strings.isNotBlank(user.getLastName())) {
        mdxUser.setLastName(user.getLastName());
      }
      if (Strings.isNotBlank(user.getPhone())) {
        mdxUser.setPhone(user.getPhone());
      }
    }

    MdxUserWrapper mdxUserWrapper = new MdxUserWrapper();
    mdxUserWrapper.setUser(mdxUser);

    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users.json")
        .withContentType(mdxType.toString())
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withBody(GSON.toJson(mdxUserWrapper))
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error creating MDX user", "Encountered an authorization error creating MDX user").withReport(true);
          }
        })
        .withProcessor(response -> {
          MdxRealtimeResponse<MdxUser> mdxRealtimeResponse = new MdxRealtimeResponse<>(response.getStatus());
          if (response.getStatus() == HttpStatus.OK) {
            mdxRealtimeResponse.setObject(GSON.fromJson(response.getBody(), MdxUserWrapper.class).getUser());
          }
          return mdxRealtimeResponse;
        })
        .post()
        .throwException()
        .getObject();
  }

  /**
   * Deletes a user
   *
   * @param userId
   */
  public HttpStatus delete(String userId) {
    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users/" + userId + ".xml")
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error deleting MDX user", "Encountered an authorization error deleting MDX user").withReport(true);
          }
        })
        .delete()
        .throwException()
        .getStatus();
  }

  /**
   * Updates a user
   *
   * @param userId
   * @param user
   * @return
   */
  public MdxRealtimeResponse<MdxUser> update(String userId, MdxUser user) {
    MdxUserWrapper mdxUserWrapper = new MdxUserWrapper();
    mdxUserWrapper.setUser(user);

    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users/" + userId + ".json")
        .withContentType(mdxType.toString())
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withBody(GSON.toJson(mdxUserWrapper))
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error updating MDX user", "Encountered an authorization error updating MDX user").withReport(true);
          }
        })
        .withProcessor(response -> {
          MdxRealtimeResponse<MdxUser> mdxRealtimeResponse = new MdxRealtimeResponse<>(response.getStatus());
          if (response.getStatus() == HttpStatus.OK) {
            mdxRealtimeResponse.setObject(GSON.fromJson(response.getBody(), MdxUserWrapper.class).getUser());
          }
          return mdxRealtimeResponse;
        })
        .put()
        .throwException()
        .getObject();
  }

  /**
   * Re-maps a user id
   *
   * @param userId
   * @param newUserId
   * @return
   */
  public MdxRealtimeResponse<MdxUser> remapId(String userId, String newUserId) {
    MdxUserWrapper mdxUserWrapper = new MdxUserWrapper();
    MdxUser user = new MdxUser();
    user.setId(newUserId);
    mdxUserWrapper.setUser(user);

    MediaType mdxType = new MediaType("application", "vnd.moneydesktop.mdx.v5+json");

    return connection.request("/" + connection.getConfig().getClientId() + "/users/" + userId + "/remap_id.json")
        .withContentType(mdxType.toString())
        .withAccept(mdxType.toString())
        .withHeaders(headers -> {
          headers.put("Accept-Encoding", "gzip, deflate, br");
          headers.put("MD-API-KEY", connection.getConfig().getApiKey());
        })
        .withBody(GSON.toJson(mdxUserWrapper))
        .withOnComplete(response -> {
          HttpStatus status = response.getStatus();
          if (status == HttpStatus.UNAUTHORIZED) {
            throw new UnauthorizedException("Encountered an authorization error remapping MDX user", "Encountered an authorization error remapping MDX user").withReport(true);
          }
        })
        .withProcessor(response -> {
          MdxRealtimeResponse<MdxUser> mdxRealtimeResponse = new MdxRealtimeResponse<>(response.getStatus());
          if (response.getStatus() == HttpStatus.OK) {
            mdxRealtimeResponse.setObject(GSON.fromJson(response.getBody(), MdxUserWrapper.class).getUser());
          }
          return mdxRealtimeResponse;
        })
        .put()
        .throwException()
        .getObject();
  }
}
