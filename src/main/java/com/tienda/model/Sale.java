package com.tienda.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Instant createdAt = Instant.now();

  private String customerName;

  @Column(precision = 12, scale = 2)
  private BigDecimal total;

  private String status = "CREATED";

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SaleItem> items = new ArrayList<>();

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

  public String getCustomerName() { return customerName; }
  public void setCustomerName(String customerName) { this.customerName = customerName; }

  public BigDecimal getTotal() { return total; }
  public void setTotal(BigDecimal total) { this.total = total; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public List<SaleItem> getItems() { return items; }
  public void setItems(List<SaleItem> items) { this.items = items; }
}
