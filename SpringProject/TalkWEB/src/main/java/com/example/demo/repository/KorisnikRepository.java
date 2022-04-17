package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{
	
	Korisnik findByUsername(String username);

	@Query("SELECT k FROM Korisnik k INNER JOIN Predavac p ON k.idKorisnik = p.korisnik_idKorisnik")
	List<Korisnik> findPredavace();
}
