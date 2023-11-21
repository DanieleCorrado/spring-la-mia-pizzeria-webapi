package com.example.springlamiapizzeriacrud.repository;


import com.example.springlamiapizzeriacrud.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
