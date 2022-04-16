package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.PredavacRepository;

import model.Kategorija;

@Controller
@RequestMapping(value="/administrator")
public class AdministratorController {
	
	@Autowired
	PredavacRepository predavacRepo;
	
	@Autowired
	KategorijaRepository kategorijaRepo;
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@RequestMapping(value = "/dodajKategoriju", method = RequestMethod.POST)
	public boolean dodajKategoriju(@RequestParam String naziv) {
		Kategorija kategorija = kategorijaRepo.findKategorija(naziv);
		if(kategorija == null) {
			Kategorija k = new Kategorija();
			k.setNaziv(naziv);
			k = kategorijaRepo.save(k);
			if(k != null) {
				return true;
			}
		}
		return false;
	}
	

}
