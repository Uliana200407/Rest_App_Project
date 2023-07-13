USE book_data_base;
CREATE TABLE books (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR
      (255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  publication_year INT NOT NULL,
  genre VARCHAR(255) NOT NULL
);
