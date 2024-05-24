package com.mx.path.model.mdx.model.profile;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.gson.annotations.SerializedName;
import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.challenges.Challenge;

@EqualsAndHashCode(callSuper = true)
@Data
public final class SecurityQuestions extends MdxBase<SecurityQuestions> {
  private List<Challenge> challenges;

  @SerializedName("question_list")
  private Challenge questionList;

  public List<Challenge> getChallenges() {
    return challenges;
  }

  public void setChallenges(List<Challenge> challenges) {
    this.challenges = challenges;
  }

  public Challenge getQuestionList() {
    return questionList;
  }

  public void setQuestionList(Challenge questionList) {
    this.questionList = questionList;
  }
}
