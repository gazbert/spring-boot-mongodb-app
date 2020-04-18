package com.gazbert.mongosample.repository;

import com.gazbert.mongosample.model.Registration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * The custom repository implementation for finding a given Contact address in a Registration.
 *
 * @author gazbert
 */
public class CustomRegistrationRepositoryImpl implements CustomRegistrationRepository {

  private MongoTemplate mongoTemplate;

  /**
   * Creates the repository with the MongoDB template.
   *
   * @param mongoTemplate the MongoDB template.
   */
  @Autowired
  public CustomRegistrationRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public List<Registration> findByContactAddress(String contactAddress) {
    final Query query = new Query();
    query.addCriteria(Criteria.where("contactAddresses.address").in(contactAddress));
    return mongoTemplate.find(query, Registration.class);
  }
}
