package com.mx.path.model.mdx.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mx.common.models.MdxList;
import com.mx.path.model.mdx.model.account.Account;
import com.mx.path.model.mdx.model.account.AccountNumber;
import com.mx.path.model.mdx.model.account.AccountNumbers;
import com.mx.path.model.mdx.model.account.AccountOwner;
import com.mx.path.model.mdx.model.account.AccountOwnerDetails;
import com.mx.path.model.mdx.model.account.AccountTransactions;
import com.mx.path.model.mdx.model.account.OnDemandAccounts;
import com.mx.path.model.mdx.model.account.Transaction;
import com.mx.path.model.mdx.model.account.TransactionsPage;
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
import com.mx.path.model.mdx.model.documents.Document;
import com.mx.path.model.mdx.model.id.Authentication;
import com.mx.path.model.mdx.model.id.ForgotUsername;
import com.mx.path.model.mdx.model.id.MfaChallenge;
import com.mx.path.model.mdx.model.id.ResetPassword;
import com.mx.path.model.mdx.model.location.Location;
import com.mx.path.model.mdx.model.managed_cards.Destination;
import com.mx.path.model.mdx.model.managed_cards.ManagedCard;
import com.mx.path.model.mdx.model.managed_cards.TravelSchedule;
import com.mx.path.model.mdx.model.ondemand.MdxListWrapper;
import com.mx.path.model.mdx.model.ondemand.MdxOnDemandDeserializer;
import com.mx.path.model.mdx.model.ondemand.MdxOnDemandMdxListSerializer;
import com.mx.path.model.mdx.model.ondemand.MdxOnDemandSerializer;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountNumberXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountNumbersXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountOwnerDetailsXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountOwnerXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountTransactionsMixIn;
import com.mx.path.model.mdx.model.ondemand.mixins.AccountXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.MixinDefinition;
import com.mx.path.model.mdx.model.ondemand.mixins.OnDemandAccountsXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.SessionXmlMixin;
import com.mx.path.model.mdx.model.ondemand.mixins.TransactionMixIn;
import com.mx.path.model.mdx.model.ondemand.mixins.TransactionsPageMixin;
import com.mx.path.model.mdx.model.origination.Origination;
import com.mx.path.model.mdx.model.payment.Bill;
import com.mx.path.model.mdx.model.payment.Merchant;
import com.mx.path.model.mdx.model.payment.MerchantCategory;
import com.mx.path.model.mdx.model.payment.Payee;
import com.mx.path.model.mdx.model.payment.Payment;
import com.mx.path.model.mdx.model.payment.RecurringPayment;
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
import com.mx.path.model.mdx.model.profile.Email;
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
    builder.registerTypeAdapter(Account.class, new MdxWrappableSerializer("account"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Account>>() {
    }.getType(), new MdxWrappableSerializer("accounts"));
    // Origination
    builder.registerTypeAdapter(Origination.class, new MdxWrappableSerializer("origination"));
    // Origination challenge
    builder.registerTypeAdapter(com.mx.path.model.mdx.model.challenges.Challenge.class, new MdxWrappableSerializer("challenge"));
    // Authentication
    builder.registerTypeAdapter(Authentication.class, new MdxWrappableSerializer("authentication"));
    // Transaction
    builder.registerTypeAdapter(Transaction.class, new MdxWrappableSerializer("transaction"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Transaction>>() {
    }.getType(), new MdxWrappableSerializer("transactions"));
    // AccountType
    builder.registerTypeAdapter(AccountType.class, new MdxWrappableSerializer("account_type"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AccountType>>() {
    }.getType(), new MdxWrappableSerializer("account_types"));
    // Transfer
    builder.registerTypeAdapter(Transfer.class, new MdxWrappableSerializer("transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Transfer>>() {
    }.getType(), new MdxWrappableSerializer("transfers"));
    // RecurringTransfer
    builder.registerTypeAdapter(RecurringTransfer.class, new MdxWrappableSerializer("recurring_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<RecurringTransfer>>() {
    }.getType(), new MdxWrappableSerializer("recurring_transfers"));
    // TransferAmountOptions
    builder.registerTypeAdapter(TransferAmountOption.class, new MdxWrappableSerializer("amount_option"));
    builder.registerTypeAdapter(new TypeToken<MdxList<TransferAmountOption>>() {
    }.getType(), new MdxWrappableSerializer("amount_options"));
    // Frequency
    builder.registerTypeAdapter(Frequency.class, new MdxWrappableSerializer("frequency"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Frequency>>() {
    }.getType(), new MdxWrappableSerializer("frequencies"));
    // RecurringPayout
    builder.registerTypeAdapter(RecurringPayout.class, new MdxWrappableSerializer("recurring_payout"));
    builder.registerTypeAdapter(new TypeToken<MdxList<RecurringPayout>>() {
    }.getType(), new MdxWrappableSerializer("recurring_payouts"));
    // PayoutSettings
    builder.registerTypeAdapter(PayoutSettings.class, new MdxWrappableSerializer("payout_setting"));
    builder.registerTypeAdapter(new TypeToken<MdxList<PayoutSettings>>() {
    }.getType(), new MdxWrappableSerializer("payout_settings"));
    // PayoutContactMethod
    builder.registerTypeAdapter(PayoutContactMethod.class, new MdxWrappableSerializer("payout_contact_method"));
    builder.registerTypeAdapter(new TypeToken<MdxList<PayoutContactMethod>>() {
    }.getType(), new MdxWrappableSerializer("payout_contact_methods"));
    // Recipient
    builder.registerTypeAdapter(Recipient.class, new MdxWrappableSerializer("recipient"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Recipient>>() {
    }.getType(), new MdxWrappableSerializer("recipients"));
    // PayoutMethod
    builder.registerTypeAdapter(PayoutMethod.class, new MdxWrappableSerializer("payout_method"));
    builder.registerTypeAdapter(new TypeToken<MdxList<PayoutMethod>>() {
    }.getType(), new MdxWrappableSerializer("payout_methods"));
    // Payout
    builder.registerTypeAdapter(Payout.class, new MdxWrappableSerializer("payout"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Payout>>() {
    }.getType(), new MdxWrappableSerializer("payouts"));
    // PayoutRequest
    builder.registerTypeAdapter(PayoutRequest.class, new MdxWrappableSerializer("payout_request"));
    builder.registerTypeAdapter(new TypeToken<MdxList<PayoutRequest>>() {
    }.getType(), new MdxWrappableSerializer("payout_requests"));
    // Challenge
    builder.registerTypeAdapter(Challenge.class, new MdxWrappableSerializer("challenge"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Challenge>>() {
    }.getType(), new MdxWrappableSerializer("challenges"));
    // Question
    builder.registerTypeAdapter(Question.class, new MdxWrappableSerializer("question"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Question>>() {
    }.getType(), new MdxWrappableSerializer("questions"));
    // Option
    builder.registerTypeAdapter(Option.class, new MdxWrappableSerializer("option"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Option>>() {
    }.getType(), new MdxWrappableSerializer("options"));
    // ChallengeAnswer
    builder.registerTypeAdapter(ChallengeAnswer.class, new MdxWrappableSerializer("challenge_answer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<ChallengeAnswer>>() {
    }.getType(), new MdxWrappableSerializer("challenge_answers"));
    // Authorization
    builder.registerTypeAdapter(Authorization.class, new MdxWrappableSerializer("authorization"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Authorization>>() {
    }.getType(), new MdxWrappableSerializer("authorizations"));
    // CheckImages
    builder.registerTypeAdapter(CheckImage.class, new MdxWrappableSerializer("check_image"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CheckImage>>() {
    }.getType(), new MdxWrappableSerializer("check_images"));
    // CreditReportSetting
    builder.registerTypeAdapter(CreditReportSettings.class, new MdxWrappableSerializer("settings"));
    // CreditReport
    builder.registerTypeAdapter(CreditReport.class, new MdxWrappableSerializer("credit_report"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CreditReport>>() {
    }.getType(), new MdxWrappableSerializer("credit_reports"));
    // CreditReportScoreFactor
    builder.registerTypeAdapter(CreditReportScoreFactor.class, new MdxWrappableSerializer("score_factor"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CreditReportScoreFactor>>() {
    }.getType(), new MdxWrappableSerializer("score_factors"));
    // CrossAccountTransfer
    builder.registerTypeAdapter(CrossAccountTransfer.class, new MdxWrappableSerializer("cross_account_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CrossAccountTransfer>>() {
    }.getType(), new MdxWrappableSerializer("cross_account_transfers"));
    //CrossAccountRecurringTransfer
    builder.registerTypeAdapter(CrossAccountRecurringTransfer.class, new MdxWrappableSerializer("recurring_cross_account_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<CrossAccountRecurringTransfer>>() {
    }.getType(), new MdxWrappableSerializer("recurring_cross_account_transfers"));
    //Destination Accounts
    builder.registerTypeAdapter(DestinationAccount.class, new MdxWrappableSerializer("destination"));
    builder.registerTypeAdapter(new TypeToken<MdxList<DestinationAccount>>() {
    }.getType(), new MdxWrappableSerializer("destinations"));
    // Documents
    builder.registerTypeAdapter(Document.class, new MdxWrappableSerializer("document"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Document>>() {
    }.getType(), new MdxWrappableSerializer("documents"));
    // Location
    builder.registerTypeAdapter(Location.class, new MdxWrappableSerializer("location"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Location>>() {
    }.getType(), new MdxWrappableSerializer("locations"));
    // Destination
    builder.registerTypeAdapter(Destination.class, new MdxWrappableSerializer("destination"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Destination>>() {
    }.getType(), new MdxWrappableSerializer("destinations"));
    // TravelSchedule
    builder.registerTypeAdapter(TravelSchedule.class, new MdxWrappableSerializer("travel_schedule"));
    builder.registerTypeAdapter(new TypeToken<MdxList<TravelSchedule>>() {
    }.getType(), new MdxWrappableSerializer("travel_schedules"));
    // ManagedCard
    builder.registerTypeAdapter(ManagedCard.class, new MdxWrappableSerializer("managed_card"));
    builder.registerTypeAdapter(new TypeToken<MdxList<ManagedCard>>() {
    }.getType(), new MdxWrappableSerializer("managed_cards"));
    // RemoteDeposit
    builder.registerTypeAdapter(RemoteDeposit.class, new MdxWrappableSerializer("remote_deposit"));
    builder.registerTypeAdapter(new TypeToken<MdxList<RemoteDeposit>>() {
    }.getType(), new MdxWrappableSerializer("remote_deposits"));
    // MfaChallenge
    builder.registerTypeAdapter(MfaChallenge.class, new MdxWrappableSerializer("mfa_challenge"));
    builder.registerTypeAdapter(new TypeToken<MdxList<MfaChallenge>>() {
    }.getType(), new MdxWrappableSerializer("mfa_challenges"));
    //ResetPassword
    builder.registerTypeAdapter(ResetPassword.class, new MdxWrappableSerializer("reset_password"));
    //ForgotUsername
    builder.registerTypeAdapter(ForgotUsername.class, new MdxWrappableSerializer("forgot_username"));
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

    module.addSerializer(AccountNumbers.class, new MdxOnDemandSerializer<>(
        new MixinDefinition(AccountNumbers.class, AccountNumbersXmlMixin.class),
        new MixinDefinition(AccountNumber.class, AccountNumberXmlMixin.class)));
    module.addSerializer(AccountOwner.class, new MdxOnDemandSerializer<>(
        new MixinDefinition(AccountOwner.class, AccountOwnerXmlMixin.class),
        new MixinDefinition(AccountOwnerDetails.class, AccountOwnerDetailsXmlMixin.class)));

    module.addSerializer(OnDemandAccounts.class, new MdxOnDemandSerializer<>(
        new MixinDefinition(OnDemandAccounts.class, OnDemandAccountsXmlMixin.class),
        new MixinDefinition(Account.class, AccountXmlMixin.class)));
  }

  private static void registerProfileModelClasses(GsonBuilder builder) {
    // Profiles
    builder.registerTypeAdapter(Profile.class, new MdxWrappableSerializer("profile"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Profile>>() {
    }.getType(), new MdxWrappableSerializer("profiles"));
    // Addresses
    builder.registerTypeAdapter(Address.class, new MdxWrappableSerializer("address"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Address>>() {
    }.getType(), new MdxWrappableSerializer("addresses"));
    // Phones
    builder.registerTypeAdapter(Phone.class, new MdxWrappableSerializer("phone"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Phone>>() {
    }.getType(), new MdxWrappableSerializer("phones"));
    // Emails
    builder.registerTypeAdapter(Email.class, new MdxWrappableSerializer("email"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Email>>() {
    }.getType(), new MdxWrappableSerializer("emails"));
    // Update Password
    builder.registerTypeAdapter(Password.class, new MdxWrappableSerializer("password"));
    builder.registerTypeAdapter(new TypeToken<Password>() {
    }.getType(), new MdxWrappableSerializer("update_password"));
    //UserName
    builder.registerTypeAdapter(UserName.class, new MdxWrappableSerializer("username"));
    builder.registerTypeAdapter(new TypeToken<UserName>() {
    }.getType(), new MdxWrappableSerializer("update_username"));
  }

  private static void registerAchTransfersModels(GsonBuilder builder) {
    builder.registerTypeAdapter(AchTransfer.class, new MdxWrappableSerializer("ach_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AchTransfer>>() {
    }.getType(), new MdxWrappableSerializer("ach_transfers"));

    builder.registerTypeAdapter(AchScheduledTransfer.class, new MdxWrappableSerializer("ach_scheduled_transfer"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AchScheduledTransfer>>() {
    }.getType(), new MdxWrappableSerializer("ach_scheduled_transfers"));

    builder.registerTypeAdapter(AchAccount.class, new MdxWrappableSerializer("ach_account"));
    builder.registerTypeAdapter(new TypeToken<MdxList<AchAccount>>() {
    }.getType(), new MdxWrappableSerializer("ach_accounts"));
  }

  private static void registerDisputesModels(GsonBuilder builder) {
    // Disputes
    builder.registerTypeAdapter(Dispute.class, new MdxWrappableSerializer("dispute"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Dispute>>() {
    }.getType(), new MdxWrappableSerializer("disputes"));
    // Disputed Transactions
    builder.registerTypeAdapter(DisputedTransaction.class, new MdxWrappableSerializer("disputed_transaction"));
    builder.registerTypeAdapter(new TypeToken<MdxList<DisputedTransaction>>() {
    }.getType(), new MdxWrappableSerializer("disputed_transactions"));
  }

  private static void registerPaymentsModels(GsonBuilder builder) {
    // Merchant
    builder.registerTypeAdapter(Merchant.class, new MdxWrappableSerializer("merchant"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Merchant>>() {
    }.getType(), new MdxWrappableSerializer("merchants"));
    // MerchantCategory
    builder.registerTypeAdapter(MerchantCategory.class, new MdxWrappableSerializer("merchant_category"));
    builder.registerTypeAdapter(new TypeToken<MdxList<MerchantCategory>>() {
    }.getType(), new MdxWrappableSerializer("merchant_categories"));
    // Payee
    builder.registerTypeAdapter(Payee.class, new MdxWrappableSerializer("payee"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Payee>>() {
    }.getType(), new MdxWrappableSerializer("payees"));
    // Bill
    builder.registerTypeAdapter(Bill.class, new MdxWrappableSerializer("bill"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Bill>>() {
    }.getType(), new MdxWrappableSerializer("bills"));
    // Payment
    builder.registerTypeAdapter(Payment.class, new MdxWrappableSerializer("payment"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Payment>>() {
    }.getType(), new MdxWrappableSerializer("payments"));
    // Recurring Payment
    builder.registerTypeAdapter(RecurringPayment.class, new MdxWrappableSerializer("recurring_payment"));
    builder.registerTypeAdapter(new TypeToken<MdxList<RecurringPayment>>() {
    }.getType(), new MdxWrappableSerializer("recurring_payments"));
  }

  private static void registerMultistageTransferModels(GsonBuilder builder) {
    // Fee
    builder.registerTypeAdapter(Fee.class, new MdxWrappableSerializer("fee"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Fee>>() {
    }.getType(), new MdxWrappableSerializer("fees"));
    // Repayment
    builder.registerTypeAdapter(Repayment.class, new MdxWrappableSerializer("repayment"));
    builder.registerTypeAdapter(new TypeToken<MdxList<Repayment>>() {
    }.getType(), new MdxWrappableSerializer("repayments"));
  }
}
