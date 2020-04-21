package com.gazbert.mongosample.it;

import static com.gazbert.mongosample.services.RegistrationService.ALICE_AOR;
import static com.gazbert.mongosample.services.RegistrationService.BOB_AOR;
import static com.gazbert.mongosample.services.RegistrationService.CAROL_AOR;
import static com.gazbert.mongosample.services.UserService.DAVE_AOR;
import static junit.framework.TestCase.assertTrue;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.CoreMatchers.is;

import com.gazbert.mongosample.Application;
import com.gazbert.mongosample.model.User;
import com.gazbert.mongosample.repository.UserRepository;
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
 * Integration tests for the Users.
 *
 * @author gazbert
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserIT {

  private static final Logger LOG = LoggerFactory.getLogger(UserIT.class);

  @Autowired
  @SuppressWarnings("unused")
  private UserRepository userRepository;

  @ClassRule
  public static GenericContainer rabbit = new GenericContainer("mongo:4.0").withExposedPorts(27017);

  /**
   * Tests that the Users have been created successfully.
   *
   * <p>Expect Alice, Bob, and Carol... Dave's User was created but then deleted.
   */
  @Test
  public void testUserCreation() {
    LOG.info("Checking if all users have been created...");
    final Boolean registrationsCreated =
        await().atMost(10, TimeUnit.SECONDS).until(areUsersCreated(), is(true));
    assertTrue(registrationsCreated);
  }

  /**
   * Tests that a User has been updated successfully.
   *
   * <p>Expect Carol's user account to be disabled.
   */
  @Test
  public void testUserUpdates() {
    LOG.info("Checking if Carol's user account has been updated...");
    final Boolean userUpdated =
        await().atMost(10, TimeUnit.SECONDS).until(hasUserBeenUpdated(), is(true));
    assertTrue(userUpdated);
  }

  /**
   * Tests that a User has been removed successfully.
   *
   * <p>Expect Dave's user to be removed.
   */
  @Test
  public void testUserRemoval() {
    LOG.info("Checking if Dave's user has been removed...");
    final Boolean userRemoved =
        await().atMost(10, TimeUnit.SECONDS).until(hasUserBeenRemoved(), is(true));
    assertTrue(userRemoved);
  }

  // --------------------------------------------------------------------------
  // Util methods
  // --------------------------------------------------------------------------

  private Callable<Boolean> areUsersCreated() {
    return () -> {
      final User alice = userRepository.findBySipAor(ALICE_AOR);
      final User bob = userRepository.findBySipAor(BOB_AOR);
      final User carol = userRepository.findBySipAor(CAROL_AOR);
      return alice != null && bob != null && carol != null;
    };
  }

  private Callable<Boolean> hasUserBeenUpdated() {
    return () -> {
      final User carol = userRepository.findBySipAor(CAROL_AOR);
      return carol != null && !carol.isAccountEnabled();
    };
  }

  private Callable<Boolean> hasUserBeenRemoved() {
    return () -> {
      final User dave = userRepository.findBySipAor(DAVE_AOR);
      return dave == null;
    };
  }
}
