package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer>{
	
	@Query("select k from Kategorija k where k.naziv = :naziv")
	Kategorija findKategorija(@Param("naziv") String naziv);
	
}
