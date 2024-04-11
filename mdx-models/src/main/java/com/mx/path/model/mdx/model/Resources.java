package com.mx.path.model.mdx.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.account.AccountDetails;
import com.mx.path.model.mdx.model.account.AccountNumbers;
import com.mx.path.model.mdx.model.account.AccountOwner;
import com.mx.path.model.mdx.model.account.AccountOwnerDetails;
import com.mx.path.model.mdx.model.account.AccountTransactions;
import com.mx.path.model.mdx.model.account.OnDemandAccountNumbers;
import com.mx.path.model.mdx.model.account.OnDemandAccounts;
import com.mx.path.model.mdx.model.account.Overdraft;
import com.mx.path.model.mdx.model.account.StopPayment;
import com.mx.path.model.mdx.model.account.StopPaymentReason;
import com.mx.path.model.mdx.model.account.Transaction;
import com.mx.path.model.mdx.model.account.TransactionsPage;
import com.mx.path.model.mdx.model.account.alerts.AccountAlert;
import com.mx.path.model.mdx.model.account.alerts.DeliveryMethod;
import com.mx.path.model.mdx.model.ach_transfer.AchAccount;
import com.mx.path.model.mdx.model.ach_transfer.AchScheduledTransfer;
import com.mx.path.model.mdx.model.ach_transfer.AchTransfer;
import com.mx.path.model.mdx.model.authorization.Authorization;
import com.mx.path.model.mdx.model.check.CheckImage;
import com.mx.path.model.mdx.model.credit_report.CreditReport;
import com.mx.path.model.mdx.model.credit_report.CreditReportScoreFactor;
import com.mx.path.model.mdx.model.credit_report.CreditReportSettings;
import com.mx.path.model.mdx.model.cross_account_transfer.CrossAccountRecurringTransfer;
import com.mx.path.model.mdx.model.cross_account_transfer.CrossAccountTransfer;
import com.mx.path.model.mdx.model.cross_account_transfer.DestinationAccount;
import com.mx.path.model.mdx.model.dispute.Dispute;
import com.mx.path.model.mdx.model.dispute.DisputedTransaction;
import com.mx.path.model.mdx.model.documents.DeliveryPreferences;
import com.mx.path.model.mdx.model.documents.Document;
import com.mx.path.model.mdx.model.id.Authentication;
import com.mx.path.model.mdx.model.id.ForgotUsername;
import com.mx.path.model.mdx.model.id.MfaChallenge;
import com.mx.path.model.mdx.model.id.ResetPassword;
import com.mx.path.model.mdx.model.id.UnlockUser;
import com.mx.path.model.mdx.model.location.Location;
import com.mx.path.model.mdx.model.managed_cards.Destination;
import com.mx.path.model.mdx.model.managed_cards.ManagedCard;
import com.mx.path.model.mdx.model.managed_cards.TravelSchedule;
import com.mx.path.model.mdx.model.ondemand.MdxListWrapper;
import com.mx.path.model.mdx.model.ondemand.MdxOnDemandDeserializer;
import com.mx.path.model.mdx.model.ondemand.MdxOnDemandMdxListSerializer;
import com.mx.path.model.mdx.model.ondemand.MdxOnDemandSerializer;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountNumbersXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountOwnerDetailsXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountOwnerXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountTransactionsMixIn;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.MixinDefinition;
import com.mx.path.model.mdx.model.ondemand.mixins.OnDemandAccountNumbersXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.OnDemandAccountsXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.SessionXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.TransactionMixIn;
import com.mx.path.model.mdx.model.ondemand.mixins.TransactionsPageMixin;
import com.mx.path.model.mdx.model.origination.Origination;
import com.mx.path.model.mdx.model.payment.Bill;
import com.mx.path.model.mdx.model.payment.Enrollment;
import com.mx.path.model.mdx.model.payment.Merchant;
import com.mx.path.model.mdx.model.payment.MerchantCategory;
import com.mx.path.model.mdx.model.payment.Payee;
import com.mx.path.model.mdx.model.payment.Payment;
import com.mx.path.model.mdx.model.payment.RecurringPayment;
import com.mx.path.model.mdx.model.payment.Settings;
import com.mx.path.model.mdx.model.payout.Challenge;
import com.mx.path.model.mdx.model.payout.ChallengeAnswer;
import com.mx.path.model.mdx.model.payout.Option;
import com.mx.path.model.mdx.model.payout.Payout;
import com.mx.path.model.mdx.model.payout.PayoutContactMethod;
import com.mx.path.model.mdx.model.payout.PayoutMethod;
import com.mx.path.model.mdx.model.payout.PayoutRequest;
import com.mx.path.model.mdx.model.payout.PayoutSettings;
import com.mx.path.model.mdx.model.payout.Question;
import com.mx.path.model.mdx.model.payout.Recipient;
import com.mx.path.model.mdx.model.payout.RecurringPayout;
import com.mx.path.model.mdx.model.profile.Address;
import com.mx.path.model.mdx.model.profile.ChallengeQuestions;
import com.mx.path.model.mdx.model.profile.Email;
import com.mx.path.model.mdx.model.profile.NewPassword;
import com.mx.path.model.mdx.model.profile.NewUserName;
import com.mx.path.model.mdx.model.profile.Password;
import com.mx.path.model.mdx.model.profile.Phone;
import com.mx.path.model.mdx.model.profile.Profile;
import com.mx.path.model.mdx.model.profile.UserName;
import com.mx.path.model.mdx.model.remote_deposit.RemoteDeposit;
import com.mx.path.model.mdx.model.transfer.Fee;
import com.mx.path.model.mdx.model.transfer.RecurringTransfer;
import com.mx.path.model.mdx.model.transfer.Repayment;
import com.mx.path.model.mdx.model.transfer.Transfer;
import com.mx.path.model.mdx.model.transfer.TransferAmountOption;

