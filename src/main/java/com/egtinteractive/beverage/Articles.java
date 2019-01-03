package com.egtinteractive.beverage;

public interface Articles {

  public default int getPrice() {
    return 0;
  }

  public default String getName() {
    return null;
  }
}
