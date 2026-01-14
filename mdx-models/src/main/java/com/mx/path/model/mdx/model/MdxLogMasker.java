package com.mx.path.model.mdx.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mx.path.core.common.lang.Strings;
import com.mx.path.core.common.security.LogValueRegex;

public class MdxLogMasker {

  private static final HashSet<String> MDX_PAYLOAD_REGEX = new HashSet<>();
  private static final HashSet<String> HEADER_KEY_SET = new HashSet<>();
  private static final List<Pattern> PAYLOAD_PATTERN_SET = new ArrayList<>();
  private static final String MASK = "**MASKED**";

  public static String maskHeaderValue(String header, String value) {
    if (HEADER_KEY_SET.contains(header.toLowerCase(Locale.ENGLISH))) {
      return MASK;
    }

    return value;
  }

  public static String maskPayload(String payload) {
    return applyPatternsToPayload(payload);
  }

  private static void registerHeaderKeys() {
    HEADER_KEY_SET.add("mdx-session-key");
    HEADER_KEY_SET.add("mx-auth-token");
    HEADER_KEY_SET.add("mx-refresh-token");
    HEADER_KEY_SET.add("mx-session-key");
    HEADER_KEY_SET.add("x-csrf-token");
    HEADER_KEY_SET.add("x-request-token");
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
    buildP2PTransferPayloadPatterns();
    buildPaymentPayloadPatterns();
    buildPayoutPayloadPatterns();
    buildProfilePayloadPatterns();
    buildRemoteDepositPayloadPatterns();
    buildTransferPayloadPatterns();

    for (String regex : MDX_PAYLOAD_REGEX) {
      Pattern mask = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
      PAYLOAD_PATTERN_SET.add(mask);
    }
  }