/**
 * Registration for all MDX resources
 * <p>
 * When adding a new MDX model, register the serializer here to enable MDX style
 * JSON serialization/deserialization.
 */
public class Resources {

  @SuppressWarnings("MethodLength")
  public static void registerResources(GsonBuilder builder) {
    // LocalDate
    builder.registerTypeAdapter(LocalDate.class, new MdxLocalDateSerializer());
    builder.setDateFormat("YYYY-MM-dd");
    // Account
    builder.registerTypeAdapter(Account.class, new ModelWrappableSerializer("account"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Account>>() {
    }.getType(), new ModelWrappableSerializer("accounts"));
    builder.registerTypeAdapter(AccountNumbers.class, new ModelWrappableSerializer("account"));
    builder.registerTypeAdapter(AccountDetails.class, new ModelWrappableSerializer("account_details"));
    builder.registerTypeAdapter(Overdraft.class, new ModelWrappableSerializer("overdraft"));
    builder.registerTypeAdapter(StopPayment.class, new ModelWrappableSerializer("stop_payment"));
    builder.registerTypeAdapter(new TypeToken<MdxList<StopPayment>>() {
    }.getType(), new ModelWrappableSerializer("stop_payments"));
    builder.registerTypeAdapter(StopPaymentReason.class, new ModelWrappableSerializer("stop_payment_reason"));
    builder.registerTypeAdapter(new TypeToken<MdxList<StopPaymentReason>>() {
    }.getType(), new ModelWrappableSerializer("stop_payment_reasons"));
    // Origination
    builder.registerTypeAdapter(Origination.class, new ModelWrappableSerializer("origination"));
    // Origination challenge
    builder.registerTypeAdapter(com.mx.path.model.mdx.model.challenges.Challenge.class, new ModelWrappableSerializer("challenge"));
    // Authentication
    builder.registerTypeAdapter(Authentication.class, new ModelWrappableSerializer("authentication"));
    builder.registerTypeAdapter(com.mx.path.model.mdx.model.id.v20240213.Authentication.class, new ModelWrappableSerializer("authentication"));
    // Transaction
    builder.registerTypeAdapter(Transaction.class, new ModelWrappableSerializer("transaction"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Transaction>>() {
    }.getType(), new ModelWrappableSerializer("transactions"));
    // AccountType
    builder.registerTypeAdapter(AccountType.class, new ModelWrappableSerializer("account_type"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AccountType>>() {
    }.getType(), new ModelWrappableSerializer("account_types"));
    // Transfer
    builder.registerTypeAdapter(Transfer.class, new ModelWrappableSerializer("transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Transfer>>() {
    }.getType(), new ModelWrappableSerializer("transfers"));
    // RecurringTransfer
    builder.registerTypeAdapter(RecurringTransfer.class, new ModelWrappableSerializer("recurring_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<RecurringTransfer>>() {
    }.getType(), new ModelWrappableSerializer("recurring_transfers"));
    // TransferAmountOptions
    builder.registerTypeAdapter(TransferAmountOption.class, new ModelWrappableSerializer("amount_option"));
    builder.registerTypeAdapter(new TypeToken<MdxList<TransferAmountOption>>() {
    }.getType(), new ModelWrappableSerializer("amount_options"));
    // Frequency
    builder.registerTypeAdapter(Frequency.class, new ModelWrappableSerializer("frequency"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Frequency>>() {
    }.getType(), new ModelWrappableSerializer("frequencies"));
    // RecurringPayout
    builder.registerTypeAdapter(RecurringPayout.class, new ModelWrappableSerializer("recurring_payout"));
    builder.registerTypeAdapter(new TypeToken<MdxList<RecurringPayout>>() {
    }.getType(), new ModelWrappableSerializer("recurring_payouts"));
    // PayoutSettings
    builder.registerTypeAdapter(PayoutSettings.class, new ModelWrappableSerializer("payout_setting"));
    builder.registerTypeAdapter(new TypeToken<MdxList<PayoutSettings>>() {
    }.getType(), new ModelWrappableSerializer("payout_settings"));
    // PayoutContactMethod
    builder.registerTypeAdapter(PayoutContactMethod.class, new ModelWrappableSerializer("payout_contact_method"));
    builder.registerTypeAdapter(new TypeToken<MdxList<PayoutContactMethod>>() {
    }.getType(), new ModelWrappableSerializer("payout_contact_methods"));
    // Recipient
    builder.registerTypeAdapter(Recipient.class, new ModelWrappableSerializer("recipient"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Recipient>>() {
    }.getType(), new ModelWrappableSerializer("recipients"));
    // PayoutMethod
    builder.registerTypeAdapter(PayoutMethod.class, new ModelWrappableSerializer("payout_method"));
    builder.registerTypeAdapter(new TypeToken<MdxList<PayoutMethod>>() {
    }.getType(), new ModelWrappableSerializer("payout_methods"));
    // Payout
    builder.registerTypeAdapter(Payout.class, new ModelWrappableSerializer("payout"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Payout>>() {
    }.getType(), new ModelWrappableSerializer("payouts"));
    // PayoutRequest
    builder.registerTypeAdapter(PayoutRequest.class, new ModelWrappableSerializer("payout_request"));
    builder.registerTypeAdapter(new TypeToken<MdxList<PayoutRequest>>() {
    }.getType(), new ModelWrappableSerializer("payout_requests"));
    // Challenge
    builder.registerTypeAdapter(Challenge.class, new ModelWrappableSerializer("challenge"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Challenge>>() {
    }.getType(), new ModelWrappableSerializer("challenges"));
    // Question
    builder.registerTypeAdapter(Question.class, new ModelWrappableSerializer("question"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Question>>() {
    }.getType(), new ModelWrappableSerializer("questions"));
    // Option
    builder.registerTypeAdapter(Option.class, new ModelWrappableSerializer("option"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Option>>() {
    }.getType(), new ModelWrappableSerializer("options"));
    // ChallengeAnswer
    builder.registerTypeAdapter(ChallengeAnswer.class, new ModelWrappableSerializer("challenge_answer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<ChallengeAnswer>>() {
    }.getType(), new ModelWrappableSerializer("challenge_answers"));
    // Authorization
    builder.registerTypeAdapter(Authorization.class, new ModelWrappableSerializer("authorization"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Authorization>>() {
    }.getType(), new ModelWrappableSerializer("authorizations"));
    // CheckImages
    builder.registerTypeAdapter(CheckImage.class, new ModelWrappableSerializer("check_image"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CheckImage>>() {
    }.getType(), new ModelWrappableSerializer("check_images"));
    // CreditReportSetting
    builder.registerTypeAdapter(CreditReportSettings.class, new ModelWrappableSerializer("settings"));
    // CreditReport
    builder.registerTypeAdapter(CreditReport.class, new ModelWrappableSerializer("credit_report"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CreditReport>>() {
    }.getType(), new ModelWrappableSerializer("credit_reports"));
    // CreditReportScoreFactor
    builder.registerTypeAdapter(CreditReportScoreFactor.class, new ModelWrappableSerializer("score_factor"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CreditReportScoreFactor>>() {
    }.getType(), new ModelWrappableSerializer("score_factors"));
    // CrossAccountTransfer
    builder.registerTypeAdapter(CrossAccountTransfer.class, new ModelWrappableSerializer("cross_account_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CrossAccountTransfer>>() {
    }.getType(), new ModelWrappableSerializer("cross_account_transfers"));
    //CrossAccountRecurringTransfer
    builder.registerTypeAdapter(CrossAccountRecurringTransfer.class, new ModelWrappableSerializer("recurring_cross_account_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CrossAccountRecurringTransfer>>() {
    }.getType(), new ModelWrappableSerializer("recurring_cross_account_transfers"));
    //Destination Accounts
    builder.registerTypeAdapter(DestinationAccount.class, new ModelWrappableSerializer("destination"));
    builder.registerTypeAdapter(new TypeToken<MdxList<DestinationAccount>>() {
    }.getType(), new ModelWrappableSerializer("destinations"));
    // Documents
    builder.registerTypeAdapter(Document.class, new ModelWrappableSerializer("document"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Document>>() {
    }.getType(), new ModelWrappableSerializer("documents"));
    // DeliveryPreferences
    builder.registerTypeAdapter(DeliveryPreferences.class, new ModelWrappableSerializer("delivery_preferences"));
    // Location
    builder.registerTypeAdapter(Location.class, new ModelWrappableSerializer("location"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Location>>() {
    }.getType(), new ModelWrappableSerializer("locations"));
    // Destination
    builder.registerTypeAdapter(Destination.class, new ModelWrappableSerializer("destination"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Destination>>() {
    }.getType(), new ModelWrappableSerializer("destinations"));
    // TravelSchedule
    builder.registerTypeAdapter(TravelSchedule.class, new ModelWrappableSerializer("travel_schedule"));
    builder.registerTypeAdapter(new TypeToken<MdxList<TravelSchedule>>() {
    }.getType(), new ModelWrappableSerializer("travel_schedules"));
    // ManagedCard
    builder.registerTypeAdapter(ManagedCard.class, new ModelWrappableSerializer("managed_card"));
    builder.registerTypeAdapter(new TypeToken<MdxList<ManagedCard>>() {
    }.getType(), new ModelWrappableSerializer("managed_cards"));
    // RemoteDeposit
    builder.registerTypeAdapter(RemoteDeposit.class, new ModelWrappableSerializer("remote_deposit"));
    builder.registerTypeAdapter(new TypeToken<MdxList<RemoteDeposit>>() {
    }.getType(), new ModelWrappableSerializer("remote_deposits"));
    // MfaChallenge
    builder.registerTypeAdapter(MfaChallenge.class, new ModelWrappableSerializer("mfa_challenge"));
    builder.registerTypeAdapter(new TypeToken<MdxList<MfaChallenge>>() {
    }.getType(), new ModelWrappableSerializer("mfa_challenges"));
    // ResetPassword
    builder.registerTypeAdapter(ResetPassword.class, new ModelWrappableSerializer("reset_password"));
    // ForgotUsername
    builder.registerTypeAdapter(ForgotUsername.class, new ModelWrappableSerializer("forgot_username"));
    // UnlockUser
    builder.registerTypeAdapter(UnlockUser.class, new ModelWrappableSerializer("unlock_user"));
    // Register Profile related models
    registerProfileModelClasses(builder);
    // Register ACH Transfer related models
    registerAchTransfersModels(builder);
    // Register Dispute related models
    registerDisputesModels(builder);
    // Register Payment related models
    registerPaymentsModels(builder);
    // Register multistage transfer models
    registerMultistageTransferModels(builder);
    // Register account alert models
    registerAccountAlertModels(builder);
  }

  public static void registerOnDemandResources(SimpleModule module) {
    module.addDeserializer(Authentication.class, new MdxOnDemandDeserializer<>(Authentication.class, "/session"));
    module.addSerializer(Authentication.class, new MdxOnDemandSerializer<>(new MixinDefinition(Authentication.class, SessionXmlMixin.class)));

    module.addSerializer(Account.class, new MdxOnDemandSerializer<>(new MixinDefinition(Account.class, AccountXmlMixin.class)));
    module.addSerializer(MdxListWrapper.class, new MdxOnDemandMdxListSerializer(new MixinDefinition(Account.class, AccountXmlMixin.class)));

    module.addSerializer(AccountTransactions.class, new MdxOnDemandSerializer<>(
        new MixinDefinition(AccountTransactions.class, AccountTransactionsMixIn.class),
        new MixinDefinition(TransactionsPage.class, TransactionsPageMixin.class),
        new MixinDefinition(Transaction.class, TransactionMixIn.class)));

    module.addSerializer(AccountOwner.class, new MdxOnDemandSerializer<>(
        new MixinDefinition(AccountOwner.class, AccountOwnerXmlMixin.class),
        new MixinDefinition(AccountOwnerDetails.class, AccountOwnerDetailsXmlMixin.class)));

    module.addSerializer(OnDemandAccounts.class, new MdxOnDemandSerializer<>(
        new MixinDefinition(OnDemandAccounts.class, OnDemandAccountsXmlMixin.class),
        new MixinDefinition(Account.class, AccountXmlMixin.class)));
    module.addSerializer(OnDemandAccountNumbers.class, new MdxOnDemandSerializer<>(
        new MixinDefinition(OnDemandAccountNumbers.class, OnDemandAccountNumbersXmlMixin.class),
        new MixinDefinition(AccountNumbers.class, AccountNumbersXmlMixin.class)));
  }

  private static void registerProfileModelClasses(GsonBuilder builder) {
    // Profiles
    builder.registerTypeAdapter(Profile.class, new ModelWrappableSerializer("profile"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Profile>>() {
    }.getType(), new ModelWrappableSerializer("profiles"));
    // Addresses
    builder.registerTypeAdapter(Address.class, new ModelWrappableSerializer("address"));
    // Challenge Questions
    builder.registerTypeAdapter(ChallengeQuestions.class, new ModelWrappableSerializer("challenge_questions"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Address>>() {
    }.getType(), new ModelWrappableSerializer("addresses"));
    // Phones
    builder.registerTypeAdapter(Phone.class, new ModelWrappableSerializer("phone"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Phone>>() {
    }.getType(), new ModelWrappableSerializer("phones"));
    // Emails
    builder.registerTypeAdapter(Email.class, new ModelWrappableSerializer("email"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Email>>() {
    }.getType(), new ModelWrappableSerializer("emails"));
    // Update Password
    builder.registerTypeAdapter(Password.class, new ModelWrappableSerializer("password"));
    builder.registerTypeAdapter(new TypeToken<Password>() {
    }.getType(), new ModelWrappableSerializer("update_password"));
    //UserName
    builder.registerTypeAdapter(UserName.class, new ModelWrappableSerializer("username"));
    builder.registerTypeAdapter(new TypeToken<UserName>() {
    }.getType(), new ModelWrappableSerializer("update_username"));
    // New Update Password
    builder.registerTypeAdapter(NewPassword.class, new ModelWrappableSerializer("password"));
    builder.registerTypeAdapter(new TypeToken<NewPassword>() {
    }.getType(), new ModelWrappableSerializer("password"));
    // New UserName
    builder.registerTypeAdapter(NewUserName.class, new ModelWrappableSerializer("username"));
    builder.registerTypeAdapter(new TypeToken<NewUserName>() {
    }.getType(), new ModelWrappableSerializer("username"));
  }

  private static void registerAchTransfersModels(GsonBuilder builder) {
    builder.registerTypeAdapter(AchTransfer.class, new ModelWrappableSerializer("ach_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AchTransfer>>() {
    }.getType(), new ModelWrappableSerializer("ach_transfers"));

    builder.registerTypeAdapter(AchScheduledTransfer.class, new ModelWrappableSerializer("ach_scheduled_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AchScheduledTransfer>>() {
    }.getType(), new ModelWrappableSerializer("ach_scheduled_transfers"));

    builder.registerTypeAdapter(AchAccount.class, new ModelWrappableSerializer("ach_account"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AchAccount>>() {
    }.getType(), new ModelWrappableSerializer("ach_accounts"));
  }

  private static void registerDisputesModels(GsonBuilder builder) {
    // Disputes
    builder.registerTypeAdapter(Dispute.class, new ModelWrappableSerializer("dispute"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Dispute>>() {
    }.getType(), new ModelWrappableSerializer("disputes"));
    // Disputed Transactions
    builder.registerTypeAdapter(DisputedTransaction.class, new ModelWrappableSerializer("disputed_transaction"));
    builder.registerTypeAdapter(new TypeToken<MdxList<DisputedTransaction>>() {
    }.getType(), new ModelWrappableSerializer("disputed_transactions"));
  }

  private static void registerPaymentsModels(GsonBuilder builder) {
    // Merchant
    builder.registerTypeAdapter(Merchant.class, new ModelWrappableSerializer("merchant"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Merchant>>() {
    }.getType(), new ModelWrappableSerializer("merchants"));
    // MerchantCategory
    builder.registerTypeAdapter(MerchantCategory.class, new ModelWrappableSerializer("merchant_category"));
    builder.registerTypeAdapter(new TypeToken<MdxList<MerchantCategory>>() {
    }.getType(), new ModelWrappableSerializer("merchant_categories"));
    // Payee
    builder.registerTypeAdapter(Payee.class, new ModelWrappableSerializer("payee"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Payee>>() {
    }.getType(), new ModelWrappableSerializer("payees"));
    // Bill
    builder.registerTypeAdapter(Bill.class, new ModelWrappableSerializer("bill"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Bill>>() {
    }.getType(), new ModelWrappableSerializer("bills"));
    // Payment
    builder.registerTypeAdapter(Enrollment.class, new ModelWrappableSerializer("enrollment"));
    builder.registerTypeAdapter(Settings.class, new ModelWrappableSerializer("settings"));
    builder.registerTypeAdapter(Payment.class, new ModelWrappableSerializer("payment"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Payment>>() {
    }.getType(), new ModelWrappableSerializer("payments"));
    // Recurring Payment
    builder.registerTypeAdapter(RecurringPayment.class, new ModelWrappableSerializer("recurring_payment"));
    builder.registerTypeAdapter(new TypeToken<MdxList<RecurringPayment>>() {
    }.getType(), new ModelWrappableSerializer("recurring_payments"));
  }

  private static void registerMultistageTransferModels(GsonBuilder builder) {
    // Fee
    builder.registerTypeAdapter(Fee.class, new ModelWrappableSerializer("fee"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Fee>>() {
    }.getType(), new ModelWrappableSerializer("fees"));
    // Repayment
    builder.registerTypeAdapter(Repayment.class, new ModelWrappableSerializer("repayment"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Repayment>>() {
    }.getType(), new ModelWrappableSerializer("repayments"));
  }

  private static void registerAccountAlertModels(GsonBuilder builder) {
    // AccountAlert
    builder.registerTypeAdapter(AccountAlert.class, new ModelWrappableSerializer("alert"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AccountAlert>>() {
    }.getType(), new ModelWrappableSerializer("alerts"));
    // DeliveryMethod
    builder.registerTypeAdapter(new TypeToken<MdxList<DeliveryMethod>>() {
    }.getType(), new ModelWrappableSerializer("delivery_methods"));
  }
}
