package com.gazbert.mongosample.model;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
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

  /** Indexed this attribute as it will be looked up lots. */
  @Indexed private String aor;

  /** Shows how not to store some data in the database. */
  @Transient private String currentCacheId;

  private List<ContactAddress> contactAddresses;


  public Registration() {
    contactAddresses = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAor() {
    return aor;
  }

  public void setAor(String aor) {
    this.aor = aor;
  }

  public List<ContactAddress> getContactAddresses() {
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
        .add("aor", aor)
        .add("currentCacheId", currentCacheId)
        .add("contactAddresses", contactAddresses)
        .toString();
  }
}
