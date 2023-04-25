package com.mx.path.model.mdx.model.id;

import java.util.List;

import com.mx.path.model.mdx.model.MdxBase;

public class MfaChallengeQuestion extends MdxBase<MfaChallengeQuestion> {

  private String answer;
  private String id;
  private List<MfaChallengeOption> options;
  private String prompt;
  private String title;
  private String type;

  public final String getAnswer() {
    return answer;
  }

  public final void setAnswer(String answer) {
    this.answer = answer;
  }

  public final String getId() {
    return id;
  }

  public final void setId(String id) {
    this.id = id;
  }

  public final List<MfaChallengeOption> getOptions() {
    return options;
  }

  public final void setOptions(List<MfaChallengeOption> options) {
    this.options = options;
  }

  public final String getPrompt() {
    return prompt;
  }

  public final void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public final String getTitle() {
    return title;
  }

  public final void setTitle(String title) {
    this.title = title;
  }

  public final String getType() {
    return type;
  }

  public final void setType(String type) {
    this.type = type;
  }
}
