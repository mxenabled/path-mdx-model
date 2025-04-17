package com.mx.path.model.mdx.model

import spock.lang.Specification
import spock.lang.Unroll

class MdxLogMaskerTest extends Specification {
  @Unroll
  def "maskHeaderValue() masks #header to #expectedResult"() {
    when:
    String result = MdxLogMasker.maskHeaderValue(header, "something_sensitive")

    then:
    result == expectedResult

    where:
    header             || expectedResult
    "mx-refresh-token" || "**MASKED**"
  }

  @Unroll
  def "maskPayload() masks Account JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                      || expectedResult
    "\"account_number\":\"1234567890\""          || "\"account_number\":\"**MASKED**\""
    "\"id\":\"ACCOUNT-123\""                     || "\"id\":\"**MASKED**\""
    "\"name\":\"Checking Account\""              || "\"name\":\"**MASKED**\""
    "\"nickname\":\"My Checking\""               || "\"nickname\":\"**MASKED**\""
    "\"routing_number\":\"121122676\""           || "\"routing_number\":\"**MASKED**\""
    "\"routing_transit_number\":\"0260-0959-3\"" || "\"routing_transit_number\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Account XML fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                        || expectedResult
    "<account_number>1234567890</account_number>"                  || "<account_number>**MASKED**</account_number>"
    "<id>ACCOUNT-123</id>"                                         || "<id>**MASKED**</id>"
    "<name>Checking Account</name>"                                || "<name>**MASKED**</name>"
    "<nickname>My Checking</nickname>"                             || "<nickname>**MASKED**</nickname>"
    "<routing_number>121122676</routing_number>"                   || "<routing_number>**MASKED**</routing_number>"
    "<routing_transit_number>0260-0959-3</routing_transit_number>" || "<routing_transit_number>**MASKED**</routing_transit_number>"
  }

  @Unroll
  def "maskPayload() masks AccountDetails JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                                                                                          || expectedResult
    "\"extended_fields\":[{\n    \"type\": \"STRING\",\n    \"name\": \"secret field\",\n    \"string_value\": \"secret value\"\n}]" || "\"extended_fields\":[**MASKED**]"
  }

  @Unroll
  def "maskPayload() masks AccountNumbers JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                             || expectedResult
    "\"account_number\":\"1234567890\"" || "\"account_number\":\"**MASKED**\""
    "\"routing_number\":\"121122676\""  || "\"routing_number\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks AccountOwnerDetails JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                        || expectedResult
    "\"address\":\"3401 N Thanksgiving Way #500\"" || "\"address\":\"**MASKED**\""
    "\"city\":\"Lehi\""                            || "\"city\":\"**MASKED**\""
    "\"email\":\"john.doe@example.com\""           || "\"email\":\"**MASKED**\""
    "\"owner_name\":\"John Doe\""                  || "\"owner_name\":\"**MASKED**\""
    "\"phone\":\"1-222-333-4444\""                 || "\"phone\":\"**MASKED**\""
    "\"zip_code\":\"84043\""                       || "\"zip_code\":\"**MASKED**\""
    "\"state\":\"UT\""                             || "\"state\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks DeliveryMethod JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                            || expectedResult
    "\"description\":\"3401 N Thanksgiving Way #500\"" || "\"description\":\"**MASKED**\""
    "\"target\":\"Email - *1@example.com\""            || "\"target\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Transaction JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                || expectedResult
    "\"account_id\":\"ACCOUNT-123\""       || "\"account_id\":\"**MASKED**\""
    "\"check_number\":\"1234\""            || "\"check_number\":\"**MASKED**\""
    "\"description\":\"Test transaction\"" || "\"description\":\"**MASKED**\""
    "\"id\":\"TRANSACTION-123\""           || "\"id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""               || "\"memo\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Transaction XML fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                       || expectedResult
    "<account_id>ACCOUNT-123</account_id>"        || "<account_id>**MASKED**</account_id>"
    "<check_number>1234</check_number>"           || "<check_number>**MASKED**</check_number>"
    "<description>Test transaction</description>" || "<description>**MASKED**</description>"
    "<id>TRANSACTION-123</id>"                    || "<id>**MASKED**</id>"
    "<memo>Test memo</memo>"                      || "<memo>**MASKED**</memo>"
  }

  @Unroll
  def "maskPayload() masks AccountListOptions JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                              || expectedResult
    "\"account_id\":\"ACCOUNT-123\""     || "\"account_id\":\"**MASKED**\""
    "\"ach_account_id\":\"ACCOUNT-456\"" || "\"ach_account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks AchAccount JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                               || expectedResult
    "\"account_id\":\"ACCOUNT-123\""      || "\"account_id\":\"**MASKED**\""
    "\"account_number\":\"1234567890\""   || "\"account_number\":\"**MASKED**\""
    "\"bank_name\":\"Test Credit Union\"" || "\"bank_name\":\"**MASKED**\""
    "\"id\":\"ACH-ACCOUNT-123\""          || "\"id\":\"**MASKED**\""
    "\"name\":\"Test Name\""              || "\"name\":\"**MASKED**\""
    "\"nickname\":\"Test Nickname\""      || "\"nickname\":\"**MASKED**\""
    "\"routing_number\":\"121122676\""    || "\"routing_number\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks AchScheduledTransfer JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                     || expectedResult
    "\"from_account_id\":\"ACCOUNT-1\""         || "\"from_account_id\":\"**MASKED**\""
    "\"from_ach_account_id\":\"ACH-ACCOUNT-1\"" || "\"from_ach_account_id\":\"**MASKED**\""
    "\"id\":\"ACH-TRANSFER-1\""                 || "\"id\":\"**MASKED**\""
    "\"memo\":\"Test ACH Transfer\""            || "\"memo\":\"**MASKED**\""
    "\"to_account_id\":\"ACCOUNT-2\""           || "\"to_account_id\":\"**MASKED**\""
    "\"to_ach_account_id\":\"ACH-ACCOUNT-2\""   || "\"to_ach_account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks AchTransfer JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                     || expectedResult
    "\"from_account_id\":\"ACCOUNT-1\""         || "\"from_account_id\":\"**MASKED**\""
    "\"from_ach_account_id\":\"ACH-ACCOUNT-1\"" || "\"from_ach_account_id\":\"**MASKED**\""
    "\"id\":\"ACH-TRANSFER-1\""                 || "\"id\":\"**MASKED**\""
    "\"memo\":\"Test ACH Transfer\""            || "\"memo\":\"**MASKED**\""
    "\"to_account_id\":\"ACCOUNT-2\""           || "\"to_account_id\":\"**MASKED**\""
    "\"to_ach_account_id\":\"ACH-ACCOUNT-2\""   || "\"to_ach_account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Authorization JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                                         || expectedResult
    "\"account_id\":\"ACCOUNT-1\""                                                  || "\"account_id\":\"**MASKED**\""
    "\"cookies\":[{\n    \"cookie1\": \"value1\",\n    \"cookie2\": \"value2\"\n}]" || "\"cookies\":[**MASKED**]"
    "\"device_id\":\"ACH-TRANSFER-1\""                                              || "\"device_id\":\"**MASKED**\""
    "\"headers\":[{\n    \"header1\": \"value1\",\n    \"header2\": \"value2\"\n}]" || "\"headers\":[**MASKED**]"
    "\"token\":\"j8tGkb0STI827r0aMBOvJN9tBU08nFsc8gSivZLIfBw=\""                    || "\"token\":\"**MASKED**\""
    "\"tokens\":[{\n    \"token1\": \"value1\",\n    \"token2\": \"value2\"\n}]"    || "\"tokens\":[**MASKED**]"
    "\"url\":\"http:/test.example.com/sso?token=b1d36291310644fe921b8bdff8d08d61\"" || "\"url\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Question JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                      || expectedResult
    "\"answer\":\"Test answer\"" || "\"answer\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks CheckImage JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                               || expectedResult
    "\"account_id\":\"ACCOUNT-1\""                                        || "\"account_id\":\"**MASKED**\""
    "\"back_image\":\"data:image/png;base64,iVBORw0KGAAAUAAAAFCJggg==\""  || "\"back_image\":\"**MASKED**\""
    "\"check_number\":\"1234\""                                           || "\"check_number\":\"**MASKED**\""
    "\"front_image\":\"data:image/png;base64,iVBORw0KGAAAUAAAAFCJggg==\"" || "\"front_image\":\"**MASKED**\""
    "\"transaction_id\":\"TRANSACTION-1\""                                || "\"transaction_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks CreditReportSettings JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                   || expectedResult
    "\"first_name\":\"John\"" || "\"first_name\":\"**MASKED**\""
    "\"last_name\":\"Doe\""   || "\"last_name\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks CrossAccountRecurringTransfer JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                  || expectedResult
    "\"destination_id\":\"DESTINATION-123\"" || "\"destination_id\":\"**MASKED**\""
    "\"from_account_id\":\"ACCOUNT-123\""    || "\"from_account_id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""                 || "\"memo\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks CrossAccountRecurringTransfer JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                  || expectedResult
    "\"destination_id\":\"DESTINATION-123\"" || "\"destination_id\":\"**MASKED**\""
    "\"from_account_id\":\"ACCOUNT-123\""    || "\"from_account_id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""                 || "\"memo\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks CrossAccountTransfer JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                  || expectedResult
    "\"destination_id\":\"DESTINATION-123\"" || "\"destination_id\":\"**MASKED**\""
    "\"from_account_id\":\"ACCOUNT-123\""    || "\"from_account_id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""                 || "\"memo\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks DestinationAccount JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                      || expectedResult
    "\"account_holder\":\"Test account holder\"" || "\"account_holder\":\"**MASKED**\""
    "\"account_number\":\"1234567890\""          || "\"account_number\":\"**MASKED**\""
    "\"id\":\"ACCOUNT-123\""                     || "\"id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks FeeListOptions JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                  || expectedResult
    "\"destination_id\":\"DESTINATION-123\"" || "\"destination_id\":\"**MASKED**\""
    "\"from_account_id\":\"ACCOUNT-123\""    || "\"from_account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks VerificationMethod JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                      || expectedResult
    "\"email_address\":\"john.doe@example.com\"" || "\"email_address\":\"**MASKED**\""
    "\"phone_number\":\"1-222-333-4444\""        || "\"phone_number\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Dispute JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                                || expectedResult
    "\"account_id\":\"ACCOUNT-123\""                                       || "\"account_id\":\"**MASKED**\""
    "\"card_id\":\"CARD-123\""                                             || "\"card_id\":\"**MASKED**\""
    "\"case_number\":\"182744AAQ093\""                                     || "\"case_number\":\"**MASKED**\""
    "\"contact_phone\":\"1-222-333-4444\""                                 || "\"contact_phone\":\"**MASKED**\""
    "\"member_name\":\"John Doe\""                                         || "\"member_name\":\"**MASKED**\""
    "\"source_image\":\"data:image/png;base64,iVBORw0KGAAAUAAAAFCJggg==\"" || "\"source_image\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks DeliveryPreferences JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                          || expectedResult
    "\"account_id\":\"ACCOUNT-123\"" || "\"account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Document JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                                   || expectedResult
    "\"account_id\":\"ACCOUNT-123\""                                          || "\"account_id\":\"**MASKED**\""
    "\"file_data\":\"data:application/pdf;base64,iVBORw0KGAAAUAAAAFCJggg==\"" || "\"file_data\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks DocumentSearch JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                          || expectedResult
    "\"account_id\":\"ACCOUNT-123\"" || "\"account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Authentication JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                          || expectedResult
    "\"access_token\":\"167f0a358b054953ae7c9527e2d273a9\""          || "\"access_token\":\"**MASKED**\""
    "\"client_device_token\":\"UNIQUE_TOKEN_FOR_THIS_DEVICE\""       || "\"client_device_token\":\"**MASKED**\""
    "\"device_make\":\"Apple\""                                      || "\"device_make\":\"**MASKED**\""
    "\"device_model\":\"iPhone X\""                                  || "\"device_model\":\"**MASKED**\""
    "\"device_operating_system\":\"iOS\""                            || "\"device_operating_system\":\"**MASKED**\""
    "\"device_operating_system_version\":\"11.4.1\""                 || "\"device_operating_system_version\":\"**MASKED**\""
    "\"device_latitude\":40.4296944"                                 || "\"device_latitude\":**MASKED**"
    "\"device_longitude\":-111.8931454"                              || "\"device_longitude\":**MASKED**"
    "\"device_iovation_token\":\"ac1d3a2455a9444ebc942c7842660ed2\"" || "\"device_iovation_token\":\"**MASKED**\""
    "\"login\":\"johndoe\""                                          || "\"login\":\"**MASKED**\""
    "\"password\":\"topsecret\""                                     || "\"password\":\"**MASKED**\""
    "\"refresh_token\":\"2ed7c86985d0404a8728fe4c621711b5\""         || "\"refresh_token\":\"**MASKED**\""
    "\"token\":\"b43cac3cfbcc45d4abecef87064b63ce\""                 || "\"token\":\"**MASKED**\""
    "\"userkey\":\"25ff030acfba4cdf91618dc23b4795e4\""               || "\"userkey\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks MfaChallenge JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                || expectedResult
    "\"answer\":\"12345\"" || "\"answer\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks MfaChallengeQuestion JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                || expectedResult
    "\"answer\":\"12345\"" || "\"answer\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks ManagedCard JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                           || expectedResult
    "\"account_id\":\"ACCOUNT-123\""                                  || "\"account_id\":\"**MASKED**\""
    "\"expiration_on_card\":\"06/97\""                                || "\"expiration_on_card\":\"**MASKED**\""
    "\"image_url\":\"https://logos.co/customers/123/card_image.jpg\"" || "\"image_url\":\"**MASKED**\""
    "\"name_on_card\":\"John Doe\""                                   || "\"name_on_card\":\"**MASKED**\""
    "\"new_pin\":\"12345\""                                           || "\"new_pin\":\"**MASKED**\""
    "\"pin\":\"12345\""                                               || "\"pin\":\"**MASKED**\""
    "\"unmasked_cvv\":\"123\""                                        || "\"unmasked_cvv\":\"**MASKED**\""
    "\"unmasked_number_on_card\":\"1111222233334444\""                || "\"unmasked_number_on_card\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks TravelSchedule JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                         || expectedResult
    "\"card_ids\":[\"CARD_ID_1\", \"CARD_ID_2\"]"   || "\"card_ids\":[**MASKED**]"
    "\"email_address\":\"john.doe@example.com\""    || "\"email_address\":\"**MASKED**\""
    "\"primary_phone_number\":\"1-222-333-4444\""   || "\"primary_phone_number\":\"**MASKED**\""
    "\"secondary_phone_number\":\"1-333-444-5555\"" || "\"secondary_phone_number\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Origination JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                         || expectedResult
    "\"login_token\":\"123456789\"" || "\"login_token\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Payee JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                            || expectedResult
    "\"account_number\":\"123456789\"" || "\"account_number\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Payment JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                      || expectedResult
    "\"account_id\":\"ACCOUNT-123\""             || "\"account_id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""                     || "\"memo\":\"**MASKED**\""
    "\"routing_transit_number\":\"0260-0959-3\"" || "\"routing_transit_number\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks RecurringPayment JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                          || expectedResult
    "\"account_id\":\"ACCOUNT-123\"" || "\"account_id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""         || "\"memo\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks ChallengeAnswer (Payout) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                      || expectedResult
    "\"answer\":\"Test answer\"" || "\"answer\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Payout JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                          || expectedResult
    "\"account_id\":\"ACCOUNT-123\""                 || "\"account_id\":\"**MASKED**\""
    "\"challenge_answer\":\"Test answer\""           || "\"challenge_answer\":\"**MASKED**\""
    "\"memo\":\"Test memo\""                         || "\"memo\":\"**MASKED**\""
    "\"sender_name\":\"John Doe\""                   || "\"sender_name\":\"**MASKED**\""
    "\"token\":\"f48c8aa0e66d4d53979b36c4c46c8abc\"" || "\"token\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks PayoutContactMethod JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                      || expectedResult
    "\"email_address\":\"john.doe@example.com\"" || "\"email_address\":\"**MASKED**\""
    "\"phone_number\":\"1-222-333-4444\""        || "\"phone_number\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks PayoutMethod JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                || expectedResult
    "\"account_number\":\"1234567890\""    || "\"account_number\":\"**MASKED**\""
    "\"routing_number\":\"121122676\""     || "\"routing_number\":\"**MASKED**\""
    "\"send_to\":\"john.doe@example.com\"" || "\"send_to\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks PayoutRequest JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                          || expectedResult
    "\"account_id\":\"ACCOUNT-123\"" || "\"account_id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""         || "\"memo\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks PayoutSettings JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                      || expectedResult
    "\"email_address\":\"john.doe@example.com\"" || "\"email_address\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Question (Payout) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                      || expectedResult
    "\"answer\":\"Test answer\"" || "\"answer\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Recipient (Payout) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                   || expectedResult
    "\"first_name\":\"John\"" || "\"first_name\":\"**MASKED**\""
    "\"last_name\":\"Doe\""   || "\"last_name\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks RecurringPayout JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                          || expectedResult
    "\"account_id\":\"ACCOUNT-123\""                 || "\"account_id\":\"**MASKED**\""
    "\"challenge_answer\":\"Test answer\""           || "\"challenge_answer\":\"**MASKED**\""
    "\"memo\":\"Test memo\""                         || "\"memo\":\"**MASKED**\""
    "\"token\":\"41bb602a0ab44896ad28d7de270e24ce\"" || "\"token\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Address (Profile) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                 || expectedResult
    "\"account_id\":\"ACCOUNT-123\""                        || "\"account_id\":\"**MASKED**\""
    "\"address_line_one\":\"3401 N Thanksgiving Way #500\"" || "\"address_line_one\":\"**MASKED**\""
    "\"address_line_two\":\"Test address line 2\""          || "\"address_line_two\":\"**MASKED**\""
    "\"city\":\"Lehi\""                                     || "\"city\":\"**MASKED**\""
    "\"country\":\"US\""                                    || "\"country\":\"**MASKED**\""
    "\"postal_code\":\"84043\""                             || "\"postal_code\":\"**MASKED**\""
    "\"state\":\"UT\""                                      || "\"state\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Email (Profile) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                      || expectedResult
    "\"email_address\":\"john.doe@example.com\"" || "\"email_address\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks NewPassword (Profile) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                               || expectedResult
    "\"current_password\":\"topsecret1\"" || "\"current_password\":\"**MASKED**\""
    "\"new_password\":\"topsecret2\""     || "\"new_password\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks NewUserName (Profile) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                        || expectedResult
    "\"new_username\":\"johndoe\"" || "\"new_username\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Phone (Profile) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                               || expectedResult
    "\"phone_number\":\"1-222-333-4444\"" || "\"phone_number\":\"**MASKED**\""
    "\"work_extension\":\"1234\""         || "\"work_extension\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Profile JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                            || expectedResult
    "\"birth_date_on\":\"2015-01-01\"" || "\"birth_date_on\":\"**MASKED**\""
    "\"first_name\":\"John\""          || "\"first_name\":\"**MASKED**\""
    "\"gender\":\"MALE\""              || "\"gender\":\"**MASKED**\""
    "\"last_name\":\"Doe\""            || "\"last_name\":\"**MASKED**\""
    "\"middle_name\":\"William\""      || "\"middle_name\":\"**MASKED**\""
    "\"ssn\":\"123456789\""            || "\"ssn\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks RemoteDeposit JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                                                        || expectedResult
    "\"account_id\":\"Account-123\""                                               || "\"account_id\":\"**MASKED**\""
    "\"back_of_check_image\":\"data:image/png;base64,iVBORw0KGAAAUAAAAFCJggg==\""  || "\"back_of_check_image\":\"**MASKED**\""
    "\"front_of_check_image\":\"data:image/png;base64,iVBORw0KGAAAUAAAAFCJggg==\"" || "\"front_of_check_image\":\"**MASKED**\""
    "\"memo\":\"Test memo\""                                                       || "\"memo\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks AccountListOptions (Transfer) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                          || expectedResult
    "\"account_id\":\"Account-123\"" || "\"account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks FeeListOptions (Transfer) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                               || expectedResult
    "\"from_account_id\":\"Account-123\"" || "\"from_account_id\":\"**MASKED**\""
    "\"to_account_id\":\"Account-456\""   || "\"to_account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks RecurringTransfer JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                               || expectedResult
    "\"from_account_id\":\"Account-123\"" || "\"from_account_id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""              || "\"memo\":\"**MASKED**\""
    "\"to_account_id\":\"Account-456\""   || "\"to_account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks Transfer JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                    || expectedResult
    "\"from_account_id\":\"Account-123\""      || "\"from_account_id\":\"**MASKED**\""
    "\"memo\":\"Test memo\""                   || "\"memo\":\"**MASKED**\""
    "\"repayment_account_id\":\"Account-456\"" || "\"repayment_account_id\":\"**MASKED**\""
    "\"to_account_id\":\"Account-789\""        || "\"to_account_id\":\"**MASKED**\""
  }

  @Unroll
  def "maskPayload() masks TransferAmountOptionListOptions (Transfer) JSON fields"() {
    when:
    String result = MdxLogMasker.maskPayload(payload)

    then:
    result == expectedResult

    where:
    payload                                      || expectedResult
    "\"destination_account_id\":\"Account-123\"" || "\"destination_account_id\":\"**MASKED**\""
    "\"source_account_id\":\"Account-456\""      || "\"source_account_id\":\"**MASKED**\""
  }
}
