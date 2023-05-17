package com.mx.path.model.mdx.model.challenges;

import java.util.List;

import lombok.Data;

import com.google.gson.annotations.SerializedName;
import com.mx.path.core.common.configuration.ConfigurationField;
import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class Button extends MdxBase<Button> {
  @ConfigurationField
  private String type;

  @ConfigurationField(elementType = Action.class)
  private List<Action> actions;

  @ConfigurationField
  @SerializedName("requires_valid_answers")
  private Boolean requiresValidAnswers;

  @ConfigurationField
  @SerializedName("disabled_duration")
  private Integer disabledDurationSeconds;

  @ConfigurationField
  @SerializedName("is_submit_button")
  private Boolean isSubmitButton;
}
