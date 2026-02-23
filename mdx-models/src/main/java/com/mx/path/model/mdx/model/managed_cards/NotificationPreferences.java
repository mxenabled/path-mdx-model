package com.mx.path.model.mdx.model.managed_cards;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;

@Data
@EqualsAndHashCode(callSuper = true)
public final class NotificationPreferences extends MdxBase<NotificationPreferences> {

  private Boolean allowPushNotification;
}
