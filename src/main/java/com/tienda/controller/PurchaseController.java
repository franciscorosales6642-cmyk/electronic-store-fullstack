package com.tienda.controller;

import com.tienda.dto.PurchaseRequest;
import com.tienda.dto.PurchaseResponse;
import com.tienda.model.Sale;
import com.tienda.repository.SaleRepository;
import com.tienda.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {

  private final SaleService saleService;
  private final SaleRepository saleRepo;

  public PurchaseController(SaleService saleService, SaleRepository saleRepo) {
    this.saleService = saleService;
    this.saleRepo = saleRepo;
  }

  // Realizar compra (carrito)
  @PostMapping("/purchases")
  public PurchaseResponse purchase(@RequestBody @Valid PurchaseRequest req) {
    Sale s = saleService.createPurchase(req);
    return new PurchaseResponse(s.getId(), s.getTotal());
  }

  // Consultar ventas (admin)
  @GetMapping("/sales")
  public List<Sale> sales() {
    return saleRepo.findAll();
  }

  @GetMapping("/sales/{id}")
  public Sale saleDetail(@PathVariable Long id) {
    return saleRepo.findById(id).orElseThrow();
  }
}
