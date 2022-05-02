package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KursRepository;
import com.example.demo.repository.PolaznikRepository;
import com.example.demo.repository.PredavacRepository;

import model.Kur;
import model.Lekcija;
import model.Polaznik;

@Controller
@RequestMapping(value="/polaznik")
public class PolaznikController {
	
	@Autowired
	KursRepository kursRepo;
	
	@Autowired
	PredavacRepository predavacRepo;
	
	@Autowired
	KategorijaRepository kategorijaRepo;
	
	@Autowired
	PolaznikRepository polaznikRepo;
		
	@PostMapping(value="/prijavaNaKurs")
	public ResponseEntity<Boolean> prijavaNaKurs(@RequestParam(name="idKorisnik") Integer idKorisnik, @RequestParam(name="idKurs") Integer idKurs){
		Polaznik polaznik = polaznikRepo.getById(idKorisnik);
		Kur kurs = kursRepo.getById(idKurs);
		polaznik.getKurs().add(kurs);
		kurs.getPolazniks().add(polaznik);
		polaznikRepo.save(polaznik);
		kursRepo.save(kurs);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@GetMapping(value="/pronadjiLekcijeKursa/{idKurs}")
	public ResponseEntity<List<Lekcija>> pronadjiLekcijeKursa(@PathVariable int idKurs) {
		Kur kurs = kursRepo.getById(idKurs);
		return new ResponseEntity<List<Lekcija>>(kurs.getLekcijas(), HttpStatus.OK);
	}
}
