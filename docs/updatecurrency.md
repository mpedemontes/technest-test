# Update an account

Used to update an account currency (and change its balance) with a given id

**URL**: `/account/<id>/currency/<currency>`

**URL Parameters**: 
* `id=[Integer]` where `id` is the ID of the account on the server
* `currency=[String]` where `currency` is the currency which is wanted to be set
 
**Method**: `PUT`

## Success Response

**Condition**: If account exists and given currency is valid

**Code**: `200 OK`

**Content example**:

```json
{
    "id": 1,
    "name": "newName",
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
        "number": 59.269915,
        "factory": {
            "defaultMonetaryContext": {
                "precision": 0,
                "maxScale": 63,
                "fixedScale": false,
                "amountType": "org.javamoney.moneta.Money",
                "empty": false,
                "providerName": null
            },
            "amountType": "org.javamoney.moneta.Money",
            "maxNumber": null,
            "minNumber": null,
            "maximalMonetaryContext": {
                "precision": 0,
                "maxScale": -1,
                "fixedScale": false,
                "amountType": "org.javamoney.moneta.Money",
                "empty": false,
                "providerName": null
            }
        },
        "context": {
            "precision": 256,
            "maxScale": -1,
            "fixedScale": false,
            "amountType": "org.javamoney.moneta.Money",
            "empty": false,
            "providerName": null
        },
        "negative": false,
        "zero": false,
        "positive": true,
        "positiveOrZero": true,
        "negativeOrZero": false,
        "numberStripped": 59.269915
    },
    "treasury": false
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

**Condition**: If `currency` is null

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Currency can not be null"
}
```

**Condition** If account with given `id` does not exist

**Code**: `404 NOT FOUND`

**Content**: 

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Account with id <id> not found"
}
```

**Condition**: If given `currency` does not exist

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Unknown currency code: <currency>"
}
```
