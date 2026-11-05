package com.mx.path.model.mdx.model.external_transfer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxList;
import com.mx.path.model.mdx.model.challenges.Challenge;

/**
 * An account at another institution that the user has linked by routing and account number.
 *
 * <p>See <a href="https://developer.mx.com/drafts/mdx/external_transfer/#accounts-external-account-fields">External account fields</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ExternalAccount extends MdxBase<ExternalAccount> {
  private String id;
  private String accountId;
  private String accountNumber;
  private String bankName;
  private MdxList<Challenge> challenges;
  private Long createdAt;
  private String name;
  private String nickname;
  private String routingNumber;
  private String status;
  private String type;
}
