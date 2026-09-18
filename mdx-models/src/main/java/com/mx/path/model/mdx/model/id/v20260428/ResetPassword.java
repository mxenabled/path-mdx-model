package com.mx.path.model.mdx.model.id.v20260428;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@EqualsAndHashCode(callSuper = true)
@Data
public final class ResetPassword extends MdxBase<ResetPassword> {
  private String username;
  private List<Challenge> challenges;

  public ResetPassword() {
  }
}
