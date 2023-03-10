package com.mx.path.model.mdx.model.origination;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.common.models.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@Data
@EqualsAndHashCode(callSuper = true)
public class Origination extends MdxBase<Origination> {

  private String id;
  private List<Challenge> challenges;
  private Integer currentStep;
  private Boolean displaySteps = true;
  private String loginToken;
  private String stepTitle;
  private Integer totalSteps;

}
