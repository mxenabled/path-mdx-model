package com.mx.path.model.mdx.model.authorization;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatData extends MdxBase<ChatData> {
  private Long authorizedAt;
  private String authUserId;
  private String email;
  private String firstName;
  private String lastName;
  private String memberId;
  private String username;
}
