package com.mx.web.mdx.controllers

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.common.accessors.AccessorResponse
import com.mx.common.models.MdxList
import com.mx.models.challenges.Challenge
import com.mx.models.profile.Address
import com.mx.models.profile.Email
import com.mx.models.profile.Password
import com.mx.models.profile.Phone
import com.mx.models.profile.Profile
import com.mx.models.profile.UserName
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.profile.AddressGateway
import com.mx.path.gateway.api.profile.EmailGateway
import com.mx.path.gateway.api.profile.PhoneGateway
import com.mx.path.gateway.api.profile.ProfileGateway

import org.springframework.http.HttpStatus

import spock.lang.Specification

class ProfilesControllerTest extends Specification {
  ProfilesController subject
  Gateway gateway
  ProfileGateway profileGateway
  AddressGateway addressGateway
  EmailGateway emailGateway
  PhoneGateway phoneGateway
  String clientId

  def setup() {
    clientId = "client123"

    addressGateway = spy(AddressGateway.builder().clientId(clientId).build())
    emailGateway = spy(EmailGateway.builder().clientId(clientId).build())
    phoneGateway = spy(PhoneGateway.builder().clientId(clientId).build())
    profileGateway = spy(ProfileGateway.builder().clientId(clientId).emails(emailGateway).phones(phoneGateway).addresses(addressGateway).build())
    gateway = spy(Gateway.builder().clientId(clientId).profiles(profileGateway).build())

    subject = new ProfilesController()
  }

  def cleanup() {
    ProfilesController.clearRepository()
  }

  def "getProfile_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Profile>().withResult(new Profile())
    doReturn(mockResponse).when(profileGateway).get()

    when:
    def response = subject.getProfile()

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "putProfile_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Profile>().withResult(new Profile())
    doReturn(mockResponse).when(profileGateway).update(any())

    when:
    def response = subject.createOrUpdateProfile(new Profile())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }


  // address CRUD

  def "getAddresses_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<MdxList<Address>>().withResult(new MdxList<>().tap {
      add(new Address())
    })
    doReturn(mockResponse).when(addressGateway).list()

    when:
    def response = subject.getAddresses()

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "getAddress_implemented"() {
    given:
    ProfilesController.setGateway(gateway)
    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(addressGateway).get("1")

    when:
    def response = subject.getAddress("1")

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "postAddress_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(addressGateway).create(any())

    when:
    def response = subject.createAddress(new Address())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "putAddress_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Address>().withResult(new Address())
    doReturn(mockResponse).when(addressGateway).update(any(), any())

    when:
    def response = subject.updateAddress("1", new Address())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "deleteAddress_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Void>()
    doReturn(mockResponse).when(addressGateway).delete("1")

    when:
    def response = subject.deleteAddress("1")

    then:
    verify(addressGateway).delete("1") || true
    response.statusCode == HttpStatus.NO_CONTENT
  }


  // phone CRUD

  def "getPhones_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<>().withResult(new MdxList<>().tap {
      add(new Phone())
    })
    doReturn(mockResponse).when(phoneGateway).list()

    when:
    def response = subject.getPhones()

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "getPhone_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Phone>().withResult(new Phone())
    doReturn(mockResponse).when(phoneGateway).get("1")

    when:
    def response = subject.getPhone("1")

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "postPhone_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Phone>().withResult(new Phone())
    doReturn(mockResponse).when(phoneGateway).create(any())

    when:
    def response = subject.createPhone(new Phone())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "putPhone_implemented - 200"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Phone>().withResult(new Phone())
    doReturn(mockResponse).when(phoneGateway).update(any(), any())

    when:
    def response = subject.updatePhone("1", new Phone())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "putPhone_implemented - 202"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Phone>().withResult(new Phone().tap {
      setChallenges(new ArrayList<Challenge>().tap {
        add(new Challenge())
      } as List<com.mx.models.payout.Challenge>)
    })
    doReturn(mockResponse).when(phoneGateway).update(any(), any())

    when:
    def response = subject.updatePhone("1", new Phone())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "deletePhone_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Void>()
    doReturn(mockResponse).when(phoneGateway).delete("1")

    when:
    def response = subject.deletePhone("1")

    then:
    verify(phoneGateway).delete("1") || true
    response.statusCode == HttpStatus.NO_CONTENT
  }


  // email CRUD

  def "getEmails_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<MdxList<Email>>().withResult(new MdxList<>())
    Email address = new Email()
    mockResponse.getResult().add(address)
    doReturn(mockResponse).when(emailGateway).list()

    when:
    def response = subject.getEmails()

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "getEmail_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Email>().withResult(new Email())
    doReturn(mockResponse).when(emailGateway).get("1")

    when:
    def response = subject.getEmail("1")

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "postEmail_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Email>().withResult(new Email())
    doReturn(mockResponse).when(emailGateway).create(any())

    when:
    def response = subject.createEmail(new Email())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "putEmail_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Email>().withResult(new Email())
    doReturn(mockResponse).when(emailGateway).update(any(), any())

    when:
    def response = subject.updateEmail("1", new Email())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.OK
  }

  def "updatePassword_implemented - 202"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<MdxList<Challenge>>().withResult(new MdxList<>().tap {
      add(new Challenge())
    })

    doReturn(mockResponse).when(profileGateway).updatePassword(any())

    when:
    def response = subject.updatePassword(new Password())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "updatePassword_implemented - 204"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Void>()

    doReturn(mockResponse).when(profileGateway).updatePassword(any())

    when:
    def response = subject.updatePassword(new Password())

    then:
    response.body == mockResponse.result
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "updatePasswordResume_implemented - 202"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<MdxList<Challenge>>().withResult(new MdxList<>().tap {
      add(new Challenge())
    })

    doReturn(mockResponse).when(profileGateway).updatePasswordResume(any(), any())

    when:
    def response = subject.updatePasswordResume("1", new Challenge())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "updatePasswordResume_implemented - 204"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Void>()

    doReturn(mockResponse).when(profileGateway).updatePasswordResume(any(), any())

    when:
    def response = subject.updatePasswordResume("1", new Challenge())

    then:
    response.body == mockResponse.result
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "deleteEmail_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Void>()
    doReturn(mockResponse).when(emailGateway).delete("1")

    when:
    def response = subject.deleteEmail("1")

    then:
    verify(emailGateway).delete("1") || true
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "updateUserName test"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Void>()
    doReturn(mockResponse).when(profileGateway).updateUserName(any())

    when:
    def response = subject.updateUserName(new UserName())

    then:
    response.statusCode == HttpStatus.NO_CONTENT
  }
}
