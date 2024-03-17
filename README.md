
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
curl --location --request GET 'http://localhost:8080/v1/price-control-center?application_date=2020-06-14T15%3A59%3A59&product_id=35455&brand_id=1' \
--header 'Content-Type: application/json' \
--data '{
    "brand_id": 1,
   "start_date": "2024-03-16T10:30:00",
    "end_date": "2024-03-26T10:30:00",
    "price_list": -1,
    "product_id": 2,
    "priority": 4,
    "price": 33.40
    
}'
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


Asegurate de tener la jdk de java21
https://www.oracle.com/java/technologies/downloads/#jdk21-linux

