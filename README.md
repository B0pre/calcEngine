# Expression Calculator v.0.0.1

---

## parameters for in memory h2

    * spring.datasource.url=jdbc:h2:mem:testdb
    * spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    * spring.datasource.driverClassName=org.h2.Driver
    * spring.datasource.username=sa
    * spring.datasource.password=password

---
launch example:
---

    java -jar calcEngine.jar

---
curl example requests:
---

* curl -X POST 'http://localhost:8080/expr' -H 'Content-Type:application/json' -d "{\\"expression\\":\\"2 + 2\\"}"
* curl -X POST 'http://localhost:8080/expr' -H 'Content-Type:application/json' -d "{\\"expression\\":\\"a = 10\\"}"
* curl -X POST 'http://localhost:8080/expr' -H 'Content-Type:application/json' -d "{\\"expression\\":\\"a\\"}"

---
documentation url:
---
http://localhost:8080/swagger-ui.html
