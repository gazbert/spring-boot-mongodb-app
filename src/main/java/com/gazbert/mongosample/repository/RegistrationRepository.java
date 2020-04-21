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
   * Looks up a Registration for a given Address of Record (AOR).
   *
   * @param aor the AOR to search for.
   * @return the Registration if found, null otherwise.
   */
  Registration findByAor(String aor);
}
