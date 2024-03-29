package com.mx.path.connect.messaging.remote.models;

import lombok.Data;

import com.mx.path.model.mdx.model.MdxBase;

@Data
public class RemoteJointOwner extends MdxBase<RemoteJointOwner> {
  private String association;
  private String fullName;
  private String subAccountId;
}
