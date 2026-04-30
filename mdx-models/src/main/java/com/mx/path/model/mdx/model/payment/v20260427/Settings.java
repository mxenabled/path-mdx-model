package com.mx.path.model.mdx.model.payment.v20260427;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@EqualsAndHashCode(callSuper = true)
@Data
public final class Settings extends MdxBase<Settings> {
  private List<Challenge> settings;
  private List<Challenge> challenges;

  public Settings() {
  }
}
