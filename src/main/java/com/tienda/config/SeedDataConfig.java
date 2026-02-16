package com.tienda.config;

import com.tienda.model.Product;
import com.tienda.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class SeedDataConfig {

  @Bean
  CommandLineRunner seedProducts(ProductRepository repo) {
    return args -> {
      if (repo.count() > 0) return;

      Product p1 = new Product();
      p1.setName("Audífonos Bluetooth");
      p1.setPrice(new BigDecimal("399.00"));
      p1.setImgUrl("https://via.placeholder.com/300x200?text=Audifonos");
      p1.setStock(50);

      Product p2 = new Product();
      p2.setName("Teclado Mecánico");
      p2.setPrice(new BigDecimal("899.00"));
      p2.setImgUrl("https://via.placeholder.com/300x200?text=Teclado");
      p2.setStock(20);

      Product p3 = new Product();
      p3.setName("Mouse Gamer");
      p3.setPrice(new BigDecimal("299.00"));
      p3.setImgUrl("https://via.placeholder.com/300x200?text=Mouse");
      p3.setStock(35);

      repo.saveAll(List.of(p1, p2, p3));
    };
  }
}
