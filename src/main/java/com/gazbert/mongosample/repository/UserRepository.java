package com.gazbert.mongosample.repository;

import com.gazbert.mongosample.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The User repository.
 *
 * @author gazbert
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

  /**
   * Searches for Users with a given firstName.
   *
   * @param firstName the first name to search for.
   * @return a list of matching Users if found, and empty list otherwise.
   */
  List<User> findByFirstName(String firstName);

  /**
   * Searches for Users with a given lastName.
   *
   * @param lastName the last name to search for.
   * @return a list of matching Users if found, and empty list otherwise.
   */
  List<User> findByLastName(String lastName);

  /**
   * Searches for Users with a given Username.
   *
   * @param username the username to search for.
   * @return a User if found, null otherwise.
   */
  User findByUsername(String username);

  /**
   * Searches for Users with a given SIP Address of Record (AOR).
   *
   * @param sipAor the SIP AOR to search for.
   * @return a User if found, null otherwise.
   */
  User findBySipAor(String sipAor);
}
