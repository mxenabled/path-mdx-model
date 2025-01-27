package com.mx.path.model.mdx.model;

import java.util.HashSet;

import lombok.Setter;

import com.mx.path.core.common.security.LogValueMasker;

// Improvement Ideas:
// - Try a different log masker instance separate from upstream
public class MdxLogMasker {
  private static final LogValueMasker LOG_MASKER;
  private static final HashSet<String> PAYLOAD_PATTERNS;

  /**
   * -- SETTER --
   *  Disable log masking for local debugging
   */
  @Setter
  private static boolean disabled = false;

  public static String maskHeaderValue(String header, String value) {
    if (disabled) {
      return value;
    }

    return LOG_MASKER.maskHeaderValue(header, value);
  }

  public static String maskPayload(String payload) {
    if (disabled) {
      return payload;
    }

    return LOG_MASKER.maskPayload(payload);
  }

  public static void registerMaskerPatterns() {
    if (disabled) {
      return;
    }

    registerHeaderKeys();
    registerPayloadPatterns();
  }

  private static void registerHeaderKeys() {
    LogValueMasker.registerHeaderKey("mx-refresh-token");
  }

  private static void registerPayloadPatterns() {
    buildAccountPayloadPatterns();
    buildAchTransferPayloadPatterns();
    buildAuthorizationPayloadPatterns();
    buildChallengesPayloadPatterns();
    buildCheckPayloadPatterns();
    buildCreditReportPayloadPatterns();
    buildCrossAccountTransferPayloadPatterns();
    buildDevicePayloadPatterns();
    buildDisputePayloadPatterns();
    buildDocumentPayloadPatterns();
    buildIdentificationPayloadPatterns();
    buildManagedCardsPayloadPatterns();
    buildOriginationPayloadPatterns();
    buildPaymentPayloadPatterns();
    buildPayoutPayloadPatterns();
    buildProfilePayloadPatterns();
    buildRemoteDepositPayloadPatterns();
    buildTransferPayloadPatterns();

    for (String pattern : PAYLOAD_PATTERNS) {
      LogValueMasker.registerPayloadPattern(pattern);
    }
  }

