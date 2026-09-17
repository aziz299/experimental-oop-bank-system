# Experimental Java & Python OOP Bank System

An experimental CLI banking application originally prototyped in Python and migrated to Java to demonstrate core Object-Oriented Programming (OOP) concepts, optimized control flow, and clean architecture.

## Features

- **Account Management**: Dynamic account creation with duplicate username prevention using Java Stream API.
- **User Authentication**: Strict credential lookup separating username checks from password validation.
- **Banking Operations**: Deposit and withdrawal transactions with balance validation.
- **Executive Access**: Dedicated administrative system secured by a 12-value key verification mechanism (sum equals 12).
- **Control Flow**: Clean menu navigation built using Java `switch` statements.

## OOP Concepts Demonstrated

- **Abstraction**: Base `user` abstract class defining shared account state and validation interfaces.
- **Inheritance**: `BankAccount` and `ExecutiveAccount` extending base class attributes.
- **Encapsulation**: Restricted attribute scope utilizing protected properties and access methods.

## Project Structure

```text
src/
└── useres/
    ├── user.java             # Abstract base class
    ├── BankAccount.java      # Standard banking operations
    └── ExecutiveAccount.java # Executive security logic
Main.java                     # Console application entry point
