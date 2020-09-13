# Find an account by id

Used to show an account information with a given id

**URL**: `/account/<id>`

**URL Parameters**: `id=[Integer]` where `id` is the ID of the account on the server
 
**Method**: `GET`

## Success Response

**Condition**: If account exists

**Code**: `200 OK`

**Content example**:

```json
{
    "id": 1,
    "name": "test2",
    "currency": {
        "context": {
            "providerName": "java.util.Currency",
            "empty": false
        },
        "numericCode": 978,
        "defaultFractionDigits": 2,
        "currencyCode": "EUR"
    },
    "balance": {
        "currency": {
            "context": {
                "providerName": "java.util.Currency",
                "empty": false
            },
            "numericCode": 978,
            "defaultFractionDigits": 2,
            "currencyCode": "EUR"
        },
        "number": 50,
        "positiveOrZero": true,
        "negativeOrZero": false,
        "numberStripped": 5E+1,
        "factory": {
            "defaultMonetaryContext": {
                "precision": 0,
                "maxScale": 63,
                "amountType": "org.javamoney.moneta.Money",
                "fixedScale": false,
                "providerName": null,
                "empty": false
            },
            "maxNumber": null,
            "minNumber": null,
            "amountType": "org.javamoney.moneta.Money",
            "maximalMonetaryContext": {
                "precision": 0,
                "maxScale": -1,
                "amountType": "org.javamoney.moneta.Money",
                "fixedScale": false,
                "providerName": null,
                "empty": false
            }
        },
        "context": {
            "precision": 256,
            "maxScale": -1,
            "amountType": "org.javamoney.moneta.Money",
            "fixedScale": false,
            "providerName": null,
            "empty": false
        },
        "negative": false,
        "zero": false,
        "positive": true
    },
    "treasury": true
}
```

## Error Response

**Condition**: If `id` is null

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Id can not be null"
}
```

**Condition**: If account with given `id` does not exist

**Code**: `404 NOT FOUND`

**Content**: 

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Account with id <id> not found"
}
```