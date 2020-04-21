package com.gazbert.mongosample.it;

import static com.gazbert.mongosample.services.RegistrationService.ALICE_AOR;
import static com.gazbert.mongosample.services.RegistrationService.BOB_AOR;
import static com.gazbert.mongosample.services.RegistrationService.CAROL_AOR;
import static com.gazbert.mongosample.services.RegistrationService.DAVE_AOR;
import static junit.framework.TestCase.assertTrue;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;

import com.gazbert.mongosample.Application;
import com.gazbert.mongosample.model.Registration;
import com.gazbert.mongosample.repository.RegistrationRepository;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

/**
 * Integration tests for the Registrations.
 *
 * @author gazbert
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RegistrationIT {

  private static final Logger LOG = LoggerFactory.getLogger(RegistrationIT.class);

  @Autowired
  @SuppressWarnings("unused")
  private RegistrationRepository registrationRepository;

  @ClassRule
  public static GenericContainer rabbit = new GenericContainer("mongo:4.0").withExposedPorts(27017);

  /**
   * Tests that the Registrations have been created successfully.
   *
   * <p>Expect Alice, Bob, and Carol... Dave's registration was created but then deleted.
   */
  @Test
  public void testRegistrationCreation() {
    LOG.info("Checking if all registrations have been created...");
    final Boolean registrationsCreated =
        await().atMost(10, TimeUnit.SECONDS).until(areRegistrationsCreated(), is(true));
    assertTrue(registrationsCreated);
  }

  /**
   * Tests that a Registration has been updated successfully.
   *
   * <p>Expect Carol's registration to be updated with 2nd Contact Address.
   */
  @Test
  public void testRegistrationUpdates() {
    LOG.info("Checking if Carol's registration has been updated...");
    final Boolean registrationUpdated =
        await().atMost(10, TimeUnit.SECONDS).until(hasRegistrationBeenUpdated(), is(true));
    assertTrue(registrationUpdated);
  }

  /**
   * Tests that a Registration has been removed successfully.
   *
   * <p>Expect Dave's registration to be removed.
   */
  @Test
  public void testRegistrationRemoval() {
    LOG.info("Checking if Dave's registration has been removed...");
    final Boolean registrationRemoved =
        await().atMost(10, TimeUnit.SECONDS).until(hasRegistrationBeenRemoved(), is(true));
    assertTrue(registrationRemoved);
  }

  // --------------------------------------------------------------------------
  // Util methods
  // --------------------------------------------------------------------------

  private Callable<Boolean> areRegistrationsCreated() {
    return () -> {
      final Registration aliceRegistration = registrationRepository.findByAor(ALICE_AOR);
      final Registration bobRegistration = registrationRepository.findByAor(BOB_AOR);
      final Registration carolRegistration = registrationRepository.findByAor(CAROL_AOR);
      return aliceRegistration != null && bobRegistration != null && carolRegistration != null;
    };
  }

  private Callable<Boolean> hasRegistrationBeenUpdated() {
    return () -> {
      final Registration carolRegistration = registrationRepository.findByAor(CAROL_AOR);
      return carolRegistration != null && carolRegistration.getContactAddresses().size() == 2;
    };
  }

  private Callable<Boolean> hasRegistrationBeenRemoved() {
    return () -> {
      final Registration daveRegistration = registrationRepository.findByAor(DAVE_AOR);
      return daveRegistration == null;
    };
  }
}
