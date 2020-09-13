# Delete

Used to delete an account with a given id

**URL**: `/account/<id>`

**URL Parameters**: `id=[Integer]` where `id` is the ID of the account on the server
 
**Method**: `DELETE`

## Success Response

**Condition**: If account exists

**Code**: `200 OK`

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
