# Update an account

Used to update an account information with a given id

**URL**: `/account/<id>`

**URL Parameters**: `id=[Integer]` where `id` is the ID of the account on the server
 
**Method**: `PUT`

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

**Condition**: If account exists and given data is valid

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

**Condition**: If some field is null

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Name can not be null"
}
```

**Condition**: If treasury property is modified

**Code**: `403 FORBIDDEN`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Treasury property can not be modified"
}
```

**Condition**: If new balance is negative and account is not treasury

**Code**: `400 BAD REQUEST`

**Content**:

```json
{
    "cause": null,
    "stackTrace": "[...]",
    "message": "Only treasury accounts can have a negative balance"
}
```