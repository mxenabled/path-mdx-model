package com.mx.path.model.mdx.model.account.alerts;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountAlert extends MdxBase<AccountAlert> {
  private List<Challenge> challenges;
  private List<AlertCriteria> criteria;
  private List<String> deliveryMethodIds;
  private String id;
  private boolean isEnabled;
  private String name;
}
