package com.mx.path.model.mdx.model.account.alerts;

import com.mx.path.model.mdx.model.MdxBase;

public class DeliveryMethod extends MdxBase<DeliveryMethod> {
  private String channel;
  private String description;
  private boolean isEnabled;
  private String target;

  public final String getChannel() {
    return channel;
  }

  public final void setChannel(String channel) {
    this.channel = channel;
  }

  public final String getDescription() {
    return description;
  }

  public final void setDescription(String description) {
    this.description = description;
  }

  public final boolean isEnabled() {
    return isEnabled;
  }

  public final void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }

  public final String getTarget() {
    return target;
  }

  public final void setTarget(String target) {
    this.target = target;
  }
}
