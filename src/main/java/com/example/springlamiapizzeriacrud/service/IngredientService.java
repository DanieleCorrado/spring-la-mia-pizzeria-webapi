package com.example.springlamiapizzeriacrud.service;

import com.example.springlamiapizzeriacrud.exceptions.IngredientNameUniqueException;
import com.example.springlamiapizzeriacrud.model.Ingredient;
import com.example.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findByOrderByName();
    }

    public Ingredient save(Ingredient ingredient) throws IngredientNameUniqueException {
        // verifico che questo nome non esista già
        if (ingredientRepository.existsByName(ingredient.getName())) {
            throw new IngredientNameUniqueException(ingredient.getName());
        }
        // trasformo il nome in lowercase
        ingredient.setName(ingredient.getName().toLowerCase());
        // salvo su database
        return ingredientRepository.save(ingredient);
    }
}