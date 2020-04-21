package com.gazbert.mongosample.repository;

import static com.gazbert.mongosample.services.UserService.ALICE_ACCOUNT_ENABLED;
import static com.gazbert.mongosample.services.UserService.ALICE_AOR;
import static com.gazbert.mongosample.services.UserService.ALICE_EMAIL;
import static com.gazbert.mongosample.services.UserService.ALICE_FIRSTNAME;
import static com.gazbert.mongosample.services.UserService.ALICE_LASTNAME;
import static com.gazbert.mongosample.services.UserService.ALICE_USERNAME;
import static com.gazbert.mongosample.services.UserService.BOB_ACCOUNT_ENABLED;
import static com.gazbert.mongosample.services.UserService.BOB_AOR;
import static com.gazbert.mongosample.services.UserService.BOB_EMAIL;
import static com.gazbert.mongosample.services.UserService.BOB_FIRSTNAME;
import static com.gazbert.mongosample.services.UserService.BOB_LASTNAME;
import static com.gazbert.mongosample.services.UserService.BOB_USERNAME;
import static com.gazbert.mongosample.services.UserService.CAROL_AOR;
import static com.gazbert.mongosample.services.UserService.CAROL_EMAIL;
import static com.gazbert.mongosample.services.UserService.CAROL_FIRSTNAME;
import static com.gazbert.mongosample.services.UserService.CAROL_LASTNAME;
import static com.gazbert.mongosample.services.UserService.CAROL_USERNAME;
import static com.gazbert.mongosample.services.UserService.DAVE_AOR;
import static org.assertj.core.api.Assertions.assertThat;

import com.gazbert.mongosample.model.User;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The UserService will create a bunch of Users and save them to MongoDB.
 *
 * <p>These tests check the Users are saved as expected.
 *
 * @author gazbert
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {

  @Autowired
  @SuppressWarnings("unused")
  private UserRepository userRepository;

  @Test
  public void testUsersCreatedSuccessfully() {

    final List<User> allUsers = userRepository.findAll();
    assertThat(allUsers.size()).isEqualTo(3); // Alice, Bob, and Carol

    final User alice = userRepository.findBySipAor(ALICE_AOR);

    assertThat(alice.getFirstName()).isEqualTo(ALICE_FIRSTNAME);
    assertThat(alice.getLastName()).isEqualTo(ALICE_LASTNAME);
    assertThat(alice.getUsername()).isEqualTo(ALICE_USERNAME);
    assertThat(alice.getEmail()).isEqualTo(ALICE_EMAIL);
    assertThat(alice.isAccountEnabled()).isEqualTo(ALICE_ACCOUNT_ENABLED);
    assertThat(alice.getSipAor()).isEqualTo(ALICE_AOR);

    final User bob = userRepository.findBySipAor(BOB_AOR);

    assertThat(bob.getFirstName()).isEqualTo(BOB_FIRSTNAME);
    assertThat(bob.getLastName()).isEqualTo(BOB_LASTNAME);
    assertThat(bob.getUsername()).isEqualTo(BOB_USERNAME);
    assertThat(bob.getEmail()).isEqualTo(BOB_EMAIL);
    assertThat(bob.isAccountEnabled()).isEqualTo(BOB_ACCOUNT_ENABLED);
    assertThat(bob.getSipAor()).isEqualTo(BOB_AOR);
  }

  @Test
  public void testUserUpdatedSuccessfully() {
    final User carol = userRepository.findBySipAor(CAROL_AOR);

    assertThat(carol.getFirstName()).isEqualTo(CAROL_FIRSTNAME);
    assertThat(carol.getLastName()).isEqualTo(CAROL_LASTNAME);
    assertThat(carol.getUsername()).isEqualTo(CAROL_USERNAME);
    assertThat(carol.getEmail()).isEqualTo(CAROL_EMAIL);
    assertThat(carol.isAccountEnabled()).isEqualTo(false); // it's been disabled
    assertThat(carol.getSipAor()).isEqualTo(CAROL_AOR);
  }

  @Test
  public void testUserRemovedSuccessfully() {
    final User dave = userRepository.findBySipAor(DAVE_AOR);
    assertThat(dave).isNull();
  }
}
