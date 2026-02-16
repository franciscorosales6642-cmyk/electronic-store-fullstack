package com.tienda.controller;

import com.tienda.model.Product;
import com.tienda.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductRepository repo;

  public ProductController(ProductRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Product> listActive() {
    return repo.findByActiveTrueOrderByIdAsc();
  }

  @GetMapping("/{id}")
  public Product get(@PathVariable Long id) {
    return repo.findById(id).orElseThrow();
  }

  @PostMapping
  public Product create(@RequestBody @Valid Product p) {
    return repo.save(p);
  }
}
