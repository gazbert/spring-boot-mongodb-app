package com.gazbert.mongosample.repository;

import com.gazbert.mongosample.model.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The Registrations repository.
 *
 * @author gazbert
 */
@Repository
public interface RegistrationRepository
    extends CustomRegistrationRepository, MongoRepository<Registration, String> {

  /**
   * Looks up a Registration for a given AOR.
   *
   * @param addressOfRecord the AOR to search for.
   * @return the Registration if found, null otherwise.
   */
  Registration findByAddressOfRecord(String addressOfRecord);
}
