package com.gazbert.mongosample.services;

import com.gazbert.mongosample.model.ContactAddress;
import com.gazbert.mongosample.model.Registration;
import com.gazbert.mongosample.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A dummy Registration service.
 *
 * <p>These actions would normally be triggered by a SIP request, e.g. REGISTER.
 *
 * <p>An operator could also trigger these kinds of actions via a Admin UI.
 *
 * <p>The service just logs out stuff.
 *
 * @author gazbert
 */
@Service
public class RegistrationService {

  private static final Logger LOG = LoggerFactory.getLogger(RegistrationService.class);

  public static final String ALICE_AOR = "sip:alice@wonderland.net";
  public static final String ALICE_CONTACT_ADDRESS = "sip:alice@192.168.33.111";
  public static final int ALICE_CONTACT_ADDRESS_EXPIRES = 360;
  private static final String ALICE_CACHE_ID = "cache-123";

  public static final String BOB_AOR = "sip:bob@constructor.net";
  public static final String BOB_CONTACT_ADDRESS = "sip:bob@192.168.33.333";
  public static final int BOB_CONTACT_ADDRESS_EXPIRES = 360;
  private static final String BOB_CACHE_ID = "cache-456";

  public static final String CAROL_AOR = "sip:carol@hoth.net";
  public static final String CAROL_CONTACT_ADDRESS_1 = "sip:carol@192.168.33.777";
  public static final String CAROL_CONTACT_ADDRESS_2 = "sip:carol-messenger@192.168.33.222";
  public static final int CAROL_CONTACT_ADDRESS_EXPIRES_1 = 360;
  public static final int CAROL_CONTACT_ADDRESS_EXPIRES_2 = 120;
  private static final String CAROL_CACHE_ID = "cache-789";

  public static final String DAVE_AOR = "sip:dave@seti-aplha-5.net";
  private static final String DAVE_CONTACT_ADDRESS = "sip:dave@192.168.33.888";
  private static final int DAVE_CONTACT_ADDRESS_EXPIRES = 360;
  private static final String DAVE_CACHE_ID = "cache-901";

  private RegistrationRepository registrationRepository;

  /**
   * Creates the service and injects the Registration repository.
   *
   * @param registrationRepository the Registration repository.
   */
  @Autowired
  public RegistrationService(RegistrationRepository registrationRepository) {
    this.registrationRepository = registrationRepository;
  }

  /** Creates some Registrations. */
  public void createRegistrations() {

    // Clear out any old stuff
    registrationRepository.deleteAll();

    // ------------------------------------------------------------------------
    // Create a SIP registration for Alice
    // ------------------------------------------------------------------------
    final Registration aliceRegistration = new Registration();
    final ContactAddress aliceContactAddress = new ContactAddress();
    aliceContactAddress.setAddress(ALICE_CONTACT_ADDRESS);
    aliceContactAddress.setExpires(ALICE_CONTACT_ADDRESS_EXPIRES);
    aliceRegistration.setAor(ALICE_AOR);
    aliceRegistration.addContactAddress(aliceContactAddress);
    aliceRegistration.setCurrentCacheId(ALICE_CACHE_ID);

    LOG.info("");
    LOG.info("Created Registration for Alice: {}", registrationRepository.save(aliceRegistration));

    // ------------------------------------------------------------------------
    // Create a SIP registration for Bob
    // ------------------------------------------------------------------------
    final Registration bobRegistration = new Registration();
    final ContactAddress bobContactAddress = new ContactAddress();
    bobContactAddress.setAddress(BOB_CONTACT_ADDRESS);
    bobContactAddress.setExpires(BOB_CONTACT_ADDRESS_EXPIRES);
    bobRegistration.setAor(BOB_AOR);
    bobRegistration.addContactAddress(bobContactAddress);
    bobRegistration.setCurrentCacheId(BOB_CACHE_ID);

    LOG.info("Created Registration for Bob: {}", registrationRepository.save(bobRegistration));
  }

  /** Fetches some Registrations. */
  public void fetchSomeRegistrations() {

    // ------------------------------------------------------------------------
    // Fetch all Registrations
    // ------------------------------------------------------------------------
    LOG.info("");
    LOG.info("Registrations found using findAll():");
    for (Registration registration : registrationRepository.findAll()) {
      LOG.info("{}", registration);
    }

    // ------------------------------------------------------------------------
    // Fetch individual Registrations
    // ------------------------------------------------------------------------
    LOG.info("");
    LOG.info("Registration found with findByAor('" + ALICE_AOR + "'):");
    LOG.info("{}", registrationRepository.findByAor(ALICE_AOR));

    LOG.info("");
    LOG.info("Registration found with findByAor('" + BOB_AOR + "'):");
    LOG.info("{}", registrationRepository.findByAor(BOB_AOR));

    LOG.info("");
    LOG.info("Registration found with findByContactAddress('" + ALICE_CONTACT_ADDRESS + "'):");
    LOG.info("{}", registrationRepository.findByContactAddress(ALICE_CONTACT_ADDRESS));
  }

  /** Update a Registration. */
  public void updateRegistration() {

    // ------------------------------------------------------------------------
    // Create a SIP registration for Carol
    // ------------------------------------------------------------------------
    final Registration carolRegistration = new Registration();
    final ContactAddress carolContactAddress1 = new ContactAddress();
    carolContactAddress1.setAddress(CAROL_CONTACT_ADDRESS_1);
    carolContactAddress1.setExpires(CAROL_CONTACT_ADDRESS_EXPIRES_1);
    carolRegistration.setAor(CAROL_AOR);
    carolRegistration.addContactAddress(carolContactAddress1);
    carolRegistration.setCurrentCacheId(CAROL_CACHE_ID);

    LOG.info("");
    LOG.info("Created Registration for Carol: {}", registrationRepository.save(carolRegistration));

    // ------------------------------------------------------------------------
    // Update Carol's Registration with new Contact Address
    // ------------------------------------------------------------------------
    final ContactAddress carolContactAddress2 = new ContactAddress();
    carolContactAddress2.setAddress(CAROL_CONTACT_ADDRESS_2);
    carolContactAddress2.setExpires(CAROL_CONTACT_ADDRESS_EXPIRES_2);
    carolRegistration.addContactAddress(carolContactAddress2);

    LOG.info("Updated Registration for Carol: {}", registrationRepository.save(carolRegistration));
  }

  /** Remove a Registration. */
  public void removeRegistration() {

    // ------------------------------------------------------------------------
    // Create SIP registration for Dave
    // ------------------------------------------------------------------------
    final Registration daveRegistration = new Registration();
    final ContactAddress daveContactAddress = new ContactAddress();
    daveContactAddress.setAddress(DAVE_CONTACT_ADDRESS);
    daveContactAddress.setExpires(DAVE_CONTACT_ADDRESS_EXPIRES);
    daveRegistration.setAor(DAVE_AOR);
    daveRegistration.addContactAddress(daveContactAddress);
    daveRegistration.setCurrentCacheId(DAVE_CACHE_ID);

    LOG.info("");
    LOG.info("Created Registration for Dave: {}", registrationRepository.save(daveRegistration));

    // ------------------------------------------------------------------------
    // Remove SIP registration for Dave
    // ------------------------------------------------------------------------
    registrationRepository.delete(daveRegistration);
    LOG.info("Removed Registration for Dave");
  }
}
