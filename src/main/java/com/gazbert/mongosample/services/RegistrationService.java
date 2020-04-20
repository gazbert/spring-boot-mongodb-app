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
    aliceRegistration.setAddressOfRecord(ALICE_AOR);
    aliceRegistration.addContactAddress(aliceContactAddress);
    aliceRegistration.setCurrentCacheId(ALICE_CACHE_ID);

    // ------------------------------------------------------------------------
    // Create a SIP registration for Bob
    // ------------------------------------------------------------------------
    final Registration bobRegistration = new Registration();
    final ContactAddress bobContactAddress = new ContactAddress();
    bobContactAddress.setAddress(BOB_CONTACT_ADDRESS);
    bobContactAddress.setExpires(BOB_CONTACT_ADDRESS_EXPIRES);
    bobRegistration.setAddressOfRecord(BOB_AOR);
    bobRegistration.addContactAddress(bobContactAddress);
    bobRegistration.setCurrentCacheId(BOB_CACHE_ID);

    // ------------------------------------------------------------------------
    // Save them to MongoDB
    // ------------------------------------------------------------------------
    registrationRepository.save(aliceRegistration);
    registrationRepository.save(bobRegistration);
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
    LOG.info("Registration found with findByAddressOfRecord('" + ALICE_AOR + "'):");
    LOG.info(String.valueOf(registrationRepository.findByAddressOfRecord(ALICE_AOR)));

    LOG.info("");
    LOG.info("Registration found with findByAddressOfRecord('" + BOB_AOR + "'):");
    LOG.info(String.valueOf(registrationRepository.findByAddressOfRecord(BOB_AOR)));

    LOG.info("");
    LOG.info("Registration found with findByContactAddress('" + ALICE_CONTACT_ADDRESS + "'):");
    LOG.info(String.valueOf(registrationRepository.findByContactAddress(ALICE_CONTACT_ADDRESS)));
  }
}
