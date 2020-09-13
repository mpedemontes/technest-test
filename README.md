# Technest tecnical test

This REST API allows users to create, read, update and delete accounts and to transfer money between them.

## Endpoints

* [Create](docs/create.md): `POST`
* [Find all accounts](docs/findall.md): `GET/account`
* [Find an account by its ID](docs/findbyid.md): `GET/account/<id>`
* [Update an account](docs/updateaccount.md): `PUT/account/<id>`
* [Update an account currency](docs/updatecurrency.md): `PUT/account/<id>/currency/<currency>`
* [Delete an account](docs/delete.md): `DELETE/account/<id>`
* [Transfer money from one account to another](docs/transfer.md): `GET/account/<sender_id>/transfer/<receiver_id>/amount`

## H2 Console
H2 database console can be accessed using the following credentials:
* User name: sa
* Password: password