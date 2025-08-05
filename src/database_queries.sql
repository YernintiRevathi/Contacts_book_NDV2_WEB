CREATE DATABASE contact_book;

-- Use that database
USE  contact_book;

Create the contacts table
CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    address VARCHAR(255)
);