
# API CONTROL DE PRECIO

Gestionamos precios para distintas empresas : D




## Usage/Examples

Guardar Precio:
```javascript
curl --location 'http://localhost:8080/v1/price-control-center' \
--header 'Content-Type: application/json' \
--data '{
    "brand_id": 2,
   "start_date": "2020-06-14T12:30:00",
    "end_date": "2020-06-14T20:30:00",
    "price_list": 5,
    "product_id": 35455,
    "priority": 1,
    "price": 100.00,
    "currency": "EUR"
}'
```

Consultar Precio:
tips-fecha-formato: "2020-06-14T12:30:00"

```javascript
curl --location 'http://localhost:8080/v1/price-control-center?application_date=2020-06-14T15%3A59%3A59&product_id=35455&brand_id=1'
```






## Installation

Clona este repositorio en tu máquina local:

```bash
git clone https://github.com/ruben-furlan/price-control-center.git
```
Navega al directorio raíz del proyecto:    

```bash
cd price-control-center
```

Compila y ejecuta la aplicación usando Mave

```bash
mvn spring-boot:run
```

Test unitarios
```bash
cd price-control-center
```

```bash
mvn test
```

Test de integracion 
```bash
cd price-control-center
```

```bash
mvn failsafe:integration-test
```