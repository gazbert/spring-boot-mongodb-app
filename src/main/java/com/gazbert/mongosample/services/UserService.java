package com.gazbert.mongosample.services;

import com.gazbert.mongosample.model.User;
import com.gazbert.mongosample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A dummy User service.
 *
 * <p>These collections of actions would normally be triggered by an operator invoking a REST API or
 * some other interface, e.g. UI.
 *
 * <p>The service just logs out stuff.
 *
 * @author gazbert
 */
@Service
public class UserService {

  private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

  public static final String ALICE_AOR = "sip:alice@wonderland.net";
  public static final String ALICE_USERNAME = "alice123";
  public static final String ALICE_FIRSTNAME = "Alice";
  public static final String ALICE_LASTNAME = "Wonderland";
  public static final String ALICE_EMAIL = "alice.winderland@wonderland.net";
  public static final boolean ALICE_ACCOUNT_ENABLED = true;

  public static final String BOB_AOR = "sip:bob@constructor.net";
  public static final String BOB_USERNAME = "bob345";
  public static final String BOB_FIRSTNAME = "Bob";
  public static final String BOB_LASTNAME = "Dabuilder";
  public static final String BOB_EMAIL = "bob.dabuilder@constructor.net";
  public static final boolean BOB_ACCOUNT_ENABLED = true;

  public static final String CAROL_AOR = "sip:carol@hoth.net";
  public static final String CAROL_USERNAME = "carol789";
  public static final String CAROL_FIRSTNAME = "Carol";
  public static final String CAROL_LASTNAME = "Singer";
  public static final String CAROL_EMAIL = "carol.singer@christmas.net";

  private static final String DAVE_AOR = "sip:dave@seti-aplha-5.net";
  private static final String DAVE_USERNAME = "dave012";
  private static final String DAVE_FIRSTNAME = "Dave";
  private static final String DAVE_LASTNAME = "Badger";
  private static final String DAVE_EMAIL = "dave.badger@prosephony.net";
  private static final boolean DAVE_ACCOUNT_ENABLED = true;

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /** Creates and saves some Users. */
  public void createUsers() {

    // Clear out any old stuff
    userRepository.deleteAll();

    // ------------------------------------------------------------------------
    // Create a couple of Users
    // ------------------------------------------------------------------------
    final User alice = new User();
    alice.setFirstName(ALICE_FIRSTNAME);
    alice.setLastName(ALICE_LASTNAME);
    alice.setUsername(ALICE_USERNAME);
    alice.setEmail(ALICE_EMAIL);
    alice.setAccountEnabled(ALICE_ACCOUNT_ENABLED);
    alice.setSipAor(ALICE_AOR);

    final User bob = new User();
    bob.setFirstName(BOB_FIRSTNAME);
    bob.setLastName(BOB_LASTNAME);
    bob.setUsername(BOB_USERNAME);
    bob.setEmail(BOB_EMAIL);
    bob.setAccountEnabled(BOB_ACCOUNT_ENABLED);
    bob.setSipAor(BOB_AOR);

    // ------------------------------------------------------------------------
    // Save them to MongoDB
    // ------------------------------------------------------------------------
    userRepository.save(alice);
    userRepository.save(bob);
  }

  /** Fetches some Users. */
  public void fetchSomeUsers() {

    // ------------------------------------------------------------------------
    // Fetch all Users
    // ------------------------------------------------------------------------
    LOG.info("Users found using findAll():");
    for (User user : userRepository.findAll()) {
      LOG.info("{}", user);
    }

    // ------------------------------------------------------------------------
    // Fetch individual Users
    // ------------------------------------------------------------------------
    LOG.info("");
    LOG.info("User found with findByFirstName('" + ALICE_FIRSTNAME + "'):");
    for (User user : userRepository.findByFirstName(ALICE_FIRSTNAME)) {
      LOG.info("{}", user);
    }

    LOG.info("");
    LOG.info("User found with findByLastName('" + BOB_ACCOUNT_ENABLED + "'):");
    for (User user : userRepository.findByLastName(BOB_LASTNAME)) {
      LOG.info("{}", user);
    }

    LOG.info("");
    LOG.info("User found with findByUsername('" + ALICE_USERNAME + "'):");
    LOG.info("{}", userRepository.findByUsername(ALICE_USERNAME));

    LOG.info("");
    LOG.info("User found with findBySipAor('" + BOB_AOR + "'):");
    LOG.info("{}", userRepository.findBySipAor(BOB_AOR));
  }

  /** Update a User. */
  public void updateUser() {

    // ------------------------------------------------------------------------
    // Create a User
    // ------------------------------------------------------------------------
    final User carol = new User();
    carol.setFirstName(CAROL_FIRSTNAME);
    carol.setLastName(CAROL_LASTNAME);
    carol.setUsername(CAROL_USERNAME);
    carol.setEmail(CAROL_EMAIL);
    carol.setAccountEnabled(true);
    carol.setSipAor(CAROL_AOR);

    LOG.info("");
    LOG.info("Created User for Carol: {}", userRepository.save(carol));

    // ------------------------------------------------------------------------
    // Update User's account to disable it
    // ------------------------------------------------------------------------
    carol.setAccountEnabled(false);
    LOG.info("Updated User for Carol: {}", userRepository.save(carol));
  }

  /** Remove a User. */
  public void removeUser() {

    // ------------------------------------------------------------------------
    // Create a User
    // ------------------------------------------------------------------------
    final User dave = new User();
    dave.setFirstName(DAVE_FIRSTNAME);
    dave.setLastName(DAVE_LASTNAME);
    dave.setUsername(DAVE_USERNAME);
    dave.setEmail(DAVE_EMAIL);
    dave.setAccountEnabled(DAVE_ACCOUNT_ENABLED);
    dave.setSipAor(DAVE_AOR);

    LOG.info("");
    LOG.info("Created User for Dave: {}", userRepository.save(dave));

    // ------------------------------------------------------------------------
    // Remove User
    // ------------------------------------------------------------------------
    userRepository.delete(dave);
    LOG.info("Removed User for Dave");
  }
}
