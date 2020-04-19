package com.gazbert.mongosample.model;

import com.google.common.base.MoreObjects;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a SIP Registration entity.
 *
 * @author gazbert
 */
@Document
public class Registration {

  @Id private String id;

  /** Indexed this attribute as it will be looked up lots! */
  @Indexed private String addressOfRecord;

  /** Shows how not to store some data in the database */
  @Transient private String currentCacheId;

  private Set<ContactAddress> contactAddresses;

  public Registration() {
    contactAddresses = new HashSet<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAddressOfRecord() {
    return addressOfRecord;
  }

  public void setAddressOfRecord(String addressOfRecord) {
    this.addressOfRecord = addressOfRecord;
  }

  public Set<ContactAddress> getContactAddresses() {
    return contactAddresses;
  }

  public boolean addContactAddress(ContactAddress contactAddress) {
    return this.contactAddresses.add(contactAddress);
  }

  public boolean removeContactAddress(ContactAddress contactAddress) {
    return this.contactAddresses.remove(contactAddress);
  }

  public String getCurrentCacheId() {
    return currentCacheId;
  }

  public void setCurrentCacheId(String currentCacheId) {
    this.currentCacheId = currentCacheId;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("addressOfRecord", addressOfRecord)
        .add("currentCacheId", currentCacheId)
        .add("contactAddresses", contactAddresses)
        .toString();
  }
}
