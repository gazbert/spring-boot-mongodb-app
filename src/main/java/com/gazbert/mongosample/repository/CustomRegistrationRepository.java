package com.gazbert.mongosample.repository;

import com.gazbert.mongosample.model.Registration;
import java.util.List;

/**
 * A custom repository for finding a matching Contact address in a Registration.
 *
 * @author gazbert
 */
public interface CustomRegistrationRepository {

  /**
   * Looks up a Registration for a given Contact address.
   *
   * @param contactAddress the Contact address.
   * @return a Registration if found, null otherwise.
   */
  List<Registration> findByContactAddress(String contactAddress);
}
