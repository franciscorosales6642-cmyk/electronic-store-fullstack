package com.tienda.controller;

import com.tienda.model.Product;
import com.tienda.repository.ProductRepository;
import com.tienda.repository.SaleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

  private final ProductRepository productRepo;
  private final SaleRepository saleRepo;

  public AdminWebController(ProductRepository productRepo, SaleRepository saleRepo) {
    this.productRepo = productRepo;
    this.saleRepo = saleRepo;
  }

  @GetMapping
  public String home(Model model) {
    model.addAttribute("productCount", productRepo.count());
    model.addAttribute("saleCount", saleRepo.count());
    model.addAttribute("latestSales", saleRepo.findAll().stream().limit(10).toList());
    return "admin/index";
  }

  @GetMapping("/products")
  public String products(Model model) {
    model.addAttribute("products", productRepo.findAll());
    model.addAttribute("productForm", new Product());
    return "admin/products";
  }

  @PostMapping("/products")
  public String createProduct(
      @RequestParam String name,
      @RequestParam String price,
      @RequestParam(required = false) String imgUrl,
      @RequestParam(defaultValue = "0") Integer stock,
      @RequestParam(defaultValue = "true") Boolean active
  ) {
    Product p = new Product();
    p.setName(name);
    p.setPrice(new BigDecimal(price));
    p.setImgUrl(imgUrl);
    p.setStock(stock);
    p.setActive(active);
    productRepo.save(p);
    return "redirect:/admin/products";
  }

  @GetMapping("/sales")
  public String sales(Model model) {
    model.addAttribute("sales", saleRepo.findAll());
    return "admin/sales";
  }
}
