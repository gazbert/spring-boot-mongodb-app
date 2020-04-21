package com.gazbert.mongosample.repository;

import static com.gazbert.mongosample.services.RegistrationService.ALICE_AOR;
import static com.gazbert.mongosample.services.RegistrationService.ALICE_CONTACT_ADDRESS;
import static com.gazbert.mongosample.services.RegistrationService.ALICE_CONTACT_ADDRESS_EXPIRES;
import static com.gazbert.mongosample.services.RegistrationService.BOB_AOR;
import static com.gazbert.mongosample.services.RegistrationService.BOB_CONTACT_ADDRESS;
import static com.gazbert.mongosample.services.RegistrationService.BOB_CONTACT_ADDRESS_EXPIRES;
import static com.gazbert.mongosample.services.RegistrationService.CAROL_AOR;
import static com.gazbert.mongosample.services.RegistrationService.CAROL_CONTACT_ADDRESS_1;
import static com.gazbert.mongosample.services.RegistrationService.CAROL_CONTACT_ADDRESS_2;
import static com.gazbert.mongosample.services.RegistrationService.CAROL_CONTACT_ADDRESS_EXPIRES_1;
import static com.gazbert.mongosample.services.RegistrationService.CAROL_CONTACT_ADDRESS_EXPIRES_2;
import static com.gazbert.mongosample.services.RegistrationService.DAVE_AOR;
import static org.assertj.core.api.Assertions.assertThat;

import com.gazbert.mongosample.model.Registration;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The UserService will create a bunch of Registrations and save them to MongoDB.
 *
 * <p>These tests check the Registrations are saved as expected.
 *
 * @author gazbert
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationRepositoryTests {

  @Autowired
  @SuppressWarnings("unused")
  private RegistrationRepository registrationRepository;

  @Test
  public void testRegistrationsCreatedSuccessfully() {

    final List<Registration> allRegistrations = registrationRepository.findAll();
    assertThat(allRegistrations.size()).isEqualTo(3); // Alice, Bob, and Carol... but no Dave.

    final Registration aliceRegistration = registrationRepository.findByAor(ALICE_AOR);
    assertThat(aliceRegistration.getAor()).isEqualTo(ALICE_AOR);
    // Null because it's transient
    assertThat(aliceRegistration.getCurrentCacheId()).isEqualTo(null);
    assertThat(aliceRegistration.getContactAddresses().size()).isEqualTo(1);
    assertThat(aliceRegistration.getContactAddresses().get(0).getAddress())
        .isEqualTo(ALICE_CONTACT_ADDRESS);
    assertThat(aliceRegistration.getContactAddresses().get(0).getExpires())
        .isEqualTo(ALICE_CONTACT_ADDRESS_EXPIRES);

    final Registration bobRegistration = registrationRepository.findByAor(BOB_AOR);
    assertThat(bobRegistration.getAor()).isEqualTo(BOB_AOR);
    // Null because it's transient
    assertThat(bobRegistration.getCurrentCacheId()).isEqualTo(null);
    assertThat(bobRegistration.getContactAddresses().size()).isEqualTo(1);
    assertThat(bobRegistration.getContactAddresses().get(0).getAddress())
        .isEqualTo(BOB_CONTACT_ADDRESS);
    assertThat(bobRegistration.getContactAddresses().get(0).getExpires())
        .isEqualTo(BOB_CONTACT_ADDRESS_EXPIRES);

    final Registration carolRegistration = registrationRepository.findByAor(CAROL_AOR);
    assertThat(carolRegistration.getAor()).isEqualTo(CAROL_AOR);
  }

  @Test
  public void testRegistrationUpdatedSuccessfully() {
    final Registration carolRegistration = registrationRepository.findByAor(CAROL_AOR);
    assertThat(carolRegistration.getAor()).isEqualTo(CAROL_AOR);

    // Null because it's transient
    assertThat(carolRegistration.getCurrentCacheId()).isEqualTo(null);

    assertThat(carolRegistration.getContactAddresses().size()).isEqualTo(2);

    assertThat(carolRegistration.getContactAddresses().get(0).getAddress())
        .isEqualTo(CAROL_CONTACT_ADDRESS_1);
    assertThat(carolRegistration.getContactAddresses().get(0).getExpires())
        .isEqualTo(CAROL_CONTACT_ADDRESS_EXPIRES_1);

    assertThat(carolRegistration.getContactAddresses().get(1).getAddress())
        .isEqualTo(CAROL_CONTACT_ADDRESS_2);
    assertThat(carolRegistration.getContactAddresses().get(1).getExpires())
        .isEqualTo(CAROL_CONTACT_ADDRESS_EXPIRES_2);
  }

  @Test
  public void testRegistrationRemovedSuccessfully() {
    final Registration daveRegistration = registrationRepository.findByAor(DAVE_AOR);
    assertThat(daveRegistration).isNull();
  }
}
