package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KursRepository;
import com.example.demo.repository.LekcijaRepository;

import model.Lekcija;

@Controller
@RequestMapping(value="/predavac")
public class PredavacController {
	
	@Autowired
	LekcijaRepository lekcijaRepo;
	
	@Autowired
	KursRepository kursRepo;
	
	@RequestMapping(value="/dodajLekciju", method=RequestMethod.GET)
	public ResponseEntity<Boolean> dodajLekciju(@RequestParam("idKursa") Integer idKursa, @RequestParam("slika") byte[] slika, @RequestParam("tekst") String tekst, @RequestParam("urlVidea") String urlVidea){
		Lekcija lekcija = new Lekcija();
		lekcija.setKur(kursRepo.getById(idKursa));
		lekcija.setSlika(slika);
		lekcija.setTekst(tekst);
		lekcija.setUrlVidea(urlVidea);
		lekcija = lekcijaRepo.save(lekcija);
		if(lekcija != null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
}