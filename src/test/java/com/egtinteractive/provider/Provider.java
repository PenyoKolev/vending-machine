package com.egtinteractive.provider;

public class Provider {

  Generator generator = new Generator();
  
  public static Object[][] standByMachine() {
    return new Object[][] {{Generator.standByMode()}};
  }

  public static Object[][] serviceMachine() {
    return new Object[][] {{Generator.serviceMode() }};
  }
}
