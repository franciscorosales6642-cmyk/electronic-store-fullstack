package com.tienda.service;

import com.tienda.model.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AdminNotifier {
  private static final Logger log = LoggerFactory.getLogger(AdminNotifier.class);

  public void notifyNewSale(Sale sale) {
    // "Notificación" para el admin (cumple práctica): se registra en logs.
    // Puedes cambiarlo luego por email o websocket.
    log.info("✅ NUEVA COMPRA REGISTRADA -> saleId={}, total={}, cliente={}, items={}",
        sale.getId(),
        sale.getTotal(),
        sale.getCustomerName(),
        sale.getItems() == null ? 0 : sale.getItems().size()
    );
  }
}
