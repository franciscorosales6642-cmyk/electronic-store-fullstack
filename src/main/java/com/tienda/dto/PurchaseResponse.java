package com.tienda.dto;

import java.math.BigDecimal;

public class PurchaseResponse {
  public Long saleId;
  public BigDecimal total;

  public PurchaseResponse(Long saleId, BigDecimal total) {
    this.saleId = saleId;
    this.total = total;
  }
}
