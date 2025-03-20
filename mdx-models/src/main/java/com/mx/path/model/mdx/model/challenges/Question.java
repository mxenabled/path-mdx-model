package com.mx.path.model.mdx.model.challenges;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.mx.path.model.mdx.model.MdxBase;
import com.mx.path.model.mdx.model.MdxNested;

@MdxNested
@Data
@EqualsAndHashCode(callSuper = true)
public final class Question extends MdxBase<Question> {
  private String id;
  private String prompt;
  private String promptType;
  private String info;
  private List<Option> options;
  private String answer;
  private String errorMessage;
  private Boolean isBottomAnchored;
  private Boolean isRequired;
  private String regexValidation;
  private String regexValidationErrorMessage;
  private String parentQuestionId;
  private Integer width;
  private Button buttonData;
  private Image imageData;
  private Text textData;
  private Camera cameraData;
  private JsonData jsonData;
  private FederatedLoginData federatedLoginData;
  private HtmlData htmlData;
  private InfoData infoData;
}
