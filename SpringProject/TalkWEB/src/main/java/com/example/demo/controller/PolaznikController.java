package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.KursRepository;
import com.example.demo.repository.PredavacRepository;

import model.Kategorija;
import model.Korisnik;
import model.Kur;
import model.Predavac;

@Controller
@RequestMapping(value="/polaznik")
public class PolaznikController {
	
	@Autowired
	KursRepository kursRepo;
	
	@Autowired
	PredavacRepository predavacRepo;
	
	@RequestMapping(value="/sviKursevi", method=RequestMethod.GET)
	@ResponseBody
	public List<Kur> sviKursevi(){
		return kursRepo.findAll();
	}
	
	@RequestMapping(value="/prikazKursa", method=RequestMethod.GET)
	@ResponseBody
	public Kur prikazKursa(@RequestParam("idKursa") int id) {
		return kursRepo.getById(id);
	}

	@GetMapping(value="/pronadjiPredavaca/{idKurs}")
	@ResponseBody
	public Korisnik pronadjiPredavaca(@PathVariable Integer idKurs) {
		Predavac p = kursRepo.getById(idKurs).getPredavac();
		return p.getKorisnik();
	}
	
	@GetMapping(value="/pronadjiKategoriju/{idKurs}")
	@ResponseBody
	public Kategorija pronadjiKategoriju(@PathVariable Integer idKurs) {
		Kur k = kursRepo.getById(idKurs);
		return k.getKategorija();
	}
	
	@GetMapping(value="/pronadjiKursPoNazivu/{nazivKursa}")
	@ResponseBody
	public Kur pronadjiKursPoNazivu(@PathVariable String nazivKursa) {
		Kur k = kursRepo.findByNaziv(nazivKursa);
		return k;
	}
}
