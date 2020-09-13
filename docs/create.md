# Create

Used to create a new account

**URL**: `/account`

**Method**: `POST`

**Data constraints**:

```json
{
  "name": "[account's user name]",
  "currency": "[valid currency]",
  "balance": "[initial account balance]",
  "treasury": "[true or false according to the account's properties]"
}
```

## Success Response

**Code**: `200 OK`

**Content example**:

```json
{
    "id": 1,
    "name": "test1",
    "currency": {
        "context": {
            "empty": false,
            "providerName": "java.util.Currency"
        },
        "defaultFractionDigits": 2,
        "currencyCode": "USD",
        "numericCode": 840
    },
    "balance": {
        "currency": {
            "context": {
                "empty": false,
                "providerName": "java.util.Currency"
            },
            "defaultFractionDigits": 2,
            "currencyCode": "USD",
            "numericCode": 840
        },
        "number": 5,
        "factory": {
            "defaultMonetaryContext": {
                "precision": 0,
                "amountType": "org.javamoney.moneta.Money",
                "maxScale": 63,
                "fixedScale": false,
                "empty": false,
                "providerName": null
            },
            "amountType": "org.javamoney.moneta.Money",
            "minNumber": null,
            "maxNumber": null,
            "maximalMonetaryContext": {
                "precision": 0,
                "amountType": "org.javamoney.moneta.Money",
                "maxScale": -1,
                "fixedScale": false,
                "empty": false,
                "providerName": null
            }
        },
        "context": {
            "precision": 256,
            "amountType": "org.javamoney.moneta.Money",
            "maxScale": -1,
            "fixedScale": false,
            "empty": false,
            "providerName": null
        },
        "negative": false,
        "zero": false,
        "positive": true,
        "positiveOrZero": true,
        "negativeOrZero": false,
        "numberStripped": 5
    },
    "treasury": false
}
```

## Error Response

**Condition**: If some field is null

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Treasury can not be null"
}
```

**Condition**: If balance is negative and account is not treasury

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Only treasury accounts can have a negative balance"
}
```