  private static void buildAccountPayloadPatterns() {
    // Account - https://developer.mx.com/mdx/v5/#mdx-data-models-account-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("id"));
    PAYLOAD_PATTERNS.add(jsonStringField("name"));
    PAYLOAD_PATTERNS.add(jsonStringField("nickname"));
    PAYLOAD_PATTERNS.add(jsonStringField("routing_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("routing_transit_number"));

    // Account - MDX v5
    PAYLOAD_PATTERNS.add(xmlAttribute("account_number"));
    PAYLOAD_PATTERNS.add(xmlAttribute("id"));
    PAYLOAD_PATTERNS.add(xmlAttribute("name"));
    PAYLOAD_PATTERNS.add(xmlAttribute("nickname"));
    PAYLOAD_PATTERNS.add(xmlAttribute("routing_number"));
    PAYLOAD_PATTERNS.add(xmlAttribute("routing_transit_number"));

    // AccountNumbers - https://developer.mx.com/drafts/mdx/accounts/#accounts-retrieve-full-account-and-routing-numbers
    PAYLOAD_PATTERNS.add(jsonStringField("account_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("routing_number"));

    // AccountOwnerDetails - https://developer.mx.com/mdx/v5/index.html#mdx-data-models-account-owners
    PAYLOAD_PATTERNS.add(jsonStringField("address"));
    PAYLOAD_PATTERNS.add(jsonStringField("city"));
    PAYLOAD_PATTERNS.add(jsonStringField("email"));
    PAYLOAD_PATTERNS.add(jsonStringField("owner_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("phone"));
    PAYLOAD_PATTERNS.add(jsonStringField("state"));
    PAYLOAD_PATTERNS.add(jsonStringField("zip_code"));

    // Transaction - https://developer.mx.com/mdx/v5/#mdx-data-models-transaction-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("check_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("description"));
    PAYLOAD_PATTERNS.add(jsonStringField("id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));

    // Transaction - MDX v5
    PAYLOAD_PATTERNS.add(xmlAttribute("account_id"));
    PAYLOAD_PATTERNS.add(xmlAttribute("check_number"));
    PAYLOAD_PATTERNS.add(xmlAttribute("description"));
    PAYLOAD_PATTERNS.add(xmlAttribute("id"));
    PAYLOAD_PATTERNS.add(xmlAttribute("memo"));
  }

  private static void buildAchTransferPayloadPatterns() {
    // AccountListOptions|AchAccountListOptions - https://developer.mx.com/drafts/mdx/ach_transfer/#accounts-ach-accounts-list-ach-accounts
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("ach_account_id"));

    // AchAccount - https://developer.mx.com/drafts/mdx/ach_transfer/#accounts-ach-accounts-vs-held-accounts
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("account_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("bank_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("id"));
    PAYLOAD_PATTERNS.add(jsonStringField("name"));
    PAYLOAD_PATTERNS.add(jsonStringField("nickname"));
    PAYLOAD_PATTERNS.add(jsonStringField("routing_number"));

    // AchScheduledTransfer - https://developer.mx.com/drafts/mdx/ach_transfer/#ach-scheduled-transfers
    PAYLOAD_PATTERNS.add(jsonStringField("from_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("from_ach_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
    PAYLOAD_PATTERNS.add(jsonStringField("to_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("to_ach_account_id"));

    // AchTransfer - https://developer.mx.com/drafts/mdx/ach_transfer/#ach-transfers
    PAYLOAD_PATTERNS.add(jsonStringField("from_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("from_ach_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
    PAYLOAD_PATTERNS.add(jsonStringField("to_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("to_ach_account_id"));
  }

  private static void buildAuthorizationPayloadPatterns() {
    // Authorization - https://developer.mx.com/drafts/mdx/authorization/#authorizations
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonArray("cookies"));
    PAYLOAD_PATTERNS.add(jsonStringField("device_id"));
    PAYLOAD_PATTERNS.add(jsonArray("headers"));
    PAYLOAD_PATTERNS.add(jsonStringField("token"));
    PAYLOAD_PATTERNS.add(jsonArray("tokens"));
  }

  private static void buildChallengesPayloadPatterns() {
    // Question - https://developer.mx.com/drafts/mdx/challenge/#draft-documentation-question-fields
    PAYLOAD_PATTERNS.add(jsonStringField("answer"));
  }

  private static void buildCheckPayloadPatterns() {
    // CheckImage - https://developer.mx.com/drafts/mdx/accounts/index.html#check-images-check-image-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("back_image"));
    PAYLOAD_PATTERNS.add(jsonStringField("check_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("front_image"));
    PAYLOAD_PATTERNS.add(jsonStringField("transaction_id"));
  }

  private static void buildCreditReportPayloadPatterns() {
    // CreditReportSettings - https://developer.mx.com/drafts/mdx/credit_report/index.html#credit-report-settings-credit-report-fields
    PAYLOAD_PATTERNS.add(jsonStringField("first_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("last_name"));
  }

  private static void buildCrossAccountTransferPayloadPatterns() {
    // FeeListOptions - https://developer.mx.com/drafts/mdx/cross_account_transfer/index.html#fees-list-cross-account-transfer-fees
    PAYLOAD_PATTERNS.add(jsonStringField("destination_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("from_account_id"));

    // CrossAccountRecurringTransfer - https://developer.mx.com/drafts/mdx/cross_account_transfer/index.html#recurring-cross-account-transfers-delete-a-destination-data-flow
    PAYLOAD_PATTERNS.add(jsonStringField("destination_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("from_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));

    // CrossAccountTransfer - https://developer.mx.com/drafts/mdx/cross_account_transfer/index.html#cross-account-transfers-cross-account-transfer-fields
    PAYLOAD_PATTERNS.add(jsonStringField("destination_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("from_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));

    // DestinationAccount - https://developer.mx.com/drafts/mdx/cross_account_transfer/index.html#destinations
    PAYLOAD_PATTERNS.add(jsonStringField("account_holder"));
    PAYLOAD_PATTERNS.add(jsonStringField("account_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("id"));
  }

  private static void buildDevicePayloadPatterns() {
    // VerificationMethod - https://developer.mx.com/drafts/mdx/device/index.html#verification-methods
    PAYLOAD_PATTERNS.add(jsonStringField("email_address"));
    PAYLOAD_PATTERNS.add(jsonStringField("phone_number"));
  }

  private static void buildDisputePayloadPatterns() {
    // Dispute - https://developer.mx.com/drafts/mdx/accounts/index.html#disputes-dispute
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("card_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("case_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("contact_phone"));
    PAYLOAD_PATTERNS.add(jsonStringField("member_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("source_image"));
  }

  private static void buildDocumentPayloadPatterns() {
    // DeliveryPreferences - https://developer.mx.com/drafts/mdx/documents/index.html#documents-update-delivery-preferences
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));

    // Document - https://developer.mx.com/drafts/mdx/documents/index.html#documents-document-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("file_data"));

    // DocumentSearch - https://developer.mx.com/drafts/mdx/documents/index.html#documents-list-documents
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
  }

  private static void buildIdentificationPayloadPatterns() {
    // Authentication - https://developer.mx.com/drafts/mdx/id/#authentications-authenticate
    PAYLOAD_PATTERNS.add(jsonStringField("access_token"));
    PAYLOAD_PATTERNS.add(jsonStringField("client_device_token"));
    PAYLOAD_PATTERNS.add(jsonStringField("device_make"));
    PAYLOAD_PATTERNS.add(jsonStringField("device_model"));
    PAYLOAD_PATTERNS.add(jsonStringField("device_operating_system"));
    PAYLOAD_PATTERNS.add(jsonStringField("device_operating_system_version"));
    PAYLOAD_PATTERNS.add(jsonNumberField("device_latitude"));
    PAYLOAD_PATTERNS.add(jsonNumberField("device_longitude"));
    PAYLOAD_PATTERNS.add(jsonStringField("device_iovation_token"));
    PAYLOAD_PATTERNS.add(jsonStringField("login"));
    PAYLOAD_PATTERNS.add(jsonStringField("password"));
    PAYLOAD_PATTERNS.add(jsonStringField("refresh_token"));
    PAYLOAD_PATTERNS.add(jsonStringField("token"));
    PAYLOAD_PATTERNS.add(jsonStringField("userkey"));

    // MfaChallenge - https://developer.mx.com/drafts/mdx/id/#authentications-multi-factor-authentication-version-20240213-mfa-challenge-fields
    PAYLOAD_PATTERNS.add(jsonStringField("answer"));

    // MfaChallengeQuestion - https://developer.mx.com/drafts/mdx/id/#authentications-multi-factor-authentication-version-20240213-question-fields
    PAYLOAD_PATTERNS.add(jsonStringField("answer"));
  }

  private static void buildManagedCardsPayloadPatterns() {
    // ManagedCard - https://developer.mx.com/drafts/mdx/managed_cards/index.html#managed-cards
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("expiration_on_card"));
    PAYLOAD_PATTERNS.add(jsonStringField("image_url"));
    PAYLOAD_PATTERNS.add(jsonStringField("name_on_card"));
    PAYLOAD_PATTERNS.add(jsonStringField("new_pin"));
    PAYLOAD_PATTERNS.add(jsonStringField("pin"));
    PAYLOAD_PATTERNS.add(jsonStringField("unmasked_cvv"));
    PAYLOAD_PATTERNS.add(jsonStringField("unmasked_number_on_card"));

    // TravelSchedule - https://developer.mx.com/drafts/mdx/managed_cards/index.html#destinations-list-global-destinations
    PAYLOAD_PATTERNS.add(jsonArray("card_ids"));
    PAYLOAD_PATTERNS.add(jsonStringField("email_address"));
    PAYLOAD_PATTERNS.add(jsonStringField("primary_phone_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("secondary_phone_number"));
  }

  private static void buildOriginationPayloadPatterns() {
    // Origination - https://developer.mx.com/drafts/mdx/origination/index.html#mdx-origination-origination-fields
    PAYLOAD_PATTERNS.add(jsonStringField("login_token"));
  }

  private static void buildPaymentPayloadPatterns() {
    // Payee - https://developer.mx.com/drafts/mdx/payment/#payees-payee-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_number"));

    // Payment - https://developer.mx.com/drafts/mdx/payment/#payments
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
    PAYLOAD_PATTERNS.add(jsonStringField("routing_transit_number"));

    // RecurringPayment - https://developer.mx.com/drafts/mdx/payment/#recurring-payments-recurring-payment-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
  }

  private static void buildPayoutPayloadPatterns() {
    // ChallengeAnswer - https://developer.mx.com/drafts/mdx/payout/#dealing-with-challenges-answer-a-challenge
    PAYLOAD_PATTERNS.add(jsonStringField("answer"));

    // Payout - https://developer.mx.com/drafts/mdx/payout/#payouts-payout-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("challenge_answer"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
    PAYLOAD_PATTERNS.add(jsonStringField("sender_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("token"));

    // PayoutContactMethod - https://developer.mx.com/drafts/mdx/payout/#payout-contact-methods-payout-contact-method-fields
    PAYLOAD_PATTERNS.add(jsonStringField("email_address"));
    PAYLOAD_PATTERNS.add(jsonStringField("phone_number"));

    // PayoutMethod - https://developer.mx.com/drafts/mdx/payout/#payout-methods-payout-method-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("routing_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("send_to"));

    // PayoutRequest - https://developer.mx.com/drafts/mdx/payout/#payout-requests-payout-request-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));

    // PayoutSettings
    PAYLOAD_PATTERNS.add(jsonStringField("email_address"));

    // Question
    PAYLOAD_PATTERNS.add(jsonStringField("answer"));

    // Recipient - https://developer.mx.com/drafts/mdx/payout/#recipients-recipient-fields
    PAYLOAD_PATTERNS.add(jsonStringField("first_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("last_name"));

    // RecurringPayout - https://developer.mx.com/drafts/mdx/payout/#recurring-payouts-recurring-payout-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("challenge_answer"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
    PAYLOAD_PATTERNS.add(jsonStringField("token"));
  }

  private static void buildProfilePayloadPatterns() {
    // Address - https://developer.mx.com/drafts/mdx/profile/#addresses-address-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("address_line_one"));
    PAYLOAD_PATTERNS.add(jsonStringField("address_line_two"));
    PAYLOAD_PATTERNS.add(jsonStringField("city"));
    PAYLOAD_PATTERNS.add(jsonStringField("country"));
    PAYLOAD_PATTERNS.add(jsonStringField("postal_code"));
    PAYLOAD_PATTERNS.add(jsonStringField("state"));

    // Email - https://developer.mx.com/drafts/mdx/profile/#email-addresses-email-address-fields
    PAYLOAD_PATTERNS.add(jsonStringField("email_address"));

    // NewPassword - https://developer.mx.com/drafts/mdx/profile/#update-password-new-update-password-fields
    PAYLOAD_PATTERNS.add(jsonStringField("current_password"));
    PAYLOAD_PATTERNS.add(jsonStringField("new_password"));

    // NewUserName - https://developer.mx.com/drafts/mdx/profile/#update-username-update-username-fields
    PAYLOAD_PATTERNS.add(jsonStringField("new_username"));

    // Phone - https://developer.mx.com/drafts/mdx/profile/#phone-numbers-phone-number-fields
    PAYLOAD_PATTERNS.add(jsonStringField("phone_number"));
    PAYLOAD_PATTERNS.add(jsonStringField("work_extension"));

    // Profile - https://developer.mx.com/drafts/mdx/profile/#profile-profile-fields
    PAYLOAD_PATTERNS.add(jsonStringField("birth_date_on"));
    PAYLOAD_PATTERNS.add(jsonStringField("first_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("gender"));
    PAYLOAD_PATTERNS.add(jsonStringField("last_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("middle_name"));
    PAYLOAD_PATTERNS.add(jsonStringField("ssn"));
  }

  private static void buildRemoteDepositPayloadPatterns() {
    // RemoteDeposit - https://developer.mx.com/drafts/mdx/remote_deposit/#remote-deposits-remote-deposit-fields
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("back_of_check_image"));
    PAYLOAD_PATTERNS.add(jsonStringField("front_of_check_image"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
  }

  private static void buildTransferPayloadPatterns() {
    // AccountListOptions - https://developer.mx.com/drafts/mdx/transfer/#transfer-accounts-list-accounts
    PAYLOAD_PATTERNS.add(jsonStringField("account_id"));

    // FeeListOptions - https://developer.mx.com/drafts/mdx/transfer/#fees-list-transfer-fees
    PAYLOAD_PATTERNS.add(jsonStringField("from_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("to_account_id"));

    // TransferAmountOptionListOptions - https://developer.mx.com/drafts/mdx/transfer/#transfer-amount-options-list-all-transfer-amount-options
    PAYLOAD_PATTERNS.add(jsonStringField("destination_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("source_account_id"));

    // RecurringTransfer - https://developer.mx.com/drafts/mdx/transfer/#recurring-transfers-data-flows
    PAYLOAD_PATTERNS.add(jsonStringField("from_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
    PAYLOAD_PATTERNS.add(jsonStringField("to_account_id"));

    // Transfer - https://developer.mx.com/drafts/mdx/transfer/#transfers-data-flows
    PAYLOAD_PATTERNS.add(jsonStringField("from_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("memo"));
    PAYLOAD_PATTERNS.add(jsonStringField("repayment_account_id"));
    PAYLOAD_PATTERNS.add(jsonStringField("to_account_id"));
  }

  private static String jsonStringField(String name) {
    return String.format("\"%s\"\\s*:\\s*\"(.+?)\"", name);
  }

  private static String jsonNumberField(String name) {
    return String.format("\"%s\"\\s*:\\s*([-+]?\\d+\\.?\\d*E?\\+?\\d*)", name);
  }

  private static String jsonArray(String name) {
    return String.format("\"%s\"\\s*:\\s*\\[([\\s\\S]+?)\\]", name);
  }

  private static String xmlAttribute(String name) {
    return String.format("\\<[\\w:]*%1$s\\>([\\s\\S]+?)\\<\\/[\\w:]*%1$s\\>", name);
  }

  static {
    LOG_MASKER = new LogValueMasker();
    PAYLOAD_PATTERNS = new HashSet<>();
  }
}
