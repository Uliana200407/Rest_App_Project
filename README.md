# Rest_App_Project
To run the server we can use varios programs as Postman, CURL, Paw, Insomnia etc.

## Postman requests
- GET /api/books: Retrieve all books.
- GET /api/books/{id}: Retrieve a book by its ID.
- POST /api/books: Create a new book.
- PUT /api/books/{id}: Update an existing book by its ID.
- DELETE /api/books/{id}: Delete a book by its ID.
## CURL requests in terminal
**Create a book (_POST_ request):**
```
curl -X POST -H "Content-Type: application/json" -d '{"title":"New Book","author":"New Name","isbn":"1234567890"}' http://localhost:8080/api/books
```
**Get a book by ID (_GET_ request):**
```
curl http://localhost:8080/api/books/{id}
```
**Update a book (_PUT_ request):**
```
curl -X PUT -H "Content-Type: application/json" -d '{"title":"Updated Book ","author":"Updated Name","isbn":"1234567890"}' http://localhost:8080/api/books/{id}
```
**Delete a book (_DELETE_ request):**
```
curl -X DELETE http://localhost:8080/api/books/{id}
```
