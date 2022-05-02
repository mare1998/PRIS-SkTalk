package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KursRepository;

import model.Kategorija;
import model.Kur;

@Controller
@RequestMapping(value="/posetilac")
public class PosetilacController {
	
	@Autowired
	KursRepository kursRepo;
	
	@Autowired
	KategorijaRepository kategorijaRepo;
	
	@RequestMapping(value="/sviKursevi", method=RequestMethod.GET)
	public ResponseEntity<List<Kur>> sviKursevi(){
		return new ResponseEntity<List<Kur>>(kursRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value="/pronadjiKursPoNazivu/{nazivKursa}")
	public ResponseEntity<Kur> pronadjiKursPoNazivu(@PathVariable String nazivKursa) {
		Kur k = kursRepo.findByNaziv(nazivKursa);
		return new ResponseEntity<Kur>(k, HttpStatus.OK);
	}
	
	@GetMapping(value="/kurseviZaKategoriju/{idKategorija}")
	public ResponseEntity<List<Kur>> kurseviZaKategoriju(@PathVariable Integer id){
		Kategorija k = kategorijaRepo.getById(id);
		List<Kur> kursevi = kursRepo.findByKategorija(k);
		return new ResponseEntity<List<Kur>>(kursevi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sveKategorije", method = RequestMethod.GET)
	public ResponseEntity<List<Kategorija>> sveKategorije() {
		return new ResponseEntity<List<Kategorija>>(kategorijaRepo.findAll(), HttpStatus.OK);
	}
}
