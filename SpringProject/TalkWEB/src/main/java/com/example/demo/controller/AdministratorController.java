package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.KategorijaRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.KursRepository;
import com.example.demo.repository.PredavacRepository;

import model.Kategorija;
import model.Korisnik;
import model.Kur;

@Controller
@RequestMapping(value="/administrator")
public class AdministratorController {
	
	@Autowired
	PredavacRepository predavacRepo;
	
	@Autowired
	KategorijaRepository kategorijaRepo;
	
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@Autowired
	KursRepository kursRepo;
	
	@RequestMapping(value = "/dodajKategoriju", method = RequestMethod.POST)
	@ResponseBody
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
	
	@RequestMapping(value = "/dodajKurs", method = RequestMethod.POST)
	@ResponseBody
	public boolean dodajKurs(@RequestParam String naziv, @RequestParam String opis, @RequestParam String ocekivaniIshod, @RequestParam int idKategorije, @RequestParam int idPredavaca) {
		Kur k = new Kur();
		k.setNaziv(naziv);
		k.setOpis(opis);
		k.setOcekivaniIshod(ocekivaniIshod);
		k.setKategorija(kategorijaRepo.getById(idKategorije));
		k.setPredavac(predavacRepo.getById(idPredavaca));
		k = kursRepo.save(k);
		if(k != null) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/vratiPredavace", method = RequestMethod.GET)
	@ResponseBody
	public List<Korisnik> vratiPredavace() {
		return korisnikRepo.findPredavace();
	}
	
	@RequestMapping(value = "/vratiKategorije", method = RequestMethod.GET)
	@ResponseBody
	public List<Kategorija> vratiKategorije() {
		return kategorijaRepo.findAll();
	}
}
