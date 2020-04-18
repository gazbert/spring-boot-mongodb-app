package com.gazbert.mongosample;

import com.gazbert.mongosample.model.ContactAddress;
import com.gazbert.mongosample.model.Registration;
import com.gazbert.mongosample.model.User;
import com.gazbert.mongosample.repository.CustomRegistrationRepository;
import com.gazbert.mongosample.repository.RegistrationRepository;
import com.gazbert.mongosample.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A simple application that uses MongoDB to store and manage User details.
 *
 * @author gazbert
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);
  private UserRepository userRepository;
  private RegistrationRepository registrationRepository;

  @Autowired
  public Application(UserRepository userRepository, RegistrationRepository registrationRepository) {
    this.userRepository = userRepository;
    this.registrationRepository = registrationRepository;
  }

  /**
   * Starts the app.
   *
   * @param args no args required.
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  /**
   * Runs the sample app.
   *
   * @param args no args passed.
   */
  @Override
  public void run(String... args) {

    // Clear out any old stuff
    userRepository.deleteAll();
    registrationRepository.deleteAll();

    // Create a couple of users
    final User alice = new User();
    alice.setFirstName("Alice");
    alice.setLastName("Wonderland");
    alice.setUsername("alice123");
    alice.setEmail("alice.winderland@wonderland.net");
    alice.setAccountEnabled(true);
    alice.setSipAor("sip:alice@wonderland.net");

    final User bob = new User();
    bob.setFirstName("Bob");
    bob.setLastName("Dabuilder");
    bob.setUsername("bob345");
    bob.setEmail("bob.dabuilder@constructor.net");
    bob.setAccountEnabled(true);
    bob.setSipAor("sip:bob@constructor.net");

    // Save them to MongoDB
    userRepository.save(alice);
    userRepository.save(bob);

    // Fetch all Users
    LOG.info("Users found using findAll():");
    for (User user : userRepository.findAll()) {
      LOG.info(user.toString());
    }

    // Fetch individual Users
    LOG.info("");
    LOG.info("User found with findByFirstName('Alice'):");
    for (User user : userRepository.findByFirstName("Alice")) {
      LOG.info(user.toString());
    }

    LOG.info("");
    LOG.info("User found with findByLastName('Dabuilder'):");
    for (User user : userRepository.findByLastName("Dabuilder")) {
      LOG.info(user.toString());
    }

    LOG.info("");
    LOG.info("User found with findByUsername('sip:bob@constructor.net'):");
    LOG.info(userRepository.findByUsername("alice123").toString());

    LOG.info("");
    LOG.info("User found with findBySipAor('sip:bob@constructor.net'):");
    LOG.info(userRepository.findBySipAor("sip:bob@constructor.net").toString());

    // Create a SIP registration for Alice
    final Registration aliceRegistration = new Registration();
    final ContactAddress aliceContactAddress = new ContactAddress();
    aliceContactAddress.setAddress("sip:alice@192.168.33.111");
    aliceContactAddress.setExpires(360);
    aliceRegistration.setAddressOfRecord(alice.getSipAor());
    aliceRegistration.addContactAddress(aliceContactAddress);

    // Create a SIP registration for Bob
    final Registration bobRegistration = new Registration();
    final ContactAddress bobContactAddress = new ContactAddress();
    bobContactAddress.setAddress("sip:bob@192.168.33.333");
    bobContactAddress.setExpires(360);
    bobRegistration.setAddressOfRecord(bob.getSipAor());
    bobRegistration.addContactAddress(bobContactAddress);

    // Save them to MongoDB
    registrationRepository.save(aliceRegistration);
    registrationRepository.save(bobRegistration);

    // Fetch all Registrations
    LOG.info("");
    LOG.info("Registrations found using findAll():");
    for (Registration registration : registrationRepository.findAll()) {
      LOG.info(registration.toString());
    }

    // Fetch individual Registrations
    LOG.info("");
    LOG.info("Registration found with findByAddressOfRecord('sip:alice@wonderland.net'):");
    LOG.info(registrationRepository.findByAddressOfRecord("sip:alice@wonderland.net").toString());

    LOG.info("");
    LOG.info("Registration found with findByAddressOfRecord('sip:bob@constructor.net'):");
    LOG.info(registrationRepository.findByAddressOfRecord("sip:bob@constructor.net").toString());

    LOG.info("");
    LOG.info("Registration found with findByContactAddress('sip:alice@192.168.33.111'):");
    LOG.info(registrationRepository.findByContactAddress("sip:alice@192.168.33.111").toString());
  }
}
