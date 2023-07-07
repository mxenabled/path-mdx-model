package com.mx.path.service.connection.realtime;

import lombok.Getter;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import com.mx.path.connect.http.HttpAccessorConnection;
import com.mx.path.core.common.configuration.Configuration;

@SuppressFBWarnings("EQ_DOESNT_OVERRIDE_EQUALS")
public class MdxRealtimeConnection extends HttpAccessorConnection {
  @Getter
  private MdxRealtimeConnectionConfiguration config;
  private MdxRealtimeAccountsConnection mdxRealtimeAccountsConnection;
  private MdxRealtimeMembersConnection mdxRealtimeMembersConnection;
  private MdxRealtimeUsersConnection mdxRealtimeUsersConnection;

  public MdxRealtimeConnection(@Configuration MdxRealtimeConnectionConfiguration configuration) {
    this.config = configuration;
  }

  /**
   * APIs for dealing with accounts
   *
   * @return
   */
  public MdxRealtimeAccountsConnection accounts() {
    if (mdxRealtimeAccountsConnection == null) {
      mdxRealtimeAccountsConnection = new MdxRealtimeAccountsConnection(this);
    }
    return mdxRealtimeAccountsConnection;
  }

  /**
   * APIs for dealing with members
   *
   * @return
   */
  public MdxRealtimeMembersConnection members() {
    if (mdxRealtimeMembersConnection == null) {
      mdxRealtimeMembersConnection = new MdxRealtimeMembersConnection(this);
    }
    return mdxRealtimeMembersConnection;
  }

  /**
   * APIs for dealing with users
   *
   * @return
   */
  public MdxRealtimeUsersConnection users() {
    if (mdxRealtimeUsersConnection == null) {
      mdxRealtimeUsersConnection = new MdxRealtimeUsersConnection(this);
    }
    return mdxRealtimeUsersConnection;
  }
}
