# Transfer money

Used to transfer money from an account to another one

**URL**: `/account/<sender_id>/transfer/<receiver_id>/<amount>`

**URL Parameters**: 
* `sender_id=[Integer]` where `sender_id` is the ID of the sender account on the server
* `receiver_id=[Integer]` where `receiver_id` is the ID of the receiver account on the server
* `amount=[BigDecimal]` where `amount` is the amount is wanted to be transferred
 
**Request Parameters**: `currency=[String]` where `currency`is the currency is wanted to be used upon transferring the money. If no currency is specified, the sender account's currency is taken by default

**Method**: `GET`

## Success Response

**Condition**: If both accounts exists and given data is valid

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
        "numberStripped": 5E+1
    },
    "treasury": false
}
```

## Error Response

**Condition**: If `sender_id` or `receiver_id` is null

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Id can not be null"
}
```

**Condition**: If `amount` is null

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Amount to transfer can not be null"
}
```

**Condition**: If `amount` is lower than or equal to 0

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Amount to transfer must be greater than 0"
}
```

**Condition**: If `sender_id` and `receiver_id` have the same value

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Sender and receiver must be different accounts"
}
```

**Condition**: If account with given `sender_id` does not exist

**Code**: `404 NOT FOUND`

**Content**: 

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Account with id <sender_id> not found"
}
```

**Condition**: If account with given `receiver_id` does not exist

**Code**: `404 NOT FOUND`

**Content**: 

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Account with id <receiver_id> not found"
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

**Condition**: If sender account does not have enough funds

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Account with id <sender_id> does not have enough funds. Only treasury accounts can have a negative balance."
}
```