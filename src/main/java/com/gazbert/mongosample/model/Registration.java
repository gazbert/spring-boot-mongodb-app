package com.gazbert.mongosample.model;

import com.google.common.base.MoreObjects;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;

/**
 * Represents a SIP Registration entity.
 *
 * @author gazbert
 */
public class Registration {

  @Id private String id;
  private String addressOfRecord;
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

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("addressOfRecord", addressOfRecord)
        .add("contactAddresses", contactAddresses)
        .toString();
  }
}
