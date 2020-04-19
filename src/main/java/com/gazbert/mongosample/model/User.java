package com.gazbert.mongosample.model;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Represents a User entity.
 *
 * @author gazbert
 */
@Document
public class User {

  /**
   * The name 'id' fits the standard name for a MongoDB ID, so it does not require any special
   * annotation to tag it for Spring Data MongoDB. Autogenerated.
   */
  @Id private String id;

  /** Indexed this attribute as it will be looked up lots! */
  @Indexed private String sipAor;

  private String username;
  private String firstName;
  private String lastName;

  /** Shows how to override the default field name in the database */
  @Field("emailAddress")
  private String email;

  private boolean isAccountEnabled;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSipAor() {
    return sipAor;
  }

  public void setSipAor(String sipAor) {
    this.sipAor = sipAor;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isAccountEnabled() {
    return isAccountEnabled;
  }

  public void setAccountEnabled(boolean accountEnabled) {
    isAccountEnabled = accountEnabled;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("sipAor", sipAor)
        .add("username", username)
        .add("firstName", firstName)
        .add("lastName", lastName)
        .add("email", email)
        .add("isAccountEnabled", isAccountEnabled)
        .toString();
  }
}
