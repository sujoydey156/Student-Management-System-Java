# Student Management System

A console-based Student Management System developed using **Java, JDBC, and Oracle Database**.

The application allows users to perform complete CRUD operations on student records through a simple menu-driven interface.

## 📌 Features

- Add a new student
- View all students
- Search student by ID
- Update student details
- Delete student
- Menu-driven console interface
- Oracle Database connectivity using JDBC
- Uses `PreparedStatement` for SQL operations

## 🛠️ Technologies Used

- Java
- JDBC
- Oracle Database
- SQL
- Oracle JDBC Driver

## 🗄️ Database Structure

Create the following table in Oracle:

```sql
CREATE TABLE student (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    marks NUMBER
);
