package com.gazbert.mongosample.model;

import com.google.common.base.MoreObjects;

/**
 * Represents a SIP Contact Address.
 *
 * @author gazbert
 */
public class ContactAddress {

  private String address;
  private int expires;

  public int getExpires() {
    return expires;
  }

  public void setExpires(int expires) {
    this.expires = expires;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("address", address)
        .add("expires", expires)
        .toString();
  }
}
