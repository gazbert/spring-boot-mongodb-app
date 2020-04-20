package com.gazbert.mongosample.repository;

import static com.gazbert.mongosample.services.RegistrationService.ALICE_AOR;
import static com.gazbert.mongosample.services.RegistrationService.ALICE_CONTACT_ADDRESS;
import static com.gazbert.mongosample.services.RegistrationService.ALICE_CONTACT_ADDRESS_EXPIRES;
import static com.gazbert.mongosample.services.RegistrationService.BOB_AOR;
import static com.gazbert.mongosample.services.RegistrationService.BOB_CONTACT_ADDRESS;
import static com.gazbert.mongosample.services.RegistrationService.BOB_CONTACT_ADDRESS_EXPIRES;
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
    assertThat(allRegistrations.size()).isEqualTo(2);

    final Registration aliceRegistration = registrationRepository.findByAddressOfRecord(ALICE_AOR);
    assertThat(aliceRegistration.getAddressOfRecord()).isEqualTo(ALICE_AOR);
    // Null because it's transient
    assertThat(aliceRegistration.getCurrentCacheId()).isEqualTo(null);
    assertThat(aliceRegistration.getContactAddresses().size()).isEqualTo(1);
    assertThat(aliceRegistration.getContactAddresses().get(0).getAddress())
        .isEqualTo(ALICE_CONTACT_ADDRESS);
    assertThat(aliceRegistration.getContactAddresses().get(0).getExpires())
        .isEqualTo(ALICE_CONTACT_ADDRESS_EXPIRES);

    final Registration bobRegistration = registrationRepository.findByAddressOfRecord(BOB_AOR);
    assertThat(bobRegistration.getAddressOfRecord()).isEqualTo(BOB_AOR);
    // Null because it's transient
    assertThat(bobRegistration.getCurrentCacheId()).isEqualTo(null);
    assertThat(bobRegistration.getContactAddresses().size()).isEqualTo(1);
    assertThat(bobRegistration.getContactAddresses().get(0).getAddress())
        .isEqualTo(BOB_CONTACT_ADDRESS);
    assertThat(bobRegistration.getContactAddresses().get(0).getExpires())
        .isEqualTo(BOB_CONTACT_ADDRESS_EXPIRES);
  }
}
