package com.tienda.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class PurchaseRequest {
  public String customerName;

  @NotEmpty
  public List<Item> items;

  public static class Item {
    public Long productId;
    public Integer quantity;
  }
}
