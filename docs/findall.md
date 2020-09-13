# Find all accounts

Used to list all accounts contained in database

**URL**: `/account`

**Method**: `GET`

## Success Response

**Code**: `200 OK`

**Content example**:

```json
[
    {
        "id": 1,
        "name": "test2",
        "currency": {
            "context": {
                "empty": false,
                "providerName": "java.util.Currency"
            },
            "defaultFractionDigits": 2,
            "currencyCode": "EUR",
            "numericCode": 978
        },
        "balance": {
            "currency": {
                "context": {
                    "empty": false,
                    "providerName": "java.util.Currency"
                },
                "defaultFractionDigits": 2,
                "currencyCode": "EUR",
                "numericCode": 978
            },
            "number": 50,
            "factory": {
                "defaultMonetaryContext": {
                    "precision": 0,
                    "amountType": "org.javamoney.moneta.Money",
                    "fixedScale": false,
                    "maxScale": 63,
                    "empty": false,
                    "providerName": null
                },
                "amountType": "org.javamoney.moneta.Money",
                "maxNumber": null,
                "minNumber": null,
                "maximalMonetaryContext": {
                    "precision": 0,
                    "amountType": "org.javamoney.moneta.Money",
                    "fixedScale": false,
                    "maxScale": -1,
                    "empty": false,
                    "providerName": null
                }
            },
            "context": {
                "precision": 256,
                "amountType": "org.javamoney.moneta.Money",
                "fixedScale": false,
                "maxScale": -1,
                "empty": false,
                "providerName": null
            },
            "negative": false,
            "zero": false,
            "positive": true,
            "positiveOrZero": true,
            "negativeOrZero": false,
            "numberStripped": 5E+1
        },
        "treasury": true
    },
    {
        "id": 2,
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
            "number": -5,
            "factory": {
                "defaultMonetaryContext": {
                    "precision": 0,
                    "amountType": "org.javamoney.moneta.Money",
                    "fixedScale": false,
                    "maxScale": 63,
                    "empty": false,
                    "providerName": null
                },
                "amountType": "org.javamoney.moneta.Money",
                "maxNumber": null,
                "minNumber": null,
                "maximalMonetaryContext": {
                    "precision": 0,
                    "amountType": "org.javamoney.moneta.Money",
                    "fixedScale": false,
                    "maxScale": -1,
                    "empty": false,
                    "providerName": null
                }
            },
            "context": {
                "precision": 256,
                "amountType": "org.javamoney.moneta.Money",
                "fixedScale": false,
                "maxScale": -1,
                "empty": false,
                "providerName": null
            },
            "negative": true,
            "zero": false,
            "positive": false,
            "positiveOrZero": false,
            "negativeOrZero": true,
            "numberStripped": -5
        },
        "treasury": true
    }
]
```