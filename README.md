# Technest tecnical test

This REST API allows users to create, read, update and delete accounts and to transfer money between them.

## Endpoints

* [Create](docs/create-account.md): `POST`
* [Find all accounts](docs/find-all.md): `GET/account`
* [Find an account by its ID](docs/find-by-id.md): `GET/account/<id>`
* [Update an account](docs/update-account.md): `PUT/account/<id>`
* [Update an account currency](docs/update-currency.md): `PUT/account/<id>/currency/<currency>`
* [Delete an account](docs/delete-account.md): `DELETE/account/<id>`
* [Transfer money from one account to another](docs/transfer-money.md): `GET/account/<sender_id>/transfer/<receiver_id>/amount`

## H2 Console
H2 database console can be accessed using the following credentials:
* User name: sa
* Password: password