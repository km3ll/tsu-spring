# learn-h2

## h2-console

URL: `localhost:8080/h2-console`

## commands

Create

```bash
curl --location --request POST 'localhost:8080/api/customer' \
--header 'Content-Type: application/json' \
--data '{
    "id": 2200,
    "name": "John Doe"
}'
```

Update

```bash
curl --location --request PUT 'localhost:8080/api/customer' \
--header 'Content-Type: application/json' \
--data '{
    "id": 2200,
    "name": "John Doe"
}'
```

FindAll

```bash
curl --location --request GET 'localhost:8080/api/customer' \
--header 'Accept: application/json'
```

FindById

```bash
curl --location --request GET 'localhost:8080/api/customer/2200' \
--header 'Accept: application/json'
```

DeleteById

```bash
curl --location --request DELETE 'localhost:8080/api/customer/2200' \
--header 'Accept: application/json'
```