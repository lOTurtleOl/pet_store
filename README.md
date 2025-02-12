# **PetStore Backend Application**

Welcome to the PetStore Backend Application! This project provides a RESTful API to manage store-related data, supporting operations such as creating, retrieving, updating, and deleting records for pet stores, customers, and employees. It is built using Spring Boot and connects to a MySQL database.

## **Table of Contents**

Introduction

Features

Technologies Used

Setup and Installation

API Endpoints

Contributing

## **Introduction**

This backend application serves as the core data management system for the PetStore project. It handles all CRUD operations for pet store data, including customers and employees.

## **Features**

Create Pet Stores: Add new pet store records to the database.

Retrieve Pet Stores: Fetch details of individual pet stores or a list of all stores.

Update Pet Stores: Modify existing pet store records.

Delete Pet Stores: Remove pet stores from the database.

Manage Customers: Create, retrieve, update, and delete customer records.

Manage Employees: Create, retrieve, update, and delete employee records.

Database Integration: Connects to a MySQL database using JPA for data persistence.

## **Technologies Used**

Java

Spring Boot

MySQL

JPA (Java Persistence API)

## **Setup and Installation**

Clone the repository:

git clone https://github.com/YourUsername/pet_store.git

Navigate to the project directory:

cd pet_store

Configure the database connection:Update the application.yaml file with your MySQL database credentials.

Run the application:

./mvnw spring-boot:run

Note: Ensure MySQL is running and the database schema is set up according to the provided Entity Relationship Diagram (ERD).

## **API Endpoints**

Here are some basic endpoints:

GET /pet_store - Retrieve a list of all pet stores.

GET /pet_store/{petStoreId} - Retrieve details of a specific pet store.

POST /pet_store - Add a new pet store.

PUT /pet_store/{petStoreId} - Update details of an existing pet store.

DELETE /pet_store/{petStoreId} - Delete a pet store from the database.

GET /pet_store/{petStoreId}/customer - Retrieve details of a specific customer.

POST /pet_store/{petStoreId}/customer - Add a new customer.

PUT /pet_store/{petStoreId}/customer/{customerId} - Update details of an existing customer.

DELETE /pet_store/{petStoreId}/customer/{customerId} - Delete a customer from the database.

GET /pet_store/{petStoreId}/employee - Retrieve a list of all employees.

GET /pet_store/{petStoreId}/employee/{employeeId} - Retrieve details of a specific employee.

POST /pet_store/{petStoreId}/employee/{employeeId} - Add a new employee.

PUT /pet_store/{petStoreId}/employee/{employeeId} - Update details of an existing employee.

DELETE /pet_store/{petStoreId}/employee/{employeeId} - Delete an employee from the database.

## **Contributing**

Contributions are welcome! Feel free to fork the repository and submit a pull request with your improvements.
