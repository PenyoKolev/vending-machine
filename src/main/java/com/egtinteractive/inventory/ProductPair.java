package com.egtinteractive.inventory;

import com.egtinteractive.beverage.Products;

public class ProductPair {
  private Products product;
  private int quantity;

  public ProductPair(Products product, int quantity) {
    this.setProduct(product);
    this.setQuantity(quantity);
  }

  public Products getProduct() {
    return product;
  }

  public void setProduct(Products product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
