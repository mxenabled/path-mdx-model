package com.mx.path.model.mdx.model.external_transfer

import java.time.LocalDate

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.Resources

import spock.lang.Specification

class ExternalTransferSerializationTest extends Specification {
  Gson gson

  def setup() {
    def builder = new GsonBuilder()
    Resources.registerResources(builder)
    gson = builder.create()
  }

  def "deserializes an external transfer from the spec's wire format"() {
    given:
    def json = """{"external_transfer": {
      "id": "EXTERNAL-TRANSFER-1",
      "recurring_external_transfer_id": "RECURRING-EXTERNAL-TRANSFER-1",
      "status": "IN_PROCESS",
      "amount": 100.25,
      "from_external_account_id": "EXTERNAL-ACCOUNT-1",
      "to_account_id": "ACCOUNT-123",
      "confirmation_id": "CONF-1",
      "post_on": "2026-11-30",
      "posted_on": "2026-11-30",
      "deliver_on": "2026-12-03",
      "created_at": 1793232000,
      "updated_at": 1793664000
    }}"""

    when:
    def result = gson.fromJson(json, ExternalTransfer)

    then:
    result.id == "EXTERNAL-TRANSFER-1"
    result.recurringExternalTransferId == "RECURRING-EXTERNAL-TRANSFER-1"
    result.status == "IN_PROCESS"
    result.amount == 100.25G
    result.fromExternalAccountId == "EXTERNAL-ACCOUNT-1"
    result.toAccountId == "ACCOUNT-123"
    result.confirmationId == "CONF-1"
    result.postOn == LocalDate.of(2026, 11, 30)
    result.postedOn == LocalDate.of(2026, 11, 30)
    result.deliverOn == LocalDate.of(2026, 12, 3)
    result.createdAt == 1793232000L
    result.updatedAt == 1793664000L
  }

  def "serializes recurring external transfers under the plural wrapper with spec field names"() {
    given:
    def list = new MdxList<RecurringExternalTransfer>().tap {
      add(new RecurringExternalTransfer().tap {
        setId("RECURRING-EXTERNAL-TRANSFER-1")
        setStatus("ACTIVE")
        setFrequencyId("MONTHLY")
        setFromExternalAccountId("EXTERNAL-ACCOUNT-1")
        setToAccountId("ACCOUNT-123")
        setStartOn(LocalDate.of(2026, 10, 30))
        setEndAfterCount(12)
        setLastTransferOn(LocalDate.of(2026, 10, 30))
        setNextTransferOn(LocalDate.of(2026, 11, 30))
      })
    }

    when:
    def type = new TypeToken<MdxList<RecurringExternalTransfer>>() {}.getType()
    def json = JsonParser.parseString(gson.toJson(list.wrapped(), type)).asJsonObject
    def item = json.getAsJsonArray("recurring_external_transfers").get(0).asJsonObject

    then:
    item.get("id").asString == "RECURRING-EXTERNAL-TRANSFER-1"
    item.get("frequency_id").asString == "MONTHLY"
    item.get("from_external_account_id").asString == "EXTERNAL-ACCOUNT-1"
    item.get("start_on").asString == "2026-10-30"
    item.get("end_after_count").asInt == 12
    item.get("last_transfer_on").asString == "2026-10-30"
    item.get("next_transfer_on").asString == "2026-11-30"
  }

  def "serializes an external account under its wrapper"() {
    given:
    def account = new ExternalAccount().tap {
      setId("EXTERNAL-ACCOUNT-1")
      setType("CHECKING")
      setStatus("VERIFIED")
      setAccountNumber("****32")
      setRoutingNumber("*******12")
    }

    when:
    def json = JsonParser.parseString(gson.toJson(account.wrapped())).asJsonObject.getAsJsonObject("external_account")

    then:
    json.get("id").asString == "EXTERNAL-ACCOUNT-1"
    json.get("account_number").asString == "****32"
    json.get("routing_number").asString == "*******12"
  }
}