  private static void buildAccountPayloadPatterns() {
    // Account - https://developer.mx.com/mdx/v5/#mdx-data-models-account-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("nickname"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("routing_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("routing_transit_number"));

    // Account - MDX v5
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("account_number"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("id"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("name"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("nickname"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("routing_number"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("routing_transit_number"));

    // AccountDetails - https://developer.mx.com/drafts/mdx/accounts/#accounts-account-details-additional
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonArray("extended_fields"));

    // AccountNumbers - https://developer.mx.com/drafts/mdx/accounts/#accounts-retrieve-full-account-and-routing-numbers
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("routing_number"));

    // AccountOwnerDetails - https://developer.mx.com/mdx/v5/index.html#mdx-data-models-account-owners
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("address"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("city"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("email"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("owner_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("phone"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("state"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("zip_code"));

    // DeliveryMethod (Alert) - https://developer.mx.com/drafts/mdx/accounts/#accounts-alerts-delivery-method
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("description"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("target"));

    // Transaction - https://developer.mx.com/mdx/v5/#mdx-data-models-transaction-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("check_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("description"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));

    // Transaction - MDX v5
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("account_id"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("check_number"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("description"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("id"));
    MDX_PAYLOAD_REGEX.add(xmlElementRegex("memo"));
  }

  private static void buildAchTransferPayloadPatterns() {
    // AccountListOptions|AchAccountListOptions - https://developer.mx.com/drafts/mdx/ach_transfer/#accounts-ach-accounts-list-ach-accounts
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("ach_account_id"));

    // AchAccount - https://developer.mx.com/drafts/mdx/ach_transfer/#accounts-ach-accounts-vs-held-accounts
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("bank_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("nickname"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("routing_number"));

    // AchScheduledTransfer - https://developer.mx.com/drafts/mdx/ach_transfer/#ach-scheduled-transfers
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_ach_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("to_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("to_ach_account_id"));

    // AchTransfer - https://developer.mx.com/drafts/mdx/ach_transfer/#ach-transfers
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_ach_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("to_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("to_ach_account_id"));
  }

  private static void buildAuthorizationPayloadPatterns() {
    // Authorization - https://developer.mx.com/drafts/mdx/authorization/#authorizations
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonArray("cookies"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("device_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonArray("headers"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("token"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonArray("tokens"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("url"));
  }

  private static void buildChallengesPayloadPatterns() {
    // Question - https://developer.mx.com/drafts/mdx/challenge/#draft-documentation-question-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("answer"));
  }

  private static void buildCheckPayloadPatterns() {
    // CheckImage - https://developer.mx.com/drafts/mdx/accounts/index.html#check-images-check-image-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("back_image"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("check_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("front_image"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("transaction_id"));
  }

  private static void buildCreditReportPayloadPatterns() {
    // CreditReportSettings - https://developer.mx.com/drafts/mdx/credit_report/index.html#credit-report-settings-credit-report-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("first_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("last_name"));
  }

  private static void buildCrossAccountTransferPayloadPatterns() {
    // CrossAccountRecurringTransfer - https://developer.mx.com/drafts/mdx/cross_account_transfer/index.html#recurring-cross-account-transfers-delete-a-destination-data-flow
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("destination_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));

    // CrossAccountTransfer - https://developer.mx.com/drafts/mdx/cross_account_transfer/index.html#cross-account-transfers-cross-account-transfer-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("destination_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));

    // DestinationAccount - https://developer.mx.com/drafts/mdx/cross_account_transfer/index.html#destinations
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_holder"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("id"));

    // FeeListOptions - https://developer.mx.com/drafts/mdx/cross_account_transfer/index.html#fees-list-cross-account-transfer-fees
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("destination_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_account_id"));
  }

  private static void buildDevicePayloadPatterns() {
    // VerificationMethod - https://developer.mx.com/drafts/mdx/device/index.html#verification-methods
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("email_address"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("phone_number"));
  }

  private static void buildDisputePayloadPatterns() {
    // Dispute - https://developer.mx.com/drafts/mdx/accounts/index.html#disputes-dispute
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("card_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("case_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("contact_phone"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("member_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("source_image"));
  }

  private static void buildDocumentPayloadPatterns() {
    // DeliveryPreferences - https://developer.mx.com/drafts/mdx/documents/index.html#documents-update-delivery-preferences
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));

    // Document - https://developer.mx.com/drafts/mdx/documents/index.html#documents-document-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("file_data"));

    // DocumentSearch - https://developer.mx.com/drafts/mdx/documents/index.html#documents-list-documents
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
  }

  private static void buildIdentificationPayloadPatterns() {
    // Authentication - https://developer.mx.com/drafts/mdx/id/#authentications-authenticate
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("access_token"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("client_device_token"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("device_make"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("device_model"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("device_operating_system"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("device_operating_system_version"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonNumber("device_latitude"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonNumber("device_longitude"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("device_iovation_token"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("login"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("password"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("refresh_token"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("token"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("userkey"));

    // MfaChallenge - https://developer.mx.com/drafts/mdx/id/#authentications-multi-factor-authentication-version-20240213-mfa-challenge-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("answer"));

    // MfaChallengeQuestion - https://developer.mx.com/drafts/mdx/id/#authentications-multi-factor-authentication-version-20240213-question-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("answer"));
  }

  private static void buildManagedCardsPayloadPatterns() {
    // ManagedCard - https://developer.mx.com/drafts/mdx/managed_cards/index.html#managed-cards
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("expiration_on_card"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("image_url"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("name_on_card"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("new_pin"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("pin"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("unmasked_cvv"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("unmasked_number_on_card"));

    // TravelSchedule - https://developer.mx.com/drafts/mdx/managed_cards/index.html#destinations-list-global-destinations
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonArray("card_ids"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("email_address"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("primary_phone_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("secondary_phone_number"));
  }

  private static void buildOriginationPayloadPatterns() {
    // Origination - https://developer.mx.com/drafts/mdx/origination/index.html#mdx-origination-origination-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("login_token"));
  }

  private static void buildP2PTransferPayloadPatterns() {
    // P2P Transfer - https://developer.mx.com/drafts/mdx/p2p_transfer/index.html#p2p-transfers
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("recipient_verification_answer"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("recipient_verification_question"));
  }

  private static void buildPaymentPayloadPatterns() {
    // Payee - https://developer.mx.com/drafts/mdx/payment/#payees-payee-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_number"));

    // Payment - https://developer.mx.com/drafts/mdx/payment/#payments
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("routing_transit_number"));

    // RecurringPayment - https://developer.mx.com/drafts/mdx/payment/#recurring-payments-recurring-payment-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
  }

  private static void buildPayoutPayloadPatterns() {
    // ChallengeAnswer - https://developer.mx.com/drafts/mdx/payout/#dealing-with-challenges-answer-a-challenge
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("answer"));

    // Payout - https://developer.mx.com/drafts/mdx/payout/#payouts-payout-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("challenge_answer"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("sender_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("token"));

    // PayoutContactMethod - https://developer.mx.com/drafts/mdx/payout/#payout-contact-methods-payout-contact-method-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("email_address"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("phone_number"));

    // PayoutMethod - https://developer.mx.com/drafts/mdx/payout/#payout-methods-payout-method-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("routing_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("send_to"));

    // PayoutRequest - https://developer.mx.com/drafts/mdx/payout/#payout-requests-payout-request-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));

    // PayoutSettings
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("email_address"));

    // Question
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("answer"));

    // Recipient - https://developer.mx.com/drafts/mdx/payout/#recipients-recipient-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("first_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("last_name"));

    // RecurringPayout - https://developer.mx.com/drafts/mdx/payout/#recurring-payouts-recurring-payout-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("challenge_answer"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("token"));
  }

  private static void buildProfilePayloadPatterns() {
    // Address - https://developer.mx.com/drafts/mdx/profile/#addresses-address-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("address_line_one"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("address_line_two"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("city"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("country"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("postal_code"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("state"));

    // Email - https://developer.mx.com/drafts/mdx/profile/#email-addresses-email-address-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("email_address"));

    // NewPassword - https://developer.mx.com/drafts/mdx/profile/#update-password-new-update-password-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("current_password"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("new_password"));

    // NewUserName - https://developer.mx.com/drafts/mdx/profile/#update-username-update-username-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("new_username"));

    // Phone - https://developer.mx.com/drafts/mdx/profile/#phone-numbers-phone-number-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("phone_number"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("work_extension"));

    // Profile - https://developer.mx.com/drafts/mdx/profile/#profile-profile-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("birth_date_on"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("first_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("gender"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("last_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("middle_name"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("ssn"));
  }

  private static void buildRemoteDepositPayloadPatterns() {
    // RemoteDeposit - https://developer.mx.com/drafts/mdx/remote_deposit/#remote-deposits-remote-deposit-fields
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("back_of_check_image"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("front_of_check_image"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
  }

  private static void buildTransferPayloadPatterns() {
    // AccountListOptions - https://developer.mx.com/drafts/mdx/transfer/#transfer-accounts-list-accounts
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("account_id"));

    // FeeListOptions - https://developer.mx.com/drafts/mdx/transfer/#fees-list-transfer-fees
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("to_account_id"));

    // RecurringTransfer - https://developer.mx.com/drafts/mdx/transfer/#recurring-transfers-data-flows
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("to_account_id"));

    // Transfer - https://developer.mx.com/drafts/mdx/transfer/#transfers-data-flows
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("from_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("memo"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("repayment_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("to_account_id"));

    // TransferAmountOptionListOptions - https://developer.mx.com/drafts/mdx/transfer/#transfer-amount-options-list-all-transfer-amount-options
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("destination_account_id"));
    MDX_PAYLOAD_REGEX.add(LogValueRegex.jsonString("source_account_id"));
  }

  private static String xmlElementRegex(String fieldName) {
    return String.format("\\<[\\w:]*%1$s\\>([\\s\\S]+?)\\<\\/[\\w:]*%1$s\\>", fieldName);
  }

  private static String applyPatternsToPayload(String payload) {
    for (Pattern p : PAYLOAD_PATTERN_SET) {
      Matcher m = p.matcher(payload);
      int start = 0;

      while (m.find(start)) {
        String patternMatch = m.group();

        // Apply masking to all matching groups
        for (int i = 1; i <= m.groupCount(); i++) {
          if (!Strings.isBlank(m.group(i))) {
            patternMatch = patternMatch.replace(m.group(i), MASK);
          }
        }
        payload = payload.replace(m.group(), patternMatch);
        start = m.start() + 1;
      }
    }

    return payload;
  }

  static {
    registerHeaderKeys();
    registerPayloadPatterns();
  }
}
