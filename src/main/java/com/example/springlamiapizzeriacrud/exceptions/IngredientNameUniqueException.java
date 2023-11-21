package com.example.springlamiapizzeriacrud.exceptions;

public class IngredientNameUniqueException extends RuntimeException {
    public IngredientNameUniqueException(String message) {
        super(message);
    }
}

