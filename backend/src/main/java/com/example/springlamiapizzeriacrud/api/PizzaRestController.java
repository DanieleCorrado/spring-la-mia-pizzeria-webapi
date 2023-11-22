package com.example.springlamiapizzeriacrud.api;

import com.example.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.example.springlamiapizzeriacrud.model.Pizza;
import com.example.springlamiapizzeriacrud.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    // Endpoint per la lista di tutte le pizze
    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> search) {

        return pizzaService.getPizzaList(search);
    }

    // Endpoint per visualizzare i dettagli di una singola pizza
    @GetMapping("/{id}")
    public Pizza details(@PathVariable Integer id) {

        try {
            return pizzaService.getPizzaById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // Endpoin per la creazione di una nuova pizza
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {

        try {
            return pizzaService.createPizza(pizza);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    // Endpoint per modificare una pizza
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza){

        pizza.setId(id);

        try {
            return pizzaService.editPizza(pizza);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // Endpoint per l'eliminazione di una pizza
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Pizza pizzaToDelete = pizzaService.getPizzaById(id);
            pizzaService.deletePizza(pizzaToDelete.getId());
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
