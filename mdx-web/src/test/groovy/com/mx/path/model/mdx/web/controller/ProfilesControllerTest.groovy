package com.mx.path.model.mdx.web.controller

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify

import com.mx.path.core.common.accessor.PathResponseStatus
import com.mx.path.gateway.accessor.AccessorResponse
import com.mx.path.gateway.api.Gateway
import com.mx.path.gateway.api.profile.AddressGateway
import com.mx.path.gateway.api.profile.ChallengeQuestionGateway
import com.mx.path.gateway.api.profile.EmailGateway
import com.mx.path.gateway.api.profile.PhoneGateway
import com.mx.path.gateway.api.profile.ProfileGateway
import com.mx.path.model.mdx.model.MdxList
import com.mx.path.model.mdx.model.challenges.Challenge
import com.mx.path.model.mdx.model.profile.Address
import com.mx.path.model.mdx.model.profile.ChallengeQuestions
import com.mx.path.model.mdx.model.profile.Email
import com.mx.path.model.mdx.model.profile.Password
import com.mx.path.model.mdx.model.profile.Phone
import com.mx.path.model.mdx.model.profile.Profile
import com.mx.path.model.mdx.model.profile.UserName

import org.springframework.http.HttpStatus

import spock.lang.Specification

class ProfilesControllerTest extends Specification {
  ProfilesController subject
  Gateway gateway
  ProfileGateway profileGateway
  AddressGateway addressGateway
  ChallengeQuestionGateway challengeQuestionGateway
  EmailGateway emailGateway
  PhoneGateway phoneGateway
  String clientId

  def setup() {
    clientId = "client123"

    addressGateway = spy(AddressGateway.builder().clientId(clientId).build())
    challengeQuestionGateway = spy(ChallengeQuestionGateway.builder().clientId(clientId).build())
    emailGateway = spy(EmailGateway.builder().clientId(clientId).build())
    phoneGateway = spy(PhoneGateway.builder().clientId(clientId).build())
    profileGateway = spy(ProfileGateway.builder().clientId(clientId).emails(emailGateway).phones(phoneGateway).addresses(addressGateway).challengeQuestions(challengeQuestionGateway).build())
    gateway = spy(Gateway.builder().clientId(clientId).profiles(profileGateway).build())

    subject = new ProfilesController()
  }

  def cleanup() {
    ProfilesController.clearGateway()
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

  def "putAddress_implemented - 202"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Address>().withResult(new Address().tap {
      setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
    })
    doReturn(mockResponse).when(addressGateway).update(any(), any())

    when:
    def response = subject.updateAddress("1", new Address())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "putAddress_implemented - 200"() {
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

  def "getChallengeQuestions_implemented"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<ChallengeQuestions>().withResult(new ChallengeQuestions())
    doReturn(mockResponse).when(challengeQuestionGateway).list()

    when:
    def response = subject.getChallengeQuestions()

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "updateChallengeQuestions_implemented response is 202"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<ChallengeQuestions>().withResult(
        new ChallengeQuestions().tap {
          setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
        }
        )

    doReturn(mockResponse).when(challengeQuestionGateway).update(any())

    when:
    def response = subject.updateChallengeQuestions(new ChallengeQuestions())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "updateChallengeQuestions_implemented response is 204"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<ChallengeQuestions>().withResult(new ChallengeQuestions()).withStatus(PathResponseStatus.ACCEPTED)
    doReturn(mockResponse).when(challengeQuestionGateway).update(any())

    when:
    def response = subject.updateChallengeQuestions(new ChallengeQuestions())

    then:
    response.statusCode == HttpStatus.NO_CONTENT
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
      } as List<com.mx.path.model.mdx.model.payout.Challenge>)
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

  def "putEmail_implemented - 202"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Email>().withResult(new Email().tap {
      setChallenges(new MdxList<Challenge>().tap { add(new Challenge()) })
    })
    doReturn(mockResponse).when(emailGateway).update(any(), any())

    when:
    def response = subject.updateEmail("1", new Email())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "putEmail_implemented - 200"() {
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

    def mockResponse = new AccessorResponse<Password>().withResult(new Password().tap {
      setChallenges(new MdxList<>().tap { add(new Challenge()) })
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

    def mockResponse = new AccessorResponse<Password>().withResult(new Password())

    doReturn(mockResponse).when(profileGateway).updatePassword(any())

    when:
    def response = subject.updatePassword(new Password())

    then:
    response.body == null
    response.statusCode == HttpStatus.NO_CONTENT
  }

  def "updatePasswordResume_implemented - 202"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<Password>().withResult(new Password().tap {
      setChallenges(new MdxList<>().tap { add(new Challenge()) })
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

    def mockResponse = new AccessorResponse<Password>().withResult(new Password())

    doReturn(mockResponse).when(profileGateway).updatePasswordResume(any(), any())

    when:
    def response = subject.updatePasswordResume("1", new Challenge())

    then:
    response.body == null
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

  def "updateUserName_implemented - 202"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<UserName>().withResult(new UserName().tap {
      setChallenges(new MdxList<>().tap {add(new Challenge()) })
    })

    doReturn(mockResponse).when(profileGateway).updateUserName(any())

    when:
    def response = subject.updateUserName(new UserName())

    then:
    response.body == mockResponse.result
    response.body.wrapped
    response.statusCode == HttpStatus.ACCEPTED
  }

  def "updateUserName_implemented - 204"() {
    given:
    ProfilesController.setGateway(gateway)

    def mockResponse = new AccessorResponse<UserName>().withResult(new UserName())

    doReturn(mockResponse).when(profileGateway).updateUserName(any())

    when:
    def response = subject.updateUserName(new UserName())

    then:
    response.body == null
    response.statusCode == HttpStatus.NO_CONTENT
  }
}
