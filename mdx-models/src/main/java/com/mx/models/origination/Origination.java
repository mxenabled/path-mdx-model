package com.mx.models.origination;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.models.MdxBase;
import com.mx.models.challenges.Challenge;

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
