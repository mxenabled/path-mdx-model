package com.mx.path.model.mdx.model.profile;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.google.gson.annotations.SerializedName;
import com.mx.path.core.common.model.Internal;
import com.mx.path.model.mdx.model.MdxBase;

@Data
@EqualsAndHashCode(callSuper = true)
public final class Profile extends MdxBase<Profile> {
  @SerializedName("birth_date_on")
  private LocalDate birthDate;
  private String firstName;
  private Gender gender;
  private String lastName;
  private String middleName;
  private String ssn;

  // --------------------------------------------------------
  // Internal Fields
  //  ** These fields will not render in web responses.
  //  ** They are only for internal communication.
  // --------------------------------------------------------

  @Internal
  private boolean billPayEligible;
  @Internal
  private boolean billPayEnrolled;
  @Internal
  private boolean remoteDepositEligible;
  @Internal
  private boolean remoteDepositEnrolled;

  // enum definition

  public enum Gender {
    @SerializedName("MALE")
    MALE("Male"), @SerializedName("FEMALE")
    FEMALE("Female"), @SerializedName("UNDECLARED")
    UNDECLARED("Gender Undeclared");

    private final String description;

    Gender(String description) {
      this.description = description;
    }

    public String getDescription() {
      return description;
    }

  }
}
