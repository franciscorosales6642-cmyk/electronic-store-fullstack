BACKEND (Spring Boot)

Requisitos:
- Java 17
- Maven (o IntelliJ con Maven)

Cómo ejecutar (H2):
1) cd backend-springboot
2) mvn spring-boot:run
3) Abrir:
   - Admin Web: http://localhost:8080/admin
   - API products: http://localhost:8080/api/products
   - H2 console: http://localhost:8080/h2
     JDBC URL: jdbc:h2:mem:tienda
     User: sa  Pass: (vacío)

Cómo ejecutar con MySQL:
1) Crea BD: tienda
2) Edita src/main/resources/application-mysql.properties
3) mvn spring-boot:run -Dspring-boot.run.profiles=mysql

Compra de prueba (curl):
curl -X POST http://localhost:8080/api/purchases -H "Content-Type: application/json" -d "{\"customerName\":\"Juan\",\"items\":[{\"productId\":1,\"quantity\":2}]}"

Notificación al admin:
- Al comprar, el backend escribe un log: "✅ NUEVA COMPRA REGISTRADA ..."
