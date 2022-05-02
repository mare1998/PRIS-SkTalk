package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Kategorija;
import model.Kur;

public interface KursRepository extends JpaRepository<Kur, Integer>{
	
	public Kur findByNaziv(String nazivKursa);
	
	public List<Kur> findByKategorija(Kategorija k);

}
