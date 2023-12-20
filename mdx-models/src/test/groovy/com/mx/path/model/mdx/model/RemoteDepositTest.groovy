package com.mx.path.model.mdx.model

import java.time.LocalDate

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mx.path.core.common.serialization.LocalDateTypeAdapter
import com.mx.path.model.mdx.model.remote_deposit.RemoteDeposit

import spock.lang.Specification

class RemoteDepositTest extends Specification {
  def gsonSerializer = new GsonBuilder()
  .registerTypeAdapter(
  LocalDate,
  LocalDateTypeAdapter.builder().build()
  ).create()

  def "fromJson with SerializedNames"() {
    given:
    def remoteDepositJson = "{\n" +
        "      \"id\": \"67214\",\n" +
        "      \"account_id\": \"2C82B008107C6312CD322624249AD9B1\",\n" +
        "      \"status\": \"POSTED\",\n" +
        "      \"amount\": 0.01,\n" +
        "      \"memo\": \"\",\n" +
        "      \"confirmation_id\": \"67214\",\n" +
        "      \"user_id\": \"U-89BD784AA17A7C43CCB7FDBAD9DD185D\",\n" +
        "      \"posted_on\": null,\n" +
        "      \"created_on\": null,\n" +
        "      \"updated_on\": null,\n" +
        "      \"posted_at\": 1701216000,\n" +
        "      \"created_at\": 1701216000,\n" +
        "      \"updated_at\": 1701216000\n" +
        "    }"

    Gson gson = new Gson()

    when:
    def remoteDeposit = gsonSerializer.fromJson(remoteDepositJson, RemoteDeposit)

    then:
    remoteDeposit.id == "67214"
    remoteDeposit.accountId == "2C82B008107C6312CD322624249AD9B1"
    remoteDeposit.status == "POSTED"
    remoteDeposit.amount == 0.01
    remoteDeposit.memo == ""
    remoteDeposit.confirmationId == "67214"
    remoteDeposit.postedOn == null
    remoteDeposit.createdOn == null
    remoteDeposit.updatedOn == null
    remoteDeposit.postedAt == 1701216000
    remoteDeposit.createdAt == 1701216000
    remoteDeposit.updatedAt == 1701216000
  }
}
