# Rest_App_Project
To run the server we can use varios programs as postman,or CURL,Paw etc.

## Postman requests
- GET /api/books: Retrieve all books.
- GET /api/books/{id}: Retrieve a book by its ID.
- POST /api/books: Create a new book.
- PUT /api/books/{id}: Update an existing book by its ID.
- DELETE /api/books/{id}: Delete a book by its ID.
## CURL requests in terminal
**Create a book (POST request):**
   
curl https://api.github.com/repos/{owner}/{repo}/readme -H "Accept: application/vnd.github.v3+json"
