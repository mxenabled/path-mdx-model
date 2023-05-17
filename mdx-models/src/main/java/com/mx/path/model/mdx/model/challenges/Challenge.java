package com.mx.path.model.mdx.model.challenges;

import java.util.List;

import lombok.Data;

import com.mx.path.core.common.configuration.ConfigurationField;
import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class Challenge extends MdxBase<Challenge> {
  @ConfigurationField
  private String id;

  @ConfigurationField
  private String prompt;

  @ConfigurationField
  private String title;

  @ConfigurationField(elementType = Question.class)
  private List<Question> questions;

  @ConfigurationField
  private String[] modes;

  @ConfigurationField
  private String format;

  @ConfigurationField(elementType = Action.class)
  private List<Action> actions;
}
