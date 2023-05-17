package com.mx.path.model.mdx.model.challenges;

import java.util.List;

import lombok.Data;

import com.google.gson.annotations.SerializedName;
import com.mx.path.core.common.configuration.ConfigurationField;
import com.mx.path.model.mdx.model.MdxBase;

@Data
public final class Question extends MdxBase<Question> {
  @ConfigurationField
  private String id;

  @ConfigurationField
  private String prompt;

  @ConfigurationField
  @SerializedName("prompt_type")
  private String promptType;

  @ConfigurationField
  private String info;

  @ConfigurationField(elementType = Option.class)
  private List<Option> options;

  @ConfigurationField
  private String answer;

  @ConfigurationField
  @SerializedName("error_message")
  private String errorMessage;

  @ConfigurationField
  @SerializedName("is_required")
  private Boolean isRequired;

  @ConfigurationField
  @SerializedName("regex_validation")
  private String regexValidation;

  @ConfigurationField
  @SerializedName("regex_validation_error_message")
  private String regexValidationErrorMessage;

  @ConfigurationField
  @SerializedName("parent_question_id")
  private String parentQuestionId;

  @ConfigurationField
  @SerializedName("width")
  private Integer width;

  @ConfigurationField
  @SerializedName("button_data")
  private Button buttonData;

  @ConfigurationField
  @SerializedName("image_data")
  private Image imageData;

  @ConfigurationField
  @SerializedName("text_data")
  private Text textData;

  @ConfigurationField
  @SerializedName("camera_data")
  private Camera cameraData;

  @ConfigurationField
  @SerializedName("json_data")
  private JsonData jsonData;
}
