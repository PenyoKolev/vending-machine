package com.egtinteractive.beverage;

public interface Articles {
//  int price = 0;
//  String name = null;
  
  public default int getPrice() {
    return 0;
  }

  public default String getName() {
    return null;
  }
}
