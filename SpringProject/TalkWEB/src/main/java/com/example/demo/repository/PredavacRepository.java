package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;
import model.Predavac;

public interface PredavacRepository extends JpaRepository<Predavac, Integer>{

	Predavac findByKorisnik(Korisnik korisnik);
	
}
