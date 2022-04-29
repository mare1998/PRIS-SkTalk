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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KursRepository;
import com.example.demo.repository.PolaznikRepository;
import com.example.demo.repository.PredavacRepository;

import model.Kategorija;
import model.Korisnik;
import model.Kur;
import model.Lekcija;
import model.Polaznik;
import model.Predavac;

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

	
	@RequestMapping(value="/sviKursevi", method=RequestMethod.GET)
	public ResponseEntity<List<Kur>> sviKursevi(){
		return new ResponseEntity<List<Kur>>(kursRepo.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/prikazKursa", method=RequestMethod.GET)
	public ResponseEntity<Kur> prikazKursa(@RequestParam("idKursa") int id) {
		return new ResponseEntity<Kur>(kursRepo.getById(id),HttpStatus.OK);
	}

	@GetMapping(value="/pronadjiPredavaca/{idKurs}")
	public ResponseEntity<Korisnik> pronadjiPredavaca(@PathVariable Integer idKurs) {
		Predavac p = kursRepo.getById(idKurs).getPredavac();
		return new ResponseEntity<Korisnik>(p.getKorisnik(), HttpStatus.OK);
	}
	
	@GetMapping(value="/pronadjiKategoriju/{idKurs}")
	public ResponseEntity<Kategorija> pronadjiKategoriju(@PathVariable Integer idKurs) {
		Kur k = kursRepo.getById(idKurs);
		return new ResponseEntity<Kategorija>(k.getKategorija(), HttpStatus.OK);
	}
	
	@GetMapping(value="/pronadjiKursPoNazivu/{nazivKursa}")
	public ResponseEntity<Kur> pronadjiKursPoNazivu(@PathVariable String nazivKursa) {
		Kur k = kursRepo.findByNaziv(nazivKursa);
		return new ResponseEntity<Kur>(k, HttpStatus.OK);
	}
	
	@GetMapping(value="/kurseviZaKategoriju/{idKursa}")
	public ResponseEntity<List<Kur>> kurseviZaKategoriju(@PathVariable Integer id){
		Kategorija k = kategorijaRepo.getById(id);
		List<Kur> kursevi = kursRepo.findByKategorija(k);
		return new ResponseEntity<List<Kur>>(kursevi, HttpStatus.OK);
	}
	
	@RequestMapping(value="/prijavaNaKurs", method=RequestMethod.POST)
	public ResponseEntity<Boolean> prijavaNaKurs(@RequestParam(name="idKorisnik") Integer idKorisnik, @RequestParam(name="idKurs") Integer idKurs){
		Polaznik polaznik = polaznikRepo.getById(idKorisnik);
		Kur kurs = kursRepo.getById(idKurs);
		boolean dodatKurs = polaznik.getKurs().add(kurs);
		boolean dodatPolaznik = kurs.getPolazniks().add(polaznik);
		if(dodatKurs && dodatPolaznik) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@GetMapping(value="/pronadjiLekcijeKursa/{nazivKursa}")
	public ResponseEntity<List<Lekcija>> pronadjiLekcijeKursa(@PathVariable String nazivKursa) {
		Kur kurs = kursRepo.findByNaziv(nazivKursa);
		return new ResponseEntity<List<Lekcija>>(kurs.getLekcijas(), HttpStatus.OK);
	}
}
