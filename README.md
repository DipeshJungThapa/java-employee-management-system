# Java Employee Management System

This project is a simple Employee Management System built using Java. It allows you to manage employee records efficiently.

## Requirements

- Java 11 or higher
- A local database (MySQL recommended)

## Setting Up a Local Database

To use the Employee Management System, you need to create a local database. Below is the SQL code to set up the necessary database and table.

### SQL Code

```sql
CREATE DATABASE employee_management;

USE employee_management;

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    hire_date DATE NOT NULL
);
