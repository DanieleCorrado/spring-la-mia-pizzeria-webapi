package com.example.springlamiapizzeriacrud.service;

import com.example.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.example.springlamiapizzeriacrud.model.Pizza;
import com.example.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    // Istanzio automaticamente PizzaRepository tramite Autowired
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getPizzaList(Optional<String> search) {
        // Se è stato passato un parametro di ricerca
        if (search.isPresent()) {
            // Lo prendo con il .GET() e lo utilizzo per farmi ritornare una lista filtrata in base al nome
            return pizzaRepository.findByNameContainingIgnoreCase(search.get());
        } else {
            // Altrimenti ritorno lista completa
            return pizzaRepository.findAll();
        }
    }

    public Pizza getPizzaById(Integer id) throws PizzaNotFoundException {

        Optional<Pizza> result = pizzaRepository.findById(id);

        if (result.isPresent()) {

            return result.get();
        } else {

            throw new PizzaNotFoundException("Pizza with ID " + id + ": Not Found");
        }
    }

    public Pizza createPizza(Pizza pizza) throws RuntimeException {
        return pizzaRepository.save(pizza);
    }

    public Pizza editPizza(Pizza pizza) throws PizzaNotFoundException {
        Pizza pizzaToEdit = getPizzaById(pizza.getId());
        pizzaToEdit.setName(pizza.getName());
        pizzaToEdit.setDescription(pizza.getDescription());
        pizzaToEdit.setPhoto(pizza.getPhoto());
        pizzaToEdit.setPrice(pizza.getPrice());
        pizzaToEdit.setIngredients(pizza.getIngredients());

        // Salvo la pizza
        return pizzaRepository.save(pizzaToEdit);
    }

    public void deletePizza(Integer id) {
        pizzaRepository.deleteById(id);
    }
}