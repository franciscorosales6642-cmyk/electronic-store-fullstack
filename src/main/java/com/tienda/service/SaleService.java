package com.tienda.service;

import com.tienda.dto.PurchaseRequest;
import com.tienda.model.Product;
import com.tienda.model.Sale;
import com.tienda.model.SaleItem;
import com.tienda.repository.ProductRepository;
import com.tienda.repository.SaleRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class SaleService {

  private final ProductRepository productRepo;
  private final SaleRepository saleRepo;
  private final AdminNotifier adminNotifier;

  public SaleService(ProductRepository productRepo, SaleRepository saleRepo, AdminNotifier adminNotifier) {
    this.productRepo = productRepo;
    this.saleRepo = saleRepo;
    this.adminNotifier = adminNotifier;
  }

  @Transactional
  public Sale createPurchase(@Valid PurchaseRequest req) {
    Sale sale = new Sale();
    sale.setCustomerName(req.customerName == null || req.customerName.isBlank() ? "Cliente" : req.customerName);

    BigDecimal total = BigDecimal.ZERO;

    for (PurchaseRequest.Item it : req.items) {
      if (it == null || it.productId == null) {
        throw new IllegalArgumentException("Item inválido.");
      }

      Product p = productRepo.findById(it.productId)
          .orElseThrow(() -> new IllegalArgumentException("Producto no existe: " + it.productId));

      int qty = (it.quantity == null || it.quantity <= 0) ? 1 : it.quantity;

      BigDecimal lineTotal = p.getPrice().multiply(BigDecimal.valueOf(qty));

      SaleItem si = new SaleItem();
      si.setSale(sale);
      si.setProduct(p);
      si.setQuantity(qty);
      si.setUnitPrice(p.getPrice());
      si.setLineTotal(lineTotal);

      sale.getItems().add(si);
      total = total.add(lineTotal);
    }

    sale.setTotal(total);

    Sale saved = saleRepo.save(sale);

    // Notificar al administrador
    adminNotifier.notifyNewSale(saved);

    return saved;
  }
}
